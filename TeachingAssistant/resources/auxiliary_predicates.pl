/* Sublista de una lista */

sublist(L,SUB):-
    append(AUX,_,L),
    append(_,SUB,AUX).

/* Primero y ultimo de la lista */

first([FIRST|COLA],FIRST).

last([LAST],LAST).

last([NO_LAST|COLA],LAST):-
	last(COLA,LAST).

/* Operadores aritmeticos */

arithmetic_operator('PLUS').
arithmetic_operator('MINUS').
arithmetic_operator('TIMES').
arithmetic_operator('DIVIDE').
arithmetic_operator('REMAINDER').

/* Operadores comparativos */

comparision_operator('EQUALS').
comparision_operator('NOT_EQUALS').
comparision_operator('GREATER').
comparision_operator('LESS_EQUALS').
comparision_operator('LESS').
comparision_operator('GREATER_EQUALS').

/* Operadores sobre bits */

bit_operator('COMPLEMENT').
bit_operator('LEFT_SHIFT').
bit_operator('RIGHT_SHIFT_SIGNED').
bit_operator('RIGHT_SHIFT_UNSIGNED').
bit_operator('BIT_AND_ASSIGN').
bit_operator('XOR').
bit_operator('BIT_OR_ASSIGN').
bit_operator('AND').
bit_operator('OR').
bit_operator('CONDITIONAL_AND').
bit_operator('CONDITIONAL_OR').
bit_operator('NOT').

/* Lista sin repetidos */

append_set([],X,X):- !.

append_set(X,[],X):- !.

append_set(L,[X|COLA],NEWL):-
	member(X,L),
	append_set(L,COLA,NEWL),
	!.

append_set(L,[X|COLA],NEWL):-
	append(L,[X],L2),
	append_set(L2,COLA,NEWL),
	!.

/* Predicados letter y same_letter */

letter(X):-
    (
        X > 96,
        X < 123
    );
    (
        X > 64,
        X < 91
    ),
    !.

same_letter(97,65).
same_letter(65,97).
same_letter(98,66).
same_letter(66,98).
same_letter(99,67).
same_letter(67,99).
same_letter(100,68).
same_letter(68,100).
same_letter(101,69).
same_letter(69,101).
same_letter(102,70).
same_letter(70,102).
same_letter(103,71).
same_letter(71,103).
same_letter(104,72).
same_letter(72,104).
same_letter(105,73).
same_letter(73,105).
same_letter(106,74).
same_letter(74,106).
same_letter(107,75).
same_letter(75,107).
same_letter(108,76).
same_letter(76,108).
same_letter(109,77).
same_letter(77,109).
same_letter(110,78).
same_letter(78,110).
same_letter(111,79).
same_letter(79,111).
same_letter(112,80).
same_letter(80,112).
same_letter(113,81).
same_letter(81,113).
same_letter(114,82).
same_letter(82,114).
same_letter(115,83).
same_letter(83,115).
same_letter(116,84).
same_letter(84,116).
same_letter(117,85).
same_letter(85,117).
same_letter(118,86).
same_letter(86,118).
same_letter(119,87).
same_letter(87,119).
same_letter(120,88).
same_letter(88,120).
same_letter(121,89).
same_letter(89,121).
same_letter(122,90).
same_letter(90,122).
same_letter(97,97).
same_letter(65,65).
same_letter(98,98).
same_letter(66,66).
same_letter(99,99).
same_letter(67,67).
same_letter(100,100).
same_letter(68,68).
same_letter(101,101).
same_letter(69,69).
same_letter(102,102).
same_letter(70,70).
same_letter(103,103).
same_letter(71,71).
same_letter(104,104).
same_letter(72,72).
same_letter(105,105).
same_letter(73,73).
same_letter(106,106).
same_letter(74,74).
same_letter(107,107).
same_letter(75,75).
same_letter(108,108).
same_letter(76,76).
same_letter(109,109).
same_letter(77,77).
same_letter(110,110).
same_letter(78,78).
same_letter(111,111).
same_letter(79,79).
same_letter(112,112).
same_letter(80,80).
same_letter(113,113).
same_letter(81,81).
same_letter(114,114).
same_letter(82,82).
same_letter(115,115).
same_letter(83,83).
same_letter(116,116).
same_letter(84,84).
same_letter(117,117).
same_letter(85,85).
same_letter(118,118).
same_letter(86,86).
same_letter(119,119).
same_letter(87,87).
same_letter(120,120).
same_letter(88,88).
same_letter(121,121).
same_letter(89,89).
same_letter(122,122).
same_letter(90,90).

/* Method name codes */

starts_with_set([115,101,116|_]).

starts_with_add([97,100,100|_]).

starts_with_put([112,117,116|_]).

starts_with_get([103,101,116|_]).

get_name([103,101,116|REST],NAME):-
	get_name2(REST,NAME),
	!.

get_name([105,115|REST],NAME):-
    get_name2(REST,NAME),
    !.

get_name2([],[]):- !.

get_name2([],_):- !.

get_name2(_,[]):- !.

get_name2([X|REST1],[X|REST2]):-
    letter(X),
    !,
    get_name2(REST1,REST2).

get_name2([X|REST1],[Y|REST2]):-
    same_letter(X,Y),
    !,
    get_name2(REST1,REST2).

get_name2([X|REST1],REST2):-
    not(letter(X)),
    !,
    get_name2(REST1,REST2).

get_name2(REST1,[X|REST2]):-
    not(letter(X)),
    !,
    get_name2(REST1,REST2).

/* Contiene type o tipo */

is_named_type([X,Y,Z,T|REST]):-
	same_letter(X,116),
	same_letter(Y,121),
	same_letter(Z,112),
	same_letter(T,101),
	!.

is_named_type([X,Y,Z,T|REST]):-
	same_letter(X,116),
	same_letter(Y,105),
	same_letter(Z,112),
	same_letter(T,111),
	!.

is_named_type([X|REST]):-
	is_named_type(REST),
	!.

/* Tipos de colecciones */

collection_type(ID):-
	(
		array_type(ID,_, _);
		java_collection_type(ID)
    ),
    !.

/* Colecciones de java */

java_collection_type(ID):-
	(
		list_type(ID);
		map_type(ID);
		set_type(ID)
    ),
    !.

/* Tipos de lista */

list_type(TYPE):-
	(
		parameterized_type(TYPE, fqn('java.util.Vector'), _);
	    parameterized_type(TYPE, fqn('java.util.ArrayList'), _);
	    parameterized_type(TYPE, fqn('java.util.AbstractList'), _);
	    parameterized_type(TYPE, fqn('java.util.AbstractSequentialList'), _);
	    parameterized_type(TYPE, fqn('java.util.concurrent.CopyOnWriteArrayList'), _);
	    parameterized_type(TYPE, fqn('java.util.LinkedList'), _);
	    parameterized_type(TYPE, fqn('java.util.List'), _);
	    parameterized_type(TYPE, fqn('java.util.Stack'), _)
	),
	!.

list_type(TYPE):-
	(
		type(TYPE, name('Vector'));
		type(TYPE, name('List'));
		type(TYPE, name('ArrayList'));
		type(TYPE, name('AbstractList'));
		type(TYPE, name('AbstractSequentialList'));
		type(TYPE, name('Stack'));
		type(TYPE, name('LinkedList'));
		type(TYPE, name('CopyOnWriteArrayList'))
	),
	!.

/* Tipos de map */

