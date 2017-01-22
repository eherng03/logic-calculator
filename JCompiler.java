import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Locale;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
* Compiler class, it compiles during runtime all the .java files
*
* @author Alba, Eva and Hector
*
*/
public class JCompiler {
	
    public static void compilar() throws IOException {
    	
      File directorio = new File(".");
      //Introducimos en un array todos los archivos .java que encontremos en el directorio
      File[] javaFiles = directorio.listFiles(
              new FilenameFilter() {
                  public boolean accept(File file, String name) {
                      return name.endsWith(".java");
                  }
              });
        
      JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
      
      DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
      StandardJavaFileManager fileManager = javaCompiler.getStandardFileManager(diagnostics, Locale.getDefault(), Charset.forName("UTF-8"));
      Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(javaFiles));
      javaCompiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits).call();
        
      for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
          System.out.format("Error on line %d in %d%n", diagnostic.getLineNumber(), diagnostic.getSource().toString());
      }        

      fileManager.close();
    }
    //TODO lo de las clases que decía Javi
}