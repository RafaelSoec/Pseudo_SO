package unb.modules.memory;



import unb.modules.memory.interfaces.PaginationAlgorithm;

import java.util.LinkedList;

public abstract class AbstractPaginationAlgorithm implements PaginationAlgorithm {
    protected int pageFault;
    protected int frames;
    public LinkedList memoryList;

    public void setFrames(int frames) {
        if (frames < 0)
            throw new IllegalArgumentException();
        this.frames = frames;
        pageFault = 0;
    }

    public int getPageFaultCount() {
        return pageFault;
    }
}
