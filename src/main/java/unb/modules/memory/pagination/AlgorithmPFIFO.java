package unb.modules.memory.pagination;

import unb.modules.memory.AbstractPaginationAlgorithm;

import java.util.LinkedList;

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
    public void memory_insert(String pageNumber) {
        // Verificar se pagina ja esta na lista
        if (!quadros.contains(pageNumber)) {
            // Verificar se ainda tem espaço na memoria
            if (quadros.size() < numeroDeQuadros)
                quadros.add(pageNumber);
            else {
                quadros.remove(insertion);
                quadros.add(insertion, pageNumber);
                insertion++;
            }
            numeroDeFalhas++;
        }
    }
}
