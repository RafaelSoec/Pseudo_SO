package unb.modules.memory.pagination;

import unb.modules.memory.AbstractPaginationAlgorithm;

import java.util.LinkedList;
import java.util.List;

// FIFO: First In, First Out
public class AlgorithmPFIFO extends AbstractPaginationAlgorithm {

    public AlgorithmPFIFO(int frames) {
        super.setFrames(frames);
        this.memoryList = new LinkedList();
    }

    public int getPageFaultCount() {
        return super.getPageFaultCount();
    }

    @Override
    public int memory_insert(List<Integer> pageNumbers) {
        for (int i = 0; i < pageNumbers.size(); i++) {
            // Verificar se pagina ja esta na lista
            if (!memoryList.contains(pageNumbers.get(i))) {
                pageFault++;
                // Verificar se ainda tem espaço na memoria
                if (memoryList.size() < frames)
                    memoryList.add(pageNumbers.get(i));
                else {
                    memoryList.remove();
                    memoryList.add(pageNumbers.get(i));
                    //insertion++;
                }
            }
        }
        return pageFault;
    }
}
