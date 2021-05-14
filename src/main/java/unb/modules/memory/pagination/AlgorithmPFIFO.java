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
                // Verifica se ainda tem espaço na memoria, se sim insere no final da fila
                if (memoryList.size() < frames)
                    memoryList.add(pageNumbers.get(i));
                // Senao remove o primeiro elemento e insere o novo elemento no final da fila
                else {
                    memoryList.remove();
                    memoryList.add(pageNumbers.get(i));
                }
            }
        }
        return pageFault;
    }
}
