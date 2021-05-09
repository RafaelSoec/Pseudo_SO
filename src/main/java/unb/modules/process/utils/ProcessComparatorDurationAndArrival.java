package unb.modules.process.utils;

import java.util.Comparator;

import unb.modules.process.dtos.Procedure;

public class ProcessComparatorDurationAndArrival implements Comparator<Procedure>{
    public int compare(Procedure a, Procedure b){
    	if(a.getDurationTime() - b.getDurationTime() == 0) {
            return a.getArrivalTime() - b.getArrivalTime() ;
    	}

        return a.getDurationTime() - b.getDurationTime();
    }
}
