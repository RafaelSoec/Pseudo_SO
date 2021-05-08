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
		// No round Robin o tempo de chegada é zero pra todos processos
		for(Procedure proc : procedures) {
			proc.setArrivalTime(0);
		}
		
		// Sortear processos pelo tempo de chegada
		Collections.sort(procedures, new ProcessComparatorArrival());

		// Instanciar uma lista de processos auxiliar
		List<Procedure> proceduresAux = super.generateProcedureListAux(procedures);
		List<Long> procedureList = new ArrayList<Long>();
		this.executeLoop(proceduresAux, procedureList);

		ResultSchedullingProcess result = new ResultSchedullingProcess();
		result = super.calculateAverageResults(procedureList, procedures, SchedullingAlgorithmEnum.ROUND_ROBIN);

		super.generateResultSchedullingFileAlgorithm(procedureList, SchedullingAlgorithmEnum.ROUND_ROBIN);

		return result;
	}

	@Override
	public ResultSchedullingProcess nonPreemptiveExecution(List<Procedure> procedures) {
		// TODO Auto-generated method stub
		throw new RuntimeException("O algoritmo Round Robin é preemptivo.");
	}

	private void executeLoop(List<Procedure> proceduresAux, List<Long> results) {
		List<Integer> removedItems = new ArrayList<Integer>();

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
					j = QUANTUM;
					removedItems.add(i);
				}
			}
		}

		// remover processos executados
		Collections.reverse(removedItems);
		for (int item : removedItems) {
			proceduresAux.remove(item);
		}

		while (!proceduresAux.isEmpty()) {
			this.executeLoop(proceduresAux, results);
		}
	}

}
