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
		List<Procedure> procedureList = new ArrayList<Procedure>();
		// Instanciar uma lista de processos auxiliar
		List<Procedure> proceduresAux = super.generateProcedureListAux(procedures);
		// Sortear processos pelo tempo de chegada
		Collections.sort(proceduresAux, new ProcessComparatorArrival());
		
		//iniciar semaforo
		for (int i = 0; i < proceduresAux.size(); i++) {
			// salvar tempos dos processos
			int timeLeft = proceduresAux.get(i).getDurationTime();
			// verifica se o processo foi completamente executado
			while (timeLeft > 0) {
//				System.out.println("Executing process: " + procedures.get(i).getId());
				procedureList.add(proceduresAux.get(i));
				timeLeft--;
			}
		}

		ResultSchedullingProcess result = new ResultSchedullingProcess();
		result = this.calculateAverageResults(procedureList);

		super.generateResultSchedullingFileAlgorithm(procedureList, SchedullingAlgorithmEnum.FIFO);

		return result;
	}
}