package jminusminus;

import static jminusminus.CLConstants.GOTO;
import static jminusminus.CLConstants.POP;

/**
 * The AST node for a plus (+) expression. In j--, as in Java, + is overloaded
 * to denote addition for numbers and concatenation for Strings.
 */

class JTernaryExpression extends JExpression {

    JExpression lhs;
    JExpression conditionExpr;
    JExpression consequentExpr;
    JExpression alternativeExpr;

    /**
     * Construct an AST node for an addition expression given its line number,
     * and the lhs and rhs operands.
     *
     * @param line
     *            line in which the addition expression occurs in the source
     *            file.
     * @param lhs
     *            the lhs operand.
     * @param conditionExpression
     *            the condition to determine which expression is assigned to the lhs.
     * @param consequentExpr
     *            if the condition is true, this value is evaluated and is assigned to the lhs
     * @param alternativeExpr
     *            if the condition if false, then this value is evaluated and is assigned to the rhs
     */

    public JTernaryExpression(int line, JExpression lhs, JExpression conditionExpression, JExpression consequentExpr, JExpression alternativeExpr) {
        super(line);
        this.lhs = lhs;
        this.conditionExpr = conditionExpression;
        this.consequentExpr = consequentExpr;
        this.alternativeExpr = alternativeExpr;
    }

    /**
     * Analysis involves first analyzing the operands. If this is a string
     * concatenation, we rewrite the subtree to make that explicit (and analyze
     * that). Otherwise we check the types of the addition operands and compute
     * the result type.
     *
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JExpression analyze(Context context) {
        conditionExpr = (JExpression) conditionExpr.analyze(context);
        consequentExpr = (JExpression) consequentExpr.analyze(context);
        alternativeExpr = (JExpression) alternativeExpr.analyze(context);
        if (conditionExpr.type() != Type.BOOLEAN) {
            JAST.compilationUnit.reportSemanticError(line(), "Ternary condition must be boolean");
        }
        if (consequentExpr.type() == Type.INT && alternativeExpr.type() == Type.INT) {
            type = Type.INT;
        } else if (consequentExpr.type() == Type.STRING && alternativeExpr.type() == Type.STRING) {
            type = Type.STRING;
        } else {
            type = Type.ANY;
            JAST.compilationUnit.reportSemanticError(line(),
                    "Invalid operand types for ternary. Consequent alternative and destination variable Types must match");
        }
        return this;
    }

    /**
     * Any string concatenation has been rewritten as a JStringConcatenationOp
     * (in analyze()), so code generation here involves simply generating code
     * for loading the operands onto the stack and then generating the
     * appropriate add instruction.
     *
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    public void codegen(CLEmitter output) {

        String alternativeExprLabel = output.createLabel();
        String endLabel = output.createLabel();

        // Evaluate the condition, if FALSE, jump to alternative expression
        conditionExpr.codegen(output, alternativeExprLabel,false);

        // If the condition was true we will do the consequent
        consequentExpr.codegen(output);
        output.addBranchInstruction(GOTO, endLabel);

        // Otherwise do the alternative
        output.addLabel(alternativeExprLabel);
        alternativeExpr.codegen(output);

        output.addLabel(endLabel);
    }

    /** TODO: make this a complete std out for JTernaryExpression */
    @Override
    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JTernaryExpression line=\"%d\"", line());
        p.indentRight();
        p.printf("<Lhs>\n");
        p.indentRight();
        lhs.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Lhs>\n");
        p.printf("<TrueExpr>\n");
        p.indentRight();
        consequentExpr.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Rhs>\n");
        p.indentLeft();
        p.printf("</JTernaryExpression>\n");
    }

}
