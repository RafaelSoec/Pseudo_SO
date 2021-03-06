package unb.modules.memory.pagination;

import unb.modules.memory.AbstractPaginationAlgorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// Segunda Chance: com o bit R sendo zerado a cada 3 referencias feitas a memoria
public class AlgorithmSecondChance extends AbstractPaginationAlgorithm {
    ArrayList<Integer> bits;
    private static int pointer = 0;

    public AlgorithmSecondChance(int frames) {
        super.setFrames(frames);
        this.memoryList = new LinkedList();
        this.bits = new ArrayList<Integer>();
    }

    @Override
    public int memory_insert(List<Integer> pageNumbers) {
        String pageNumber = null;
        int tmp = 0, counter = 0;

        for (int i = 0; i < pageNumbers.size(); i++) {
            // Verificar se pagina ja esta em memoria
            tmp = memoryList.indexOf(pageNumbers.get(i));

            // caso a pagina não esteja em memoria
            if (tmp == -1) {
                pageFault++;
                // Caso tenha espaço insere no final da fila
                if (memoryList.size() < frames) {
                    memoryList.add(pageNumbers.get(i));
                    bits.add(0);
                } else {
                    // Verifica o bit R para encontrar primeiro mais antigo nao utilizado recentemente
                    while (bits.get(pointer) == 1 && pointer < frames - 1) {
                        pointer++;
                    }

                    // Remove o menos utilizado e insere o novo elemento no final da lista
                    memoryList.remove(pointer);
                    bits.remove(pointer);
                    memoryList.add(pageNumbers.get(i));
                    bits.add(0);
                }
            // Se ja esta na memoria seta o bit R para 1
            } else
                bits.set(tmp, 1);

            // Acrescenta o contador a cada referencia da memória, quando chega em 3 zera a lista de bits R
            counter++;
            if (counter == 3) {
                for (int z = 0; z < bits.size(); z++) {
                    bits.set(z, 0);
                }
                counter = 0;
            }
        }
        return pageFault;
    }
}
