package unb.modules.memory.pagination;

import unb.modules.memory.AbstractPaginationAlgorithm;

import java.util.LinkedList;
import java.util.List;

// LRU: Least Recently Used ou Menos Recentemente Utilizado
public class AlgorithmLRU extends AbstractPaginationAlgorithm {
    LinkedList memoryList;
    public AlgorithmLRU(int frames) {
        super.setFrames(frames);
        this.memoryList = new LinkedList();
    }

    @Override
    public int memory_insert(List<Integer> pageNumbers) {
        int tmp;
        for (int i = 0; i < pageNumbers.size(); i++) {
            // Verifica se pagina ja esta na lista
            tmp = memoryList.indexOf(pageNumbers.get(i));

            if (tmp == -1) {
                pageFault++;
                if (memoryList.size() >= frames)
                    memoryList.remove();
            } else {
                memoryList.remove(tmp);
            }
            memoryList.add(pageNumbers.get(i));
        }
        return pageFault;
    }
}
