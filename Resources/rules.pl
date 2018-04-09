/* Encapsulamiento */

/* Atributo publico */

public_attribute(ID):-
    field_declaration(ID, _, _, _, _, _, _, compilation_unit(UNIT)),
    public_field(ID),
	not(final_field(ID)),
    not(static_field(ID)),
	model_unit(UNIT).

/* Coleccion retornada */

returned_collection(ID):-
    return_statement(ID, _, expression(EXP), body_declaration(METHOD), _, compilation_unit(UNIT)),
    public_method(METHOD),
    field_access(EXP,FIELD),
    (
        field_declaration(FIELD, _, _, type(TYPE), _, _, _, _);
        not(returned_collection_checked(ID)),
        method_declaration(METHOD, _, _, _, _, _, return_type(TYPE), _, _, _, _)
    ),
    collection_type(TYPE),
    not(public_field(FIELD)),
    not(final_field(FIELD)),
    model_unit(UNIT),
    assert(returned_collection_checked(ID)).

/* Coleccion asignada */

assigned_collection(ID):-
    assignment(ID, _, operator('ASSIGN'), left_operand(LO), right_operand(RO), body_declaration(METHOD), _, compilation_unit(UNIT)),
    method_declaration(METHOD, _, _, _, parameters(PARAMETERS), _, _, _, _, _, _),
    public_method(METHOD),
    field_access(LO,FIELD),
    var_access(RO,VAR),
    member(VAR,PARAMETERS),
    not(public_field(FIELD)),
    (
        field_declaration(FIELD, _, _, type(TYPE), _, _, _, _);
        not(assigned_collection_checked(ID)),
        variable_declaration(VAR , _, _, type(TYPE), _, _, initializer(null), _, _, _)
    ),
    collection_type(TYPE),
    model_unit(UNIT),
    assert(assigned_collection_checked(ID)).

/* Acceso directo a atributo */

direct_access_to_attribute(ID):-
    field_access(ID, _, expression(NOT_THIS), field(FIELD), _, _, compilation_unit(UNIT)),
    not(this_expression(NOT_THIS, _, _, _, _, _)),
    field_declaration(FIELD, _, _, _, _, _, _, _),
    not(public_field(FIELD)),
    model_unit(ID).

direct_access_to_attribute(ID):-
    qualified_name(ID, _, _, name(FIELD), _, _, compilation_unit(UNIT)),
    field_declaration(FIELD, _, _, _, _, _, _, _),
    not(public_field(FIELD)),
    model_unit(ID).


/* Diseño */

/* Clase abstracta sin metodos abstractos */

abstract_class_without_abstract_method(ID):-
    class_declaration(ID, _, _, _, _, super_type(ST), implements(IMPLEMENTS), _, methods(METHODS), _, compilation_unit(UNIT)),
    abstract_class(ID),
    not(length(METHODS,0)),
    length(IMPLEMENTS,0),
    not(
        (
            ST \= null,
            (
                abstract_class(ST);
                not(class_declaration(ST, _, _, _, _, _, _, _, _, _, _))
            )
        )
    ),
    not(
        (
            method_declaration(METHOD, parent(ID), _, _, _, _, _, _, _, _, _),
            abstract_method(METHOD)
        )
    ),
    model_unit(UNIT).

/* Clase o interfaz que no define ningun metodo -- */

absence_of_methods(ID):-
    ( 
        class_declaration(ID, _, _, _, _, super_type(ST), implements(IMPLEMENTS), _, methods(METHODS), _, compilation_unit(UNIT));
        interface_declaration(ID, _, _, _, _, _, _, _, methods(METHODS), _, compilation_unit(UNIT))
    ),
    (
        length(METHODS,0);
        (
            not(length(METHODS,0)),
            filter_methods(METHODS,AUX),
            length(AUX,0),
            not(
                (
                    abstract_class(ID),
                    ST \= null
                )
            )
        )
    ),
    model_unit(UNIT).

/* Interfaz no implementada */

unimplemented_interface(ID):-
    interface_declaration(ID, _, _, _, _, _, _, _, _, _, _),
    not(
        (
            (
                interface_declaration(C1, _, _, _, _, _, implements(IMPLEMENTS), _, _, _, _);
                class_declaration(C1, _, _, _, _, _, implements(IMPLEMENTS), _, _, _, _)
            ),
            member(ID,IMPLEMENTS)
        )
    ).

/* Clase abstracta no extendida */

non_extended_abstract_class(ID):-
    abstract_class(ID),
    not(class_declaration(C1, _, _, _, _, super_type(ID), _, _, _, _, _)).

/* Interfaz innecesaria */

