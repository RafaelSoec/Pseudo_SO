package unb.modules.process.scheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import unb.modules.process.AbstractSchedulingAlgorithm;
import unb.modules.process.dtos.Procedure;
import unb.modules.process.dtos.ResultSchedullingProcess;
import unb.modules.process.enums.SchedullingAlgorithmEnum;
import unb.modules.process.utils.ProcessComparatorArrival;
import unb.modules.process.utils.ProcessComparatorArrivalAndDuration;
import unb.modules.process.utils.ProcessComparatorDuration;
import unb.modules.process.utils.ProcessComparatorDurationAndArrival;

//SJF: Shortest Job First
public class AlgorithmSJF extends AbstractSchedulingAlgorithm {

	@Override
	public ResultSchedullingProcess preemptiveExecution(List<Procedure> procedures) {

		List<Procedure> procedureList = new ArrayList<Procedure>();
		procedureList = this.createExecutionList(procedures);

		ResultSchedullingProcess result = new ResultSchedullingProcess();
		result = this.calculateAverageResults(procedureList);

		super.generateResultSchedullingFileAlgorithm(procedureList, SchedullingAlgorithmEnum.SJF);

		return result;
	}

	@Override
	public ResultSchedullingProcess nonPreemptiveExecution(List<Procedure> procedures) {
		List<Procedure> procedureList = new ArrayList<Procedure>();
		List<Procedure> proceduresAux = super.generateProcedureListAux(procedures);
		// Sortear processos pelo tempo de chegada e duracao
		Collections.sort(proceduresAux, new ProcessComparatorArrivalAndDuration());
		
		if (!proceduresAux.isEmpty()) {
			Procedure proc = proceduresAux.get(0);
			int durationTime = proc.getDurationTime();

			while (durationTime > 0) {
				procedureList.add(proc);
				durationTime--;
			}
			proceduresAux.remove(0);
		}

		// Sortear processos pelo tempo de duração
		Collections.sort(proceduresAux, new ProcessComparatorDuration());

		for (Procedure proced : proceduresAux) {
			int durationTime = proced.getDurationTime();
			while (durationTime > 0) {
				procedureList.add(proced);
				durationTime--;
			}
		}

		ResultSchedullingProcess result = new ResultSchedullingProcess();
		result = super.calculateAverageResults(procedureList);
		super.generateResultSchedullingFileAlgorithm(procedureList, SchedullingAlgorithmEnum.SJF);

		return result;
	}

	private List<Procedure> createExecutionList(List<Procedure> procedures) {
		List<Procedure> procedureList = new ArrayList<Procedure>();

		// Sortear processos pelo tempo de duração
		Collections.sort(procedures, new ProcessComparatorArrival());
		List<Procedure> proceduresAux = super.generateProcedureListAux(procedures);
		
		int actualIndex = 0;
		while (!proceduresAux.isEmpty()) {
			int nextIndex = actualIndex + 1;
			Procedure actualProc = proceduresAux.get(actualIndex);

			int durationTime = actualProc.getDurationTime();
			// Executa o ultimo processo até o final
			if (proceduresAux.size() == 1) {
				while (durationTime > 0) {
					procedureList.add(actualProc);
					durationTime--;
				}
				proceduresAux.remove(actualIndex);
			}

			// Executa um processo até que atinja o tempo de chegada de outro processo
			// ou até que finalize o processo
			if (nextIndex < proceduresAux.size()) {
				Procedure nextProc = proceduresAux.get(nextIndex);
				// A execução maxima entre um processo e outro é definido pelo seu tempo de
				// execução
				// ou a diferença entre os tempos de chegadas do processo atual e o proximo
				// processo
				int maxDesloc = nextProc.getArrivalTime() - actualProc.getArrivalTime();
				
				// restam dois componnentes na lista que chegaram ao mesmo tempo
				if(maxDesloc == 0 && proceduresAux.size() > 2) {
					maxDesloc = durationTime;
				}else {
					maxDesloc = (maxDesloc < 0 || durationTime < maxDesloc) ? durationTime : maxDesloc;
				}
				for (int j = 0; j < maxDesloc; j++) {
					durationTime--;
					// Executa o processo até o seu tempo de duração e depois o remove da fila
					if (durationTime <= 0) {
						proceduresAux.remove(actualIndex);
						durationTime = 0;
					}

					actualProc.setDurationTime(durationTime);
					procedureList.add(actualProc);
				}

				actualIndex++;
				// Evita erros de indices no array
				if (actualIndex >= proceduresAux.size()) {
					actualIndex = 0;
					// ordenar a lista por tempo de chegada
					Collections.sort(proceduresAux, new ProcessComparatorDurationAndArrival());
				}

			} else {
				actualIndex = 0;
			}
		}
		
		List<Procedure> newProcedureList = new ArrayList<Procedure>();
		// recuperar valores que são atualizados no processo de escalonamennto
		for (Procedure proc : procedureList) {
			for (Procedure proc2 : procedures) {
				if(proc2.getId() == proc.getId()) {
					newProcedureList.add(proc2);
					break;
				}
			}
		}

		return newProcedureList;
	}
}