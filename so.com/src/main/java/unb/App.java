package unb;

import java.util.ArrayList;
import java.util.List;

import unb.modules.process.ProcessMain;
import unb.modules.process.dtos.ProcessDTO;

/**
 */
public class App {
    public static void main( String[] args ){
        toSchedullerProcess();
    }
    
    
    public static void toSchedullerProcess(){
    	ProcessDTO procedure = new ProcessDTO();
    	procedure.setArrivalTime(100L);
    	procedure.setDurationTime(200L);
    	
    	List<ProcessDTO> procedures = new ArrayList<ProcessDTO>();
    	procedures.add(procedure);
    	procedures.add(procedure);
    	
    	
        ProcessMain processManager = new ProcessMain(procedures);
        processManager.toSchedullerProcess();
    }
}
