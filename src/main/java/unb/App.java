package unb;

import unb.modules.kernel.KernelMain;

/**
 * 
 */
public class App {
    public static void main( String[] args ){
//		String nameFile = "schedulling_process_in.txt";
//		String nameFileMemory = "memory_process_in.txt";
//		String nameFileInputOutput = "requests_in.txt";
    	
    	
    	String a = "/Users/rafael-souza/Downloads/TEM_Cap7_jun15.doc";
		
    	KernelMain kernel = new KernelMain(args);
    	kernel.execute();
    }

}
