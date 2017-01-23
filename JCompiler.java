package INCO;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
* Compiler class, it compiles during runtime all the .java files.
*
* @author Alba, Eva and Hector.
*
*/
public class JCompiler {
	
	/**
	 * This method compiles during runtime all the .java files.
	 * 
	 * @throws IOException
	 */
    public static void compile(String fileName) throws IOException {
    
   
    	
      JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
      
      if (javaCompiler == null) {
			throw new IllegalStateException("No JavaCompiler provided by the Java platform, you need to use a JDK rather than a JRE");
      }
      
      int x = javaCompiler.run(null, null, null, "./src/INCO/" + fileName + ".java");
      
      File a = new File("./src/INCO/" + fileName + ".class");
      a.renameTo(new File("./bin/INCO/"  + a.getName()));
      a.delete();
    }
}