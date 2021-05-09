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

		// Sortear processos pelo tempo de chegada
		Collections.sort(procedures, new ProcessComparatorArrival());
		for (int i = 0; i < procedures.size(); i++) {
			// salvar tempos dos processos
			int timeLeft = procedures.get(i).getDurationTime();
			// verifica se o processo foi completamente executado
			while (timeLeft > 0) {
//				System.out.println("Executing process: " + procedures.get(i).getId());
				procedureList.add(procedures.get(i));
				timeLeft--;
			}
		}

		ResultSchedullingProcess result = new ResultSchedullingProcess();
		result = this.calculateAverageResults(procedureList);

		super.generateResultSchedullingFileAlgorithm(procedureList, SchedullingAlgorithmEnum.FIFO);

		return result;
	}

//	@Override
//	public ResultSchedullingProcess calculateAverageResults(List<Long> procedureList, List<Procedure> procedures) {
//		List<ResultSchedullingProcess> listAverageResult = new ArrayList<ResultSchedullingProcess>();
//
//		Double executionTime = 0D;
//		Double responseTime = 0D;
//		Double waitTime = 0D;
//		int cont = 0;
//		for (Procedure proc : procedures) {
//			executionTime = 0D;
//			responseTime = 0D;
//			waitTime = 0D;
//
//			for (int i = 0; i < procedureList.size(); i++) {
//				if (procedureList.get(i) == proc.getId()) {
//					// marcar a espera por atraso na iniciação da execuçao
//					// essa condição só deve ser satisfeita uma vez
//					if (executionTime <= 0 && i > proc.getArrivalTime()) {
//						responseTime = Double.valueOf(i - proc.getArrivalTime());
//						waitTime += responseTime;
//					}
//
////							// ativar contagem
//					if (cont < proc.getDurationTime()) {
//						executionTime++;
//					}
//					cont++;
//				} else {
//					if ((executionTime > 0 && cont < proc.getDurationTime())) {
//						executionTime++;
//						waitTime++;
//					}
//				}
//			}
//
//			ResultSchedullingProcess averageResult = new ResultSchedullingProcess();
//			averageResult.setExecutionTime(executionTime);
//			averageResult.setResponseTime(responseTime);
//			averageResult.setWaitTime(waitTime);
//			listAverageResult.add(averageResult);
//			cont = 0;
//		}
//
//		executionTime = 0D;
//		responseTime = 0D;
//		waitTime = 0D;
//		for (ResultSchedullingProcess result : listAverageResult) {
//			executionTime += result.getExecutionTime();
//			responseTime += result.getResponseTime();
//			waitTime += result.getWaitTime();
//		}
//
//		// limitar em duas casas decimais
//
//		ResultSchedullingProcess averageResult = new ResultSchedullingProcess();
//		averageResult.setExecutionTime(MathUtils.round((executionTime / procedures.size()), 2));
//		averageResult.setResponseTime(MathUtils.round((responseTime / procedures.size()), 2));
//		averageResult.setWaitTime(MathUtils.round((waitTime / procedures.size()), 2));
//
//		return averageResult;
//	}

}