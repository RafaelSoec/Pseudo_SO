package unb.modules.memory;

import java.util.LinkedList;

public abstract class AbstractPaginationAlgorithm {
    protected int numeroDeFalhas;
    protected int numeroDeQuadros;
    public LinkedList quadros;

    public AbstractPaginationAlgorithm(int numeroDeQuadros) {
        if (numeroDeQuadros < 0)
            throw new IllegalArgumentException();
        this.numeroDeQuadros = numeroDeQuadros;
        numeroDeFalhas = 0;
    }

    public int getPageFaultCount() {
        return numeroDeFalhas;
    }

    public abstract void memory_insert(String pageNumber);

    public void imprimirQuadros() {
        System.out.print("Quadros: ");
        for (int i = 0; i < quadros.size(); i++) {
            System.out.print(quadros.get(i) + " ");
        }
        System.out.println();
    }
}
