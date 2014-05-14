import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class FallingItemsLoader {
	@SuppressWarnings("rawtypes")
	public static Class [] LoadClass( String directory)
			throws MalformedURLException, InstantiationException, IllegalAccessException {// void mo2aktn
		Class[] classes = null;
		try {

			File file = new File(directory);
			String[] files = file.list();
			ArrayList<String> classNames = new ArrayList<String>();
			for (int i = 0; i < files.length; i++) {
				if (files[i].endsWith(".class"))
					classNames.add(files[i].replaceAll(".class", ""));
			}
			@SuppressWarnings("deprecation")
			URL url = file.toURL();
			URL[] urls = new URL[] { url };

			ClassLoader cl = new URLClassLoader(urls);
            classes = new Class[classNames.size()];
            for (int i = 0; i < classes.length; i++) {
				
            	classes[i]=cl.loadClass(classNames.get(i));// loading the class.class
            
            	
                 
                
            }
			

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return classes ;

	}

	public static void main(String[] args) throws MalformedURLException, InstantiationException, IllegalAccessException {
      String directory = "D:\\College Work\\3rd Semester\\OOP\\FinalProject\\fallingItemsClasses";
      LoadClass(directory);
     
     
	}
}
