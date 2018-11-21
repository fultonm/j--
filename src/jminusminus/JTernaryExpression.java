package jminusminus;

/**
 * The AST node for a plus (+) expression. In j--, as in Java, + is overloaded
 * to denote addition for numbers and concatenation for Strings.
 */

class JTernaryExpression extends JExpression {

    JExpression lhs;
    JExpression conditionExpr;
    JExpression trueExpr;
    JExpression falseExpr;

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
     * @param trueExpression
     *            if the condition is true, this value is evaluated and is assigned to the lhs
     * @param falseExpression
     *            if the condition if false, then this value is evaluated and is assigned to the rhs
     */

    public JTernaryExpression(int line, JExpression lhs, JExpression conditionExpression, JExpression trueExpression, JExpression falseExpression) {
        super(line);
        this.lhs = lhs;
        this.conditionExpr = conditionExpression;
        this.trueExpr = trueExpression;
        this.falseExpr = falseExpression;
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
        lhs = (JExpression) lhs.analyze(context);
        trueExpr = (JExpression) trueExpr.analyze(context);
        falseExpr = (JExpression) falseExpr.analyze(context);
        if (lhs.type() == Type.STRING || trueExpr.type() == Type.STRING || falseExpr.type() == Type.STRING) {
            return (new JStringConcatenationOp(line, lhs, trueExpr))
                    .analyze(context);
        } else if (lhs.type() == Type.INT && trueExpr.type() == Type.INT && falseExpr.type() == Type.INT) {
            type = Type.INT;
        } else {
            type = Type.ANY;
            JAST.compilationUnit.reportSemanticError(line(),
                    "Invalid operand types for +");
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
       return;
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
        trueExpr.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Rhs>\n");
        p.indentLeft();
        p.printf("</JTernaryExpression>\n");
    }

}
