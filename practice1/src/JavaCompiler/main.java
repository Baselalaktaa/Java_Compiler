package JavaCompiler;



public class main {

    public static void main(String[] args) throws Exception{
        String input = "public void print(){" +
                "System.out.println(" + "\"test\"" +           ");" +
                "}" ;
        Compiler compiler = new Compiler(input);
        compiler.compile();
    }
}