unnecessary_interface(ID):-
    interface_declaration(ID, _, _, _, _, _, _, _, _, _, _),
    (
        interface_declaration(C1, _, _, _, _, _, implements(IMPLEMENTS), _, _, _, _);
        class_declaration(C1, _, _, _, _, _, implements(IMPLEMENTS), _, _, _, _)
    ),
    member(ID,IMPLEMENTS),
    not(
        (
            (
                interface_declaration(C2, _, _, _, _, _, implements(IMPLEMENTS_2), _, _, _, _);
                class_declaration(C2, _, _, _, _, _, implements(IMPLEMENTS_2), _, _, _, _)
            ),
            C2 \= C1,
            member(ID,IMPLEMENTS_2)
        )
    ).

/* Clase abstracta innecesaria */

unnecessary_abstract_class(ID):-
    abstract_class(ID),
    class_declaration(C1, _, _, _, _, super_type(ID), _, _, _, _, _),
    not(
        (
            class_declaration(C2, _, _, _, _, super_type(ID), _, _, _, _, _),
            C1 \= C2
        )
    ).

/* Atributos duplicados */

non_abstracted_attribute(ID):-
    class_declaration(CLASS, _, _, _, _, super_type(SUPER_CLASS), _, _, _, _, compilation_unit(UNIT)),
    SUPER_CLASS \= null,
    model_unit(UNIT),
    field_declaration(FIELD, parent(CLASS), _, _, _, _, _, _),
    not(non_abstracted_attribute_checked(FIELD)),
    assert(non_abstracted_attribute_checked(FIELD)),
    not(redefined_attribute(FIELD)),
    findall(SIMILAR,non_abstracted_attribute_aux(FIELD,SIMILAR),LIST),
    not(length(LIST,0)),
    append([FIELD],LIST,ID).

non_abstracted_attribute_aux(FIELD,SIMILAR):-
    field_declaration(FIELD, parent(CLASS), NAME, type(TYPE), _, extra_dimensions(D), _, _),
    field_declaration(SIMILAR, parent(CLASS_2), NAME, type(TYPE), _, extra_dimensions(D), _, _),
    not(non_abstracted_attribute_checked(SIMILAR)),
    common_super(CLASS,CLASS_2,CLASS_3),
    CLASS_3 \= CLASS,
    CLASS_3 \= CLASS_2,
    CLASS \= CLASS_2,
    (
        class_declaration(CLASS_3, _, _, _, _, _, _, _, _, _, _);
        interface_declaration(CLASS_3, _, _, _, _, _, _, _, _, _, _)
    ),
    assert(non_abstracted_attribute_checked(SIMILAR)).

/* Duplicated methods */

non_abstracted_method(ID):-
    class_declaration(CLASS, _, _, _, _, super_type(SUPER_CLASS), implements(IMPLEMENTS), _, _, _, compilation_unit(UNIT)),
    (
        SUPER_CLASS \= null;
        not(length(IMPLEMENTS,0))
    ),
    model_unit(UNIT),
    method_declaration(METHOD, parent(CLASS), _, _, _, _, _, _, _, _, _),
    not(object_method(METHOD)),
    not(non_abstracted_method_checked(METHOD)),
    assert(non_abstracted_method_checked(METHOD)),
    not(redefine_method(METHOD,_)),
    findall(SIMILAR,non_abstracted_method_aux(METHOD,SIMILAR),LIST),
    not(length(LIST,0)),
    append([METHOD],LIST,ID).

no_abstracted_method_aux(METHOD,SIMILAR):-
    method_declaration(METHOD, parent(CLASS), NAME, _, _, parameters_types(TYPES), return_type(RETURN_TYPE), dimensions(D), _, _, _),
    method_declaration(SIMILAR, parent(CLASS_2), NAME, _, _, parameters_types(TYPES), return_type(RETURN_TYPE), dimensions(D), _, _, _),
    METHOD \= SIMILAR,
    not(non_abstracted_method_checked(SIMILAR)),
    public_method(SIMILAR),
    common_super(CLASS,CLASS_2,CLASS_3),
    CLASS_3 \= CLASS,
    CLASS_3 \= CLASS_2,
    CLASS \= CLASS_2,
    (
        class_declaration(CLASS_3, _, _, _, _, _, _, _, _, _, _);
        interface_declaration(CLASS_3, _, _, _, _, _, _, _, _, _, _)
    ),
    assert(non_abstracted_method_checked(SIMILAR)).

/* Atributo sobrescrito en clase hija */

redefined_attribute(ID):-
    field_declaration(ID, parent(CLASS), NAME, type(TYPE), _, _, _, compilation_unit(UNIT)),
    field_declaration(ID_2, parent(SUPER_CLASS), NAME, type(TYPE), _, _, _, _),
    ID \= ID_2,
    is_super(SUPER_CLASS,CLASS),
    not(redefined_attribute(ID_2)),
    model_unit(UNIT).

