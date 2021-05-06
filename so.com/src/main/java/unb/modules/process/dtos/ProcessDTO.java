package unb.modules.process.dtos;

public class ProcessDTO {
	private Long id;
	private long arrivalTime;
	private long durationTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public long getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(long arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public long getDurationTime() {
		return durationTime;
	}
	public void setDurationTime(long durationTime) {
		this.durationTime = durationTime;
	}
}