map_type(TYPE):-
	(
		parameterized_type(TYPE, fqn('java.util.WeakHashMap'), _);
	    parameterized_type(TYPE, fqn('java.util.AbstractMap'), _);
	    parameterized_type(TYPE, fqn('java.util.Map'), _);
	    parameterized_type(TYPE, fqn('java.util.concurrent.ConcurrentHashMap'), _);
	    parameterized_type(TYPE, fqn('java.util.concurrent.ConcurrentSkipListMap'), _);
	    parameterized_type(TYPE, fqn('java.util.HashMap'), _);
	    parameterized_type(TYPE, fqn('java.util.Hashtable'), _);
	    parameterized_type(TYPE, fqn('java.util.IdentityHashMap'), _);
		parameterized_type(TYPE, fqn('java.util.LinkedHashMap'), _);
	    parameterized_type(TYPE, fqn('java.util.TreeMap'), _);
	    parameterized_type(TYPE, fqn('java.util.EnumMap'), _)
	),
	!.

map_type(TYPE):-
	(
		type(TYPE, name('WeakHashMap'));
		type(TYPE, name('AbstractMap'));
		type(TYPE, name('Map'));
		type(TYPE, name('ConcurrentHashMap'));
		type(TYPE, name('ConcurrentSkipListMap'));
		type(TYPE, name('HashMap'));
		type(TYPE, name('Hashtable'));
		type(TYPE, name('IdentityHashMap'));
		type(TYPE, name('LinkedHashMap'));
		type(TYPE, name('TreeMap'));
		type(TYPE, name('EnumMap'))
	),
	!.

/* Tipos de Set */

set_type(TYPE):-
    (
    	parameterized_type(TYPE, fqn('java.util.AbstractSet'), _);
	    parameterized_type(TYPE, fqn('java.util.Set'), _);
	    parameterized_type(TYPE, fqn('java.util.concurrent.ConcurrentSkipListSet'), _);
	    parameterized_type(TYPE, fqn('java.util.concurrent.CopyOnWriteArraySet'), _);
	    parameterized_type(TYPE, fqn('java.util.HashSet'), _);
	    parameterized_type(TYPE, fqn('javax.print.attribute.standard.JobStateReasons'), _);
	    parameterized_type(TYPE, fqn('java.util.LinkedHashSet'), _);
	    parameterized_type(TYPE, fqn('java.util.TreeSet'), _);
	    parameterized_type(TYPE, fqn('java.util.EnumSet'), _)
	),
	!.

set_type(TYPE):-
	(
		type(TYPE, name('AbstractSet'));
		type(TYPE, name('Set'));
		type(TYPE, name('ConcurrentSkipListSet'));
		type(TYPE, name('CopyOnWriteArraySet'));
		type(TYPE, name('HashSet'));
		type(TYPE, name('JobStateReasons'));
		type(TYPE, name('LinkedHashSet'));
		type(TYPE, name('TreeSet'));
		type(TYPE, name('EnumSet'))
	),
	!.

/* Lista todos los tipos de un tipo sin repetir */

plain_types([],[]):- !.

plain_types(ID,[ID]):-
	(
		class_declaration(ID, _, _, _, _, _, _, _, _, _, _);
		interface_declaration(ID, _, _, _, _, _, _, _, _, _, _);
		enum_declaration(ID, _, _, _, _, _, _, _)
	),
	!.

plain_types(ID,[ID]):-
    primitive_type(ID, _),
    !.

plain_types(ID,[ID]):-
    type(ID, _),
    !.

plain_types(ID,LIST):-
    array_type(ID, array_of(AO), _),
    plain_types(AO,LIST),
    !.

plain_types(ID,LIST):-
	parameterized_type(ID, _, parameters(PARAMETERS)),
    plain_types(PARAMETERS,LIST),
    !.

plain_types(ID,LIST):-
    (
        union_type(ID, types(TYPE));
        intersection_type(ID, types(TYPE))
    ),
    plain_types(TYPE,LIST),
    !.

plain_types([X|COLA],LIST):-
    plain_types(X,LIST_X),
    plain_types(COLA,LIST_COLA),
    append_set(LIST_X,LIST_COLA,LIST),
    !.

/* Instancia types */

is_type(ID):-
	class_declaration(ID, _, _, _, _, _, _, _, _, _, _);
	interface_declaration(ID, _, _, _, _, _, _, _, _, _, _);
	enum_declaration(ID, _, _, _, _, _, _, _);
	type(ID,_).

/* Tipos de clases */

abstract_class(ID):-
    class_declaration(ID, _, _, _, modifiers(MODIFIERS), _, _, _, _, _, _),
	member('abstract', MODIFIERS).

/* Tipos de metodos */

public_method(ID):-
    (
        method_declaration(ID, _, _, modifiers(MODIFIERS), _, _, _, _, _, _, _);
        constructor_declaration(ID, _, _, modifiers(MODIFIERS), _, _, _, _, _, _, _)
    ),
    member('public', MODIFIERS).

protected_method(ID):-
    (
        method_declaration(ID, _, _, modifiers(MODIFIERS), _, _, _, _, _, _, _);
        constructor_declaration(ID, _, _, modifiers(MODIFIERS), _, _, _, _, _, _, _)
    ),
    member('protected',MODIFIERS).

private_method(ID):-
    (
        method_declaration(ID, _, _, modifiers(MODIFIERS), _, _, _, _, _, _, _);
        constructor_declaration(ID, _, _, modifiers(MODIFIERS), _, _, _, _, _, _, _)
    ),
    member('private',MODIFIERS).

static_method(ID):-
    (
        method_declaration(ID, _, _, modifiers(MODIFIERS), _, _, _, _, _, _, _);
        constructor_declaration(ID, _, _, modifiers(MODIFIERS), _, _, _, _, _, _, _)
    ),
    member('static',MODIFIERS).

abstract_method(ID):-
    (
        method_declaration(ID, _, _, modifiers(MODIFIERS), _, _, _, _, _, _, _);
        constructor_declaration(ID, _, _, modifiers(MODIFIERS), _, _, _, _, _, _, _)
    ),
    member('abstract',MODIFIERS).

package_method(ID):-
    (
        method_declaration(ID, _, _, _, _, _, _, _, _, _, _);
        constructor_declaration(ID, _, _, _, _, _, _, _, _, _, _)
    ),
    not(public_method(ID)),
    not(protected_method(ID)),
    not(private_method(ID)).

/* Tipos de fields */

public_field(ID):-
    field_declaration(ID, _, _, _, modifiers(MODIFIERS), _, _, _),
    member('public',MODIFIERS).

protected_field(ID):-
    field_declaration(ID, _, _, _, modifiers(MODIFIERS), _, _, _),
    member('protected',MODIFIERS).

private_field(ID):-
	field_declaration(ID, _, _, _, modifiers(MODIFIERS), _, _, _),
    member('private',MODIFIERS).

static_field(ID):-
    field_declaration(ID, _, _, _, modifiers(MODIFIERS), _, _, _),
    member('static',MODIFIERS).

final_field(ID):-
	field_declaration(ID, _, _, _, modifiers(MODIFIERS), _, _, _),
    member('final',MODIFIERS).

package_field(ID):-
    field_declaration(ID, _, _, _, _, _, _, _),
    not(public_field(ID)),
    not(protected_field(ID)),
    not(private_field(ID)).

/* Main class */