/* Herencia */

/* Uso de super para invocar un método sin estar redefiniendolo */

super_in_method_invocation(ID):-
    super_method_invocation(ID, _, method(SUPER), arguments(ARGUMENTS), body_declaration(METHOD), _, compilation_unit(UNIT)),
    (
        method_declaration(SUPER, _, NAME, _, _, _, _, _, _, _, _);
        method(SUPER, name(NAME), _, _)
    ),
    not(method_declaration(METHOD, _, NAME, _, parameters(ARGUMENTS), _, _, _, _, _, _)),
    model_unit(UNIT).

/* Uso de super para acceder a un atributo */

super_in_field_access(ID):-
    super_field_access(ID, _, _, _, _, compilation_unit(UNIT)),
    model_unit(UNIT).

/* Redefine llamando al Super */

redefine_with_super(ID):-
    super_method_invocation(INVOCATION, parent(PARENT), method(SUPER_METHOD), _, body_declaration(ID), _, compilation_unit(UNIT)),
    method_declaration(ID, _, _, _, _, _, _, _, body(BODY), _, _),
    (
        block(BODY, _, statements([INVOCATION]), _, _, _);
        (
            block(BODY, _, statements([RETURN]), _, _, _),
            return_statement(RETURN, _, expression(INVOCATION), _, _, _)
        )
    ),
    redefine_method(ID,SUPER_METHOD),
    model_unit(UNIT).

/* Clase sin comportamiento, solo con getters y setters */

lazy_class(ID):-
    class_declaration(ID, _, _, _, _, _, _, _, methods(METHODS), _, compilation_unit(UNIT)),
    not (abstract_class(ID)),
    filter_methods(METHODS,JUST_METHODS),
    filter_public_methods(JUST_METHODS,PUBLIC_METHODS),
    not(length(PUBLIC_METHODS,0)),
    filter_behaviours(PUBLIC_METHODS,BEHAVIOURS),
    length(BEHAVIOURS,0),
    model_unit(UNIT).

/* Polimorfismo */

/* Uso del instanceOf fuera del equals */

use_of_instanceof(ID):-
    (
        instanceof_expression(ID, _, _, _, body_declaration(METHOD), _, compilation_unit(UNIT));
        (
            method(IS_INSTANCE, name('isInstance'), _, _),
            method_invocation(ID, _, _, method(IS_INSTANCE), arguments([]), body_declaration(METHOD), _, compilation_unit(UNIT))
        )
    ),
    not(is_equals(METHOD)),
    model_unit(UNIT).

/* Uso de una variable booleana como tipo */

boolean_as_type(ID):-
    field_declaration(FIELD, parent(CLASS), _, type(BOOLEAN), _, _, _, compilation_unit(UNIT)),
    (
        primitive_type(BOOLEAN, code('boolean'));
        type(BOOLEAN , name('Boolean'))
    ),
    model_unit(UNIT),
    findall(IF,boolean_as_type_aux(FIELD,IF),ID),
    not(length(ID,0)).

boolean_as_type_aux(FIELD,IF):-
    field_declaration(FIELD, parent(CLASS), _, type(BOOLEAN), _, _, _, _),
    if_statement(IF, _, condition(CONDITION), then(THEN), else(ELSE), _, type_declaration(CLASS), _),
    THEN \= null,
    ELSE \= null,
    (
        field_access(CONDITION,FIELD);
        (
            prefix_expression(CONDITION, _, _, operand(OPERAND), _, _, _),
            field_access(OPERAND,FIELD)
        );
        (
            (
                infix_expression(CONDITION, _, _, left_operand(OPERAND), right_operand(LITERAL), extended_operands([]), _, _, _);
                infix_expression(CONDITION, _, _, left_operand(LITERAL), right_operand(OPERAND), extended_operands([]), _, _, _)
            ),
            field_access(OPERAND,FIELD),
            boolean_literal(LITERAL, _, _, _, _, _)
        )
    ).

/* Atributo usado en switch */

switch_of_attribute(ID):-
    field_declaration(FIELD, _, _, _, _, _, _, compilation_unit(UNIT)),
    model_unit(UNIT),
    findall(SWITCH,switch_of_attribute_aux(FIELD,SWITCH),ID),
    not(length(ID,0)).

switch_of_attribute_aux(FIELD,SWITCH):-
    field_declaration(FIELD, parent(CLASS), _, _, _, _, _, _),
    switch_statement(SWITCH, _, switch(EXP), _, _, type_declaration(CLASS), _),
    field_access(EXP,FIELD).

