package unb.modules.memory;

import unb.modules.memory.enums.PaginationAlgorithmEnum;

import java.util.ArrayList;
import java.util.List;

public class Memory {
    int frames;
    List<Integer> ref_mem = new ArrayList<Integer>();


    public Memory() {
    }

    public Memory(int frames, List<Integer> ref_mem) {
        this.frames = frames;
        this.ref_mem = ref_mem;
    }

    public void toMemoryProcess(String typeAlgh) {
        PaginationAlgorithmEnum type = null;

        if (typeAlgh.equals(PaginationAlgorithmEnum.FIFO.getName())) {
            type = PaginationAlgorithmEnum.FIFO;
        } else if (typeAlgh.equals(PaginationAlgorithmEnum.Second_Chance.getName())) {
            type = PaginationAlgorithmEnum.Second_Chance;
        } else {
            type = PaginationAlgorithmEnum.LRU;
        }

    }




}
