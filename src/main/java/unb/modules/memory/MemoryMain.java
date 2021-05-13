package unb.modules.memory;

import unb.modules.memory.dtos.ResultPaginationProcess;
import unb.modules.memory.enums.PaginationAlgorithmEnum;
import unb.modules.memory.interfaces.PaginationAlgorithm;
import unb.modules.memory.pagination.AlgorithmLRU;
import unb.modules.memory.pagination.AlgorithmPFIFO;
import unb.modules.memory.pagination.AlgorithmSecondChance;
import unb.utils.ManagerFile;

import java.util.ArrayList;
import java.util.List;

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
        PaginationAlgorithm algorithm = null;

        if (typeAlgh.equals(PaginationAlgorithmEnum.FIFO.getName())) {
            type = PaginationAlgorithmEnum.FIFO;
            algorithm = new AlgorithmPFIFO(frames);
        } else if (typeAlgh.equals(PaginationAlgorithmEnum.Second_Chance.getName())) {
            type = PaginationAlgorithmEnum.Second_Chance;
           // algorithm = new AlgorithmLRU();
        } else {
            type = PaginationAlgorithmEnum.LRU;
            //algorithm = new AlgorithmSecondChance();
        }

        StringBuilder results = new StringBuilder();
        algorithm.memory_insert(ref_mem);
        ResultPaginationProcess resultPag = null;
        results.append(this.generateStringResultPagination(type, resultPag));
        this.generateResultFilePagination(results.toString());
    }

    private String generateStringResultPagination(final PaginationAlgorithmEnum algorithm, final ResultPaginationProcess resultAlgorithm) {
        StringBuilder results = new StringBuilder();

        results.append(algorithm.getName()).append(" ").append("resultAlgorithm.getNumeroDeFalhas()").append("\n");

        return results.toString();
    }

    private void generateResultFilePagination(final String results) {
        String path = "files/memory";
        String nameFile = "memory_out.txt";

        ManagerFile.generateFile(path, nameFile, results);
        System.out.println(ManagerFile.readFile(path, nameFile));
    }
}
