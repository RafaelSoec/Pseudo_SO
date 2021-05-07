package unb.modules.process.scheduling;

import java.util.Collections;
import java.util.List;

import unb.modules.process.dtos.Procedure;
import unb.modules.process.dtos.ResultSchedullingProcess;
import unb.modules.process.interfaces.SchedulingAlgorithm;
import unb.modules.process.utils.ProcessComparatorDuration;

//SJF: Shortest Job First
public class AlgorithmSJF implements SchedulingAlgorithm {

	@Override
	public ResultSchedullingProcess execute(List<Procedure> procedures) {
		ResultSchedullingProcess result = new ResultSchedullingProcess();
		Double executionTime = 0D;
		
		// Sortear processos pelo tempo de duração
		Collections.sort(procedures, new ProcessComparatorDuration());
		for (int i = 0; i < procedures.size(); i++) {
			int timeLeft = procedures.get(i).getDurationTime();
			// verifica se o processo foi completamente executado
			while (timeLeft > 0) {
				executionTime++;
//				System.out.println("Executing process: " + procedures.get(i).getId());
				timeLeft--;
			}
		}
		
		result.setExecutionTime(executionTime);

		return result;
	}

}