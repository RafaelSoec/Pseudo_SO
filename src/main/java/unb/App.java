package unb;

import java.util.ArrayList;
import java.util.List;

import unb.modules.process.ProcessMain;
import unb.modules.process.dtos.Procedure;

/**
 */
public class App {
    public static void main( String[] args ){
        toSchedullerProcess();
    }
    
    
    public static void toSchedullerProcess(){
    	Procedure procedure1 = new Procedure();
    	procedure1.setId(1L);
//    	procedure1.setArrivalTime(0);
//    	procedure1.setDurationTime(7);
    	procedure1.setArrivalTime(0);
    	procedure1.setDurationTime(8);
    	
    	Procedure procedure2 = new Procedure();
    	procedure2.setId(2L);
//    	procedure2.setArrivalTime(2);
//    	procedure2.setDurationTime(4);
    	procedure2.setArrivalTime(1);
    	procedure2.setDurationTime(5);

    	Procedure procedure3 = new Procedure();
    	procedure3.setId(3L);
//    	procedure3.setArrivalTime(4);
//    	procedure3.setDurationTime(1);
    	procedure3.setArrivalTime(3);
    	procedure3.setDurationTime(1);

    	Procedure procedure4 = new Procedure();
    	procedure4.setId(4L);
//    	procedure4.setArrivalTime(5);
//    	procedure4.setDurationTime(4);
    	procedure4.setArrivalTime(6);
    	procedure4.setDurationTime(4);
    	
    	List<Procedure> procedures = new ArrayList<Procedure>();
    	procedures.add(procedure1);
    	procedures.add(procedure2);
    	procedures.add(procedure3);
    	procedures.add(procedure4);
    	
        ProcessMain processManager = new ProcessMain(procedures);
        processManager.toSchedullerProcess();
    }
}
