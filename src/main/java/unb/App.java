package unb;

import unb.modules.kernel.KernelMain;


/** Pseudo Sistema Operacional implementado para a disciplina de sistemas operacionais.
 *  Integrantes:
 *  Caio Calixto Fasolak Alves - 15/0078676
 * 	Jean Rodrigues Magalhães - 15/0079923
 * 	Marcelo Áxel C. de Nazaré - 15/0080727
 * 	Rafael Oliveira de Souza - 15/0081537
 * 
 */


//Apenas para iniciar o pseudo SO.

public class App {
    public static void main( String[] args ){
		

    	KernelMain kernel = new KernelMain(args);
    	kernel.execute();
    }

}