/* Parametro usado en switch */

switch_of_param(ID):-
    switch_statement(ID, _, switch(SWITCH), _, body_declaration(METHOD), type_declaration(CLASS), compilation_unit(UNIT)),
    method_declaration(METHOD, _, _, _, parameters(PARAMS), _, _, _, _, _, _),
    variable_declaration(SWITCH, _, _, type(TYPE), _, _, _, _, _, _),
    member(SWITCH,PARAMS),
    model_unit(UNIT).

/* Atributo llamado tipo o type */

type_attribute(ID):-
    field_declaration(ID, _, FIELD_NAME, _, _, _, _, compilation_unit(UNIT)),
    atom_codes(FIELD_NAME,FIELD_NAME_CODES),
    contains_word_type(FIELD_NAME_CODES),
    model_unit(UNIT).

/* Clase con numeros en su nombre */

class_name_contains_number(ID):-
    class_declaration(ID, _, CLASS_NAME, _, _, _, _, _, _, _, compilation_unit(UNIT)),
    atom_codes(CLASS_NAME,CLASS_NAME_CODES),
    contains_number(CLASS_NAME_CODES),
    model_unit(UNIT).
    
/* Composicion */

/* Referencia a clases hermanas en lugar de al padre */

reference_to_sister_class_instead_of_father(ID):-
    field_declaration(ID, parent(CLASS), NAME, type(TYPE), _, _, _, compilation_unit(UNIT)),
    plain_types(TYPE,LIST),
    member(CLASS_2,LIST),
    class_declaration(CLASS_2, _, _, _, _, _, _, _, _, _, _),  
    CLASS \= CLASS_2,
    common_super(CLASS,CLASS_2,SUPER),
    SUPER \= CLASS_2,
    SUPER \= CLASS,
    model_unit(UNIT).

/* Falta de delegacion */

lack_of_delegation(ID):-
    field_declaration(FIELD, parent(CLASS), _, type(TYPE), _, _, _, compilation_unit(UNIT)),
    plain_types(TYPE,LIST),
    member(CLASS_2,LIST),
    common_super(CLASS,CLASS_2,SUPER),
    method_declaration(ID, parent(CLASS), NAME, _, _, parameters_types(PARAMETER_TYPES), return_type(RETURN), dimensions(D), _, _, _),
    define_behaviour(ID),
    (
        (
            method_declaration(METHOD_2, _, NAME, _, _, parameters_types(PARAMETER_TYPES), return_type(RETURN), dimensions(D), _, _, _),
            has_method(CLASS_2,METHOD_2),
            not(delegation(ID,METHOD_2))
        );
        (
            object_method(ID),
            not(delegation(ID,FIELD,CLASS_2))
        )
    ),
    not(delegation_checked(ID)),
    model_unit(UNIT),
    assert(delegation_checked(ID)).

/* Modelo vs Main */

/* Impresion por pantalla */

system_out_print(ID):-
    is_print(PRINT),
    method_invocation(PRINT, _, _, _, _, body_declaration(ID), _, compilation_unit(UNIT)),
    not(system_out_print_checked(ID)),
    assert(system_out_print_checked(ID)),
    model_unit(UNIT).

/* Clase del modelo en la unidad del Main */

main_class_as_model_class(ID):-
    main_class(ID),
    (
        class_declaration(ID_2, _, _, _, _, _, _, _, _, _, _);
        interface_declaration(ID_2, _, _, _, _, _, _, _, _, _, _)
    ),
    ID \= ID_2,
    common_super(ID,ID_2,_),
    not(main_class_as_model_class_checked(ID)),
    assert(main_class_as_model_class_checked(ID)).

/* Otros */

/* Uso de == en lugar de equals  */

not_use_of_equals(ID):-
    infix_expression(ID, _, operator('EQUALS'), left_operand(LO), right_operand(RO), _, _, _, compilation_unit(UNIT)),
    resolve_expression(LO,LO_RESOLVED),
    resolve_expression(RO,RO_RESOLVED),
    not(null_literal(LO_RESOLVED,_,_,_,_)),
    not(null_literal(RO_RESOLVED,_,_,_,_)),
    (
        need_equals(LO_RESOLVED);
        (
            not(need_equals(LO_RESOLVED)),
            need_equals(RO_RESOLVED)
        )
    ),
    model_unit(UNIT).

/* Metodo abstracto en interfaz */

abstract_method_in_interface(ID):-
    interface_declaration(INTERFACE, _, _, _, _, _, _, _, _, _, compilation_unit(UNIT)),
    method_declaration(ID, parent(INTERFACE), _, _, _, _, _, _, _, _, _),
    abstract_method(ID),
    model_unit(UNIT).

