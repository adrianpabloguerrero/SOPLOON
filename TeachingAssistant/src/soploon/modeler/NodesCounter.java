package soploon.modeler;

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

public class NodesCounter extends ASTVisitor {

	private int count;

	public NodesCounter() {
		this.count = 0;
	}
	
	public int getCount() {
		return count;
	}
	
	public boolean visit(AnnotationTypeDeclaration node) {	
		count++;
		return true;
	}

	public boolean visit(AnnotationTypeMemberDeclaration node) {
		count++;
		return true;
	}

	public boolean visit(AnonymousClassDeclaration node) {
		count++;
		return true;
	}

	public boolean visit(ArrayAccess node) {
		count++;
		return true;
	}

	public boolean visit(ArrayCreation node) {
		count++;
		return true;
	}

	public boolean visit(ArrayInitializer node) {
		count++;
		return true;
	}

	public boolean visit(ArrayType node) {
		count++;
		return true;
	}

	public boolean visit(AssertStatement node) {
		count++;
		return true;
	}

	public boolean visit(Assignment node) {
		count++;
		return true;
	}

	public boolean visit(Block node) {
		count++;
		return true;
	}

	public boolean visit(BlockComment node) {
		count++;
		return true;
	}

	public boolean visit(BooleanLiteral node) {
		count++;
		return true;
	}

	public boolean visit(BreakStatement node) {
		count++;
		return true;
	}

	public boolean visit(CastExpression node) {
		count++;
		return true;
	}

	public boolean visit(CatchClause node) {
		count++;
		return true;
	}

	public boolean visit(CharacterLiteral node) {
		count++;
		return true;
	}

	public boolean visit(ClassInstanceCreation node) {
		count++;
		return true;
	}

	public boolean visit(CompilationUnit node) {
		count++;
		return true;
	}

	public boolean visit(ConditionalExpression node) {
		count++;
		return true;
	}

	public boolean visit(ConstructorInvocation node) {
		count++;
		return true;
	}

	public boolean visit(ContinueStatement node) {
		count++;
		return true;
	}

	public boolean visit(CreationReference node) {
		count++;
		return true;
	}

	public boolean visit(Dimension node) {
		count++;
		return true;
	}

	public boolean visit(DoStatement node) {
		count++;
		return true;
	}

	public boolean visit(EmptyStatement node) {
		count++;
		return true;
	}

	public boolean visit(EnhancedForStatement node) {
		count++;
		return true;
	}

	public boolean visit(EnumConstantDeclaration node) {
		count++;
		return true;
	}

	public boolean visit(EnumDeclaration node) {
		count++;
		return true;
	}

	public boolean visit(ExpressionMethodReference node) {
		count++;
		return true;
	}

	public boolean visit(ExpressionStatement node) {
		count++;
		return true;
	}

	public boolean visit(FieldAccess node) {
		count++;
		return true;
	}

	public boolean visit(FieldDeclaration node) {
		count++;
		return true;
	}

	public boolean visit(ForStatement node) {
		count++;
		return true;
	}

	public boolean visit(IfStatement node) {
		count++;
		return true;
	}

	public boolean visit(ImportDeclaration node) {
		count++;
		return true;
	}

	public boolean visit(InfixExpression node) {
		count++;
		return true;
	}

	public boolean visit(Initializer node) {
		count++;
		return true;
	}

	public boolean visit(InstanceofExpression node) {
		count++;
		return true;
	}

	public boolean visit(IntersectionType node) {
		count++;
		return true;
	}

	public boolean visit(Javadoc node) {
		count++;
		return true;
	}

	public boolean visit(LabeledStatement node) {
		count++;
		return true;
	}

	public boolean visit(LambdaExpression node) {
		count++;
		return true;
	}

	public boolean visit(LineComment node) {
		count++;
		return true;
	}

	public boolean visit(MarkerAnnotation node) {
		count++;
		return true;
	}

