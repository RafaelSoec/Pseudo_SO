package unb.modules.memory.pagination;

import java.util.LinkedList;

public class Algorithm {
    protected int numeroDeFalhas;
    protected int numeroDeQuadros;
    LinkedList quadros;

    public Algorithm(int numeroDeQuadros) {
        if (numeroDeQuadros < 0)
            throw new IllegalArgumentException();
        this.numeroDeQuadros = numeroDeQuadros;
        numeroDeFalhas = 0;
    }

    public int getPageFaultCount() {
        return numeroDeFalhas;
    }

    public void insert(String pageNumber);

    public void imprimirQuadros() {
        System.out.print("Quadros: ");
        for (int i = 0; i < quadros.size(); i++) {
            System.out.print(quadros.get(i) + " ");
        }
        System.out.println();
    }
}