/* Constructor publico en clase abstracta */

public_constructor_in_abstract_class(ID):-
    abstract_class(CLASS),
    constructor_declaration(ID, parent(CLASS), _, _, _, _, _, _, _, _, compilation_unit(UNIT)),
    public_method(ID),
    model_unit(UNIT).

/* Cohesion */

/* Muchas interfaces implementadas */

many_implements(ID):-
    class_declaration(ID, _, _, _, _, _, implements(IMPLEMENTS), _, _, _, compilation_unit(UNIT)),
    length(IMPLEMENTS,LENGTH),
    LENGTH > 2,
    model_unit(UNIT).

/* Baja cohesion */

low_cohesion(ID):-
    ( 
        class_declaration(ID, _, _, _, _, _, _, _, methods(METHODS), _, compilation_unit(UNIT));
        interface_declaration(ID, _, _, _, _, _, _, _, methods(METHODS), _, compilation_unit(UNIT))
    ),
    filter_methods(METHODS,JUST_METHODS),
    filter_public_methods(JUST_METHODS,PUBLIC_METHODS),
    filter_behaviours(PUBLIC_METHODS,BEHAVIOURS),
    length(BEHAVIOURS,LENGTH),
    LENGTH > 9, /* Maximum number of public behaviours */
    model_unit(UNIT).

/* Clase Dios */

god_class(ID):-
    findall(CID,class_declaration(CID, _, _, _, _, _, _, _, _, _, _),LIST),
    findall(SHORT,short_class(SORT),LIST_OF_SHORTS),
    length(LIST,T),
    length(LIST_OF_SHORTS,N),
    N is T - 1,
    long_class(ID),
    class_declaration(ID, _, _, _, _, _, _, _, _, _, compilation_unit(UNIT)),
    model_unit(UNIT).

/* Constantes */

/* Inovcacion a método con constantes como parametros */

constant_in_invocation(LITERAL):-
    (
        boolean_literal(LITERAL,parent(INVOCATION),value(VAL),_,_,compilation_unit(UNIT));
        character_literal(LITERAL,parent(INVOCATION),value(VAL),_,_,compilation_unit(UNIT));
        number_literal(LITERAL,parent(INVOCATION),value(VAL),_,_,compilation_unit(UNIT));
        string_literal(LITERAL,parent(INVOCATION),value(VAL),_,_,compilation_unit(UNIT))
    ),
    not(valid_literal(VAL)),
    (
        (
            method_invocation(INVOCATION, _, _, _, _, _, _, _),
            not(is_print(INVOCATION))
        );
        constructor_invocation(INVOCATION, _, _, _, _, _, _);
        class_instance_creation(INVOCATION, _, _, _, _, _, _, _);
        super_method_invocation(INVOCATION, _, _, _, _, _, _);
        super_constructor_invocation(INVOCATION, _, _, _, _, _, _, _)
    ),
    not(dinamic_attribute(LITERAL)),
    model_unit(UNIT).

/* Constante en asignación */

constant_in_assignment(LITERAL):-
    (
        character_literal(LITERAL,parent(PARENT),value(VAL),_,_,compilation_unit(UNIT));
        number_literal(LITERAL,parent(PARENT),value(VAL),_,_,compilation_unit(UNIT));
        string_literal(LITERAL,parent(PARENT),value(VAL),_,_,compilation_unit(UNIT))
    ),
    not(valid_literal(VAL)),
    (
        variable_declaration(PARENT, _, _, _, _, _, initializer(LITERAL), _, _, _);
        assignment(PARENT, _, _, _, right_operand(LITERAL), _, _, _)
    ),
    model_unit(UNIT).

/* Constantes en comparaciones */

constant_in_comparision(LITERAL):-
    (
        boolean_literal(LITERAL,parent(PARENT),value(VAL),_,_,compilation_unit(UNIT));
        character_literal(LITERAL,parent(PARENT),value(VAL),_,_,compilation_unit(UNIT));
        number_literal(LITERAL,parent(PARENT),value(VAL),_,_,compilation_unit(UNIT));
        string_literal(LITERAL,parent(PARENT),value(VAL),_,_,compilation_unit(UNIT))
    ),
    not(valid_literal(VAL)),
    infix_expression(PARENT, _, operator(O), _, _, _, _, _, _),
    comparision_operator(O),
    model_unit(UNIT).

/* Operacion aritmetica con constantes */