	public boolean visit(MemberRef node) {
		count++;
		return true;
	}

	public boolean visit(MemberValuePair node) {
		count++;
		return true;
	}

	public boolean visit(MethodRef node) {
		count++;
		return true;
	}

	public boolean visit(MethodRefParameter node) {
		count++;
		return true;
	}

	public boolean visit(MethodDeclaration node) {
		count++;
		return true;
	}

	public boolean visit(MethodInvocation node) {
		count++;
		return true;
	}

	public boolean visit(Modifier node) {
		count++;
		return true;
	}

	public boolean visit(NameQualifiedType node) {
		count++;
		return true;
	}

	public boolean visit(NormalAnnotation node) {
		count++;
		return true;
	}

	public boolean visit(NullLiteral node) {
		count++;
		return true;
	}

	public boolean visit(NumberLiteral node) {
		count++;
		return true;
	}

	public boolean visit(PackageDeclaration node) {
		count++;
		return true;
	}

	public boolean visit(ParameterizedType node) {
		count++;
		return true;
	}

	public boolean visit(ParenthesizedExpression node) {
		count++;
		return true;
	}

	public boolean visit(PostfixExpression node) {
		count++;
		return true;
	}

	public boolean visit(PrefixExpression node) {
		count++;
		return true;
	}

	public boolean visit(PrimitiveType node) {
		count++;
		return true;
	}

	public boolean visit(QualifiedName node) {
		count++;
		return true;
	}

	public boolean visit(QualifiedType node) {
		count++;
		return true;
	}

	public boolean visit(ReturnStatement node) {
		count++;
		return true;
	}

	public boolean visit(SimpleName node) {
		count++;
		return true;
	}

	public boolean visit(SimpleType node) {
		count++;
		return true;
	}

	public boolean visit(SingleMemberAnnotation node) {
		count++;
		return true;
	}

	public boolean visit(SingleVariableDeclaration node) {
		count++;
		return true;
	}

	public boolean visit(StringLiteral node) {
		count++;
		return true;
	}

	public boolean visit(SuperConstructorInvocation node) {
		count++;
		return true;
	}

	public boolean visit(SuperFieldAccess node) {
		count++;
		return true;
	}

	public boolean visit(SuperMethodInvocation node) {
		count++;
		return true;
	}

	public boolean visit(SuperMethodReference node) {
		count++;
		return true;
	}

	public boolean visit(SwitchCase node) {
		count++;
		return true;
	}

	public boolean visit(SwitchStatement node) {
		count++;
		return true;
	}

	public boolean visit(SynchronizedStatement node) {
		count++;
		return true;
	}

	public boolean visit(TagElement node) {
		count++;
		return true;
	}

	public boolean visit(TextElement node) {
		count++;
		return true;
	}

	public boolean visit(ThisExpression node) {
		count++;
		return true;
	}

	public boolean visit(ThrowStatement node) {
		count++;
		return true;
	}

	public boolean visit(TryStatement node) {
		count++;
		return true;
	}

	public boolean visit(TypeDeclaration node) {
		count++;
		return true;
	}

	public boolean visit(TypeDeclarationStatement node) {
		count++;
		return true;
	}

	public boolean visit(TypeLiteral node) {
		count++;
		return true;
	}

	public boolean visit(TypeMethodReference node) {
		count++;
		return true;
	}

	public boolean visit(TypeParameter node) {
		count++;
		return true;
	}

	public boolean visit(UnionType node) {
		count++;
		return true;
	}

	public boolean visit(VariableDeclarationExpression node) {
		count++;
		return true;
	}

	public boolean visit(VariableDeclarationStatement node) {
		count++;
		return true;
	}

	public boolean visit(VariableDeclarationFragment node) {
		count++;
		return true;
	}

	public boolean visit(WhileStatement node) {
		count++;
		return true;
	}

	public boolean visit(WildcardType node) {
		count++;
		return true;
	}

}
