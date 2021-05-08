package unb.modules.process.scheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import unb.modules.process.AbstractSchedulingAlgorithm;
import unb.modules.process.dtos.Procedure;
import unb.modules.process.dtos.ResultSchedullingProcess;
import unb.modules.process.enums.SchedullingAlgorithmEnum;
import unb.modules.process.utils.ProcessComparatorArrival;

//RR: Round Robin (com quantum = 2)
// Quantum é o periodo de tempo entre cada interrupção
public class AlgorithmRR extends AbstractSchedulingAlgorithm {
	private final static Integer QUANTUM = 2;


	@Override
	public ResultSchedullingProcess preemptiveExecution(List<Procedure> procedures) {
		// Sortear processos pelo tempo de chegada
		Collections.sort(procedures, new ProcessComparatorArrival());

		// Instanciar uma lista de processos auxiliar
		List<Procedure> proceduresAux = super.generateProcedureListAux(procedures);
		List<Long> procedureList = new ArrayList<Long>();
		this.executeLoop(proceduresAux, procedureList);
		
		ResultSchedullingProcess result = new ResultSchedullingProcess();
		result = super.calculateAverageResults(procedureList, procedures);
		
		super.generateResultSchedullingFileAlgorithm(procedureList, SchedullingAlgorithmEnum.ROUND_ROBIN);

		return result;
	}

	@Override
	public ResultSchedullingProcess nonPreemptiveExecution(List<Procedure> procedures) {
		// TODO Auto-generated method stub
		throw new RuntimeException("O algoritmo Round Robin é preemptivo.");
	}

	private void executeLoop(List<Procedure> proceduresAux, List<Long> results) {
		
		for (int i = 0; i < proceduresAux.size(); i++) {
//			if (proceduresAux.get(i).getDurationTime() > 0) {
//				System.out.println("Executing process: " + proceduresAux.get(i).getId());
//			}
			
			// executar processos pelo quantum definido
			for (int j = 0; j < QUANTUM; j++) {
				int timeLeft = proceduresAux.get(i).getDurationTime();
				// verifica se o processo foi completamente executado
				if (timeLeft > 0) {
					timeLeft--;
					proceduresAux.get(i).setDurationTime(timeLeft);
//					System.out.println("Time to complete: " + timeLeft);
					results.add(proceduresAux.get(i).getId());
				} else {
					// remover processos executados
					proceduresAux.remove(i);
					j = QUANTUM;
				}
			}
		}

		while (!proceduresAux.isEmpty()) {
			this.executeLoop(proceduresAux, results);
		}
	}

}
