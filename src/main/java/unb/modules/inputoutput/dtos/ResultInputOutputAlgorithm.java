package unb.modules.inputoutput.dtos;

import unb.modules.inputoutput.enums.InputOutputEnum;

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
