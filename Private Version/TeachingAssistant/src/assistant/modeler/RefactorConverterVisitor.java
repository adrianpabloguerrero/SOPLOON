package assistant.modeler;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.AnnotationTypeMemberDeclaration;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.ArrayAccess;
import org.eclipse.jdt.core.dom.ArrayCreation;
import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.ArrayType;
import org.eclipse.jdt.core.dom.AssertStatement;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BlockComment;
import org.eclipse.jdt.core.dom.BooleanLiteral;
import org.eclipse.jdt.core.dom.BreakStatement;
import org.eclipse.jdt.core.dom.CastExpression;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.CharacterLiteral;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.ConstructorInvocation;
import org.eclipse.jdt.core.dom.ContinueStatement;
import org.eclipse.jdt.core.dom.CreationReference;
import org.eclipse.jdt.core.dom.Dimension;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.EmptyStatement;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.ExpressionMethodReference;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.Initializer;
import org.eclipse.jdt.core.dom.InstanceofExpression;
import org.eclipse.jdt.core.dom.IntersectionType;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.LabeledStatement;
import org.eclipse.jdt.core.dom.LambdaExpression;
import org.eclipse.jdt.core.dom.LineComment;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MemberRef;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.MethodRef;
import org.eclipse.jdt.core.dom.MethodRefParameter;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.NameQualifiedType;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.NullLiteral;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.ParenthesizedExpression;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.QualifiedType;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.SuperConstructorInvocation;
import org.eclipse.jdt.core.dom.SuperFieldAccess;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;
import org.eclipse.jdt.core.dom.SuperMethodReference;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.SynchronizedStatement;
import org.eclipse.jdt.core.dom.TagElement;
import org.eclipse.jdt.core.dom.TextElement;
import org.eclipse.jdt.core.dom.ThisExpression;
import org.eclipse.jdt.core.dom.ThrowStatement;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclarationStatement;
import org.eclipse.jdt.core.dom.TypeLiteral;
import org.eclipse.jdt.core.dom.TypeMethodReference;
import org.eclipse.jdt.core.dom.TypeParameter;
import org.eclipse.jdt.core.dom.UnionType;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.eclipse.jdt.core.dom.WildcardType;

public class RefactorConverterVisitor extends ASTVisitor {

