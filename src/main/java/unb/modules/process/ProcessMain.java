package unb.modules.process;

import java.util.ArrayList;
import java.util.List;

import unb.modules.process.dtos.Procedure;
import unb.modules.process.dtos.ResultSchedullingProcess;
import unb.modules.process.enums.SchedullingAlgorithmEnum;
import unb.modules.process.enums.TypeSchedullingAlgorithmEnum;
import unb.modules.process.interfaces.SchedulingAlgorithm;
import unb.modules.process.scheduling.AlgorithmFIFO;
import unb.modules.process.scheduling.AlgorithmRR;
import unb.modules.process.scheduling.AlgorithmSJF;
import unb.utils.ManagerFile;

//O módulo de gerência de processos deverá ler da entrada padrão uma lista de processos com seus
//respectivos tempos de chegada e de duração, e deverá imprimir na saída padrão uma tabela contendo os
//valores para as seguintes métricas

//	• Tempo médio de execução total do processo - turnaround;
//	• Tempo médio de resposta;
//	• Tempo médio de espera.
public class ProcessMain {
	private List<Procedure> process = new ArrayList<Procedure>();

	public ProcessMain() {
	}

	public ProcessMain(List<Procedure> process) {
		this.process = process;
	}
    
    public void executeProcess(final String nameFile){
		String path = "files/process";
		
	    	//Lê o arquivo de entrada, separada cada linha em um processo e executa todos os algoritmos de escalonamento.
		String text = ManagerFile.readFile(path, nameFile);
		String[] lines = text.split("\n");

		for(int i = 0; i < lines.length; i++) {
			String[] values = lines[i].split(" ");
			
			if(values.length == 2){
				Integer arrivalTime = Integer.valueOf(values[0]);
				Integer durationTime = Integer.valueOf(values[1]);
				
				Procedure proc = new Procedure();
				proc.setArrivalTime(arrivalTime);
				proc.setDurationTime(durationTime);
				proc.setId(Long.valueOf(i));

				this.process.add(proc);
			}else {
				throw new RuntimeException("O arquivo de entrada não está com o formato de texto correto;");
			}
		}
		
		
//		TypeSchedullingAlgorithmEnum typeExec = TypeSchedullingAlgorithmEnum.NON_PREEMPTIVE;
//		if(algorithm.contains("PREEMPTIVE")) {
//			typeExec = TypeSchedullingAlgorithmEnum.PREEMPTIVE;
//		}

//        ProcessMain processManager = new ProcessMain(procedures);
        this.toSchedullerProcessWithAllAlgorithms();
//        this.toSchedullerProcess(algorithm, typeExec);
    }

	public void toSchedullerProcess(String typeAlgh, TypeSchedullingAlgorithmEnum typeExec) {
		SchedullingAlgorithmEnum type = null;
		SchedulingAlgorithm algorithm = null;

		if (typeAlgh.equals(SchedullingAlgorithmEnum.FIFO.getName())) {
			type = SchedullingAlgorithmEnum.FIFO;
			algorithm = new AlgorithmFIFO();
		} else if (typeAlgh.equals(SchedullingAlgorithmEnum.SJF.getName())) {
			type = SchedullingAlgorithmEnum.SJF;
			algorithm = new AlgorithmSJF();
		} else if (typeAlgh.equals(SchedullingAlgorithmEnum.SJF_P.getName())) {
			type = SchedullingAlgorithmEnum.SJF_P;
			algorithm = new AlgorithmSJF();
		} else if (typeAlgh.equals(SchedullingAlgorithmEnum.ROUND_ROBIN.getName())) {
			type = SchedullingAlgorithmEnum.ROUND_ROBIN;
			algorithm = new AlgorithmRR();
		} else {
			type = SchedullingAlgorithmEnum.FIFO;
			algorithm = new AlgorithmFIFO();
		}

		StringBuilder results = new StringBuilder();
		ResultSchedullingProcess resultRR = algorithm.execute(typeExec, this.process);
		results.append(this.generateStringResultSchedulling(type, resultRR));
//		
		this.generateResultFileSchedulling(results.toString());
	}

	public void toSchedullerProcessWithAllAlgorithms() {
		SchedulingAlgorithm algorithmRR = new AlgorithmRR();
		SchedulingAlgorithm algorithmSJF = new AlgorithmSJF();
		SchedulingAlgorithm algorithmFF = new AlgorithmFIFO();

		StringBuilder results = new StringBuilder();
		ResultSchedullingProcess resultFF = algorithmFF.execute(TypeSchedullingAlgorithmEnum.NON_PREEMPTIVE,
				this.process);
		results.append(this.generateStringResultSchedulling(SchedullingAlgorithmEnum.FIFO, resultFF));

		ResultSchedullingProcess resultSJF = algorithmSJF.execute(TypeSchedullingAlgorithmEnum.NON_PREEMPTIVE,
				this.process);
		results.append(this.generateStringResultSchedulling(SchedullingAlgorithmEnum.SJF, resultSJF));

//		ResultSchedullingProcess resultSJFP = algorithmSJF.execute(TypeSchedullingAlgorithmEnum.PREEMPTIVE,
//				this.process);
//		results.append(this.generateStringResultSchedulling(SchedullingAlgorithmEnum.SJF_P, resultSJFP));

		ResultSchedullingProcess resultRR = algorithmRR.execute(TypeSchedullingAlgorithmEnum.PREEMPTIVE, this.process);
		results.append(this.generateStringResultSchedulling(SchedullingAlgorithmEnum.ROUND_ROBIN, resultRR));
//		
		this.generateResultFileSchedulling(results.toString());
	}

	private String generateStringResultSchedulling(final SchedullingAlgorithmEnum algorithm,
			final ResultSchedullingProcess resultAlgorithm) {
		StringBuilder results = new StringBuilder();

		results.append(algorithm.getName()).append(" ").append(resultAlgorithm.getExecutionTime()).append(" ")
				.append(resultAlgorithm.getResponseTime()).append(" ").append(resultAlgorithm.getWaitTime())
				.append("\n");

		return results.toString();
	}

	private void generateResultFileSchedulling(final String results) {
		String path = "files/process";
		String nameFile = "schedulling_process_out.txt";

		ManagerFile.generateFile(path, nameFile, results);
		System.out.println(ManagerFile.readFile(path, nameFile));
	}

}
