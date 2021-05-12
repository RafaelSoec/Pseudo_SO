package unb.modules.memory.pagination;

import unb.modules.memory.AbstractPaginationAlgorithm;

import java.util.LinkedList;

// LRU: Least Recently Used ou Menos Recentemente Utilizado
public class AlgorithmLRU extends AbstractPaginationAlgorithm {
    public AlgorithmLRU(int numeroDeQuadros) {
        super(numeroDeQuadros);
        this.quadros = new LinkedList();
    }

    @Override
    public void memory_insert(String pageNumber) {
        int tmp = quadros.indexOf(pageNumber);
        // Verifica se pagina ja esta na lista
        if (tmp == -1) {
            if (quadros.size() >= numeroDeQuadros)
                quadros.remove(0);
            quadros.add(pageNumber);
            numeroDeFalhas++;
        } else {
            quadros.remove(tmp);
            quadros.add(pageNumber);
        }
    }
}
