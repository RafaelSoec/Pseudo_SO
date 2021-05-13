package unb.modules.inputoutput.Algorithms;

import unb.modules.inputoutput.dtos.DiskDriver;
import unb.modules.inputoutput.dtos.ResultInputOutputAlgorithm;

import java.util.ArrayList;
import java.util.List;

/** Jean Rodrigues Magalhães - 15/0079923
 *
 * Nesta classe é implementado o algoritmo FCFS (First Come First Served - Primeiro a Chegar Primeiro a ser Servido)
 *
 * */
public class AlgorithmFCFS {

    public ResultInputOutputAlgorithm run(DiskDriver diskDriver){
        //instancia de objetos e recpção dos dados provindo do diskDriver
        ResultInputOutputAlgorithm result = new ResultInputOutputAlgorithm();
        result.setAlgorithmName("FCFS");
        List<Integer> listRequests = new ArrayList<>(diskDriver.getRequests());
        Integer totalCylinders = 0;

        //Tratativa para saber qual elemento da lista de requisições está mais proximo do cilindro atual
        if(listRequests.get(0) < diskDriver.getcurrentCylinder()) {
            totalCylinders += diskDriver.getcurrentCylinder() - listRequests.get(0);
        }else {
            totalCylinders += listRequests.get(0) - diskDriver.getcurrentCylinder();
        }

        //Funcionamento do algoritmo que consiste em sempre atender o primeiro elemento da fila, independentemente dele estar longe, perto, para cima ou para baixo
        for (int i = 0; i < listRequests.size() - 1 ; i++){
            if(listRequests.get(i) < listRequests.get(i+1)) {
                totalCylinders += listRequests.get(i+1) - listRequests.get(i);
            }else {
                totalCylinders += listRequests.get(i) - listRequests.get(i+1);
            }
        }

        result.setTotalCylinders(totalCylinders);

        return result;
    }
}
