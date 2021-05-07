package unb.modules.process.utils;

import java.util.Comparator;

import unb.modules.process.dtos.Procedure;

public class ProcessComparatorDuration implements Comparator<Procedure>{
    public int compare(Procedure a, Procedure b){
        return a.getDurationTime() - b.getDurationTime();
    }
}
