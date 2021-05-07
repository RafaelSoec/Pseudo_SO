package unb.modules.process.scheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import unb.modules.process.dtos.Procedure;
import unb.modules.process.dtos.ResultSchedullingProcess;
import unb.modules.process.interfaces.SchedulingAlgorithm;
import unb.modules.process.utils.ProcessComparatorArrival;

//RR: Round Robin (com quantum = 2)
// Quantum é o periodo de tempo entre cada interrupção
public class AlgorithmRR implements SchedulingAlgorithm {
	private final static Integer QUANTUM = 2;

	@Override
	public ResultSchedullingProcess execute(List<Procedure> procedures) {
		ResultSchedullingProcess result = new ResultSchedullingProcess();
		// Sortear processos pelo tempo de chegada
		Collections.sort(procedures, new ProcessComparatorArrival());

		// Instanciar uma lista de processos auxiliar
		List<Procedure> proceduresAux = new ArrayList<Procedure>(procedures);
		Double executionTime = this.executeLoop(proceduresAux);

		result.setExecutionTime(executionTime);
		
		return result;
	}
	
	private Double executeLoop(List<Procedure> proceduresAux) {
		Double executionTime = 0D;
		
		for (int i = 0; i < proceduresAux.size(); i++) {
			if( proceduresAux.get(i).getDurationTime() > 0) {
//				System.out.println("Executing process: " + proceduresAux.get(i).getId());
			}
			// executar processos pelo quantum definido
			for (int j = 0; j < QUANTUM; j++) {
				int timeLeft = proceduresAux.get(i).getDurationTime();
				// verifica se o processo foi completamente executado
				if (timeLeft > 0) {
					timeLeft--;
					executionTime++;
					proceduresAux.get(i).setDurationTime(timeLeft);
//					System.out.println("Time to complete: " + timeLeft);
				}else {
					// remover processos executados
					proceduresAux.remove(i);
					j = QUANTUM;
				}
			}
		}
		
		while(!proceduresAux.isEmpty()) {
			executionTime += this.executeLoop(proceduresAux);
		}
		
		return executionTime;
	}

}
