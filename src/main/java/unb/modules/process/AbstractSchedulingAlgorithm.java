package unb.modules.process;

import java.util.ArrayList;
import java.util.List;

import unb.modules.process.dtos.Procedure;
import unb.modules.process.dtos.ResultSchedullingProcess;
import unb.modules.process.enums.SchedullingAlgorithmEnum;
import unb.modules.process.interfaces.SchedulingAlgorithm;
import unb.utils.ManagerFile;
import unb.utils.MathUtils;

public abstract class AbstractSchedulingAlgorithm implements SchedulingAlgorithm {
//	public abstract ResultSchedullingProcess calculateAverageResults(List<Procedure> procedureList);
	
	public abstract ResultSchedullingProcess preemptiveExecution(List<Procedure> procedures);

	public abstract ResultSchedullingProcess nonPreemptiveExecution(List<Procedure> procedures);

	protected List<ResultSchedullingProcess> generateResultsList(List<Procedure> procedures) {
		List<ResultSchedullingProcess> results = new ArrayList<ResultSchedullingProcess>();
		for (int i = 0; i < procedures.size(); i++) {
			ResultSchedullingProcess resultProcedure = new ResultSchedullingProcess();
			results.add(resultProcedure);
		}

		return results;
	}

//	protected ResultSchedullingProcess calculateAverageResults(List<Long> procedureList, List<Procedure> procedures,
//			SchedullingAlgorithmEnum alg) {
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
//				if (alg.equals(SchedullingAlgorithmEnum.ROUND_ROBIN)) {
//					if (i >= proc.getArrivalTime() && cont < proc.getDurationTime()) {
//						executionTime++;
//					}
//				}
//
//				if (procedureList.get(i) == proc.getId()) {
//					// marcar a espera por atraso na iniciação da execuçao
//					// essa condição só deve ser satisfeita uma vez
//					if (executionTime <= 0 && i > proc.getArrivalTime()) {
//						responseTime = Double.valueOf(i - proc.getArrivalTime());
//						waitTime += responseTime;
//					}
//
////					// ativar contagem
//					if (!alg.equals(SchedullingAlgorithmEnum.ROUND_ROBIN)) {
//						if (cont < proc.getDurationTime()) {
//							executionTime++;
//						}
//					}
//					cont++;
//				} else {
//					if ((executionTime > 0 && cont < proc.getDurationTime())) {
//						if (!alg.equals(SchedullingAlgorithmEnum.ROUND_ROBIN)) {
//							executionTime++;
//						}
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

//	protected ResultSchedullingProcess calculateAverageResults(List<ResultSchedullingProcess> results) {
//		ResultSchedullingProcess averageResult = new ResultSchedullingProcess();
//
//		Double waitTime = 0D;
//		Double responseTime = 0D;
//		Double executionTime = 0D;
//		for (ResultSchedullingProcess result : results) {
//			executionTime += result.getExecutionTime();
//			responseTime += result.getResponseTime();
//			waitTime += result.getWaitTime();
//		}
//
//		averageResult.setExecutionTime(executionTime / results.size());
//		averageResult.setResponseTime(responseTime / results.size());
//		averageResult.setWaitTime(waitTime / results.size());
//
//		return averageResult;
//	}

	protected List<Procedure> generateProcedureListAux(List<Procedure> procedures) {
		// Instanciar uma lista de processos auxiliar
		List<Procedure> proceduresAux = new ArrayList<Procedure>();
		for (Procedure proc : procedures) {
			Procedure process = new Procedure(proc);
			proceduresAux.add(process);
		}

		return proceduresAux;
	}

	protected String generateStringResultSchedulling(final String algorithm,
			final ResultSchedullingProcess resultAlgorithm) {
		StringBuilder results = new StringBuilder();

		results.append(algorithm).append(" ").append(resultAlgorithm.getExecutionTime()).append(" ")
				.append(resultAlgorithm.getResponseTime()).append(" ").append(resultAlgorithm.getWaitTime())
				.append("\n");

		return results.toString();
	}

