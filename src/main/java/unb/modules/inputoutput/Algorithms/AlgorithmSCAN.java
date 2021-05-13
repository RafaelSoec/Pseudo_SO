package unb.modules.inputoutput.Algorithms;

import unb.modules.inputoutput.dtos.DiskDriver;
import unb.modules.inputoutput.dtos.ResultInputOutputAlgorithm;

import java.util.ArrayList;
import java.util.List;

/** Jean Rodrigues Magalhães - 15/0079923
 *
 * Nesta classe é implementado o algoritmo SCAN:
 * O braço do disco começa em uma ponta do disco e se movimenta
 * em direção à outra ponta, atendendo aos pedidos assim que
 * chega em cada cilindro, até atingir a outra ponta do disco
 *
 * */
public class AlgorithmSCAN {

    public ResultInputOutputAlgorithm run(DiskDriver diskDriver){
        //instancia de objetos e recpção dos dados provindo do diskDriver
        ResultInputOutputAlgorithm result = new ResultInputOutputAlgorithm();
        result.setAlgorithmName("SCAN");

        List<Integer> listRequests = new ArrayList<>(diskDriver.getRequests());
        List<Integer> listAux = new ArrayList<>();

        //a variavel test tem como função neste módulo, fazer as testagens de menor caminho entre o atual e possivel proximo cilindro
        Integer totalCylinders = 0, newCurrent = 0, oldValue = diskDriver.getcurrentCylinder();
        Integer test = diskDriver.getLastCylinder() + 1, aux =0, originalSize;

        //fazemos uma lista auxiliar para pegar apenas os cilindro de posições menores que as do atual, para seguir o sentido de CIMA PARA BAIXO
        //com o braço de leitura
        for(Integer element : listRequests){
            if (element < oldValue){
                listAux.add(element);
            }
        }

        //nesta parte é descoberto, dentre todas as requisições, qual será o próximo cilindro abaixo do atual e mais proximo
        for (int i = 0; i < listRequests.size() - 1 ; i++){
            if (listRequests.get(i) < diskDriver.getcurrentCylinder() && test > diskDriver.getcurrentCylinder() - listRequests.get(i)) {
                test = diskDriver.getcurrentCylinder() - listRequests.get(i);
                newCurrent = listRequests.indexOf(listRequests.get(i));
            }
        }

        //oldvalue corresponde ao elemento que já foi retirado da lista mas será comparado com o proximo para se calcular a distancia
        oldValue = listRequests.get(newCurrent);
        originalSize = listRequests.size();
        listRequests.remove(listRequests.get(newCurrent));

        //incremento da contagem de cilindros
        totalCylinders += test;


        while (listRequests.size() != 0) {
            //para assegurar que a variavel test sempre funcione, é atribuido um valor imediatamente após o ultimo cilindro do disco
            test = diskDriver.getLastCylinder() + 1;

            //esta parte cuida dos elementos abaixo do atual elemento
            while (listRequests.size() + listAux.size() != originalSize) {
                for (int i = 0; i < listRequests.size(); i++) {
                    if (listRequests.get(i) < oldValue && test > oldValue - listRequests.get(i)) {
                        test = oldValue - listRequests.get(i);
                        newCurrent = i;
                    }
                }
                totalCylinders += test;
                test = diskDriver.getLastCylinder() + 1;
                oldValue = listRequests.get(newCurrent);
                listRequests.remove(listRequests.get(newCurrent));
            }

            //fazemos entao com que o elemento atual seja 0
            totalCylinders += oldValue;
            oldValue = 0;
            test = diskDriver.getLastCylinder() + 1;

            //esta parte cuida dos elementos acima
            while (listRequests.size() != 0){
                for (int i = 0; i < listRequests.size() ; i++){
                    if (listRequests.get(i) > oldValue && test > listRequests.get(i) - oldValue) {
                        test = listRequests.get(i) - oldValue;
                        newCurrent = i;
                    }
                }

                totalCylinders += test;
                test = diskDriver.getLastCylinder() + 1;
                oldValue = listRequests.get(newCurrent);
                listRequests.remove(listRequests.get(newCurrent));
            }
            //garantimos entao que apoós todas as requisições, o braço alcance o ultimo cilindro passado via arquivos
            oldValue = diskDriver.getLastCylinder();
        }

        result.setTotalCylinders(totalCylinders);

        return result;
    }
}