	private static final String[] EXTERNAL_KEYS = new String[] { null, "name" };
	private static final String[] KINDS = new String[] { "package", "type", "variable", "method", "annotation", "member_value_pair"  };
	private static final String ANONYMOUS_CLASS_DECLARATION = "anonymous_class_declaration";
	private static final String[] ANONYMOUS_CLASS_DECLARATION_KEYS = new String[] { null, "parent", "declarations" };
	private static final String ARRAY_ACCESS = "array_access";
	private static final String[] ARRAY_ACCESS_KEYS = new String[] { null, "parent", "array", "index" };
	private static final String ARRAY_CREATION = "array_creation";
	private static final String[] ARRAY_CREATION_KEYS = new String[] { null, "parent", "type", "dimensions", "initializer" };
	private static final String ARRAY_INITIALIZER = "array_initializer";
	private static final String[] ARRAY_INITIALIZER_KEYS = new String[] { null, "parent", "values" };
	private static final String ARRAY_TYPE = "array_type";
	private static final String[] ARRAY_TYPE_KEYS = { null, "array_of", "dimensions_size", "dimensions" };
	private static final String ASSERT_STATEMENT = "assert_statement";
	private static final String[] ASSERT_STATEMENT_KEYS = new String[] { null, "parent", "expression", "message" };
	private static final String ASSIGNMENT = "assignment";
	private static final String[] ASSIGNMENT_KEYS = new String[] { null, "parent", "operator", "left_operand", "right_operand" };
	private static final String BLOCK = "block";
	private static final String[] BLOCK_KEYS = new String[] { null, "parent", "statements" };
	private static final String BOOLEAN_LITERAL = "boolean_literal";
	private static final String[] BOOLEAN_LITERAL_KEYS = new String[] { null, "parent", "value" };
	private static final String BREAK_STATEMENT = "break_statement";
	private static final String[] BREAK_STATEMENT_KEYS = new String[] { null, "parent", "label" };
	private static final String CAST_EXPRESSION = "cast_expression";
	private static final String[] CAST_EXPRESSION_KEYS = new String[] { null, "parent", "type", "expression" };
	private static final String CATCH_CLAUSE = "catch_clause";
	private static final String[] CATCH_CLAUSE_KEYS = new String[] { null, "parent", "exception", "body" };
	private static final String CHARACTER_LITERAL = "character_literal";
	private static final String[] CHARACTER_LITERAL_KEYS = new String[] { null, "parent", "value" };
	private static final String CLASS_INSTANCE_CREATION = "class_instance_creation";
	private static final String[] CLASS_INSTANCE_CREATION_KEYS = new String[] { null, "parent", "type", "expression", "anonymous", "constructor", "arguments", "arguments_types" };
	private static final String COMPILATION_UNIT = "compilation_unit";
	private final static String[] COMPILATION_UNIT_KEYS = { null, null, "path", "package", "imports", "types" };
	private static final String CONDITIONAL_EXPRESSION = "conditional_expression";
	private static final String[] CONDITIONAL_EXPRESSION_KEYS = new String[] { null, "parent", "condition", "then", "else" };
	private static final String CONSTRUCTOR_INVOCATION = "constructor_invocation";
	private static final String[] CONSTRUCTOR_INVOCATION_KEYS = new String[] { null, "parent", "constructor", "arguments", "arguments_types" };
	private static final String CONTINUE_STATEMENT = "continue_statement";
	private static final String[] CONTINUE_STATEMENT_KEYS = new String[] { null, "parent", "label" };
	private static final String CREATION_REFERENCE = "creation_reference";
	private static final String[] CREATION_REFERENCE_KEYS = new String[] { null, "parent", "type", "constructor", "arguments", "arguments_types" };
	private static final String DIMENSION = "dimension";
	private static final String[] DIMENSION_KEYS = new String[] { null, "parent", "annotations" };
	private static final String DO_STATEMENT = "do_statement";
	private static final String[] DO_STATEMENT_KEYS = new String[] { null, "parent", "expression", "body" };
	private static final String EMPTY_STATEMENT = "empty_statement";
	private static final String[] EMPTY_STATEMENT_KEYS = { null, "parent" };
	private static final String ENHANCED_FOR_STATEMENT = "enhanced_for_statement";
	private static final String[] ENHANCED_FOR_STATEMENT_KEYS = new String[] { null, "parent", "parameter", "expression", "body" };
	private static final String ENUM_CONSTANT_DECLARATION = "enum_constant_declaration";
	private static final String[] ENUM_CONSTANT_DECLARATION_KEYS = new String[] { null, "parent", "name", "modifiers", "constructor", "arguments", "anonymous_class" };
	private static final String ENUM_DECLARATION = "enum_declaration";
	private static final String[] ENUM_DECLARATION_KEYS = new String[] { null, "parent", null, "fqn", "modifiers", "implements", "constants", "body_declarations" };
	private static final String EXPRESSION_METHOD_REFERENCE = "expression_method_reference";
	private static final String[] EXPRESSION_METHOD_REFERENCE_KEYS = new String[] { null, "parent", "expression", "method", "arguments_types" };
	private static final String EXPRESSION_STATEMENT = "expression_statement";
	private static final String[] EXPRESSION_STATEMENT_KEYS = new String[] { null, "parent", "expression" };
	private static final String FIELD_ACCESS = "field_access";
	private static final String[] FIELD_ACCESS_KEYS = new String[] { null, "parent", "expression", "field" };
	private static final String FIELD_DECLARATION = "field_declaration";
	private static final String[] FIELD_DECLARATION_KEYS = new String[] { null, "parent", "modifiers", "type", "fragments" };
	private static final String FOR_STATEMENT = "for_statement";
	private static final String[] FOR_STATEMENT_KEYS = new String[] { null, "parent", "initializers", "expression", "updaters", "body" };
	private static final String IF_STATEMENT = "if_statement";
	private static final String[] IF_STATEMENT_KEYS = new String[] { null, "parent", "condition", "then", "else" };
	private static final String IMPORT_DECLARATION = "import_declaration";
	private final static String[] IMPORT_DECLARATION_KEYS = { null, "parent", "name", "on_demand", "static" };
	private static final String INFIX_EXPRESSION = "infix_expression";
	private static final String[] INFIX_EXPRESSION_KEYS = new String[] { null, "parent", "operator", "left_operand", "right_operand", "extended_operands" };
	private static final String INITIALIZER = "initializer";
	private static final String[] INITIALIZER_KEYS = new String[] { null, "parent", "modifiers", "body" };
	private static final String INSTANCE_OF_EXPRESSION = "instance_of_expression";
	private static final String[] INSTANCE_OF_EXPRESSION_KEYS = new String[] { null, "parent", "expression", "type" };
	private static final String INTERSECTION_TYPE = "intersection_type";
	private final static String[] INTERSECTION_TYPE_KEYS = { null, "types" };
	private static final String JAVADOC = "javadoc";
	private static final String[] JAVADOC_KEYS = new String[] { null, "parent", "tags" };
	private static final String LABELED_STATEMENT = "labeled_statement";
	private static final String[] LABELED_STATEMENT_KEYS = new String[] { null, "parent", "label", "body" };
	private static final String LAMBDA_EXPRESSION = "lambda_expression";
	private static final String[] LAMBDA_EXPRESSION_KEYS = new String[] { null, "parent", "parameters", "body" };
	private static final String LINE_COMMENT = "line_comment";
	private static final String[] LINE_COMMENT_KEYS = new String[] { null, "parent" };
	private static final String METHOD_DECLARATION = "method_declaration";
	private static final String[] METHOD_DECLARATION_KEYS = new String[] { null, "parent", null, "name", "modifiers", "parameters", "parameters_types", "return_type", "dimensions_size", "dimensions", "body", "exceptions" };
	private static final String CONSTRUCTOR_DECLARATION = "constructor_declaration";
	private static final String METHOD_INVOCATION = "method_invocation";
	private static final String[] METHOD_INVOCATION_KEYS = new String[] { null, "parent", "expression", "method", "arguments", "arguments_types" };
	private static final String MODIFIER = "modifier";
	private final static String[] MODIFIER_KEYS = { null, "parent", "keyword" };
	private static final String NAME_QUALIFIED_TYPE = "name_qualified_type";
	private final static String[] NAME_QUALIFIED_TYPE_KEYS = { null, "parent", "name", "qualifier", "annotations"};
	private static final String NULL_LITERAL = "null_literal";
	private static final String[] NULL_LITERAL_KEYS = new String[] { null, "parent" };
	private static final String NUMBER_LITERAL = "number_literal";
	private static final String[] NUMBER_LITERAL_KEYS = new String[] { null, "parent", "value" };
	private static final String PACKAGE_DECLARATION = "package_declaration";
	private static final String[] PACKAGE_DECLARATION_KEYS = new String[] { null, "name" };
	private static final String PARAMETERIZED_TYPE = "parameterized_type";
	private final static String[] PARAMETERIZED_TYPE_KEYS = { null, null, "fqn", "parameters" };
	private static final String PARENTHESIZED_EXPRESSION = "parenthesized_expression";
	private static final String[] PARENTHESIZED_EXPRESSION_KEYS = new String[] { null, "parent", "expression" };
	private static final String POSTFIX_EXPRESSION = "postfix_expression";
	private static final String[] POSTFIX_EXPRESSION_KEYS = new String[] { null, "parent", "operator", "operand" };
	private static final String PREFIX_EXPRESSION = "prefix_expression";
	private static final String[] PREFIX_EXPRESSION_KEYS = new String[] { null, "parent", "operator", "operand" };
	private static final String PRIMITIVE_TYPE = "primitive_type";
	private static final String[] PRIMITIVE_TYPE_KEYS = new String[] { null, "type" };
	private static final String QUALIFIED_NAME = "qualified_name";
	private static final String[] QUALIFIED_NAME_KEYS = new String[] { null, "parent", "fqn", "qualified", "name" };
	private static final String QUALIFIED_TYPE = "qualified_type";
	private final static String[] QUALIFIED_TYPE_KEYS = { null, "parent", "name", "qualifier", "annotations"};
	private static final String RETURN_STATEMENT = "return_statement";
	private static final String[] RETURN_STATEMENT_KEYS = new String[] { null, "parent", "expression" };
	private static final String SIMPLE_NAME = "simple_name";
	private static final String[] SIMPLE_NAME_KEYS = new String[] { null, "parent", "name", "bind" };
	private static final String SIMPLE_TYPE = "simple_type";
	private final static String[] SIMPLE_TYPE_KEYS = { null, "parent", "name", "annotations" };
	private static final String SINGLE_VARIABLE_DECLARATION = "single_variable_declaration";
	private static final String[] SINGLE_VARIABLE_DECLARATION_KEYS = new String[] { null, "parent", null, "type", "modifiers", "dimensions_size", "dimensions", "initializer" };
	private static final String STRING_LITERAL = "string_literal";
	private static final String[] STRING_LITERAL_KEYS = new String[] { null, "parent", "value" };
	private static final String SUPER_CONSTRUCTOR_INVOCATION = "super_constructor_invocation";
	private static final String[] SUPER_CONSTRUCTOR_INVOCATION_KEYS = new String[] { null, "parent", "expression", "constructor", "arguments", "arguments_types" };
	private static final String SUPER_FIELD_ACCESS = "super_field_access";
	private static final String[] SUPER_FIELD_ACCESS_KEYS = new String[] { null, "parent", "field" };
	private static final String SUPER_METHOD_INVOCATION = "super_method_invocation";
	private static final String[] SUPER_METHOD_INVOCATION_KEYS = new String[] { null, "parent", "method", "arguments" };
	private static final String SUPER_METHOD_REFERENCE = "super_method_reference";
	private static final String[] SUPER_METHOD_REFERENCE_KEYS = new String[] { null, "parent", "method", "super_qualifier", "arguments", "arguments_types" };
	private static final String SWITCH_CASE = "switch_case";
	private static final String[] SWITCH_CASE_KEYS = new String[] { null, "parent", "case" };
	private static final String SWITCH_STATEMENT = "switch_statement";
	private static final String[] SWITCH_STATEMENT_KEYS = new String[] { null, "parent", "switch", "statements" };
	private static final String SYNCHRONIZED_STATEMENT = "synchronized_statement";
	private static final String[] SYNCHRONIZED_STATEMENT_KEYS = new String[] { null, "parent", "synchronized", "body" };
	private static final String THIS_EXPRESSION = "this_expression";
	private static final String[] THIS_EXPRESSION_KEYS = new String[] { null, "parent", "expression" };
	private static final String THROW_STATEMENT = "throw_statement";
	private static final String[] THROW_STATEMENT_KEYS = new String[] { null, "parent", "throw" };
	private static final String TRY_STATEMENT = "try_statement";
	private static final String[] TRY_STATEMENT_KEYS = new String[] { null, "parent", "resources", "body", "catchs", "finally" };
	private static final String CLASS_DECLARATION = "class_declaration";
	private static final String[] CLASS_DECLARATION_KEYS = new String[] { null, "parent", null, "name", "parameters_types", "modifiers", "extends", "implements", "fields", "methods", "body_declarations" };
	private static final String INTERFACE_DECLARATION = "interface_declaration";
	private static final String[] INTERFACE_DECLARATION_KEYS = new String[] { null, "parent", null, "name", "parameters_types", "modifiers", "implements", "fields", "methods", "body_declarations" };
	private static final String TYPE_DECLARATION_STATEMENT = "type_declaration_statement";
	private static final String[] TYPE_DECLARATION_STATEMENT_KEYS = new String[] { null, "parent", "type" };
	private static final String TYPE_LITERAL = "type_literal";
	private static final String[] TYPE_LITERAL_KEYS = new String[] { null, "parent", "value" };
	private static final String TYPE_METHOD_REFERENCE = "type_method_reference";
	private static final String[] TYPE_METHOD_REFERENCE_KEYS = new String[] { null, "parent", "type", "method", "arguments_types" };
	private static final String TYPE_PARAMETER = "type_parameter";
	private final static String[] TYPE_PARAMETER_KEYS = { null, "parent", null, "modifiers", "extends" };
	private static final String UNION_TYPE = "union_type";
	private final static String[] UNION_TYPE_KEYS = { null, "arguments" };
	private static final String VARIABLE_DECLARATION_EXPRESSION = "variable_declaration_expression";
	private static final String[] VARIABLE_DECLARATION_EXPRESSION_KEYS = new String[] { null, "parent", "modifiers", "type", "fragments" };
	private static final String VARIABLE_DECLARATION_FRAGMENT = "variable_declaration_fragment";
	private static final String[] VARIABLE_DECLARATION_FRAGMENT_KEYS = new String[] { null, "parent", "name", "dimensions_size", "dimensions", "initializer" };
	private static final String VARIABLE_DECLARATION_STATEMENT = "variable_declaration_statement";
	private static final String[] VARIABLE_DECLARATION_STATEMENT_KEYS = new String[] { null, "parent", "modifiers", "type", "fragments" };
	private static final String WHILE_STATEMENT = "while_statement";
	private static final String[] WHILE_STATEMENT_KEYS = new String[] { null, "parent", "condition", "body" };
	private static final String WILDCARD_TYPE = "wildcard_type";
	private final static String[] WILDCARD_TYPE_KEYS = { null, "bound", "is_upper_bound" };

