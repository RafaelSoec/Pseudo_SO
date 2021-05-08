package unb.modules.process.scheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import unb.modules.process.AbstractSchedulingAlgorithm;
import unb.modules.process.dtos.Procedure;
import unb.modules.process.dtos.ResultSchedullingProcess;
import unb.modules.process.enums.SchedullingAlgorithmEnum;
import unb.modules.process.utils.ProcessComparatorArrival;

// FIFO: First-In, First-Out
public class AlgorithmFIFO extends AbstractSchedulingAlgorithm {

	@Override
	public ResultSchedullingProcess preemptiveExecution(List<Procedure> procedures) {
		throw new RuntimeException("O algoritmo FIFO é não preemptivo.");
	}

	@Override
	public ResultSchedullingProcess nonPreemptiveExecution(List<Procedure> procedures) {
		List<Long> procedureList = new ArrayList<Long>();

		// Sortear processos pelo tempo de chegada
		Collections.sort(procedures, new ProcessComparatorArrival());
		for (int i = 0; i < procedures.size(); i++) {
			// salvar tempos dos processos
			int timeLeft = procedures.get(i).getDurationTime();
			// verifica se o processo foi completamente executado
			while (timeLeft > 0) {
//				System.out.println("Executing process: " + procedures.get(i).getId());
				procedureList.add(procedures.get(i).getId());
				timeLeft--;
			}
		}

		ResultSchedullingProcess result = new ResultSchedullingProcess();
		result = super.calculateAverageResults(procedureList, procedures, SchedullingAlgorithmEnum.FIFO);

		super.generateResultSchedullingFileAlgorithm(procedureList, SchedullingAlgorithmEnum.FIFO);

		return result;
	}

}