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
    	KernelMain kernel = new KernelMain(args);
    	kernel.execute();
    }

}
