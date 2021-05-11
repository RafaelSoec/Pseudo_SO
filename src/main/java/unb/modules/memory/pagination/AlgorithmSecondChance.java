package unb.modules.memory.pagination;

import unb.modules.memory.AbstractPaginationAlgorithm;

import java.util.ArrayList;
import java.util.LinkedList;

// Segunda Chance: com o bit R sendo zerado a cada 3 referencias feitas a memoria
public class AlgorithmSecondChance extends AbstractPaginationAlgorithm {
    ArrayList<Integer> bits;
    private static int pointer = 0;

    public AlgorithmSecondChance(int numeroDeQuadros) {
        super(numeroDeQuadros);
        this.quadros = new LinkedList();
        this.bits = new ArrayList<Integer>();
    }

    @Override
    public void memory_insert(String pageNumber) {
        int tmp = quadros.indexOf(pageNumber);

        // caso a pagina não esteja em memoria
        if (tmp == -1) {
            if (quadros.size() < numeroDeQuadros) {
                quadros.add(pageNumber);
                bits.add(0);
            } else {
                while (bits.get(pointer) == 1) {
                    bits.set(pointer, 0);
                    pointer++;
                    if (pointer == numeroDeQuadros) {
                        pointer = 0;
                    }
                }

                //substituicao
                quadros.remove(pointer);
                bits.remove(pointer);
                quadros.add(pointer, pageNumber);
                bits.add(pointer, 0);

                pointer++;
                // ponteiro voltando ao inicio
                if (pointer == numeroDeQuadros) {
                    pointer = 0;
                }
            }
            numeroDeFalhas++;
        } else
            bits.set(tmp, 1);
    }
}
