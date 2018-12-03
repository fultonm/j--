// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import static jminusminus.CLConstants.GOTO;
import static jminusminus.CLConstants.POP;

/**
 * The AST node for a while-statement.
 */

class JForStatement extends JStatement {

    /** Initialization Statement */
    private JStatement initStatement;

    /** Test expression. */
    private JExpression condition;

    /** Post-body statement */
    private JExpression incrementerExpr;

    /** The body. */
    private JStatement body;

    /**
     * Construct an AST node for a while-statement given its line number, the
     * test expression, and the body.
     *
     * @param line
     *            line in which the while-statement occurs in the source file.
     * @param condition
     *            test expression.
     * @param body
     *            the body.
     */

    public JForStatement(int line, JStatement initStatement, JExpression condition, JExpression incrementerExpr, JStatement body) {
        super(line);
        this.initStatement = initStatement;
        this.condition = condition;
        this.incrementerExpr = incrementerExpr;
        this.body = body;
    }

    /**
     * Analysis involves analyzing the test, checking its type and analyzing the
     * body statement.
     * 
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */
    public JForStatement analyze(Context context) {
        initStatement = (JStatement) initStatement.analyze(context);
        condition = condition.analyze(context);
        condition.type().mustMatchExpected(line(), Type.BOOLEAN);
        incrementerExpr = incrementerExpr.analyze(context);
        body = (JStatement) body.analyze(context);
        return this;
    }

    /**
     * Generate code for the while loop.
     * 
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */
    public void codegen(CLEmitter output) {

        // Need a label before the body
        String bodyLabel = output.createLabel();
        String exitLabel = output.createLabel();

        // Init the variable we'll be using
        initStatement.codegen(output);
        // Check the condition, if its already false then exit without entering body
        condition.codegen(output, exitLabel, false);

        // Put the label before generating body code.
        output.addLabel(bodyLabel);

        // Do the body of the loop
        body.codegen(output);


        // Increment/decrement the counter
        incrementerExpr.codegen(output);
        // Discard the value off the stack to avoid "inconsistent stack height"!
        output.addNoArgInstruction(POP);

        // If the condition is true jump back to body
        // else we're done.
        condition.codegen(output, bodyLabel, true);

        output.addLabel(exitLabel);
    }

    /**
     * @inheritDoc
     */
//TODO: This is just the JWhileStatement writestdout
    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JWhileStatement line=\"%d\">\n", line());
        p.indentRight();
        p.printf("<TestExpression>\n");
        p.indentRight();
        condition.writeToStdOut(p);
        p.indentLeft();
        p.printf("</TestExpression>\n");
        p.printf("<Body>\n");
        p.indentRight();
        body.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Body>\n");
        p.indentLeft();
        p.printf("</JWhileStatement>\n");
    }

}