main_class(ID):-
    method_declaration(METHOD, parent(ID), 'main', _, parameters([ARGS]), parameters_types([STRING_ARRAY]), return_type(RETURN), dimensions(0), _, _, _),
    class_declaration(ID, _, _, _, _, _, _, _, _, _, _),
    public_method(METHOD),
    static_method(METHOD),
    primitive_type(RETURN, code('void')),
    (
        (
            array_type(STRING_ARRAY, array_of(STRING), dimensions(1)),
            type(STRING, name('String'))
        );
        (
            type(STRING_ARRAY, name('String')),
            variable_declaration(ARGS, _, _, _, _, extra_dimensions(1), _, _, _, _)
        )
    ).

/* Model unit */

model_unit(ID):-
	model_unit_chechked(ID),
	!.

model_unit(ID):-
    main_unit_chechked(ID),
    !,
    fail.

model_unit(ID):-
    method_declaration(METHOD, _, 'main', _, parameters([ARGS]), parameters_types([STRING_ARRAY]), return_type(RETURN), dimensions(0), _, _, compilation_unit(ID)),
    public_method(METHOD),
	static_method(METHOD),
    primitive_type(RETURN, code('void')),
    (
        (
            array_type(STRING_ARRAY, array_of(STRING), dimensions(1)),
            type(STRING, name('String'))
        );
        (
            type(STRING_ARRAY, name('String')),
            variable_declaration(ARGS, _, _, _, _, extra_dimensions(1), _, _, _, _)
        )
    ),
	!,
    fail.

model_unit(ID):-
    assert(model_unit_chechked(ID)).

/* Método setter */

is_setter(ID):-
    method_declaration(ID, _, METHOD_NAME, _, parameters(PARAMETERS), _, return_type(VOID), _, body(BODY), _, _),
    BODY \= null,
    public_method(ID),
    primitive_type(VOID, code('void')),
    (
        length(PARAMETERS,1);
        length(PARAMETERS,2)
    ),
    atom_codes(METHOD_NAME,METHOD_CODES),
    (
        starts_with_set(METHOD_CODES);
        starts_with_add(METHOD_CODES);
        starts_with_put(METHOD_CODES)
    ),
    !.

is_setter(ID):-
    method_declaration(ID, _, METHOD_NAME, _, parameters(PARAMETERS), parameters_types(PARAMETERS_TYPES), return_type(VOID), _, body(BODY), _, _),
    BODY \= null,
    public_method(ID),
    primitive_type(VOID, code('void')),
    length(PARAMETERS,1),
    block(BODY, _, statements([ASSIGNMENT]), _, _, _),
    assignment(ASSIGNMENT, _, operator('ASSIGN'), left_operand(EXP), _, _, _, _),
    field_access(EXP,FIELD),
    field_declaration(FIELD, _, _, type(FIELD_TYPE), _, _, _, _),
    member(PARAM_TYPE,PARAMETERS_TYPES),
    similar_type(FIELD_TYPE,PARAM_TYPE),
    !.

/* Metodo getter */

is_getter(ID):-
    method_declaration(ID, parent(CLASS), METHOD_NAME, _, parameters([]), _, return_type(RETURN_TYPE), _, body(BODY), _, _),
    BODY \= null,
    public_method(ID),
    not(primitive_type(RETURN_TYPE, code('void'))),
    (
        field_declaration(FIELD, parent(CLASS), FIELD_NAME, type(FIELD_TYPE), _, _, _, _);
        (
            is_super(CLASS_2,CLASS),
            field_declaration(FIELD, parent(CLASS_2), FIELD_NAME, type(FIELD_TYPE), _, _, _, _)
        )
    ),
    similar_type(RETURN_TYPE,FIELD_TYPE),
    (
        (
            atom_codes(FIELD_NAME,FIELD_CODES),
            atom_codes(METHOD_NAME,METHOD_CODES),
            get_name(METHOD_CODES,FIELD_CODES)
        );
        (
            block(BODY, _, statements([RETURN]), _, _, _),
            return_statement(RETURN, _, expression(EXP), _, _, _),
            field_access(EXP,FIELD)
        )
    ),
    !.

/* Método de acceso a elementos de una lista */

is_list_item_getter(ID):-
    method_declaration(ID, parent(CLASS), METHOD_NAME, _, _, parameters_types([INDEX_TYPE]), return_type(RETURN_TYPE), _, body(BODY), _, _),
    public_method(ID),
    BODY \= null,
    atom_codes(METHOD_NAME,METHOD_CODES),
    starts_with_get(METHOD_CODES),
    (
        primitive_type(INDEX_TYPE,code('int'));
        type(INDEX_TYPE , name('Integer'))
    ),
    field_declaration(FIELD, parent(CLASS), _, type(LIST),  _, _, _, _),
    (
        (
            list_type(LIST),
            parameterized_type(LIST, _, parameters([RETURN_TYPE]))
        );
        array_type(LIST, array_of(RETURN_TYPE), _)
    ),
    !.

/* Método de acceso a elementos de un map */

is_hm_item_getter(ID):-
    method_declaration(ID, parent(CLASS), METHOD_NAME, _, parameters([PARAM]), parameters_types([INDEX_TYPE]), return_type(RETURN_TYPE), _, body(BODY), _, _),
    public_method(ID),
    BODY \= null,
    atom_codes(METHOD_NAME,METHOD_CODES),
    starts_with_get(METHOD_CODES),
    field_declaration(FIELD, parent(CLASS), _, type(MAP),  _, _, _, _),
    map_type(MAP),
    parameterized_type(MAP, _, parameters([INDEX_TYPE,RETURN_TYPE])),
    method_invocation(INVOCATION, _, expression(EXP), method(GET), arguments([PARAM]), _, body_declaration(ID), _, _),
    field_access(EXP,FIELD),
    method(GET, name('get'), _, _),
    !.

/* Metodos de acceso a atributo */

define_access(ID):-
    (
        is_setter(ID); 
        is_getter(ID);
        is_list_item_getter(ID);
        is_hm_item_getter(ID)
    ),
    !.

/* Metodo de comportamiento */

define_behaviour(ID):-
    method_declaration(ID, _, _, _, _, _, _, _, _, _, _),
    not(define_access(ID)).

/* Filtrado de metodos */

filter_methods([],[]):- !.

filter_methods([CONSTRUCTOR|COLA],COLA2):-
    constructor_declaration(CONSTRUCTOR, _, _, _, _, _, _, _, _, _, _),
    filter_methods(COLA,COLA2),
    !.  
    
filter_methods([METHOD|COLA],[METHOD|COLA2]):-
    filter_methods(COLA,COLA2).

/* Filtrado de constructores */

filter_constructors([],[]):- !.

filter_constructors([METHOD|COLA],COLA2):-
    method_declaration(METHOD, _, _, _, _, _, _, _, _, _, _),
    filter_constructors(COLA,COLA2),
    !.  
    
filter_constructors([METHOD|COLA],[METHOD|COLA2]):-
    filter_constructors(COLA,COLA2).

/* Filtrado de metodos de comportamiento */

filter_behaviours([],[]):- !.

filter_behaviours([METHOD|COLA],COLA2):-
    define_access(METHOD),
    filter_behaviours(COLA,COLA2),
    !.  
    
filter_behaviours([METHOD|COLA],[METHOD|COLA2]):-
    filter_behaviours(COLA,COLA2).

/* Filtrado de metodos publicos */

filter_public_methods([],[]).

filter_public_methods([METHOD|COLA],[METHOD|COLA2]):-
    public_method(METHOD),
    filter_public_methods(COLA,COLA2),
    !.

