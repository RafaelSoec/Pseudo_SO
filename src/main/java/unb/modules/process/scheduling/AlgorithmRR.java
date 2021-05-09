package unb.modules.process.scheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import unb.modules.process.AbstractSchedulingAlgorithm;
import unb.modules.process.dtos.Procedure;
import unb.modules.process.dtos.ResultSchedullingProcess;
import unb.modules.process.enums.SchedullingAlgorithmEnum;
import unb.modules.process.utils.ProcessComparatorArrival;
import unb.utils.ManagerQueue;

//RR: Round Robin (com quantum = 2)
// Quantum é o periodo de tempo entre cada interrupção
public class AlgorithmRR extends AbstractSchedulingAlgorithm {
	private final static Integer QUANTUM = 2;

	@Override
	public ResultSchedullingProcess preemptiveExecution(List<Procedure> procedures) {
		
		List<Procedure> procedureList = this.generateProcedureResultList(procedures);

		ResultSchedullingProcess result = new ResultSchedullingProcess();
		result = super.calculateAverageResults(procedureList);

		super.generateResultSchedullingFileAlgorithm(procedureList, SchedullingAlgorithmEnum.ROUND_ROBIN);

		return result;
	}

	@Override
	public ResultSchedullingProcess nonPreemptiveExecution(List<Procedure> procedures) {
		// TODO Auto-generated method stub
		throw new RuntimeException("O algoritmo Round Robin é preemptivo.");
	}
	
	private List<Procedure> generateProcedureResultList(List<Procedure> procedures){
		ManagerQueue<Procedure> readyQueue = new ManagerQueue<Procedure>();

		// Instanciar uma lista de processos auxiliar
		List<Procedure> proceduresAux = super.generateProcedureListAux(procedures);
		// Sortear processos pelo tempo de chegada
		Collections.sort(proceduresAux, new ProcessComparatorArrival());
//		// No round Robin o tempo de chegada é zero pra todos processos
//		for(Procedure proc : proceduresAux) {
//			proc.setArrivalTime(0);
//		}

		List<Procedure> procedureList = new ArrayList<Procedure>();
		int execTimeAlg = 0;
		while (!proceduresAux.isEmpty()) {
			boolean isExecutedProcess = false;

			if (!readyQueue.isEmpty()) {
				// executar processos pelo quantum definido
				for (int j = 0; j < QUANTUM; j++) {
					// primeiro processo na fila de pronto
					Procedure firstProc = readyQueue.get(0);
					int timeLeft = firstProc.getDurationTime();
					if (timeLeft > 0) {
						timeLeft--;
						firstProc.setDurationTime(timeLeft);
						procedureList.add(firstProc);
					} else {
						j = QUANTUM;
						// remove processo da fila de pronto
						readyQueue.remove(0);
						proceduresAux.remove(0);
						isExecutedProcess = true;
					}
					execTimeAlg++;
				}
			}

			// Verifica o tamanho da lista de pronnto pra confirmar se é necesário adicionar
			// mais algum processo
			if (readyQueue.size() < proceduresAux.size()) {
				for (Procedure proc : proceduresAux) {
					// verifica se o processo chegou ou está na fila de pronto
					if (execTimeAlg >= proc.getArrivalTime()) {
						if(!readyQueue.contains(proc)) {
							readyQueue.insert(proc);
						}
					}
				}
			}

			if (execTimeAlg != 0) {
				// verifica se o processo foi executado, se não joga o processo pro fim da fila
				// e vai pro proximo
				if (!isExecutedProcess) {
					readyQueue.next();
				}
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