constant_in_operation(LITERAL):-
    (
        boolean_literal(LITERAL,parent(PARENT),value(VAL),_,_,compilation_unit(UNIT));
        character_literal(LITERAL,parent(PARENT),value(VAL),_,_,compilation_unit(UNIT));
        number_literal(LITERAL,parent(PARENT),value(VAL),_,_,compilation_unit(UNIT))
     ),
    not(valid_literal(VAL)),
    infix_expression(PARENT, _, operator(O), _, _, _, _, _, _),
    (
        arithmetic_operator(O);
        bit_operator(O)
    ),
    model_unit(UNIT).

/* Constante en return */

constant_in_return(LITERAL):-
    return_statement(RETURN, _, expression(LITERAL), _, _, compilation_unit(UNIT)),
    (
        character_literal(LITERAL,parent(PARENT),value(VAL),_,_,compilation_unit(UNIT));
        number_literal(LITERAL,parent(PARENT),value(VAL),_,_,compilation_unit(UNIT));
        string_literal(LITERAL,parent(PARENT),value(VAL),_,_,compilation_unit(UNIT))
    ),
    not(valid_literal(VAL)),
    model_unit(UNIT).

/* Operaciones con strings */

constant_string_in_operation(METHOD):-
    string_literal(LITERAL,parent(PARENT),value(VAL),body_declaration(METHOD),_,_),
    not(valid_literal(VAL)),
    infix_expression(PARENT, _, operator(O), _, _, _, _, _, compilation_unit(UNIT)),
    not(is_tostring(METHOD)),
    arithmetic_operator(O),
    model_unit(UNIT),
    not(constant_string_in_operation_checked(METHOD)),
    assert(constant_string_in_operation_checked(METHOD)).


/* Atributos dinamicos */

/* Atributo que debería ser dinamico */

not_dinamic_attribute(ID):-
    field_declaration(FIELD, parent(CLASS), _, type(HASHMAP), _, extra_dimensions(D), _, compilation_unit(UNIT)),
    map_type(HASHMAP),
    parameterized_type(HASHMAP, _, parameters([STRING,OTHER])),
    type(STRING, name('String')),
    field_declaration(ID, parent(CLASS), _, type(TYPE), _, _, _, _),
    ID \= FIELD,
    (
        (
            OTHER = STRING,
            TYPE = STRING
        );
        type(OTHER, name('Object'))
    ),
    not(
        (
            var_reference(ID,METHOD,CLASS,_),
            method_declaration(METHOD, _, _, _, _, _, _, _, _, _, _),
            define_behaviour(METHOD)
        )
    ),
    model_unit(UNIT).

/* Atributo que no deberia ser dinamico */

dinamic_attribute(ID):-
    method_invocation(INVOCATION, _, expression(EXP), method(METHOD), arguments([ID]), _, type_declaration(CLASS), _),
    string_literal(ID, _, _, _, _, _),
    (
        (
            field_access(EXP,FIELD),
            method(METHOD, name('get'), _, _),
            field_declaration(FIELD, parent(CLASS), _, type(HASHMAP), _, _, _, compilation_unit(UNIT)),
            map_type(HASHMAP),
            parameterized_type(HASHMAP, _, parameters([STRING,OTHER])),
            type(STRING, name('String')),
            (
                OTHER = STRING;
                type(OTHER, name('Object'))
            )
        );
        is_hm_item_getter(METHOD)
    ),
    model_unit(UNIT).
 
/* Codigo duplicado */

/* Comportamiento duplicado en clases hermanas */

similar_behaviour_in_classes_of_the_same_hierarchy(ID):-
    method_declaration(METHOD, parent(CLASS), _, _, _, _, _, _, body(BODY), _, _),
    BODY \= null,
    not(block(BODY, _, statements([]), _, _, _)),
    not(similar_behaviour_in_sister_classes_checked(METHOD)),
    assert(similar_behaviour_in_sister_classes_checked(METHOD)),
    /*define_behaviour(METHOD),*/
    hierarchy(CLASS,HIERARCHY),
    findall(DUPLICATED,similar_behaviour_in_classes_of_the_same_hierarchy_aux(METHOD,DUPLICATED,HIERARCHY),LIST),
    not(length(LIST,0)),
    append([METHOD],LIST,ID).

similar_behaviour_in_classes_of_the_same_hierarchy_aux(METHOD,DUPLICATED,HIERARCHY):-
    method_declaration(METHOD, parent(TYPE1), NAME, _, _, parameters_types(PARAMETERS_1), return_type(RETURN_1), dimensions(D), body(B1), _, _),
    member(TYPE2,HIERARCHY),
    TYPE1 \= TYPE2,
    method_declaration(DUPLICATED, parent(TYPE2), NAME, _, _, parameters_types(PARAMETERS_2), return_type(RETURN_2), dimensions(D), body(B2), _, _),
    B2 \= null,
    not(block(B2, _, statements([]), _, _, _)),
    not(similar_behaviour_in_sister_classes_checked(DUPLICATED)),
    similar_type(RETURN_1,RETURN_2),
    similar_type(PARAMETERS_1,PARAMETERS_2),
    similar_code([B1],[B2]),
    assert(similar_behaviour_in_sister_classes_checked(DUPLICATED)).

