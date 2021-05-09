package unb.modules.process.dtos;

public class ResultSchedullingProcess {
	private Double turnaroundTime = 0D;
	private Double executionTime = 0D;
	private Double responseTime = 0D;
	private Double waitTime = 0D;
	
	public Double getExecutionTime() {
		return executionTime;
	}
	public void setExecutionTime(Double executionTime) {
		this.executionTime = executionTime;
	}
	public Double getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(Double responseTime) {
		this.responseTime = responseTime;
	}
	public Double getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(Double waitTime) {
		this.waitTime = waitTime;
	}
	public Double getTurnaroundTime() {
		return turnaroundTime;
	}
	public void setTurnaroundTime(Double turnaroundTime) {
		this.turnaroundTime = turnaroundTime;
	}
}
