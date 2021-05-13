package unb.modules.inputoutput;

import unb.modules.inputoutput.Algorithms.AlgorithmFCFS;
import unb.modules.inputoutput.Algorithms.AlgorithmSSTF;
import unb.modules.inputoutput.dtos.DiskDriver;
import unb.modules.inputoutput.dtos.ResultInputOutputAlgorithm;
import unb.modules.process.dtos.Procedure;
import unb.modules.process.dtos.ResultSchedullingProcess;
import unb.modules.process.enums.SchedullingAlgorithmEnum;
import unb.modules.process.enums.TypeSchedullingAlgorithmEnum;
import unb.modules.process.interfaces.SchedulingAlgorithm;
import unb.modules.process.scheduling.AlgorithmFIFO;
import unb.modules.process.scheduling.AlgorithmRR;
import unb.modules.process.scheduling.AlgorithmSJF;
import unb.utils.ManagerFile;

import java.util.ArrayList;
import java.util.List;

public class InputOutputMain {


    private DiskDriver diskDriver;

    public InputOutputMain() {
    }

    public InputOutputMain(DiskDriver diskDrivers) {
       this.diskDriver = diskDrivers;
    }


    public void toManageIOWithAllAlgorithms() {

        List<ResultInputOutputAlgorithm> list = new ArrayList<>();
        AlgorithmFCFS algorithmFCFS = new AlgorithmFCFS();
        AlgorithmSSTF algorithmSSTF = new AlgorithmSSTF();

        ResultInputOutputAlgorithm resultFCFS = algorithmFCFS.run(this.diskDriver);
        list.add(resultFCFS);

        ResultInputOutputAlgorithm resultSSTF = algorithmSSTF.run(this.diskDriver);
        list.add(resultSSTF);

        this.generateResultIOFileAlgorithm(list);

    }

    protected void generateResultIOFileAlgorithm(List<ResultInputOutputAlgorithm> resultList) {
        String path = "files/input_output_manager";
        String nameFile = "requests_out.txt";
        StringBuilder results = new StringBuilder();

        for (ResultInputOutputAlgorithm alg : resultList) {
            results.append(alg.getAlgorithmName()).append(" ").append(alg.getTotalCylinders()).append("\n");
        }

        ManagerFile.generateFile(path, nameFile, results.toString());
        System.out.println(ManagerFile.readFile(path, nameFile));
    }




}