	protected void generateResultSchedullingFileAlgorithm(List<Procedure> procedureList,
			final SchedullingAlgorithmEnum alg) {
		String path = "files/process/schedulling";
		String nameFile = alg.getName() + "schedulling_out.txt";
		StringBuilder results = new StringBuilder();

		results.append(alg.getName()).append("\n\n");
		for (int i = 0; i < procedureList.size(); i++) {

			int cont = 0, j = i;
			int nextProc = (i + 1);
			if (nextProc < procedureList.size()) {
				while (((i + 1) < procedureList.size()) && (procedureList.get(i) == procedureList.get(nextProc))) {
					i++;
					cont++;
					nextProc = (i + 1);
				}

				results.append("Rodar processo de [").append(procedureList.get(j).getId()).append("] de [").append(j)
						.append("] ate [").append(j + cont + 1).append("]").append("\n");
			}
		}

		ManagerFile.generateFile(path, nameFile, results.toString());
		System.out.println(ManagerFile.readFile(path, nameFile));
	}

	protected ResultSchedullingProcess calculateAverageResults(List<Procedure> procedureList) {
		List<ResultSchedullingProcess> listAverageResult = new ArrayList<ResultSchedullingProcess>();
		List<Long> proceduresActives = new ArrayList<Long>();

		for (Procedure proc : procedureList) {
			System.out.print(proc.getId() + " ");
		}
		System.out.print("\n");
		
		Double executionTime = 0D;
		Double responseTime = 0D;
		Double waitTime = 0D;
		for (int i = 0; i < procedureList.size(); i++) {
			Procedure proc = procedureList.get(i);
			int indexObj = proceduresActives.indexOf(proc.getId());
			// Verifica se o processo já esta na lista de processos
			if (indexObj >= 0) {
				// verifica se o processo esta sendo executado sequencialmente,
				// caso não esteja, isso indica que o processo já foi executado anteriormente
				int prevIndex = i - 1;
				if (prevIndex >= 0) {
					Procedure prevProc = procedureList.get(prevIndex);
					while (prevIndex >= 0 && prevProc.getId() != proc.getId()) {
						waitTime++;
						prevIndex--;
						prevProc = procedureList.get(prevIndex);
					}
				}

				ResultSchedullingProcess res = listAverageResult.get(indexObj);
				executionTime = res.getExecutionTime();
				executionTime++;
				res.setExecutionTime(executionTime);
				res.setWaitTime(waitTime);

			} else {
				executionTime = 1D;
				// verifico se algum processo foi executado anteriormente
				if (!proceduresActives.isEmpty()) {
					int qtdProcExec = i;
					int totalResponseTime = qtdProcExec - proc.getArrivalTime();
					totalResponseTime = totalResponseTime < 0 ? 0 : totalResponseTime;
					responseTime = Double.valueOf(totalResponseTime);
					waitTime = responseTime;
					
					if(waitTime > 0) {
						executionTime += responseTime;
					}
				}

				proceduresActives.add(proc.getId());
				ResultSchedullingProcess averageResult = new ResultSchedullingProcess();
				averageResult.setExecutionTime(executionTime);
				averageResult.setResponseTime(responseTime);
				averageResult.setWaitTime(waitTime);

				listAverageResult.add(averageResult);
			}

		}

		waitTime = 0D;
		responseTime = 0D;
		executionTime = 0D;
		for (ResultSchedullingProcess res : listAverageResult) {
			executionTime += res.getExecutionTime();
			responseTime += res.getResponseTime();
			waitTime += res.getWaitTime();
		}

		int max = listAverageResult.size();
		ResultSchedullingProcess averageResult = new ResultSchedullingProcess();
		averageResult.setExecutionTime(MathUtils.round((executionTime / max), 2));
		averageResult.setResponseTime(MathUtils.round((responseTime / max), 2));
		averageResult.setWaitTime(MathUtils.round((waitTime / max), 2));

		return averageResult;
	}
}