/* Comportamiento duplicado en la misma clase */

similar_behaviour_in_methods_of_the_same_class(ID):-
    class_declaration(CLASS, _, _, _, _, _, _, _, _, _, compilation_unit(UNIT)),
    model_unit(UNIT),
    method_declaration(METHOD, parent(CLASS), _, _, _, _, _, _, body(BODY), _, _),
    BODY \= null,
    not(block(BODY, _, statements([]), _, _, _)),
    not(similar_behaviour_in_same_class_checked(METHOD)),
    assert(similar_behaviour_in_same_class_checked(METHOD)),
    define_behaviour(METHOD),
    findall(SIMILAR,similar_behaviour_in_methods_of_the_same_class_aux(METHOD,SIMILAR),LIST),
    not(length(LIST,0)),
    append([METHOD],LIST,ID).

similar_behaviour_in_methods_of_the_same_class_aux(METHOD,SIMILAR):-
    method_declaration(METHOD, parent(CLASS), _, _, _, parameters_types(PARAMETERS_1), return_type(RETURN_1), dimensions(D), body(B1), _, _),
    method_declaration(SIMILAR, parent(CLASS), _, _, _, parameters_types(PARAMETERS_2), return_type(RETURN_2), dimensions(D), body(B2), _, _),
    B2 \= null,
    not(block(B2, _, statements([]), _, _, _)),
    METHOD \= SIMILAR,
    not(similar_behaviour_in_same_class_checked(SIMILAR)),
    similar_type(PARAMETERS_1,PARAMETERS_2),
    similar_type(RETURN_1,RETURN_2),
    similar_code([B1],[B2]),
    assert(similar_behaviour_in_same_class_checked(SIMILAR)).

/* Metodo que siempre retorna null */

method_always_returns_null(ID):-
    method_declaration(ID, _, _, _, _, _, return_type(TYPE), _, body(B), _, compilation_unit(UNIT)),
    B \= null,
    not(primitive_type(TYPE, code('void'))),
    not(
        (
            return_statement(_, _, expression(LITERAL), body_declaration(ID), _, _),
            not(null_literal(LITERAL, _, _, _, _))
        )
    ),
    model_unit(UNIT).

/* Clase abstracta que podría ser interfaz */

abstract_class_may_be_interface(ID):-
    class_declaration(ID, _, _, _, _, super_type(null), _, _, _, _, compilation_unit(UNIT)),
    abstract_class(ID),
    not(
        (
            field_declaration(FIELD, parent(ID), _, _, _, _, _, _),
            not(static_field(FIELD))
        )
    ),
    not(
        (
            method_declaration(METHOD, parent(ID), _, _, _, _, _, _, _, _, _),
            not(abstract_method(METHOD))
        )
    ),
    model_unit(UNIT).

/* Metodo vacio */

empty_method(ID):-
    method_declaration(ID, _, _, _, _, _, _, _, body(B), _, compilation_unit(UNIT)),
    B \= null,
    block(B, _, statements([]), _, _, _),
    model_unit(UNIT).

/* Metodo redefinido como abstracto */

method_redefined_as_abstract(ID):-
    method_declaration(ID, _, _, _, _, _, _, _, _, _, compilation_unit(UNIT)),
    abstract_method(ID),
    (
        object_method(ID);
        redefine_method(ID,_)
    ),
    model_unit(UNIT),
    not(method_redefined_as_abstract_checked(ID)),
    assert(method_redefined_as_abstract_checked(ID)).


/* Atributos similares */

similar_attribute(ID):-
    class_declaration(CLASS, _, _, _, _, super_type(SUPER_CLASS), _, _, _, _, compilation_unit(UNIT)),
    SUPER_CLASS \= null,
    model_unit(UNIT),
    field_declaration(FIELD, parent(CLASS), _, _, _, _, _, _),
    not(similar_attribute_checked(FIELD)),
    assert(similar_attribute_checked(FIELD)),
    not(redefined_attribute(FIELD)),
    findall(SIMILAR,similar_attribute_aux(FIELD,SIMILAR),LIST),
    not(length(LIST,0)),
    append([FIELD],LIST,ID).

