package unb.modules.inputoutput;

import java.util.ArrayList;
import java.util.List;

import unb.modules.inputoutput.Algorithms.AlgorithmFCFS;
import unb.modules.inputoutput.Algorithms.AlgorithmSCAN;
import unb.modules.inputoutput.Algorithms.AlgorithmSSTF;
import unb.modules.inputoutput.dtos.DiskDriver;
import unb.modules.inputoutput.dtos.ResultInputOutputAlgorithm;
import unb.utils.ManagerFile;
/** Jean Rodrigues Magalhães - 15/0079923
 *
 * Nesta classe ocorrem as principais chamadas de função e utilização de cada algoritmo de gerencia de entrada/saída toManageIOWithAllAlgorithms()
 * e contém o método generateResultIOFileAlgorithm() que gera o arquivo com os dados solicitados de saída.
 *
 * */
public class InputOutputMain {

    private DiskDriver diskDriver;

    public InputOutputMain() {
    	this.diskDriver  = new DiskDriver();
    }

    public InputOutputMain(DiskDriver diskDrivers) {
       this.diskDriver = diskDrivers;
    }

    
    public void executeManageInputOutput(String nameFileInputOutput) {
    	this.toManageInputOutput(nameFileInputOutput);
    }
    

	public  void toManageInputOutput(final String nameFile){
		String path = "files/input_output_manager";

		List<Integer> requests = new ArrayList<>();
		String text = ManagerFile.readFile(path, nameFile);

		String[] lines = text.split("\n");

		for(int i = 0; i < lines.length; i++) {
			String[] values = lines[i].split(" ");
			if(values.length == 1) {
				if (i == 0) {
					this.diskDriver.setLastCylinder(Integer.parseInt(lines[i]));
				}
				if (i == 1) {
					this.diskDriver.setcurrentCylinder(Integer.parseInt(lines[i]));
				}
				if (i > 1) {
					requests.add(Integer.parseInt(lines[i]));
				}
			}else {
				throw new RuntimeException("O arquivo de entrada não está com o formato de texto correto;");
			}

		}
		this.diskDriver.setRequests(requests);

//		InputOutputMain ioManager = new InputOutputMain(diskDriver);
		this.toManageIOWithAllAlgorithms();
	}


    public void toManageIOWithAllAlgorithms() {
        List<ResultInputOutputAlgorithm> list = new ArrayList<>();
        AlgorithmFCFS algorithmFCFS = new AlgorithmFCFS();
        AlgorithmSSTF algorithmSSTF = new AlgorithmSSTF();
        AlgorithmSCAN algorithmSCAN = new AlgorithmSCAN();

        ResultInputOutputAlgorithm resultFCFS = algorithmFCFS.run(this.diskDriver);
        list.add(resultFCFS);

        ResultInputOutputAlgorithm resultSSTF = algorithmSSTF.run(this.diskDriver);
        list.add(resultSSTF);

        ResultInputOutputAlgorithm resultSCAN = algorithmSCAN.run(this.diskDriver);
        list.add(resultSCAN);

        this.generateResultIOFileAlgorithm(list);
    }

    protected void generateResultIOFileAlgorithm(List<ResultInputOutputAlgorithm> resultList) {
        String path = "files/input_output_manager";
        String nameFile = "requests_out.txt";

        System.out.println("-----------------------\n");
        System.out.println("Módulo de Entrada/Saída:\n");

        StringBuilder results = new StringBuilder();

        for (ResultInputOutputAlgorithm alg : resultList) {
            results.append(alg.getAlgorithmName()).append(" ").append(alg.getTotalCylinders()).append("\n");
        }

        ManagerFile.generateFile(path, nameFile, results.toString());
        System.out.println(ManagerFile.readFile(path, nameFile));
    }




}
