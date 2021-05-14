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

                if (memoryList.size() < frames) {
                    memoryList.add(pageNumbers.get(i));
                    bits.add(0);
                } else {
                    // Encontrar primeiro mais antigo nao utilizado recentemente
                    while (bits.get(pointer) == 1 && pointer < frames - 1) {
                        pointer++;
                    }

                    //substituicao
                    memoryList.remove(pointer);
                    bits.remove(pointer);
                    memoryList.add(pageNumbers.get(i));
                    bits.add(0);
                }
            } else
                bits.set(tmp, 1);

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
