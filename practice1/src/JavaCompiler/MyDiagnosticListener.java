package JavaCompiler;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;


public class MyDiagnosticListener implements DiagnosticListener {

    @Override
    public void report(Diagnostic diagnostic) {
        System.out.println("Code->" +  diagnostic.getCode());
        System.out.println("Column Number->" + diagnostic.getColumnNumber());
    }
}