similar_attribute_aux(FIELD,SIMILAR):-
    field_declaration(FIELD, parent(CLASS), NAME, type(TYPE), _, extra_dimensions(D), _, _),
    field_declaration(SIMILAR, parent(CLASS_2), NAME_2, type(TYPE), _, extra_dimensions(D), _, _),
    not(similar_attribute_checked(SIMILAR)),
    not(similar_attribute_checked(FIELD,CLASS_2)),
    NAME_2 \= NAME,
    common_super(CLASS,CLASS_2,CLASS_3),
    CLASS_3 \= CLASS,
    CLASS_3 \= CLASS_2,
    CLASS \= CLASS_2,
    not(field_declaration(_, parent(CLASS), NAME_2, type(TYPE), _, extra_dimensions(D), _, _)),
    not(field_declaration(_, parent(CLASS_2), NAME, type(TYPE), _, extra_dimensions(D), _, _)),
    (
        class_declaration(CLASS_3, _, _, _, _, _, _, _, _, _, _);
        interface_declaration(CLASS_3, _, _, _, _, _, _, _, _, _, _)
    ),
    assert(similar_attribute_checked(SIMILAR)),
    assert(similar_attribute_checked(FIELD,CLASS_2)).

/* Metodo solitario */

lonely_method(ID):-
    method_declaration(ID, parent(CLASS), _, _, parameters(PARAMETERS), _, return_type(RETURN_TYPE), _, body(BODY), _, compilation_unit(UNIT)),
    field_declaration(_, parent(CLASS), _, _, _, _, _, _),
    not(lonely_method_checked(ID)),
    assert(lonely_method_checked(ID)),
    BODY \= null,
    block(BODY, _, statements(STM), _, _, _),
    not(length(STM,0)),
    not(length(PARAMETERS,0)),
    not(static_method(ID)),
    not(
        (
            field_declaration(FIELD, parent(P), _, _, _, _, _, _),
            has_field(CLASS,FIELD),
            var_reference(FIELD,ID,CLASS,NOT_QUALIFIED),
            not(qualified_name(NOT_QUALIFIED, _, _, name(FIELD), body_declaration(METHOD), _, _))
        )
    ),
    not(
        (
            (
                (
                    method_invocation(_, _, expression(EXP), method(METHOD), _, body_declaration(ID), _, _),
                    METHOD \= ID,
                    (
                        EXP = null;
                        this_expression(EXP, _, _, _, _, _)
                    )
                );
                super_method_invocation(_, _, method(METHOD), _, body_declaration(ID), _, _)
            ),
            not(static_method(METHOD))
        )
    ),
    not(
        (
            this_expression(EXP, parent(PARENT), _, body_declaration(ID), _, _),
            not(
                (
                    method_invocation(PARENT, _, expression(EXP), _, _, _, _, _);
                    field_access(PARENT, _, _, _, _, _, _)
                )
            )
        )
    ),
    model_unit(UNIT).

/* Usando colecciones de Java sin el equals */

java_collections_without_equals(ID):-
    method_invocation(ID, _, expression(EXP), method(METHOD), arguments([ARG]), _, _, compilation_unit(UNIT)),
    (
        method(METHOD, name('contains'), _, _);
        method(METHOD, name('remove'), _, _)
    ),
    (
        field_declaration(ARG, _, _, type(TYPE), _, _, _, _);
        variable_declaration(ARG , _, _, type(TYPE), _, _, _, _, _, _)
    ),
    variable_declaration(ARG , _, _, type(TYPE), _, _, _, _, _, _),
    resolve_expression(EXP,VAR),
    (
        field_declaration(VAR, _, _, type(COLLECTION), _, _, _, _);
        variable_declaration(VAR , _, _, type(COLLECTION), _, _, _, _, _, _)
    ),
    java_collection_type(COLLECTION),
    (
        (
            class_declaration(TYPE, _, _, _, _, _, _, _, _, _, _),
            not(abstract_class(TYPE)),
            not(
                (
                    method_declaration(AUX, _, _, _, _, _, _, _, _, _, _),
                    is_equals(AUX),
                    has_method(TYPE,AUX)
                )
            )
        );
        (
            (
                (
                    class_declaration(TYPE, _, _, _, _, _, _, _, _, _, _),
                    abstract_class(TYPE)
                );
                interface_declaration(TYPE, _, _, _, _, _, _, _, _, _, _)
            ),
            class_declaration(CHILD, _, _, _, _, _, _, _, _, _, _),
            is_super(TYPE,CHILD),
            not(
                (
                    method_declaration(AUX, _, _, _, _, _, _, _, _, _, _),
                    is_equals(AUX),
                    has_method(CHILD,AUX)
                )
            )
        )
    ),
    model_unit(UNIT),
    not(java_collections_without_equals_checked(ID)),
    assert(java_collections_without_equals_checked(ID)).