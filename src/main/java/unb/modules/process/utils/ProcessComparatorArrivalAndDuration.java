package unb.modules.process.utils;

import java.util.Comparator;

import unb.modules.process.dtos.Procedure;

public class ProcessComparatorArrivalAndDuration implements Comparator<Procedure>{
    public int compare(Procedure a, Procedure b){
    	if(a.getArrivalTime() - b.getArrivalTime() == 0) {
    		return a.getDurationTime() - b.getDurationTime();
    	}
    	
        return a.getArrivalTime() - b.getArrivalTime() ;
    }
}
