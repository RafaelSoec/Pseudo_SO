package unb.modules.inputoutput.dtos;

import unb.modules.inputoutput.enums.InputOutputEnum;

/** Jean Rodrigues Magalhães - 15/0079923
 *
 * DTO que facilita a transferencia de dados entre chamadas de rotinas e subrotinas com o resultado de cada algoritmo
 *
 * */
public class ResultInputOutputAlgorithm {
	private String algorithmName;
	private Integer totalCylinders;


	public String getAlgorithmName() {
		return this.algorithmName;
	}

	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public Integer getTotalCylinders() {
		return this.totalCylinders;
	}

	public void setTotalCylinders(Integer totalCylinders) {
		this.totalCylinders = totalCylinders;
	}

}
