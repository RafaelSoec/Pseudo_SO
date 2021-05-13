package unb.modules.memory.pagination;

import unb.modules.memory.AbstractPaginationAlgorithm;

import java.util.LinkedList;
import java.util.List;

// FIFO: First In, First Out
public class AlgorithmPFIFO extends AbstractPaginationAlgorithm {
    private static int insertion = 0;

    public AlgorithmPFIFO(int numeroDeQuadros) {
        super.setNumeroDeQuadros(numeroDeQuadros);
        this.quadros = new LinkedList();
    }

    public int getPageFaultCount() {
        return super.getPageFaultCount();
    }

    @Override
    public void memory_insert(List<Integer> pageNumbers) {
        for (int i = 0; i < pageNumbers.size(); i++) {
            // Verificar se pagina ja esta na lista
            if (!quadros.contains(pageNumbers.get(i))) {
                // Verificar se ainda tem espaço na memoria
                if (quadros.size() < numeroDeQuadros)
                    quadros.add(pageNumbers.get(i));
                else {
                    quadros.remove(insertion);
                    quadros.add(insertion, pageNumbers.get(i));
                    //insertion++;
                }
                numeroDeFalhas++;
            }
        }
    }
}