filter_public_methods([METHOD|COLA],COLA2):-
    filter_public_methods(COLA,COLA2).

/* Devuelve una lista con todos las clases superiores */

list_super_classes(ID,[]):-
    (
        parameterized_type(ID, _, _);
        primitive_type(ID, _);
        wildcard_type(ID, _, _);
        array_type(ID, _, _);
        type(ID, _);
        interface_declaration(ID, _, _, _, _, _, _, _, _, _, _);
        class_declaration(ID, _, _, _, _, super_type(null), _, _, _, _, _)
    ),
    !.

list_super_classes(ID,[SID|COLA]):-
    class_declaration(ID, _, _, _, _, super_type(SID), _, _, _, _, _),
    list_super_classes(SID,COLA).

/* Devuelve una lista con todas las interfaces superiores */

list_super_interfaces(ID,[]):-
    (
        parameterized_type(ID, _, _);
        primitive_type(ID, _);
        wildcard_type(ID, _, _);
        array_type(ID, _, _);
        type(ID, _);
        interface_declaration(ID, _, _, _, _, super_type(null), _, _, _, _, _)
    ),
    !.

list_super_interfaces(ID,L):-
    interface_declaration(ID, _, _, _, _, _, implements(I), _, _, _, _),
    list_super_interfaces(I,L),
    !.

list_super_interfaces(ID,L):-
    class_declaration(ID, _, _, _, _, super_type(null), implements(I), _, _, _, _),
    list_super_interfaces(I,L2),
    append_set(L1,L2,L),
    !.

list_super_interfaces(ID,L):-
    class_declaration(ID, _, _, _, _, super_type(SID), implements(I), _, _, _, _),
    list_super_interfaces(SID,L1),
    list_super_interfaces(I,L2),
    append_set(L1,L2,L),
    !.

list_super_interfaces([],[]).

list_super_interfaces([ID|COLA],[ID|L]):-
    list_super_interfaces(ID,L1),
    list_super_interfaces(COLA,L2),
    append_set(L1,L2,L).

/* Lista todas las clases e interfaces superiores */

list_super_types(ID,LIST):-
    list_super_types_checked(ID,LIST),
    !.

list_super_types(ID,L):-
    list_super_classes(ID,CL),
    list_super_interfaces(ID,IL),
    append(CL,IL,L),
    assert(list_super_types_checked(ID,L)).

/* Clase o interfaz superior */

is_super(SID,ID):-
    list_super_types(ID,L),
    member(SID,L).

/* Clase o interfaz superior en comun */

common_super(X,Y,X):-
    is_super(X,Y),
    !.

common_super(X,Y,Y):-
    is_super(Y,X),
    !.

common_super(X,Y,Z):-
    is_super(Z,X),
    is_super(Z,Y),
    !.

/* Jerarquia de una clase */

hierarchy(TYPE,LIST):-
    hierarchy_checked(TYPE,LIST), !.

hierarchy(TYPE,LIST):-
    findall(AUX,same_hierarchy(TYPE,AUX),LIST),
    assert(hierarchy_checked(TYPE,LIST)).

same_hierarchy(TYPE_1,TYPE_2):-
    is_type(TYPE_1),
    is_type(TYPE_2),
    common_super(TYPE_1,TYPE_2,SUPER).

same_hierarchy(TYPE,TYPE):-
    not(is_super(_,TYPE)).

/* Metodo equals */

is_equals(ID):-
    method(ID, name('equals'), _, _).

is_equals(ID):-
    method_declaration(ID, _, 'equals', _, _, parameters_types([OBJECT]), return_type(BOOLEAN), dimensions(0), _, _, _),
    primitive_type(BOOLEAN, code('boolean')),
    type(OBJECT , name('Object')).

/* Metodo toString */

is_tostring(ID):-
    method(ID, name('toString'), _, _).

is_tostring(ID):-
    method_declaration(ID, _, 'toString', _, _, parameters_types([]), return_type(STRING), dimensions(0), _, _, _),
    type(STRING, name('String')).

/* Metodo hashCode */

is_hashcode(ID):-
    method(ID, name('hashCode'), _, _).

is_hashcode(ID):-
    method_declaration(ID, _, 'hashCode', _, _, parameters_types([]), return_type(INT), dimensions(0), _, _, _),
    primitive_type(INT, code('int')).

/* Metodo getClass */

is_getclass(ID):-
    method(ID, name('getClass'), _, _).
    
/* Metodo notify */

is_notify(ID):-
    method(ID, name('notify'), _, _).
    
/* Metodo notifyAll */

is_notifyAll(ID):-
    method(ID, name('notifyAll'), _, _).

/* Metodo wait */

is_wait(ID):-
    method(ID, name('wait'), _, _).
    
/* Acceso a field */

field_access(FIELD,FIELD):-
    field_declaration(FIELD, _, _, _, _, _, _, _).

field_access(FACT,FIELD):-
    field_access(FACT, _, expression(THIS), field(FIELD), _, _, _),
    field_declaration(FIELD, _, _, _, _, _, _, _),
    this_expression(THIS, _, expression(null), _, _, _).

field_access(FACT,FIELD):-
    cast_expression(FACT, _, _, expression(EXP), _, _, _),
    field_access(EXP,FIELD).

/* Acceso a variable */

var_access(VAR,VAR):-
    variable_declaration(VAR, _, _, _, _, _, _, _, _, _).

var_access(FACT,VAR):-
    cast_expression(FACT, _, _, expression(EXP), _, _, _),
    var_access(EXP,VAR),
    variable_declaration(VAR, _, _, _, _, _, _, _, _, _).


/* Resolver expresion simple */

resolve_expression(EXP,FIELD):-
    field_access(EXP,FIELD),
    !.

resolve_expression(EXP,EXP):-
    (
        boolean_literal(EXP,_,_,_,_,_);
        character_literal(EXP,_,_,_,_,_);
        number_literal(EXP,_,_,_,_,_);
        null_literal(EXP,_,_,_);
        string_literal(EXP,_,_,_,_,_);
        variable_declaration(EXP, _, _, _, _, _, _, _, _, _)
    ),
    !.

resolve_expression(NAME,BIND):-
    qualified_name(NAME, _, _, name(BIND), _, _, _).


resolve_expression(EXP,EXP):-
	method_invocation(EXP, _, _, _, _, _, _, _, _).

/* Necesidad del equals */

need_equals(ID):-
    string_literal(ID,_,_,_,_,_);
    (
        (
            field_declaration(ID, _, _, type(TYPE), _, _, _, _);
            variable_declaration(ID, _, _, type(TYPE), _, _, _, _, _, _);
            (
            	method_invocation(ID, _, _,  method(METHOD), _, _, _, _, _),
            	method_declaration(METHOD, _, _, _, _, _, return_type(TYPE), _, _, _, _)
            )
        ),
        not(primitive_type(TYPE,_))
    ),
    !.

/* Constantes validos */

valid_literal('0'):- !.
valid_literal('0f'):- !.
valid_literal('0.0'):- !.
valid_literal('1'):- !.
valid_literal('1.0'):- !.
valid_literal('1f'):- !.
valid_literal('""'):- !.
valid_literal('" "'):- !.

/* Impresion por pantalla */

is_print(ID):-
    nonvar(ID),
    method_invocation(ID, _, expression(SYSOUT), method(PRINT), _, _, _, _, _),
    qualified_name(SYSOUT, _, qualified(SYSTEM), name(OUT), _, _, _),
    type(SYSTEM, name('System')),
    variable(OUT, name('out'), _),
    (
        method(PRINT, name('println'), _, _);
        method(PRINT, name('print'), _, _)
    ).

