package unb.modules.process.scheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import unb.modules.process.AbstractSchedulingAlgorithm;
import unb.modules.process.dtos.Procedure;
import unb.modules.process.dtos.ResultSchedullingProcess;
import unb.modules.process.enums.SchedullingAlgorithmEnum;
import unb.modules.process.utils.ProcessComparatorArrival;
import unb.modules.process.utils.ProcessComparatorDuration;
import unb.modules.process.utils.ProcessComparatorDurationAndArrival;

//SJF: Shortest Job First
public class AlgorithmSJF extends AbstractSchedulingAlgorithm {

	@Override
	public ResultSchedullingProcess preemptiveExecution(List<Procedure> procedures) {
		// Sortear processos pelo tempo de duração
		Collections.sort(procedures, new ProcessComparatorArrival());

		List<Long> procedureList = new ArrayList<Long>();
		List<Procedure> proceduresAux = super.generateProcedureListAux(procedures);
		procedureList = this.createExecutionList(proceduresAux);
		
		ResultSchedullingProcess result = new ResultSchedullingProcess();
		result = super.calculateAverageResults(procedureList, procedures, SchedullingAlgorithmEnum.SJF);
		
		super.generateResultSchedullingFileAlgorithm(procedureList, SchedullingAlgorithmEnum.SJF);

		return result;
	}

	@Override
	public ResultSchedullingProcess nonPreemptiveExecution(List<Procedure> procedures) {
		// Sortear processos pelo tempo de chegada e duracao
		Collections.sort(procedures, new ProcessComparatorDurationAndArrival());

		List<Long> procedureList = new ArrayList<Long>();
		List<Procedure> proceduresAux = super.generateProcedureListAux(procedures);
		
		if(!proceduresAux.isEmpty()) {
			Procedure proc = proceduresAux.get(0);
			int durationTime = proc.getDurationTime();
			
			while(durationTime > 0) {
				procedureList.add(proc.getId());
				durationTime--;
			}
			proceduresAux.remove(0);
		}
		
		// Sortear processos pelo tempo de duração
		Collections.sort(proceduresAux, new ProcessComparatorDuration());
		
		for (Procedure proced : proceduresAux) {
			int durationTime = proced.getDurationTime();
			while(durationTime > 0) {
				procedureList.add(proced.getId());
				durationTime--;
			}
		}

		ResultSchedullingProcess result = new ResultSchedullingProcess();
		result = super.calculateAverageResults(procedureList, procedures, SchedullingAlgorithmEnum.SJF);
		super.generateResultSchedullingFileAlgorithm(procedureList, SchedullingAlgorithmEnum.SJF);

		return result;
	}

	private List<Long> createExecutionList(List<Procedure> procedures) {
		List<Long> procedureList = new ArrayList<Long>();
		
		int actualIndex = 0;
		while (!procedures.isEmpty()) {
			int nextIndex = actualIndex + 1;
			Procedure actualProc = procedures.get(actualIndex);

			int durationTime = actualProc.getDurationTime();
			// Executa o ultimo processo até o final
			if(procedures.size() == 1) {
				while(durationTime > 0) {
					procedureList.add(actualProc.getId());
					durationTime--;
				}
				procedures.remove(actualIndex);
			}
			
			//Executa um processo até que atinja o tempo de chegada de outro processo
			// ou até que finalize o processo
			if (nextIndex < procedures.size()) {
				Procedure nextProc = procedures.get(nextIndex);
				// A execução maxima entre um processo e outro é definido pelo seu tempo de execução
				// ou a diferença entre os tempos de chegadas do processo atual e o proximo processo
				int maxDesloc =  nextProc.getArrivalTime() - actualProc.getArrivalTime();
				maxDesloc = (maxDesloc < 0 || durationTime < maxDesloc) ? durationTime : maxDesloc;
				for (int j = 0; j < maxDesloc; j++) {
					durationTime--;
					//Executa o processo até o seu tempo de duração e depois o remove da fila
					if (durationTime <= 0) {
						procedures.remove(actualIndex);
						durationTime = 0;
					}

					actualProc.setDurationTime(durationTime);
					procedureList.add(actualProc.getId());
				}

				actualIndex++;
				//Evita erros de indices no array
				if (actualIndex >= procedures.size()) {
					actualIndex = 0;
					// ordenar a lista por tempo de chegada
					Collections.sort(procedures, new ProcessComparatorDuration());
				}

			} else {
				actualIndex = 0; 
			}
		}

		return procedureList;
	}
}