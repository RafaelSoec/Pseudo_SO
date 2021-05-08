package unb.modules.process.utils;

import java.util.Comparator;

import unb.modules.process.dtos.Procedure;

public class ProcessComparatorDurationAndArrival implements Comparator<Procedure>{
    public int compare(Procedure a, Procedure b){
        return a.getArrivalTime() - b.getArrivalTime() + a.getDurationTime() - b.getDurationTime();
    }
}
