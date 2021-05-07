package unb;

import java.util.ArrayList;
import java.util.List;

import unb.modules.process.ProcessMain;
import unb.modules.process.dtos.Procedure;
import unb.utils.ManagerFile;

/**
 */
public class App {
    public static void main( String[] args ){
        toSchedullerProcess();
    }
    
    
    public static void toSchedullerProcess(){
    	Procedure procedure1 = new Procedure();
    	procedure1.setId(1L);
    	procedure1.setArrivalTime(1);
    	procedure1.setDurationTime(8);
    	
    	Procedure procedure2 = new Procedure();
    	procedure2.setId(2L);
    	procedure2.setArrivalTime(2);
    	procedure2.setDurationTime(4);
    	
    	List<Procedure> procedures = new ArrayList<Procedure>();
    	procedures.add(procedure1);
    	procedures.add(procedure2);
    	
    	
        ProcessMain processManager = new ProcessMain(procedures);
        processManager.toSchedullerProcess();
    }
}
