package unb.modules.inputoutput.Algorithms;

import unb.modules.inputoutput.dtos.DiskDriver;
import unb.modules.inputoutput.dtos.ResultInputOutputAlgorithm;

import java.util.List;

public class AlgorithmFCFS {

    public ResultInputOutputAlgorithm run(DiskDriver diskDriver){

        ResultInputOutputAlgorithm result = new ResultInputOutputAlgorithm();
        result.setAlgorithmName("FCFS");
        List<Integer> listRequests = diskDriver.getRequests();
        Integer totalCylinders = 0;

        if(listRequests.get(0) < diskDriver.getcurrentCylinder()) {
            totalCylinders += diskDriver.getcurrentCylinder() - listRequests.get(0);
        }else {
            totalCylinders += listRequests.get(0) - diskDriver.getcurrentCylinder();
        }

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