is_print(ID):-
    var(ID),
    type(SYSTEM, name('System')),
    variable(OUT, name('out'), _),
    qualified_name(SYSOUT, _, qualified(SYSTEM), name(OUT), _, _, _),
    method_invocation(ID, _, expression(SYSOUT), method(PRINT), _, _, _, _, _),
    (
        method(PRINT, name('println'), _, _);
        method(PRINT, name('print'), _, _)
    ).

/* Redefine comportamiento */

redefine_method(METHOD,SUPER_METHOD):-
    method_declaration(METHOD, parent(CLASS), NAME, _, _, parameters_types(PARAMETER_TYPES), _, _, _, _, _),
    method_declaration(SUPER_METHOD, parent(SUPER_CLASS), NAME, _, _, parameters_types(PARAMETER_TYPES), _, _, _, _, _),
    METHOD \= SUPER_METHOD,
    is_super(SUPER_CLASS,CLASS),
    !.

redefine_method(METHD,SUPER_METHOD):-
    (
        (
            is_equals(METHOD),
            is_equals(SUPER_METHOD)
        );
        (
            is_wait(METHOD),
            is_wait(SUPER_METHOD)
        );
        (
            is_tostring(METHOD),
            is_tostring(SUPER_METHOD)
        );
        (
            is_hashcode(METHOD),
            is_hashcode(SUPER_METHOD)
        );
        (
            is_getClass(METHOD),
            is_getClass(SUPER_METHOD)
        )
    ),
    !.

/* Clase larga */

long_class(ID):-
    class_declaration(ID, _, _, _, _, _, _, _, methods(METHODS), _, _),
    filter_behaviours(METHODS,BEHAVIOURS),
    filter_methods(METHODS,JUST_METHODS),
    filter_public_methods(JUST_METHODS,PUBLIC_METHODS),
    filter_behaviours(PUBLIC_METHODS,BEHAVIOURS),
    LENGTH > 15. /* Minimun number of public behaviours to be a long class */

/* Clase corta */

short_class(ID):- 
    class_declaration(ID, _, _, _, _, _, _, _, methods(METHODS), _, _),
    filter_methods(METHODS,JUST_METHODS),
    filter_public_methods(JUST_METHODS,PUBLIC_METHODS),
    filter_behaviours(PUBLIC_METHODS,BEHAVIOURS),
    length(BEHAVIOURS,LENGTH),
    LENGTH < 3. /* Maximum number of public behaviours to be a short class */

/* Una clase posee determinado metodo */

has_method(CLASS,METHOD):-
    method_declaration(METHOD, parent(CLASS), _, _, _, _, _, _, _, _, _),
    !.

has_method(CLASS,METHOD):-
    method_declaration(METHOD, parent(PARENT), NAME, _, _, parameters_types(PARAMETER_TYPES), _, dimensions(D), _, _, _),
    PARENT \= CLASS,
    not(method_declaration(METHOD_2, parent(CLASS), NAME, _, _, parameters_types(PARAMETER_TYPES), _, dimensions(D), _, _, _)),
    (
        class_declaration(CLASS, _, _, _, _, super_type(SUPER), implements(IMPLEMENTS), _, _, _, _);
        interface_declaration(CLASS, _, _, _, _, super_type(SUPER), implements(IMPLEMENTS), _, _, _, _)
    ),
    (
        (
            SUPER \= null,
            has_method(SUPER,METHOD)
        );
        (
            member(INTERFACE,IMPLEMENTS),
            has_method(INTERFACE,METHOD)
        )
    ),
    !.

/* Una clase posee determinado atributo */

has_field(CLASS,FIELD):-
	field_declaration(FIELD, parent(CLASS), _, _, _, _, _, _),
	!.

has_field(CLASS,FIELD):-
	field_declaration(FIELD, parent(SUPER), _, _, _, _, _, _),
	CLASS \= SUPER,
    is_super(SUPER,CLASS),
    not(private_field(FIELD)),
    !.

/* Un método delega funcionalidad a un metodo hermano */

delegation(METHOD,CHILD_METHOD):-
    method_invocation(_, _, _, method(CHILD_METHOD), _, _, body_declaration(METHOD), _, _),
    !.

delegation(METHOD,FIELD,TYPE):-
    method_declaration(METHOD, _, NAME, _, parameters(PARAMETERS), _, _, _, _, _, _),
    method_invocation(_, _, expression(EXP), method(AUX_METHOD), arguments(PARAMETERS), _, body_declaration(METHOD), _, _),
 	(
        method_declaration(AUX_METHOD, _, NAME, _, _, parameters_types(PARAMETER_TYPES), _, dimensions(D), _, _, _);
        method(AUX_METHOD, name(NAME), _, _)
    ),
    (
    	(
    		field_access(EXP,FIELD),
    		field_declaration(FIELD, _, _, type(TYPE), _, _, _, _)
    	);
		variable_declaration(EXP, _, _, type(TYPE), _, _, _, _, _, _);
		(
            method_invocation(EXP, _, expression(EXP_2), method(GET), _, _, _, _, _),
            field_access(EXP_2,FIELD),
            (
                method(GET, name('get'), _, _);
                method(GET, name('elementAt'), _, _)
            )
		)
    ),
    !.

/* Metodo de la clase Objeto */

object_method(METHOD):-
    (
        is_equals(METHOD);
        is_tostring(METHOD);
        is_hashcode(METHOD);
        is_wait(METHOD);
        is_getClass(METHOD);
        is_notify(METHOD);
        is_notifyAll(METHOD)
    ),
    !.

/* Referencia a variable dentro de un método */

