// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import static jminusminus.CLConstants.GOTO;

/**
 * The AST node for a while-statement.
 */

class JTryStatement extends JStatement {

    /** Try block */
    private JBlock tryBlock ;

    /** Catch block. */
    private JBlock catchBlock;

    /** Finally block */
    private JBlock finallyBlock;

    /**
     * Construct an AST node for a while-statement given its line number, the
     * test expression, and the body.
     *
     * @param line
     *            line in which the while-statement occurs in the source file.
     * @param tryBlock
     *            the try block which may fail for some reason.
     * @param catchBlock
     *            the block to execute if the try block fails.
     */

    //TODO: There should be multiple catch blocks. Catch blocks should be preceded by an expression of which exception to catch, and which variable to bind the caught exception's details.
    public JTryStatement(int line, JBlock tryBlock, JBlock catchBlock, JBlock finallyBlock) {
        super(line);
        this.tryBlock = tryBlock;
        this.catchBlock = catchBlock;
        this.finallyBlock = finallyBlock;
    }

    /**
     * Analysis involves analyzing the test, checking its type and analyzing the
     * body statement.
     * 
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */
//TODO: This is just the JWhileStatement analyze
    public JTryStatement analyze(Context context) {
        // TODO: some analysis here
        return this;
    }

    /**
     * Generate code for the while loop.
     * 
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */
// TODO: This is just the JWhileStatement codegen
    public void codegen(CLEmitter output) {
        // Need two labels
        String test = output.createLabel();
        String out = output.createLabel();

        //TODO: Put actual codegen

        // Unconditional jump back up to test
        output.addBranchInstruction(GOTO, test);

        // The label below and outside the loop
        output.addLabel(out);
    }

    /**
     * @inheritDoc
     */
//TODO: This is just the JWhileStatement writestdout
    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JTryStatement line=\"%d\">\n", line());
        p.indentRight();
        p.indentLeft();
        p.printf("</JTryStatement>\n");
    }

}
