
package soploon.modeler.converters;

import java.util.HashMap;

import org.eclipse.jdt.core.dom.ASTNode;

import soploon.modeler.Mapper;
import soploon.modeler.PrologCode;

public class NodeConverterFactory {

	private Mapper mapper;
	private PrologCode code;
	@SuppressWarnings("rawtypes")
	private HashMap<Integer, NodeConverter> converters;
	private EmptyConverter empty_converter;
	
	@SuppressWarnings("rawtypes")
	public NodeConverterFactory(Mapper mapper, PrologCode code) {
		this.mapper = mapper;
		this.code = code;
		this.converters = new HashMap<Integer, NodeConverter>();
		this.empty_converter = new EmptyConverter(mapper,code,this);
	}

	@SuppressWarnings("rawtypes")
	public NodeConverter getConverter(ASTNode node) {
		if (node == null)
			return this.empty_converter;
		
		Integer type = node.getNodeType();

		NodeConverter converter = this.converters.get(type);
		if (converter != null)
			return converter;

		switch (type) {
			case ASTNode.ANONYMOUS_CLASS_DECLARATION: {
				converter = new AnonymousClassDeclarationPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.ARRAY_ACCESS: {
				converter = new ArrayAccessPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.ARRAY_CREATION: {
				converter = new ArrayCreationPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.ARRAY_INITIALIZER: {
				converter = new ArrayInitializerPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.ARRAY_TYPE: {
				converter = new ArrayTypePrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.ASSERT_STATEMENT: {
				converter = new AssertStatementPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.ASSIGNMENT: {
				converter = new AssignmentPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.BLOCK: {
				converter = new BlockPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.BOOLEAN_LITERAL: {
				converter = new BooleanLiteralPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.BREAK_STATEMENT: {
				converter = new BreakStatementPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.CAST_EXPRESSION: {
				converter = new CastExpressionPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.CATCH_CLAUSE: {
				converter = new CatchClausePrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.CHARACTER_LITERAL: {
				converter = new CharacterLiteralPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.CLASS_INSTANCE_CREATION: {
				converter = new ClassInstanceCreationPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.COMPILATION_UNIT: {
				converter = new CompilationUnitPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.CONDITIONAL_EXPRESSION: {
				converter = new ConditionalExpressionPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.CONSTRUCTOR_INVOCATION: {
				converter = new ConstructorInvocationPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.CONTINUE_STATEMENT: {
				converter = new ContinueStatementPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.DO_STATEMENT: {
				converter = new DoStatementPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.EMPTY_STATEMENT: {
				converter = new EmptyStatementConverter(mapper, code, this);
				break;
			}
			case ASTNode.EXPRESSION_STATEMENT: {
				converter = new ExpressionStatementPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.FIELD_ACCESS: {
				converter = new FieldAccessPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.FIELD_DECLARATION: {
				converter = new FieldDeclarationPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.FOR_STATEMENT: {
				converter = new ForStatementPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.IF_STATEMENT: {
				converter = new IfStatementPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.IMPORT_DECLARATION: {
				converter = new ImportDeclarationPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.INFIX_EXPRESSION: {
				converter = new InfixExpressionPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.INITIALIZER: {
				converter = new InitializerPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.JAVADOC: {
				converter = new EmptyConverter(mapper, code, this);
				break;
			}
			case ASTNode.LABELED_STATEMENT: {
				converter = new LabeledStatementPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.METHOD_DECLARATION: {
				converter = new MethodDeclarationPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.METHOD_INVOCATION: {
				converter = new MethodInvocationPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.NULL_LITERAL: {
				converter = new NullLiteralPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.NUMBER_LITERAL: {
				converter = new NumberLiteralPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.PACKAGE_DECLARATION: {
				converter = new PackageDeclarationPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.PARENTHESIZED_EXPRESSION: {
				converter = new ParenthesizedExpressionPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.POSTFIX_EXPRESSION: {
				converter = new PostfixExpressionPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.PREFIX_EXPRESSION: {
				converter = new PrefixExpressionPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.PRIMITIVE_TYPE: {
				converter = new PrimitiveTypePrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.QUALIFIED_NAME: {
				converter = new QualifiedNameConverter(mapper, code, this);
				break;
			}
			case ASTNode.RETURN_STATEMENT: {
				converter = new ReturnStatementPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.SIMPLE_NAME: {
				converter = new SimpleNamePrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.SIMPLE_TYPE: {
				converter = new AnnotatableTypePrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.SINGLE_VARIABLE_DECLARATION: {
				converter = new SingleVariableDeclarationPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.STRING_LITERAL: {
				converter = new StringLiteralPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.SUPER_CONSTRUCTOR_INVOCATION: {
				converter = new SuperConstructorInvocationPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.SUPER_FIELD_ACCESS: {
				converter = new SuperFieldAccessPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.SUPER_METHOD_INVOCATION: {
				converter = new SuperMethodInvocationPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.SWITCH_CASE: {
				converter = new SwitchCasePrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.SWITCH_STATEMENT: {
				converter = new SwitchStatementPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.SYNCHRONIZED_STATEMENT: {
				converter = new SynchronizedStatementPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.THIS_EXPRESSION: {
				converter = new ThisExpressionPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.THROW_STATEMENT: {
				converter = new ThrowStatementPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.TRY_STATEMENT: {
				converter = new TryStatementPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.TYPE_DECLARATION: {
				converter = new TypeDeclarationPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.TYPE_DECLARATION_STATEMENT: {
				converter = new TypeDeclarationStatementPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.TYPE_LITERAL: {
				converter = new TypeLiteralPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.VARIABLE_DECLARATION_EXPRESSION: {
				converter = new VariableDeclarationExpressionPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.VARIABLE_DECLARATION_FRAGMENT: {
				converter = new VariableDeclarationFragmentPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.VARIABLE_DECLARATION_STATEMENT: {
				converter = new VariableDeclarationStatementPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.WHILE_STATEMENT: {
				converter = new WhileStatementPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.INSTANCEOF_EXPRESSION: {
				converter = new InstanceofExpressionPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.LINE_COMMENT: {
				converter = new EmptyConverter(mapper, code, this);
				break;
			}
			case ASTNode.BLOCK_COMMENT: {
				converter = new EmptyConverter(mapper, code, this);
				break;
			}
			case ASTNode.TAG_ELEMENT: {
				converter = new EmptyConverter(mapper, code, this);
				break;
			}
			case ASTNode.TEXT_ELEMENT: {
				converter = new EmptyConverter(mapper, code, this);
				break;
			}
			case ASTNode.MEMBER_REF: {
				converter = new EmptyConverter(mapper, code, this);
				break;
			}
			case ASTNode.METHOD_REF: {
				converter = new EmptyConverter(mapper, code, this);
				break;
			}
			case ASTNode.METHOD_REF_PARAMETER: {
				converter = new EmptyConverter(mapper, code, this);
				break;
			}
			case ASTNode.ENHANCED_FOR_STATEMENT: {
				converter = new EnhancedForStatementPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.ENUM_DECLARATION: {
				converter = new EnumDeclarationPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.ENUM_CONSTANT_DECLARATION: {
				converter = new EnumConstantDeclarationPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.TYPE_PARAMETER: {
				converter = new TypeParameterPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.PARAMETERIZED_TYPE: {
				converter = new ParameterizedTypePrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.QUALIFIED_TYPE: {
				converter = new AnnotatableTypePrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.WILDCARD_TYPE: {
				converter = new WildcardTypePrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.NORMAL_ANNOTATION: {
				converter = new EmptyConverter(mapper, code, this);
				break;
			}
			case ASTNode.MARKER_ANNOTATION: {
				converter = new EmptyConverter(mapper, code, this);
				break;
			}
			case ASTNode.SINGLE_MEMBER_ANNOTATION: {
				converter = new EmptyConverter(mapper, code, this);
				break;
			}
			case ASTNode.MEMBER_VALUE_PAIR: {
				converter = new EmptyConverter(mapper, code, this);
				break;
			}
			case ASTNode.ANNOTATION_TYPE_DECLARATION: {
				converter = new EmptyConverter(mapper, code, this);
				break;
			}
			case ASTNode.ANNOTATION_TYPE_MEMBER_DECLARATION: {
				converter = new EmptyConverter(mapper, code, this);
				break;
			}
			case ASTNode.MODIFIER: {
				converter = new EmptyConverter(mapper, code, this);
				break;
			}
			case ASTNode.UNION_TYPE: {
				converter = new UnionTypePrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.DIMENSION: {
				converter = new EmptyConverter(mapper, code, this);
				break;
			}
			case ASTNode.LAMBDA_EXPRESSION: {
				converter = new LambdaExpressionPrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.INTERSECTION_TYPE: {
				converter = new IntersectionTypePrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.NAME_QUALIFIED_TYPE: {
				converter = new AnnotatableTypePrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.CREATION_REFERENCE: {
				converter = new CreationReferencePrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.EXPRESSION_METHOD_REFERENCE: {
				converter = new ExpressionMethodReferencePrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.SUPER_METHOD_REFERENCE: {
				converter = new SuperMethodReferencePrologConverter(mapper, code, this);
				break;
			}
			case ASTNode.TYPE_METHOD_REFERENCE: {
				converter = new TypeMethodReferencePrologConverter(mapper, code, this);
				break;
			}
			default: {
				converter = new EmptyConverter(mapper, code, this);
				break;
			}
		}

		this.converters.put(node.getNodeType(), converter);

		return converter;

	}
}
