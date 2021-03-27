package JavaCompiler;
import javax.tools.*;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class Compiler {

    private String input;

    public Compiler (String input){
        this.input = input;
    }

    public SimpleJavaFileObject getJavaFileContentFromString(){
        StringBuilder javaFileContent = new StringBuilder("" +
                "class TestClass {" +
                input
                +
                "}");
        //parsing
        JavaObjectFromString javaObjectFromString = null;
        try {
            javaObjectFromString = new JavaObjectFromString("TestClass" , javaFileContent.toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return javaObjectFromString;
    }

    public void compile() throws Exception{
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector diagnosticCollector = new DiagnosticCollector();
        StandardJavaFileManager standardJavaFileManager = compiler.getStandardFileManager(diagnosticCollector, null , null);
        JavaFileObject javaFileObjectFromString = getJavaFileContentFromString();
        Iterable fileObjects = Arrays.asList(javaFileObjectFromString);
        JavaCompiler.CompilationTask task = compiler.getTask(null ,standardJavaFileManager , diagnosticCollector ,null , null ,fileObjects);
        Boolean result = task.call();
        List<Diagnostic> diagnostics = diagnosticCollector.getDiagnostics();
        for (Diagnostic diagnostic : diagnostics){
            System.out.println("Code : " + diagnostic.getCode());
            System.out.println("Line : " + diagnostic.getLineNumber());
            System.out.println(diagnostic.getSource() + "  " + diagnostic.getKind());
        }
        if (result){
            System.out.println("compiled successfully");
        }
        else {
            System.out.println("failed..");
        }
    }
}
