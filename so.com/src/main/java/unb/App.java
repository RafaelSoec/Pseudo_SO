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
    	ProcessDTO procedure1 = new ProcessDTO();
    	procedure1.setId(1L);
    	procedure1.setArrivalTime(100L);
    	procedure1.setDurationTime(200L);
    	
    	ProcessDTO procedure2 = new ProcessDTO();
    	procedure2.setId(2L);
    	procedure2.setArrivalTime(100L);
    	procedure2.setDurationTime(200L);
    	
    	List<ProcessDTO> procedures = new ArrayList<ProcessDTO>();
    	procedures.add(procedure1);
    	procedures.add(procedure2);
    	
    	
        ProcessMain processManager = new ProcessMain(procedures);
        processManager.toSchedullerProcess();
    }
}
