package unb.modules.memory;


import unb.modules.memory.interfaces.PaginationAlgorithm;

import java.util.LinkedList;

public abstract class AbstractPaginationAlgorithm implements PaginationAlgorithm {
    protected int numeroDeFalhas;
    protected int numeroDeQuadros;
    public LinkedList quadros;

    public void setNumeroDeQuadros(int numeroDeQuadros) {
        if (numeroDeQuadros < 0)
            throw new IllegalArgumentException();
        this.numeroDeQuadros = numeroDeQuadros;
        numeroDeFalhas = 0;
    }

    public int getPageFaultCount() {
        return numeroDeFalhas;
    }
}