var_reference(VAR,METHOD,CLASS,REF):-
    (
        array_access(REF, _, array(VAR), _, body_declaration(METHOD), type_declaration(CLASS), _);
        array_access(REF, _, _, index(VAR), body_declaration(METHOD), type_declaration(CLASS), _);
        array_creation(REF, _, _, _, initializer(VAR), body_declaration(METHOD), type_declaration(CLASS), _);
        array_initializer(REF, _, expressions(VAR), body_declaration(METHOD), type_declaration(CLASS), _);
        assert_statement(REF, _, expression(VAR), _, body_declaration(METHOD), type_declaration(CLASS), _);
        assert_statement(REF, _, _, message(VAR), body_declaration(METHOD), type_declaration(CLASS), _);
        assignment(REF, _, _, left_operand(VAR), _, body_declaration(METHOD), type_declaration(CLASS), _);
        assignment(REF, _, _, _, right_operand(VAR), body_declaration(METHOD), type_declaration(CLASS), _);
        cast_expression(REF, _, _, expression(VAR), body_declaration(METHOD), type_declaration(CLASS), _);
        catch_clause(REF, _, exception(VAR), _, body_declaration(METHOD), type_declaration(CLASS), _);
        class_instance_creation(REF, _, _, _, _, arguments(LIST), body_declaration(METHOD), type_declaration(CLASS), _);
        conditional_expression(REF, _, condition(VAR), _, _, body_declaration(METHOD), type_declaration(CLASS), _);
        conditional_expression(REF, _, _, then(VAR), _, body_declaration(METHOD), type_declaration(CLASS), _);
        conditional_expression(REF, _, _, else(VAR), body_declaration(METHOD), type_declaration(CLASS), _);
        constructor_invocation(REF, _, _, arguments(LIST), _, body_declaration(METHOD), type_declaration(CLASS), _);
        creation_reference(REF, _, _, _, arguments(LIST), body_declaration(METHOD), type_declaration(CLASS), _);
        do_statement(REF, _, expression(VAR), _, body_declaration(METHOD), type_declaration(CLASS), _);
        enhanced_for_statement(REF, _, parameter(VAR), _, _, body_declaration(METHOD), type_declaration(CLASS), _);
        enhanced_for_statement(REF, _, _, expression(VAR), _, body_declaration(METHOD), type_declaration(CLASS), _);
        expression_method_reference(REF, _, expression(VAR), _, _, _, body_declaration(METHOD), type_declaration(CLASS), _);
        expression_method_reference(REF, _, _, _, arguments(LIST), _, body_declaration(METHOD), type_declaration(CLASS), _);
        field_access(REF, _, expression(VAR), _, body_declaration(METHOD), type_declaration(CLASS), _);
        field_access(REF, _, _, field(VAR), body_declaration(METHOD), type_declaration(CLASS), _);
        for_statement(REF, _, _, expression(VAR), _, _, body_declaration(METHOD), type_declaration(CLASS), _);
        if_statement(REF, _, condition(VAR), _, _, body_declaration(METHOD), type_declaration(CLASS), _);
        infix_expression(REF, _, _, left_operand(VAR), _, _, body_declaration(METHOD), type_declaration(CLASS), _);
        infix_expression(REF, _, _, _, right_operand(VAR), _, body_declaration(METHOD), type_declaration(CLASS), _);
        infix_expression(REF, _, _, _, _, extended_operands(LIST), body_declaration(METHOD), type_declaration(CLASS), _);
        instanceof_expression(REF, _, expression(VAR), _, body_declaration(METHOD), type_declaration(CLASS), _);
        lambda_expression(REF, _, parameters(LIST), _, body_declaration(METHOD), type_declaration(CLASS), _);
        method_declaration(METHOD, parent(CLASS), _, _, parameters(LIST), _, _, _, _, _, _);
        constructor_declaration(METHOD, parent(CLASS), _, _, parameters(LIST), _, _, _, _, _, _);
        method_invocation(REF, _, expression(VAR), _, _, _, body_declaration(METHOD), type_declaration(CLASS), _);
        method_invocation(REF, _, _, _, arguments(LIST), _, body_declaration(METHOD), type_declaration(CLASS), _);
        postfix_expression(REF, _, _, operand(VAR), body_declaration(METHOD), type_declaration(CLASS), _);
        prefix_expression(REF, _, _, operand(VAR), body_declaration(METHOD), type_declaration(CLASS), _);
        qualified_name(REF, _, qualified(VAR), _, body_declaration(METHOD), type_declaration(CLASS), _);
        qualified_name(REF, _, _, name(VAR), body_declaration(METHOD), type_declaration(CLASS), _);
        return_statement(REF, _, expression(VAR), body_declaration(METHOD), type_declaration(CLASS), _);
        super_constructor_invocation(REF, _, expression(VAR), _, _, _, body_declaration(METHOD), type_declaration(CLASS), _);
        super_constructor_invocation(REF, _, _, _, arguments(LIST), _, body_declaration(METHOD), type_declaration(CLASS), _);
        super_field_access(REF, _, field(VAR), body_declaration(METHOD), type_declaration(CLASS), _);
        super_method_invocation(REF, _, _, arguments(LIST), _, body_declaration(METHOD), type_declaration(CLASS), _);
        super_method_reference(REF, _, _, arguments(LIST), _, body_declaration(METHOD), type_declaration(CLASS), _);
        switch_case(REF, _, case(VAR), body_declaration(METHOD), type_declaration(CLASS), _);
        switch_statement(REF, _, switch(VAR), _, body_declaration(METHOD), type_declaration(CLASS), _);
        synchronized_statement(REF, _, synchronized(VAR), _, body_declaration(METHOD), type_declaration(CLASS), _);
        this_expression(REF, _, expression(VAR), body_declaration(METHOD), type_declaration(CLASS), _);
        throw_statement(REF, _, throw(VAR), body_declaration(METHOD), type_declaration(CLASS), _);
        try_statement(REF, _, resources(LIST), _, _, _, body_declaration(METHOD), type_declaration(CLASS), _);
        type_method_reference(REF, _, _, _, arguments(LIST), body_declarations(METHOD), type_declaration(CLASS), _);
        variable_declaration(REF, _, _, _, _, _, initializer(VAR), body_declaration(METHOD), type_declaration(CLASS), _);
        while_statement(REF, _, condition(VAR), _, body_declaration(METHOD), type_declaration(CLASS), _)
    ),
    (
        var(LIST);
        (
            nonvar(LIST),
            member(VAR,LIST)
        )
    ).

/* Metodos similares */

similar_method(X,X):- !.

similar_method(X,Y):-
    method_declaration(X, parent(P1), NAME, _, _, parameters_types(PARAMETERS_1), return_type(R1), dimensions(D), _, _, _),
    method_declaration(Y, parent(P2), NAME, _, _, parameters_types(PARAMETERS_2), return_type(R2), dimensions(D), _, _, _),
    !,
    P1 \= P2,
    similar_type(R1,R2),
    similar_type(P1,P2),
    similar_type(PARAMETERS_1,PARAMETERS_2).

similar_method(X,Y):-
    method_declaration(X, _, NAME, _, _, _, _, _, _, _, _),
    method(Y, name(NAME), _, _),
    !.

similar_method(X,Y):-
    method_declaration(Y, _, NAME, _, _, _, _, _, _, _, _),
    method(X, name(NAME), _, _),
    !.

similar_method(X,Y):-
    method(X, name(NAME), _, _),
    method(Y, name(NAME), _, _).

/* Tipos similares */

similar_type([],[]):- !.

similar_type([X|COLA],[Y|COLA2]):-
    similar_type(X,Y),
    similar_type(COLA,COLA2),
    !.
    
similar_type(TYPE1,TYPE1):- !.

similar_type(TYPE1,TYPE2):-
    common_super(TYPE1,TYPE2_),
    !.

similar_type(TYPE1,TYPE2):-
    parameterized_type(TYPE1, fqn(FQN), parameters(LIST1)),
    parameterized_type(TYPE2, fqn(FQN), parameters(LIST2)),
    similar_type(LIST1,LIST2).

similar_type(TYPE1,TYPE2):-
    union_type(TYPE1, types(LIST1)),
    union_type(TYPE2, types(LIST2)),
    similar_type(LIST1,LIST2).

similar_type(TYPE1,TYPE2):-
    intersection_type(TYPE1, types(LIST1)),
    intersection_type(TYPE2, types(LIST2)),
    similar_type(LIST1,LIST2).

similar_type(TYPE1,TYPE2):-
    array_type(TYPE1, array_of(AO1), dimension(D)),
    array_type(TYPE2, array_of(AO2), dimension(D)),
    similar_type(AO1,AO2).

similar_type(TYPE1,TYPE2):-
    wildcard_type(TYPE1, bound(B1), is_upper_bound(IUB)),
    wildcard_type(TYPE2, bound(B2), is_upper_bound(IUB)),
    similar_type(B1,B2).

similar_type(TYPE1,TYPE2):-
    (
        primitive_type(TYPE1 , code('int')),
        type(TYPE2 , name('Integer'))
    );
    (
        primitive_type(TYPE2 , code('int')),
        type(TYPE1, name('Integer'))
    ).

