package jminusminus;

public class JExceptionStatement extends JStatement {
    String exceptionName;

    public JExceptionStatement(int line, String exceptionName) {
        super(line);
        this.exceptionName = exceptionName;
    }

    @Override
    public JAST analyze(Context context) {
        return null;
    }

    @Override
    public void codegen(CLEmitter output) {

    }

    @Override
    public void writeToStdOut(PrettyPrinter p) {

    }
}