	private Mapper mapper;
	private PrologCode code;
	
	public RefactorConverterVisitor(Mapper mapper, PrologCode code) {
		this.mapper = mapper;
		this.code = code;
	}
	
	public boolean visit(AnnotationTypeDeclaration node) {	
		// TODO
		return true;
	}

	public boolean visit(AnnotationTypeMemberDeclaration node) {
		// TODO
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(AnonymousClassDeclaration node) {
    	String id = this.mapper.getNodeID(node);
    	
    	String parent_id = this.mapper.getNodeID(node.getParent());
            
        String declarations_ids = this.generateList(node.bodyDeclarations());
    	
        String[] args = new String[]{ id, parent_id, declarations_ids };
        
        this.code.addFact(ANONYMOUS_CLASS_DECLARATION, this.generateArgs(ANONYMOUS_CLASS_DECLARATION_KEYS,args));
		
        return true;
	}

	
	public boolean visit(ArrayAccess node) {
    	String id = this.mapper.getNodeID(node);
		
    	String parent_id = this.mapper.getNodeID(node.getParent());
    	
        String array_id = this.mapper.getNodeID(node.getArray());
        
        String index_id = this.mapper.getNodeID(node.getIndex());
        
        String[] args = new String[]{ id, parent_id, array_id, index_id };
        
        this.code.addFact(ARRAY_ACCESS, this.generateArgs(ARRAY_ACCESS_KEYS,args));
  
		return true;
	}


	@SuppressWarnings("unchecked")
	public boolean visit(ArrayCreation node) {
		String id = this.mapper.getNodeID(node);

		String parent_id = this.mapper.getNodeID(node.getParent());

        String type_id = this.mapper.getNodeID(node.getType());
                
        String expressions_ids = this.generateList(node.dimensions()); 
    	
		String initializer = this.mapper.getNodeID(node.getInitializer());

		String[] args = new String[]{ id, parent_id, type_id, expressions_ids, initializer };
        
		this.code.addFact(ARRAY_CREATION, this.generateArgs(ARRAY_CREATION_KEYS,args));
		
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(ArrayInitializer node) {
		String id = this.mapper.getNodeID(node);

		String parent_id = this.mapper.getNodeID(node.getParent());

		String expressions_ids = this.generateList(node.expressions());

		String[] args = new String[] { id, parent_id, expressions_ids };

		this.code.addFact(ARRAY_INITIALIZER, this.generateArgs(ARRAY_INITIALIZER_KEYS, args));

		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(ArrayType node) {	
		String id = this.mapper.getNodeID(node);

		String type_id = this.mapper.getNodeID(node.getElementType());

		String dimensions = Integer.toString(node.getDimensions());
		
		String dimensions_ids = this.generateList(node.dimensions());
		
		String[] args = new String[] { id, type_id, dimensions, dimensions_ids};
		
		this.code.addFact(ARRAY_TYPE, this.generateArgs(ARRAY_TYPE_KEYS, args));

		return true;
	}

	public boolean visit(AssertStatement node) {  	
    	String id = this.mapper.getNodeID(node);
    	
    	String parent_id = this.mapper.getNodeID(node.getParent());
    
    	String expression_id = this.mapper.getNodeID(node.getExpression());
    	
    	String message_id = this.mapper.getNodeID(node.getMessage());

        String[] args = new String[]{ id, parent_id, expression_id, message_id };
        
        this.code.addFact(ASSERT_STATEMENT, this.generateArgs(ASSERT_STATEMENT_KEYS,args));

		return true;
	}

	public boolean visit(Assignment node) {
		String id = this.mapper.getNodeID(node);

		String parent_id = this.mapper.getNodeID(node.getParent());
		
		String operator = this.quote(this.operator(node.getOperator().toString()));
		
		String left_operand = this.mapper.getNodeID(node.getLeftHandSide());

		String right_operand = this.mapper.getNodeID(node.getRightHandSide());

		String[] args = new String[] { id, parent_id, operator, left_operand, right_operand };

		this.code.addFact(ASSIGNMENT, this.generateArgs(ASSIGNMENT_KEYS, args));
		
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(Block node) {   	
    	String id = this.mapper.getNodeID(node);
		
    	String parent_id = this.mapper.getNodeID(node.getParent());
   
        String statements_ids = this.generateList(node.statements());
        
        String[] args = new String[]{ id, parent_id, statements_ids };

        this.code.addFact(BLOCK, this.generateArgs(BLOCK_KEYS,args));
		
		return true;
	}

	public boolean visit(BlockComment node) {
		// TODO
		return true;
	}

	public boolean visit(BooleanLiteral node) {
		String id = this.mapper.getNodeID(node);
		
		String parent_id = this.mapper.getNodeID(node.getParent());
		
		String value = this.quote(Boolean.toString(node.booleanValue()));

		String[] args = new String[] { id, parent_id, value };
		
		this.code.addFact(BOOLEAN_LITERAL, this.generateArgs(BOOLEAN_LITERAL_KEYS, args));
		
		return true;
	}

	public boolean visit(BreakStatement node) {	
		String id = this.mapper.getNodeID(node);
		
		String parent_id = this.mapper.getNodeID(node.getParent());
		
		String label = this.mapper.getNodeID(node.getLabel());
		
		String[] args = new String[] { id, parent_id, label };
		
		this.code.addFact(BREAK_STATEMENT, this.generateArgs(BREAK_STATEMENT_KEYS, args));
		
		return true;
	}

	public boolean visit(CastExpression node) {
		String id = this.mapper.getNodeID(node);
		
		String parent_id = this.mapper.getNodeID(node.getParent());
		
		String type_id = this.mapper.getNodeID(node.getType());
		
		String expression_id = this.mapper.getNodeID(node.getExpression());
		
		String[] args = new String[] { id, parent_id, type_id, expression_id };

		this.code.addFact(CAST_EXPRESSION, this.generateArgs(CAST_EXPRESSION_KEYS, args));

		return true;
	}

	public boolean visit(CatchClause node) {
    	String id = this.mapper.getNodeID(node);
		
    	String parent_id = this.mapper.getNodeID(node.getParent());
    	
		String exception_id = this.mapper.getNodeID(node.getException());
        
    	String block_id = this.mapper.getNodeID(node.getBody());
        
        String[] args = new String[]{ id, parent_id, exception_id, block_id };
        
        this.code.addFact(CATCH_CLAUSE, this.generateArgs(CATCH_CLAUSE_KEYS,args));
		
		return true;
	}

	public boolean visit(CharacterLiteral node) {
		String id = this.mapper.getNodeID(node);

		String parent_id = this.mapper.getNodeID(node.getParent());
		
		String value = this.quote(Character.toString(node.charValue()));

		String[] args = new String[] { id, parent_id, value };
		
		this.code.addFact(CHARACTER_LITERAL, this.generateArgs(CHARACTER_LITERAL_KEYS, args));

		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(ClassInstanceCreation node) {
		String id = this.mapper.getNodeID(node);
		
		String parent = this.mapper.getNodeID(node.getParent());

		String type = this.mapper.getNodeID(node.getType());
		
		String anonymous = this.mapper.getNodeID(node.getAnonymousClassDeclaration());
	
		String expression = this.mapper.getNodeID(node.getExpression());
	
		String bind = this.mapper.getBindingID(node.resolveConstructorBinding());

		String arguments = this.generateList(node.arguments());
		
		String arguments_types = this.generateList(node.typeArguments());

		String[] args = new String[] { id, parent, type, expression, anonymous, bind, arguments, arguments_types };

		this.code.addFact(CLASS_INSTANCE_CREATION, this.generateArgs(CLASS_INSTANCE_CREATION_KEYS, args));
	
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(CompilationUnit node) {
	  	String id = this.mapper.getNodeID(node);
        
        String name = this.quote(node.getJavaElement().getElementName().toString());
        
        String path = this.quote(node.getJavaElement().getPath().toString());

        String package_id = this.mapper.getNodeID(node.getPackage());
       
        String imports_ids = this.generateList(node.imports());         

    	String types_ids = this.generateList(node.types()); 

        String[] args = new String[]{ id, name, path, package_id, imports_ids, types_ids };
        
        this.code.addFact(COMPILATION_UNIT, this.generateArgs(COMPILATION_UNIT_KEYS,args));
   
		return true;
	}

	public boolean visit(ConditionalExpression node) {
		String id = this.mapper.getNodeID(node);
		
		String parent_id = this.mapper.getNodeID(node.getParent());
		
		String expression_id = this.mapper.getNodeID(node.getExpression());

		String then_expression_id = this.mapper.getNodeID(node.getThenExpression());

		String else_expression_id = this.mapper.getNodeID(node.getElseExpression());
		
		String[] args = new String[] { id, parent_id, expression_id, then_expression_id, else_expression_id };
		
		this.code.addFact(CONDITIONAL_EXPRESSION, this.generateArgs(CONDITIONAL_EXPRESSION_KEYS, args));
	
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(ConstructorInvocation node) {
		String id = this.mapper.getNodeID(node);
		
		String parent = this.mapper.getNodeID(node.getParent());
		
		String binding = this.mapper.getBindingID(node.resolveConstructorBinding());

		String arguments = this.generateList(node.arguments());

		String arguments_types = this.generateList(node.typeArguments());
		
		String[] args = new String[] { id, parent, binding, arguments, arguments_types };
		
		this.code.addFact(CONSTRUCTOR_INVOCATION, this.generateArgs(CONSTRUCTOR_INVOCATION_KEYS, args));
			
		return true;
	}

	public boolean visit(ContinueStatement node) {
		String id = this.mapper.getNodeID(node);

		String parent_id = this.mapper.getNodeID(node.getParent());

		String label = this.mapper.getNodeID(node.getLabel());
		
		String[] args = new String[] { id, parent_id, label };
		
		this.code.addFact(CONTINUE_STATEMENT, this.generateArgs(CONTINUE_STATEMENT_KEYS,args));

		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(CreationReference node) {
    	
    	String id = this.mapper.getNodeID(node);
    	
    	String parent_id = this.mapper.getNodeID(node.getParent());
    	
    	String type_id = this.mapper.getNodeID(node.getType());
    	
		String bind = this.mapper.getBindingID(node.resolveMethodBinding());
		
		String arguments_ids = this.generateList(node.typeArguments());
		
		String arguments_types = this.generateList(node.typeArguments());
		
        String[] args = new String[]{ id, parent_id, type_id, bind, arguments_ids, arguments_types };
        
        this.code.addFact(CREATION_REFERENCE, this.generateArgs(CREATION_REFERENCE_KEYS, args));
  	
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(Dimension node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		String anotations = this.generateList(node.annotations());

		String[] args = new String[] { id, parent, anotations };

		this.code.addFact(DIMENSION, this.generateArgs(DIMENSION_KEYS, args));

		return true;
	}

	public boolean visit(DoStatement node) {
		String id = this.mapper.getNodeID(node);
		
		String parent_id = this.mapper.getNodeID(node.getParent());
		
		String condition_id = this.mapper.getNodeID(node.getExpression());
		
		String block_id = this.mapper.getNodeID(node.getBody());

		String[] args = new String[] { id, parent_id, condition_id, block_id };
		
		this.code.addFact(DO_STATEMENT, this.generateArgs(DO_STATEMENT_KEYS, args));
	
		return true;
	}

	public boolean visit(EmptyStatement node) {
		String id = this.mapper.getNodeID(node);

		String parent_id = this.mapper.getNodeID(node.getParent());
		
		String[] args = new String[] { id, parent_id };
		
		this.code.addFact(EMPTY_STATEMENT, this.generateArgs(EMPTY_STATEMENT_KEYS, args));

		return true;
	}

	public boolean visit(EnhancedForStatement node) {
		String id = this.mapper.getNodeID(node);
		
		String parent_id = this.mapper.getNodeID(node.getParent());
		
		String parameter_id = this.mapper.getNodeID(node.getParameter());
		
		String expression_id = this.mapper.getNodeID(node.getExpression());
	
		String body_id = this.mapper.getNodeID(node.getBody());

		String[] args = new String[] { id, parent_id, parameter_id, expression_id, body_id };

		this.code.addFact(ENHANCED_FOR_STATEMENT, this.generateArgs(ENHANCED_FOR_STATEMENT_KEYS, args));

		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(EnumConstantDeclaration node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		String name = this.mapper.getNodeID(node.getName());

		String constructor = this.mapper.getBindingID(node.resolveConstructorBinding());
		
		String modifiers = this.generateList(node.modifiers());
		
		String arguments = this.generateList(node.arguments());

		String anonymous_class = this.mapper.getNodeID(node.getAnonymousClassDeclaration());
				
		String[] args = new String[] { id, parent, name, modifiers, constructor, arguments, anonymous_class };
		
		this.code.addFact(ENUM_CONSTANT_DECLARATION, this.generateArgs(ENUM_CONSTANT_DECLARATION_KEYS, args));
	
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(EnumDeclaration node) {
		String id = this.mapper.getNodeID(node);

		String parent_id = this.mapper.getNodeID(node.getParent());
		
		String name = this.quote(node.getName().toString());
		
		String fqn = this.quote(node.resolveBinding().getQualifiedName());

		String modifiers = this.generateList(node.modifiers());
		
		String implements_ids = this.generateList(node.superInterfaceTypes());

		String enum_constants_ids = this.generateList(node.enumConstants());
	
		String declarations_ids = this.generateList(node.bodyDeclarations());

		
		String[] args = new String[] { id, parent_id, name, fqn, modifiers, implements_ids, enum_constants_ids, declarations_ids };
		this.code.addFact(ENUM_DECLARATION, generateArgs(ENUM_DECLARATION_KEYS, args));
	
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(ExpressionMethodReference node) {	
    	String id = this.mapper.getNodeID(node);
    	
    	String parent_id = this.mapper.getNodeID(node.getParent());
    
		String name = this.mapper.getNodeID(node.getName());
		
    	String expression_id = this.mapper.getNodeID(node.getExpression());
    				
		String arguments_types = this.generateList(node.typeArguments());

		String[] args = new String[]{ id, parent_id, expression_id, name, arguments_types};
        
		this.code.addFact(EXPRESSION_METHOD_REFERENCE, this.generateArgs(EXPRESSION_METHOD_REFERENCE_KEYS,args));    	
  
		return true;
	}

	public boolean visit(ExpressionStatement node) {
		String id = this.mapper.getNodeID(node);
		
		String parent = this.mapper.getNodeID(node.getParent());
		
		String expression = this.mapper.getNodeID(node.getExpression());
		
		String[] args = new String[] { id, parent, expression };
	
		this.code.addFact(EXPRESSION_STATEMENT, this.generateArgs(EXPRESSION_STATEMENT_KEYS, args));
 
		return true;
	}

	public boolean visit(FieldAccess node) {
    	String id = this.mapper.getNodeID(node);
		
    	String parent = this.mapper.getNodeID(node.getParent());
    	
    	String  expression = this.mapper.getNodeID(node.getExpression());
		
    	String bind = this.mapper.getNodeID(node.getName());
        
        String[] args = new String[]{ id, parent, expression, bind };

        this.code.addFact(FIELD_ACCESS, this.generateArgs(FIELD_ACCESS_KEYS,args));
  
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(FieldDeclaration node) {
		String id = this.mapper.getNodeID(node);
		
		String parent = this.mapper.getNodeID(node.getParent());
		
		String type = this.mapper.getNodeID(node.getType());
		
		String modifiers = this.generateList(node.modifiers());
		
		String fragments = this.generateList(node.fragments());

		String[] args = new String[] { id, parent, modifiers, type, fragments };
		
		this.code.addFact(FIELD_DECLARATION, this.generateArgs(FIELD_DECLARATION_KEYS, args));
	
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(ForStatement node) {
		String id = this.mapper.getNodeID(node);
		
		String parent_id = this.mapper.getNodeID(node.getParent());
		
		String initializers_ids = this.generateList(node.initializers());

		String expression_id = this.mapper.getNodeID(node.getExpression());
		
		String updaters_ids = this.generateList(node.updaters());
		
		String body_id = this.mapper.getNodeID(node.getBody());
		
		String[] args = new String[] { id, parent_id, initializers_ids, expression_id, updaters_ids, body_id };
		
		this.code.addFact(FOR_STATEMENT, this.generateArgs(FOR_STATEMENT_KEYS,args));

		return true;
	}

	public boolean visit(IfStatement node) {
    	String id = this.mapper.getNodeID(node);
		
    	String parent_id = this.mapper.getNodeID(node.getParent());
		
        String condition_id = this.mapper.getNodeID(node.getExpression());
		
        String then_block_id = this.mapper.getNodeID(node.getThenStatement());
		
        String else_block_id = this.mapper.getNodeID(node.getElseStatement());
        
        String[] args = new String[]{ id, parent_id, condition_id, then_block_id, else_block_id };

        this.code.addFact(IF_STATEMENT, this.generateArgs(IF_STATEMENT_KEYS,args));

		return true;
	}

	public boolean visit(ImportDeclaration node) {
		String id = this.mapper.getNodeID(node);
    	
    	String parent_id = this.mapper.getNodeID(node.getParent());
    	
    	String name = this.mapper.getNodeID(node.getName());
    	
    	String is_on_demand = Boolean.toString(node.isOnDemand());
    	
    	String is_static = Boolean.toString(node.isStatic());
        
        String[] args = new String[]{ id, parent_id, name, is_on_demand, is_static };
        
        this.code.addFact(IMPORT_DECLARATION, this.generateArgs(IMPORT_DECLARATION_KEYS,args));
  
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(InfixExpression node) {
		String id = this.mapper.getNodeID(node);
		
		String parent_id = this.mapper.getNodeID(node.getParent());
		
		String operator = this.quote(this.operator(node.getOperator().toString()));
		
		String left_operand = this.mapper.getNodeID(node.getLeftOperand());
		
		String right_operand = this.mapper.getNodeID(node.getRightOperand());

		String extended_operands_ids = this.generateList(node.extendedOperands());

		String[] args = new String[] { id, parent_id, operator, left_operand, right_operand, extended_operands_ids };

		this.code.addFact(INFIX_EXPRESSION, this.generateArgs(INFIX_EXPRESSION_KEYS, args));

		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(Initializer node) {
		String id = this.mapper.getNodeID(node);
    	
    	String parent_id = this.mapper.getNodeID(node.getParent());
    	
		String modifiers = this.generateList(node.modifiers());
		
        String body_id = this.mapper.getNodeID(node.getBody());
    	
        String[] args = new String[]{ id, parent_id, modifiers, body_id };
        
        this.code.addFact(INITIALIZER, this.generateArgs(INITIALIZER_KEYS,args));
   
		return true;
	}

	public boolean visit(InstanceofExpression node) {
		String id = this.mapper.getNodeID(node);
    
    	String parent_id = this.mapper.getNodeID(node.getParent());
    	
    	String expression_id = this.mapper.getNodeID(node.getLeftOperand());
		
		String type_id = this.mapper.getNodeID(node.getRightOperand());

        String[] args = new String[]{ id, parent_id, expression_id, type_id };
        
        this.code.addFact(INSTANCE_OF_EXPRESSION, this.generateArgs(INSTANCE_OF_EXPRESSION_KEYS,args));
 
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(IntersectionType node) {
		String id = this.mapper.getNodeID(node);
		
    	String types_ids = this.generateList(node.types());
             
    	String[] args = new String[]{ id, types_ids };
        
    	this.code.addFact(INTERSECTION_TYPE, this.generateArgs(INTERSECTION_TYPE_KEYS,args));        
  
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(Javadoc node) {
		String id = this.mapper.getNodeID(node);
		
		String parent_id = this.mapper.getNodeID(node.getParent());
		
		String tags = this.generateList(node.tags());
		
		String[] args = new String[] { id, parent_id, tags };
		
		this.code.addFact(JAVADOC, this.generateArgs(JAVADOC_KEYS, args));
	
		return true;
	}

	public boolean visit(LabeledStatement node) {
		String id = this.mapper.getNodeID(node);
		
		String parent_id = this.mapper.getNodeID(node.getParent());
		
		String name = this.mapper.getNodeID(node.getLabel());
		
		String body_id = this.mapper.getNodeID(node.getBody());
		
		String[] args = new String[] { id, parent_id, name, body_id };

		this.code.addFact(LABELED_STATEMENT, this.generateArgs(LABELED_STATEMENT_KEYS, args));

		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(LambdaExpression node) {
	  	String id = this.mapper.getNodeID(node);
    	
    	String parent_id = this.mapper.getNodeID(node.getParent());
    	
    	String body_id = this.mapper.getNodeID(node.getBody());
        
    	String parameters_ids = this.generateList(node.parameters()); 
        
        String[] args = new String[]{ id, parent_id, parameters_ids, body_id };
        
        this.code.addFact(LAMBDA_EXPRESSION, this.generateArgs(LAMBDA_EXPRESSION_KEYS,args));
  
		return true;
	}

	public boolean visit(LineComment node) {
	 	String id = this.mapper.getNodeID(node);

		String parent_id = this.mapper.getNodeID(node.getParent());

		String[] args = new String[] { id, parent_id };
	
		this.code.addFact(LINE_COMMENT, this.generateArgs(LINE_COMMENT_KEYS, args));
	 
		return true;
	}

	public boolean visit(MarkerAnnotation node) {
		// TODO
		return true;
	}

	public boolean visit(MemberRef node) {
		// TODO
		return true;
	}

	public boolean visit(MemberValuePair node) {
		// TODO
		return true;
	}

	public boolean visit(MethodRef node) {
		// TODO
		return true;
	}

	public boolean visit(MethodRefParameter node) {
		// TODO
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(MethodDeclaration node) {
		String id = this.mapper.getNodeID(node);
		
		String parent_id = this.mapper.getNodeID(node.getParent());
		
		String fact_name = node.isConstructor() ? CONSTRUCTOR_DECLARATION : METHOD_DECLARATION;

		String name = this.mapper.getNodeID(node.getName());
		
		String identifier = this.quote(node.getName().toString());

		String parameters_ids = this.generateList(node.parameters());
			
		String parameters_types_ids = this.generateList(node.typeParameters());
		
		String modifiers = this.generateList(node.modifiers());
		
		String body_id = this.mapper.getNodeID(node.getBody());
		
		String return_type = this.mapper.getNodeID(node.getReturnType2());
		
		if (node.isConstructor())
			return_type = this.mapper.getNodeID(node.getParent());
				
		String extra_dimensions = Integer.toString(node.getExtraDimensions());
		
		String dimensions_ids = this.generateList(node.extraDimensions());
		
		String exceptions_ids = this.generateList(node.thrownExceptionTypes());
		
		String[] args = new String[] { id, parent_id, identifier, name, modifiers, parameters_ids, parameters_types_ids, return_type,extra_dimensions, dimensions_ids, body_id, exceptions_ids };
		
		this.code.addFact(fact_name, this.generateArgs(METHOD_DECLARATION_KEYS, args));

		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(MethodInvocation node) {
		String id = this.mapper.getNodeID(node);

		String parent_id = this.mapper.getNodeID(node.getParent());
		
		String expression_id = this.mapper.getNodeID(node.getExpression());
		
		String method = this.mapper.getNodeID(node.getName());
		
		String arguments_ids = this.generateList(node.arguments());

		String arguments_types = this.generateList(node.typeArguments());
		
		String[] args = new String[] { id, parent_id, expression_id, method, arguments_ids, arguments_types };
		
		this.code.addFact(METHOD_INVOCATION, this.generateArgs(METHOD_INVOCATION_KEYS, args));

		return true;
	}

	public boolean visit(Modifier node) {
		String id = this.mapper.getNodeID(node);
		
		String parent = this.mapper.getNodeID(node.getParent());
		
		String keyword = this.quote(node.getKeyword().toString());
		
		String[] args = new String[] { id, parent, keyword };
		
		this.code.addFact(MODIFIER, this.generateArgs(MODIFIER_KEYS, args));

		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(NameQualifiedType node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());
				
		String name = this.mapper.getNodeID(node.getName());

		String qualifier = this.mapper.getNodeID(node.getQualifier());

		String anotations = this.generateList(node.annotations());
				
		String[] args = new String[] { id, parent, name, qualifier, anotations };

		this.code.addFact(NAME_QUALIFIED_TYPE, this.generateArgs(NAME_QUALIFIED_TYPE_KEYS, args));
			
		return true;
	}

	public boolean visit(NormalAnnotation node) {
		// TODO
		return true;
	}

	public boolean visit(NullLiteral node) {
		String id = this.mapper.getNodeID(node);

		String parent_id = this.mapper.getNodeID(node.getParent());

		String[] args = new String[] { id, parent_id };
		
		this.code.addFact(NULL_LITERAL, this.generateArgs(NULL_LITERAL_KEYS, args));
			
		return true;
	}

	public boolean visit(NumberLiteral node) {
		String id = this.mapper.getNodeID(node);

		String parent_id = this.mapper.getNodeID(node.getParent());

		String value = this.quote(node.getToken());
		
		String[] args = new String[] { id, parent_id, value };
		
		this.code.addFact(NUMBER_LITERAL, this.generateArgs(NUMBER_LITERAL_KEYS, args));
	
		return true;
	}

	public boolean visit(PackageDeclaration node) {
		String id = this.mapper.getNodeID(node);

		String name = this.mapper.getNodeID(node.getName());
		
		String[] args = new String[] { id, name };
		
		this.code.addFact(PACKAGE_DECLARATION, this.generateArgs(PACKAGE_DECLARATION_KEYS, args));

		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(ParameterizedType node) {
		String id = this.mapper.getNodeID(node);

		String name = this.quote(node.resolveBinding().getTypeDeclaration().getName());
		
		String fqn = this.quote(node.resolveBinding().getTypeDeclaration().getQualifiedName());
		
		String arguments_ids = this.generateList(node.typeArguments());

		String[] args = new String[] { id, name, fqn, arguments_ids };
		
		this.code.addFact(PARAMETERIZED_TYPE, this.generateArgs(PARAMETERIZED_TYPE_KEYS, args));

		return true;
	}

	public boolean visit(ParenthesizedExpression node) {
		String id = this.mapper.getNodeID(node);
		
		String parent = this.mapper.getNodeID(node.getParent());
		
		String expression = this.mapper.getNodeID(node.getExpression());
		
		String[] args = new String[] { id, parent, expression };
		
		this.code.addFact(PARENTHESIZED_EXPRESSION, this.generateArgs(PARENTHESIZED_EXPRESSION_KEYS, args));
 
		return true;
	}

	public boolean visit(PostfixExpression node) {
		String id = this.mapper.getNodeID(node);
		
		String parent_id = this.mapper.getNodeID(node.getParent());
    	
    	String operator = this.quote(this.operator(node.getOperator().toString()));
		
        String operand_id = this.mapper.getNodeID(node.getOperand());

        String[] args = new String[]{ id, parent_id, operator, operand_id };

        this.code.addFact(POSTFIX_EXPRESSION, this.generateArgs(POSTFIX_EXPRESSION_KEYS,args));
  
		return true;
	}

	public boolean visit(PrefixExpression node) {
		String id = this.mapper.getNodeID(node);
		
		String parent_id = this.mapper.getNodeID(node.getParent());
		
		String operator = this.quote(this.operator(node.getOperator().toString()));
		
		String operand_id = this.mapper.getNodeID(node.getOperand());
		
		String[] args = new String[] { id, parent_id, operator, operand_id };
		
		this.code.addFact(PREFIX_EXPRESSION, this.generateArgs(PREFIX_EXPRESSION_KEYS, args));
	
		return true;
	}

	public boolean visit(PrimitiveType node) {
		String id = this.mapper.getNodeID(node);
		
		String type = this.quote(node.getPrimitiveTypeCode().toString());
		
		String[] args = new String[] { id, type };

		this.code.addFact(PRIMITIVE_TYPE, this.generateArgs(PRIMITIVE_TYPE_KEYS, args));
	
		return true;
	}

	public boolean visit(QualifiedName node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());
		
		String fqn = this.quote(node.getFullyQualifiedName());
		
		String qualifier = this.mapper.getNodeID(node.getQualifier());
		
		String name = this.mapper.getNodeID(node.getName());

		String[] args = new String[] { id, parent, fqn, qualifier, name };

		this.code.addFact(QUALIFIED_NAME, this.generateArgs(QUALIFIED_NAME_KEYS, args));
	
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(QualifiedType node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());
		
		String name = this.mapper.getNodeID(node.getName());
		
		String qualifier = this.mapper.getNodeID(node.getQualifier());
		
		String anotations = this.generateList(node.annotations());

		String[] args = new String[] { id, parent, name, qualifier, anotations };
		
		this.code.addFact(QUALIFIED_TYPE, this.generateArgs(QUALIFIED_TYPE_KEYS, args));
	
		return true;
	}

	public boolean visit(ReturnStatement node) {
		String id = this.mapper.getNodeID(node);
    	
    	String parent_id = this.mapper.getNodeID(node.getParent());
    	
    	String expression_id = this.mapper.getNodeID(node.getExpression());
        
        String[] args = new String[]{ id, parent_id, expression_id };
        
        this.code.addFact(RETURN_STATEMENT, this.generateArgs(RETURN_STATEMENT_KEYS,args));

		return true;
	}

	public boolean visit(SimpleName node) {
		String id = this.mapper.getNodeID(node);
		
		String parent = this.mapper.getNodeID(node.getParent());
		
		String name = this.quote(node.getIdentifier());
		
		String bind = this.mapper.getBindingID(node.resolveBinding());
		
		if (bind == null && node.resolveBinding() != null) {
			bind = this.mapper.getID();
			this.mapper.setBindingID(node.resolveBinding(), bind);
			String kind = KINDS[node.resolveBinding().getKind()-1];
			String[] external_args = new String[] { bind, this.quote(node.getIdentifier()) };
			
			this.code.addFact(kind, this.generateArgs(EXTERNAL_KEYS, external_args));
		}
		
		String[] args = new String[] { id, parent, name, bind };
		
		this.code.addFact(SIMPLE_NAME, this.generateArgs(SIMPLE_NAME_KEYS, args));
		
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(SimpleType node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());
		
		String name = this.mapper.getNodeID(node.getName());
		
		String annotations = this.generateList(node.annotations());

		String[] args = new String[] { id, parent, name, annotations };
		
		this.code.addFact(SIMPLE_TYPE, this.generateArgs(SIMPLE_TYPE_KEYS, args));
	
		return true;
	}

	public boolean visit(SingleMemberAnnotation node) {
		// TODO
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(SingleVariableDeclaration node) {
		String id = this.mapper.getNodeID(node);
		
		String parent_id = this.mapper.getNodeID(node.getParent());
		
		String name = this.quote(node.getName().toString());
		
		String type_id = this.mapper.getNodeID(node.getType());

		String modifiers = this.generateList(node.modifiers());
		
		String extra_dimensions = Integer.toString(node.getExtraDimensions());

		String dimensions_ids = this.generateList(node.extraDimensions());
		
		String initializer = this.mapper.getNodeID(node.getInitializer());
		
		String[] args = new String[] { id, parent_id, name, type_id, modifiers, extra_dimensions, dimensions_ids, initializer };

		this.code.addFact(SINGLE_VARIABLE_DECLARATION, this.generateArgs(SINGLE_VARIABLE_DECLARATION_KEYS, args));

		return true;
	}

	public boolean visit(StringLiteral node) {
		String id = this.mapper.getNodeID(node);

		String parent_id = this.mapper.getNodeID(node.getParent());

		String value = this.quote(node.getEscapedValue());

		String[] args = new String[] { id, parent_id, value };
		
		this.code.addFact(STRING_LITERAL, this.generateArgs(STRING_LITERAL_KEYS, args));

		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(SuperConstructorInvocation node) {
		String id = this.mapper.getNodeID(node);
		
		String parent = this.mapper.getNodeID(node.getParent());
		
		String expression = this.mapper.getNodeID(node.getExpression());
		
		String bind = this.mapper.getBindingID(node.resolveConstructorBinding());

		String arguments_ids = this.generateList(node.arguments());
		
		String arguments_types = this.generateList(node.typeArguments());
		
		String[] args = new String[] { id, parent, expression, bind, arguments_ids, arguments_types };

		this.code.addFact(SUPER_CONSTRUCTOR_INVOCATION, this.generateArgs(SUPER_CONSTRUCTOR_INVOCATION_KEYS, args));
    
		return true;
	}

	public boolean visit(SuperFieldAccess node) {
		String id = this.mapper.getNodeID(node);

		String parent_id = this.mapper.getNodeID(node.getParent());

		String bind = this.mapper.getNodeID(node.getName());
				
		String[] args = new String[] { id, parent_id, bind };
		
		this.code.addFact(SUPER_FIELD_ACCESS, this.generateArgs(SUPER_FIELD_ACCESS_KEYS, args));

		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(SuperMethodInvocation node) {
		String id = this.mapper.getNodeID(node);

		String parent_id = this.mapper.getNodeID(node.getParent());

		String name = this.mapper.getNodeID(node.getName());
		
		String arguments_ids = this.generateList(node.arguments());
				
		String[] args = new String[] { id, parent_id, name, arguments_ids };
		
		this.code.addFact(SUPER_METHOD_INVOCATION, this.generateArgs(SUPER_METHOD_INVOCATION_KEYS, args));

		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(SuperMethodReference node) {
		String id = this.mapper.getNodeID(node);

		String parent_id = this.mapper.getNodeID(node.getParent());

		String name = this.mapper.getNodeID(node.getName());
				
		String qualifier = this.mapper.getNodeID(node.getQualifier());

		String arguments_types = this.generateList(node.typeArguments());

		String[] args = new String[] { id, parent_id, name, qualifier, arguments_types};
		
		this.code.addFact(SUPER_METHOD_REFERENCE, this.generateArgs(SUPER_METHOD_REFERENCE_KEYS, args));

		return true;
	}

	public boolean visit(SwitchCase node) {
		String id = this.mapper.getNodeID(node);
    	
    	String parent_id = this.mapper.getNodeID(node.getParent());
    
    	String expression_id = this.mapper.getNodeID(node.getExpression());
        
        String[] args = new String[]{ id, parent_id, expression_id };
        
        this.code.addFact(SWITCH_CASE, this.generateArgs(SWITCH_CASE_KEYS,args));

		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(SwitchStatement node) {
	 	String id = this.mapper.getNodeID(node);
    	
    	String parent_id = this.mapper.getNodeID(node.getParent());
    
    	String expression_id = this.mapper.getNodeID(node.getExpression());
    	
        String statements_ids = this.generateList(node.statements()); 
                
        String[] args = new String[]{ id, parent_id, expression_id, statements_ids };
        
        this.code.addFact(SWITCH_STATEMENT, this.generateArgs(SWITCH_STATEMENT_KEYS,args));
 
		return true;
	}

	public boolean visit(SynchronizedStatement node) {
		String id = this.mapper.getNodeID(node);

		String parent_id = this.mapper.getNodeID(node.getParent());

		String expression_id = this.mapper.getNodeID(node.getExpression());

		String block_id = this.mapper.getNodeID(node.getBody());

		String[] args = new String[] { id, parent_id, expression_id, block_id };

		this.code.addFact(SYNCHRONIZED_STATEMENT, this.generateArgs(SYNCHRONIZED_STATEMENT_KEYS, args));

		return true;
	}

	public boolean visit(TagElement node) {
		// TODO
		return true;
	}

	public boolean visit(TextElement node) {
		// TODO
		return true;
	}

	public boolean visit(ThisExpression node) {
		String id = this.mapper.getNodeID(node);

		String parent = this.mapper.getNodeID(node.getParent());

		String fqn = this.mapper.getNodeID(node.getQualifier());
		
		String[] args = new String[] { id, parent, fqn };
		
		this.code.addFact(THIS_EXPRESSION, this.generateArgs(THIS_EXPRESSION_KEYS, args));
	
		return true;
	}

	public boolean visit(ThrowStatement node) {
    	String id = this.mapper.getNodeID(node);
		
		String parent_id = this.mapper.getNodeID(node.getParent());
    	
        String expression_id = this.mapper.getNodeID(node.getExpression());
        
        String[] args = new String[]{ id, parent_id, expression_id };
        
        this.code.addFact(THROW_STATEMENT, this.generateArgs(THROW_STATEMENT_KEYS,args));
  
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(TryStatement node) {
		String id = this.mapper.getNodeID(node);

		String parent_id = this.mapper.getNodeID(node.getParent());

		String block_id = this.mapper.getNodeID(node.getBody());

		String finally_block_id = this.mapper.getNodeID(node.getFinally());

		String resources_ids = this.generateList(node.resources());

		String catchs_ids = this.generateList(node.catchClauses());

		String[] args = new String[] { id, parent_id, resources_ids, block_id, catchs_ids, finally_block_id };
		
		this.code.addFact(TRY_STATEMENT, this.generateArgs(TRY_STATEMENT_KEYS, args));

		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(TypeDeclaration node) {
		String id = this.mapper.getNodeID(node);

		String parent_id = this.mapper.getNodeID(node.getParent());
		
		String name = this.mapper.getNodeID(node.getName());
		
		String identifier = this.quote(node.resolveBinding().getQualifiedName());
		
		String parameters_types_ids = this.generateList(node.typeParameters());

		String super_type = this.mapper.getNodeID(node.getSuperclassType());
	
		String modifiers = this.generateList(node.modifiers());
		
		String implements_ids = this.generateList(node.superInterfaceTypes());

		String declarations_ids = this.generateList(node.bodyDeclarations());

		Vector<ASTNode> fields_nodes = new Vector<ASTNode>();
		fields_nodes.addAll(Arrays.asList(node.getFields()));
		String fields_ids = this.generateList(fields_nodes);

		Vector<ASTNode> methods_nodes = new Vector<ASTNode>();
		methods_nodes.addAll(Arrays.asList(node.getMethods()));
		String methods_ids = this.generateList(methods_nodes);
		
		String[] args = null;
		
		String[] keys = node.isInterface() ? INTERFACE_DECLARATION_KEYS : CLASS_DECLARATION_KEYS;
		
		String fact_name = node.isInterface() ? INTERFACE_DECLARATION : CLASS_DECLARATION;
		
		if (node.isInterface())
			args = new String[] { id, parent_id, identifier, name, parameters_types_ids, modifiers, implements_ids, fields_ids, methods_ids, declarations_ids };
		else 
			args = new String[] { id, parent_id, identifier, name, parameters_types_ids, modifiers,super_type, implements_ids, fields_ids, methods_ids, declarations_ids };
		
		this.code.addFact(fact_name, generateArgs(keys, args));
	
		return true;
	}

	
	public boolean visit(TypeDeclarationStatement node) {
		String id = this.mapper.getNodeID(node);
		
		String parent = this.mapper.getNodeID(node.getParent());
		
		String expression = this.mapper.getNodeID(node.getDeclaration());
		
		String[] args = new String[] { id, parent, expression };
		
		this.code.addFact(TYPE_DECLARATION_STATEMENT, this.generateArgs(TYPE_DECLARATION_STATEMENT_KEYS, args));
 	
		return true;
	}

	public boolean visit(TypeLiteral node) {
		String id = this.mapper.getNodeID(node);
		
		String parent_id = this.mapper.getNodeID(node.getParent());
		
		String type_id = this.mapper.getNodeID(node.getType());

		String[] args = new String[] { id, parent_id, type_id };
		
		this.code.addFact(TYPE_LITERAL, this.generateArgs(TYPE_LITERAL_KEYS, args));

		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(TypeMethodReference node) {
		String id = this.mapper.getNodeID(node);

		String parent_id = this.mapper.getNodeID(node.getParent());
		
		String name = this.mapper.getNodeID(node.getName());
		
		String type_id = this.mapper.getNodeID(node.getType());
		
		String arguments_types = this.generateList(node.typeArguments());

		String[] args = new String[] { id, parent_id, type_id, name, arguments_types};
		
		this.code.addFact(TYPE_METHOD_REFERENCE, this.generateArgs(TYPE_METHOD_REFERENCE_KEYS, args));

		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(TypeParameter node) {
		String id = this.mapper.getNodeID(node);
		
		String parent = this.mapper.getNodeID(node.getParent());
		
		String name = this.mapper.getNodeID(node.getName());
		
		String modifiers = this.generateList(node.modifiers());
		
		String types_bounds_ids = this.generateList(node.typeBounds());
		
		String[] args = new String[] { id, parent, name, modifiers, types_bounds_ids };

		this.code.addFact(TYPE_PARAMETER, this.generateArgs(TYPE_PARAMETER_KEYS, args));

		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(UnionType node) {
		String id = this.mapper.getNodeID(node);

		String types_ids = this.generateList(node.types());
		             
		String[] args = new String[]{ id, types_ids };
		
		this.code.addFact(UNION_TYPE, this.generateArgs(UNION_TYPE_KEYS,args));
		   
		return true;
	}

	
	@SuppressWarnings("unchecked")
	public boolean visit(VariableDeclarationExpression node) {
		String id = this.mapper.getNodeID(node);
		
		String parent = this.mapper.getNodeID(node.getParent());

		String type = this.mapper.getNodeID(node.getType());

		String modifiers = this.generateList(node.modifiers());

		String fragments = this.generateList(node.fragments());

		String[] args = new String[] { id, parent, modifiers, type, fragments };
		
		this.code.addFact(VARIABLE_DECLARATION_EXPRESSION, this.generateArgs(VARIABLE_DECLARATION_EXPRESSION_KEYS, args));

		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(VariableDeclarationStatement node) {
		String id = this.mapper.getNodeID(node);
		
		String parent = this.mapper.getNodeID(node.getParent());
		
		String type = this.mapper.getNodeID(node.getType());
		
		String modifiers = this.generateList(node.modifiers());

		String fragments = this.generateList(node.fragments());

		String[] args = new String[] { id, parent, modifiers, type, fragments };
		
		this.code.addFact(VARIABLE_DECLARATION_STATEMENT, this.generateArgs(VARIABLE_DECLARATION_STATEMENT_KEYS, args));
	
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(VariableDeclarationFragment node) {
		String id = this.mapper.getNodeID(node);
    	
    	String parent = this.mapper.getNodeID(node.getParent());
    	
		String name = this.quote(node.getName().toString());

		String extra_dimensions = Integer.toString(node.getExtraDimensions());
		
		String initializer = this.mapper.getNodeID(node.getInitializer());
		
		String dimensions_ids = this.generateList(node.extraDimensions());

		String[] args = new String[] { id, parent, name, extra_dimensions, dimensions_ids, initializer };
		
		this.code.addFact(VARIABLE_DECLARATION_FRAGMENT, this.generateArgs(VARIABLE_DECLARATION_FRAGMENT_KEYS, args));
  		
		return true;
	}

	public boolean visit(WhileStatement node) {
		String id = this.mapper.getNodeID(node);
		
		String parent_id = this.mapper.getNodeID(node.getParent());
				
		String condition_id = this.mapper.getNodeID(node.getExpression());

		String block_id = this.mapper.getNodeID(node.getBody());

		String[] args = new String[] { id, parent_id, condition_id, block_id };
		
		this.code.addFact(WHILE_STATEMENT, this.generateArgs(WHILE_STATEMENT_KEYS, args));
		
		return true;
	}

	public boolean visit(WildcardType node) {
		String id = this.mapper.getNodeID(node);

		String bound_id = this.mapper.getNodeID(node.getBound());

		String is_upper_bound = Boolean.toString(node.isUpperBound());

		String[] args = new String[] { id, bound_id, is_upper_bound };
		
		this.code.addFact(WILDCARD_TYPE, this.generateArgs(WILDCARD_TYPE_KEYS, args));
	
		return true;
	}
	
	
	private String generateList(List<ASTNode> nodes) {
		String out = "[";
		if (nodes != null && !nodes.isEmpty()) {
			for (ASTNode node : nodes)
				out += this.mapper.getNodeID(node) + ",";
			out = out.substring(0, out.length() - 1);
		}
		out += "]";
		return out;
	}
	
	
	private String quote(String str) {
		str = Normalizer.normalize(str, Normalizer.Form.NFD);
		str = str.replaceAll("[^\\x00-\\x7F]", "");
		return "'" + str.replace("'", "") + "'";
	}
	
	private String operator(String operator) {
		switch (operator) {
		case "=":
			return "ASSIGN";
		case "+=":
			return "PLUS_ASSIGN";
		case "-=":
			return "MINUS_ASSIGN";
		case "*=":
			return "TIMES_ASSIGN";
		case "/=":
			return "DIVIDE_ASSIGN";
		case "&=":
			return "BIT_AND_ASSIGN";
		case "|=":
			return "BIT_OR_ASSIGN";
		case "^=":
			return "BIT_XOR_ASSIGN";
		case "%=":
			return "REMAINDER_ASSIGN";
		case "<<=":
			return "LEFT_SHIFT_ASSIGN";
		case ">>=":
			return "RIGHT_SHIFT_SIGNED_ASSIGN";
		case ">>>=":
			return "RIGHT_SHIFT_UNSIGNED_ASSIGN";
		case "*":
			return "TIMES";
		case "/":
			return "DIVIDE";
		case "%":
			return "REMAINDER";
		case "+":
			return "PLUS";
		case "-":
			return "MINUS";
		case "<<":
			return "LEFT_SHIFT";
		case ">>":
			return "RIGHT_SHIFT_SIGNED";
		case ">>>":
			return "RIGHT_SHIFT_UNSIGNED";
		case "<":
			return "LESS";
		case ">":
			return "GREATER";
		case "<=":
			return "LESS_EQUALS";
		case ">=":
			return "GREATER_EQUALS";
		case "==":
			return "EQUALS";
		case "!=":
			return "NOT_EQUALS";
		case "^":
			return "XOR";
		case "&":
			return "AND";
		case "|":
			return "OR";
		case "&&":
			return "CONDITIONAL_AND";
		case "||":
			return "CONDITIONAL_OR";
		case "++":
			return "INCREMENT";
		case "--":
			return "DECREMENT";
		case "~":
			return "COMPLEMENT";
		case "!":
			return "NOT";
		}
		return null;
	}
	
	
	private String[] generateArgs(String[] keys, String[] values) {
		String[] out = new String[keys.length];
		for (int i = 0; i < out.length; i++) {
			if (keys[i] != null)
				out[i] = keys[i] + "(" + values[i] + ")";
			else
				out[i] = values[i];
		}
		return out;
	}
}