similar_type(TYPE1,TYPE2):-
    (
        primitive_type(TYPE1 , code('double')),
        type(TYPE2 , name('Double'))
    );
    (
        primitive_type(TYPE2 , code('double')),
        type(TYPE1, name('Double'))
    ).

similar_type(TYPE1,TYPE2):-
    (
        primitive_type(TYPE1 , code('float')),
        type(TYPE2 , name('Float'))
    );
    (
        primitive_type(TYPE2 , code('float')),
        type(TYPE1, name('Float'))
    ).

similar_type(TYPE1,TYPE2):-
    (
        primitive_type(TYPE1 , code('long')),
        type(TYPE2 , name('Long'))
    );
    (
        primitive_type(TYPE2 , code('long')),
        type(TYPE1, name('Long'))
    ).


similar_type(TYPE1,TYPE2):-
    (
        primitive_type(TYPE1 , code('short')),
        type(TYPE2 , name('Short'))
    );
    (
        primitive_type(TYPE2 , code('short')),
        type(TYPE1, name('Short'))
    ).

similar_type(TYPE1,TYPE2):-
    (
        primitive_type(TYPE1 , code('char')),
        type(TYPE2 , name('Character'))
    );
    (
        primitive_type(TYPE2 , code('char')),
        type(TYPE1, name('Character'))
    ).

/* Codigo similar */

similar_code([],[]):- !.

similar_code([X|COLA],[X|COLA2]):-
    !,
    similar_code(COLA,COLA2).

similar_code([null|COLA],COLA2):-
    !,
    similar_code(COLA,COLA2).

similar_code(COLA,[null|COLA2]):-
    !,
    similar_code(COLA,COLA2).

similar_code([X|COLA],Y):-
    X \= Y,
    cast_expression(X, _, _, expression(E), _, _, _),
    !,
    similar_code([E|COLA],Y).
 
similar_code(Y,[X|COLA]):-
    X \= Y,
    cast_expression(X, _, _, expression(E), _, _, _),
    !,
    similar_code(Y,[E|COLA]).
   
similar_code([X|COLA],Y):-
    X \= Y,
    (
        array_initializer(X, _, _, _, _, _);
        assert_statement(X, _, _, _, _, _, _);
        this_expression(X, _, _, _, _, _);
        throw_statement(X, _, _, _, _, _);
        empty_statement(X, _, _, _, _);
        break_statement(X, _, _, _, _, _);
        continue_statement(X, _, _, _, _, _)
    ), 
    !,
    similar_code(COLA,Y).

similar_code(Y,[X|COLA]):-
    X \= Y,
    (
        array_initializer(X, _, _, _, _, _);
        assert_statement(X, _, _, _, _, _, _);
        this_expression(X, _, _, _, _, _);
        throw_statement(X, _, _, _, _, _);
        empty_statement(X, _, _, _, _);
        break_statement(X, _, _, _, _, _);
        continue_statement(X, _, _, _, _, _)
    ), 
    !,
    similar_code(Y,COLA).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    (
        (
            boolean_literal(X, _, _, _, _, _);
            character_literal(X, _, _, _, _, _);
            number_literal(X, _, _, _, _, _);
            string_literal(X, _, _, _, _, _);
            type_literal(X,_, _, _, _, _);
            null_literal(X, _, _, _, _)
        ),
        (
            boolean_literal(Y, _, _, _, _, _);
            character_literal(Y, _, _, _, _, _);
            number_literal(Y, _, _, _, _, _);
            string_literal(Y, _, _, _, _, _);
            type_literal(Y,_, _, _, _, _);
            null_literal(Y, _, _, _, _)
        )
    ),
    !,
    similar_code(COLA,COLA2).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    anonymous_class_declaration(X, _, declarations(L1), _),
    anonymous_class_declaration(Y, _, declarations(L2), _),
    !,
    append(L1,COLA,NEWCOLA),
    append(L2,COLA2,NEWCOLA2),
    similar_code(NEWCOLA,NEWCOLA2).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    array_access(X, _, array(A1), index(I1), _, _, _),
    array_access(Y, _, array(A2), index(I2), _, _, _),
    !,
    similar_code([A1,I1|COLA],[A2,I2|COLA2]).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    array_creation(X, _, type(T1), dimensions(D), _, _, _, _),
    array_creation(Y, _, type(T2), dimensions(D), _, _, _, _),
    !,
    similar_type(T1,T2),
    similar_code(COLA,COLA2).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    assignment(X, _, operator(O), left_operand(LO1), right_operand(RO1), _, _, _),
    assignment(Y, _, operator(O), left_operand(LO2), right_operand(RO2), _, _, _),
    !,
    similar_code([LO1,RO1|COLA],[LO2,RO2|COLA2]).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    block(X, _, statements(S1), _, _, _),
    block(Y, _, statements(S2), _, _, _),
    !,
    append(S1,COLA,NUEVACOLA),
    append(S2,COLA2,NUEVACOLA2),
    similar_code(NUEVACOLA,NUEVACOLA2).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    catch_clause(X, _, exception(E1), body(B1), _, _, _),
    catch_clause(Y, _, exception(E2), body(B2), _, _, _),
    !,
    similar_code([B1|COLA],[B2|COLA2]).
 
similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    class_instance_creation(X, _, type(T1), _, _, _, _, _, _),
    class_instance_creation(Y, _, type(T2), _, _, _, _, _, _),
    !,
    similar_type(T1,T2),
    similar_code(COLA,COLA2).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    conditional_expression(X, _, condition(C1), then(T1), else(E1), _, _, _),
    conditional_expression(Y, _, condition(C2), then(T2), else(E2), _, _, _),  
    !,
    similar_code([C1,T1,E1|COLA],[C2,T2,E2|COLA2]).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    constructor_invocation(X, _, _, constructor(C1), _, _, _, _, _),
    constructor_invocation(Y, _, _, constructor(C2), _, _, _, _, _),
    !,
    (
        C1 = null;
        (
            C1 \= null,
            C2 = null
        );
        (
            constructor_declaration(C1, parent(T1), _, _, _, _, _, _, _, _, _),
            constructor_declaration(C2, parent(T2), _, _, _, _, _, _, _, _, _),
            similar_type(T1,T2)
        )
    ),
    similar_code(NEWCOLA,NEWCOLA2).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    creation_reference(X, _, type(T1), _, _, _, _, _),
    creation_reference(Y, _, type(T2), _, _, _, _, _),
    !,
    similar_type(T1,T2),
    similar_code(COLA,COLA2).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    do_statement(X, _, expression(E1), body(B1), _, _, _),
    do_statement(Y, _, expression(E2), body(B2), _, _, _),
    !,
    similar_code([E1,B1|COLA],[E2,B2|COLA2]).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    enhanced_for_statement(X, _, parameter(P1), expression(E1), body(B1), _, _),
    enhanced_for_statement(Y, _, parameter(P2), expression(E2), body(B2), _, _),
    similar_code([P1,E1,B1|COLA],[P2,E2,B2|COLA2]).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    enum_constant_declaration(X, parent(P), _, _, _, _, _),
    enum_constant_declaration(Y, parent(P), _, _, _, _, _),
    !,
    similar_code(COLA,COLA2).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    expression_method_reference(X, _, expression(E1), method(M1), _, _, _, _, _),
    expression_method_reference(Y, _, expression(E2), method(M2), _, _, _, _, _),
    !,
    similar_method(M1,M2),
    similar_code([E1|COLA],[E2|COLA2]).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    field_access(X, _, expression(E1), field(F1), _, _, _),
    field_access(Y, _, expression(E2), field(F2), _, _, _),
    !,
    similar_code([E1,F1|COLA],[E2,F2|COLA2]).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    field_declaration(X, parent(P1), _, type(T1), _, extra_dimensions(D), _, _),
    field_declaration(Y, parent(P2), _, type(T2), _, extra_dimensions(D), _, _),
    !,
    P1 \= P2,
    similar_type(T1,T2),
    similar_code(COLA,COLA2).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    for_statement(X, _, initializers(I1), expression(E1), updaters(U1), body(B1), _, _, _),
    for_statement(Y, _, initializers(I2), expression(E2), updaters(U2), body(B2), _, _, _),
    !,
    append(I1,U1,L1),
    append(I2,U2,L2),
    append(L1,COLA,NEWCOLA),
    append(L2,COLA2,NEWCOLA2),
    similar_code([E1,B1|NEWCOLA],[E2,B2|NEWCOLA2]).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    if_statement(X, _, condition(C1), then(T1), else(E1), _, _, _),
    if_statement(Y, _, condition(C2), then(T2), else(E2), _, _, _),
    !,
    similar_code([C1,T1,E1|COLA],[C2,T2,E2|COLA2]).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    instanceof_expression(X, _, expression(E1), type(T1), _, _, _),
    instanceof_expression(Y, _, expression(E2), type(T2), _, _, _),
    !,
    similar_type(T1,T2),
    similar_code([E1|COLA],[E2|COLA2]).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    infix_expression(X, _, operator(O), left_operand(LO1), right_operand(RO1), extended_operands(L1), _, _, _),
    infix_expression(Y, _, operator(O), left_operand(LO2), right_operand(RO2), extended_operands(L2), _, _, _),
    !,
    append(L1,COLA,NEWCOLA),
    append(L2,COLA2,NEWCOLA2),
    similar_code([LO1,RO1|NEWCOLA],[LO2,RO2|NEWCOLA2]).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    labeled_statement(X, _, parameters(L1), body(B1), _, _, _),
    labeled_statement(Y, _, parameters(L2), body(B2), _, _, _),
    !,
    append(L1,COLA,NEWCOLA),
    append(L2,COLA2,NEWCOLA2),
    similar_code([B1|NEWCOLA],[B2|NEWCOLA2]).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    lambda_expression(X, _, parameters(P1), body(B1), _, _),
    lambda_expression(Y, _, parameters(P2), body(B2), _, _),
    !,
    append(P1,[B1|COLA],NEWCOLA),
    append(P2,[B2|COLA2],NEWCOLA2),
    similar_code(NEWCOLA,NEWCOLA2).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    method_invocation(X, _, expression(E1), method(M1), arguments(A1), _, _, _, _),
    method_invocation(Y, _, expression(E2), method(M2), arguments(A2), _, _, _, _),
    !,
    similar_method(M1,M2),
    append(A1,[E1|COLA],NEWCOLA),
    append(A2,[E2|COLA2],NEWCOLA2),
    similar_code(NEWCOLA,NEWCOLA2).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    postfix_expression(X, _, operator(O), operand(O1), _, _, _),
    postfix_expression(Y, _, operator(O), operand(O2), _, _, _),
    !,
    similar_code([O1|COLA],[O2|COLA2]).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    prefix_expression(X, _, operator(O), operand(O1), _, _, _),
    prefix_expression(Y, _, operator(O), operand(O2), _, _, _),
    !,
    similar_code([O1|COLA],[O2|COLA2]).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    qualified_name(X, _, qualified(Q1), name(N1), _, _, _),
    qualified_name(Y, _, qualified(Q2), name(N2), _, _, _),
    !,
    similar_code([Q1,N1|COLA],[Q2,N2|COLA2]).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    return_statement(X, _, expression(E1), _, _, _),
    return_statement(Y, _, expression(E2), _, _, _),
    !,
    similar_code([E1|COLA],[E2|COLA2]).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    super_constructor_invocation(X, _, _, _, _, _, _, type_declaration(T1), _),
    super_constructor_invocation(Y, _, _, _, _, _, _, type_declaration(T2), _),
    !,
    similar_type(T1,T2),
    similar_code(COLA,COLA2).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    super_field_access(X, _, field(F1), _, _, _),
    super_field_access(Y, _, field(F2), _, _, _),
    !,
    similar_code([F1|COLA],[F2|COLA2]).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    super_method_invocation(X, _, method(M1), _, _, _, _, _),
    super_method_invocation(Y, _, method(M2), _, _, _, _, _),
    !,
    similar_method(M1,M2),
    similar_code(COLA,COLA2).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    super_method_reference(X, _, method(M1), _, _, _, _, _),
    super_method_reference(Y, _, method(M2), _, _, _, _, _),
    !,
    similar_method(M1,M2),
    similar_code(COLA,COLA2).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    switch_case(X, _, case(C1), _, _, _),
    switch_case(Y, _, case(C2), _, _, _),
    !,
    similar_code([C1|COLA],[C2|COLA2]).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    switch_statement(X, _, switch(S1), statements(ST1), _, _, _),
    switch_statement(Y, _, switch(S2), statements(ST2), _, _, _),
    !,
    append(ST1,COLA,NEWCOLA),
    append(ST2,COLA2,NEWCOLA2),
    similar_code([S1|NEWCOLA],[S2|NEWCOLA2]).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    synchronized_statement(X, _, synchronized(S1), body(B1), _, _, _),
    synchronized_statement(Y, _, synchronized(S2), body(B2), _, _, _),
    !,
    similar_code([S1,B1|COLA],[S2,B2|COLA2]).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    try_statement(X, _, _, body(B1), catchs(C1), finally(F1), _, _, _),
    try_statement(Y, _, _, body(B2), catchs(C2), finally(F2), _, _, _),
    !,
    append(C1,COLA,NEWCOLA),
    append(C2,COLA2,NEWCOLA2),
    similar_code([B1,F1|NEWCOLA],[B2,F2|NEWCOLA2]).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    variable_declaration(X, _, _, type(T1), _, extra_dimensions(D), initializer(I1), body_declaration(B1), _, _),            
    variable_declaration(Y, _, _, type(T2), _, extra_dimensions(D), initializer(I2), body_declaration(B2), _, _),
    !,
    B1 \= B2,
    similar_type(T1,T2),
    similar_code([I1|COLA],[I2|COLA2]).

similar_code([X|COLA],[Y|COLA2]):-
    X \= Y,
    while_statement(X, _, condition(C1), body(B1), _, _, _),
    while_statement(Y, _, condition(C2), body(B2), _, _, _),
    !,
    similar_code([C1,B1|COLA],[C2,B2|COLA2]).

/* Obtengo el case de un switch */

get_case(LIST,SUBLIST):-
    sublist(LIST,SUBLIST),
    first(SUBLIST,FIRST),
    switch_case(FIRST, _, _, _, _, _),
    last(SUBLIST,LAST),
    (
        break_statement(LAST, _, _, _, _, _);
        return_statement(LAST, _, _, _, _, _)
    ),
    not(
        (
            member(ITEM,SUBLIST),
            ITEM \= FIRST,
            ITEM \= LAST,
            (
                break_statement(ITEM, _, _, _, _, _);
                return_statement(ITEM, _, _, _, _, _);
                switch_case(ITEM, _, _, _, _, _)
            )
        )
    ).