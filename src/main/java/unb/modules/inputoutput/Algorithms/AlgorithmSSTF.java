package unb.modules.inputoutput.Algorithms;

import unb.modules.inputoutput.dtos.DiskDriver;
import unb.modules.inputoutput.dtos.ResultInputOutputAlgorithm;

import java.util.ArrayList;
import java.util.List;

/** Jean Rodrigues Magalhães - 15/0079923
 *
 * Nesta classe é implementado o algoritmo SSTF (Short Seek Time First – Tempo de Busca mais Curto Primeiro)
 *
 * */
public class AlgorithmSSTF {

    public ResultInputOutputAlgorithm run(DiskDriver diskDriver){
        //instancia de objetos e recpção dos dados provindo do diskDriver
        ResultInputOutputAlgorithm result = new ResultInputOutputAlgorithm();
        result.setAlgorithmName("SSTF");

        List<Integer> listRequests = new ArrayList<>(diskDriver.getRequests());

        //a variavel test tem como função neste módulo, fazer as testagens de menor caminho entre o atual e possivel proximo cilindro
        Integer totalCylinders = 0, newCurrent = 0, oldValue = 0;
        Integer test = diskDriver.getLastCylinder() + 1;

        //nesta parte é descoberto, dentre todas as requisições, qual será o próximo cilindro do cilindro atual
        for (int i = 0; i < listRequests.size() - 1; i++){
            if (listRequests.get(i) < diskDriver.getcurrentCylinder() && test > diskDriver.getcurrentCylinder() - listRequests.get(i)){
                test = diskDriver.getcurrentCylinder() - listRequests.get(i);
                newCurrent = listRequests.indexOf(listRequests.get(i));
            }
            if(listRequests.get(i) > diskDriver.getcurrentCylinder() && test > listRequests.get(i) - diskDriver.getcurrentCylinder()){
                test = listRequests.get(i) - diskDriver.getcurrentCylinder();
                newCurrent = listRequests.indexOf(listRequests.get(i));
            }
        }

        //oldvalue corresponde ao elemento que já foi retirado da lista mas será comparado com o proximo para se calcular a distancia
        oldValue = listRequests.get(newCurrent);
        listRequests.remove(listRequests.get(newCurrent));
        totalCylinders += test;

        while (listRequests.size() != 0) {
            test = diskDriver.getLastCylinder() + 1;

            //divisao para tratar calcular a distancia entre elementos acima ou abaixo do atual e sempre testar o menor caminho com TEST
            for (int i = 0; i < listRequests.size() ; i++){
                if (listRequests.get(i) < oldValue && test > oldValue - listRequests.get(i)) {
                    test = oldValue - listRequests.get(i);
                    newCurrent = i;
                }
                if(listRequests.get(i) > oldValue && test > listRequests.get(i) - oldValue){
                    test = listRequests.get(i) - oldValue;
                    newCurrent = i;
                }
            }

            totalCylinders += test;
            oldValue = listRequests.get(newCurrent);
            listRequests.remove(listRequests.get(newCurrent));
        }

        result.setTotalCylinders(totalCylinders);

        return result;
    }
}
