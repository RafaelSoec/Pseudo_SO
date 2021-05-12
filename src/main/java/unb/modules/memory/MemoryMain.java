package unb.modules.memory;

import unb.modules.memory.enums.PaginationAlgorithmEnum;
import unb.modules.process.dtos.ResultSchedullingProcess;
import unb.utils.ManagerFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

//O programa deverá ler da entrada padrão um conjunto de número inteiros, dos quais o primeiro número
//representa a quantidade de quadros de memória disponíveis na RAM e os demais representam a sequência
//de referências às páginas, sempre um número por linha. O programa deverá imprimir na saída o número
//de faltas de páginas obtido com a utilização de cada um dos algoritmos.
public class MemoryMain {
    int frames;
    List<Integer> ref_mem = new ArrayList<Integer>();


    public MemoryMain() {
    }

    public MemoryMain(int frames, List<Integer> ref_mem) {
        this.frames = frames;
        this.ref_mem = ref_mem;
    }

    public void toMemoryProcess(String typeAlgh) {
        PaginationAlgorithmEnum type = null;

        if (typeAlgh.equals(PaginationAlgorithmEnum.FIFO.getName())) {
            type = PaginationAlgorithmEnum.FIFO;
        } else if (typeAlgh.equals(PaginationAlgorithmEnum.Second_Chance.getName())) {
            type = PaginationAlgorithmEnum.Second_Chance;
        } else {
            type = PaginationAlgorithmEnum.LRU;
        }

        StringBuilder results = new StringBuilder();

        results.append(this.generateStringResultPagination(type));
        this.generateResultFilePagination(results.toString());
    }

    private String generateStringResultPagination(final PaginationAlgorithmEnum algorithm) {
        StringBuilder results = new StringBuilder();

        results.append(algorithm.getName()).append(" ").append("resultAlgorithm.getExecutionTime()").append(" ")
                .append("resultAlgorithm.getResponseTime()").append(" ").append("resultAlgorithm.getWaitTime()")
                .append("\n");

        return results.toString();
    }

    private void generateResultFilePagination(final String results) {
        String path = "files/memory";
        String nameFile = "memory_out.txt";

        ManagerFile.generateFile(path, nameFile, results);
        System.out.println(ManagerFile.readFile(path, nameFile));
    }
}
