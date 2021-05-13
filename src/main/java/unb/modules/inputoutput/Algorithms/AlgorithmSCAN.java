package unb.modules.inputoutput.Algorithms;

import unb.modules.inputoutput.dtos.DiskDriver;
import unb.modules.inputoutput.dtos.ResultInputOutputAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmSCAN {

    public ResultInputOutputAlgorithm run(DiskDriver diskDriver){

        ResultInputOutputAlgorithm result = new ResultInputOutputAlgorithm();
        result.setAlgorithmName("SCAN");
        List<Integer> listRequests = new ArrayList<>(diskDriver.getRequests());

        Integer totalCylinders = 0, newCurrent = 0, oldValue = 0;
        int test = diskDriver.getLastCylinder() + 1;
        for (int i = 0; i < listRequests.size() - 1 ; i++){

            if (listRequests.get(i) < diskDriver.getcurrentCylinder() && test > diskDriver.getcurrentCylinder() - listRequests.get(i)) {
                test = diskDriver.getcurrentCylinder() - listRequests.get(i);
                newCurrent = listRequests.indexOf(listRequests.get(i));
            }


        }

        oldValue = listRequests.get(newCurrent);

        listRequests.remove(listRequests.get(newCurrent));

        totalCylinders += test;

        while (listRequests.size() != 0) {
            test = diskDriver.getLastCylinder() + 1;
            for (int i = 0; i < listRequests.size() ; i++){

                if (listRequests.get(i) < oldValue && test > oldValue - listRequests.get(i)) {
                    test = oldValue - listRequests.get(i);
                    newCurrent = i;
                    totalCylinders += test;
                    oldValue = listRequests.get(newCurrent);
                    listRequests.remove(listRequests.get(newCurrent));
                }

            }
            totalCylinders += oldValue;
            oldValue = 0;
            test = diskDriver.getLastCylinder() + 1;
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

            oldValue = diskDriver.getLastCylinder();
        }

        result.setTotalCylinders(totalCylinders);

        return result;
    }
}
