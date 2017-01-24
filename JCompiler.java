package INCO;
import java.io.File;
import java.io.IOException;
import javax.tools.JavaCompiler;
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
      
      javaCompiler.run(null, null, null, "./src/INCO/" + fileName + ".java");
      
      File a = new File("./src/INCO/" + fileName + ".class");
      a.renameTo(new File("./bin/INCO/"  + a.getName()));
      a.delete();
    }
}