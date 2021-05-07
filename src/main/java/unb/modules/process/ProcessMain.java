package unb.modules.process;

import java.util.ArrayList;
import java.util.List;

import unb.modules.process.dtos.Procedure;
import unb.modules.process.dtos.ResultSchedullingProcess;
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

	public void toSchedullerProcess() {
		SchedulingAlgorithm algorithmRR = new AlgorithmRR();
		SchedulingAlgorithm algorithmSJF = new AlgorithmSJF();
		SchedulingAlgorithm algorithmFF = new AlgorithmFIFO();

//		System.out.println("\nExecution FIFO \n");
//		System.out.println("\nTime of Execution FIFO: " + algorithmFF.execute(this.process));
//		System.out.println("\nExecution SJF \n");
//		System.out.println("\nTime of Execution SJF: " + algorithmSJF.execute(this.process));
//		System.out.println("\nExecution Round Robin \n");
//		System.out.println("\nTime of Execution Round Robin: " + algorithmRR.execute(this.process));

		StringBuilder results = new StringBuilder();
		ResultSchedullingProcess resultFF = algorithmFF.execute(this.process);
		results.append(this.generateStringResultSchedulling("FIFO", resultFF));
		
		ResultSchedullingProcess resultSJF = algorithmSJF.execute(this.process);
		results.append(this.generateStringResultSchedulling("SJF", resultSJF));
		
		ResultSchedullingProcess resultRR = algorithmRR.execute(this.process);
		results.append(this.generateStringResultSchedulling("RR", resultRR));
		
		this.generateResultFileSchedulling(results.toString());
	}

	private String generateStringResultSchedulling(final String algorithm, final ResultSchedullingProcess resultAlgorithm) {
		StringBuilder results = new StringBuilder();

		results.append(algorithm).append(" ")
				.append(resultAlgorithm.getExecutionTime()).append(" ")
				.append(resultAlgorithm.getResponseTime()).append(" ")
				.append(resultAlgorithm.getWaitTime())
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
