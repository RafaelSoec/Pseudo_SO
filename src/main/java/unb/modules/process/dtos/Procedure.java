package unb.modules.process.dtos;

public class Procedure {
	private Long id;
	private int arrivalTime;
	private int durationTime;
	
	public Procedure() {
		
	}
	
	public Procedure(Procedure proc) {
		this.id = proc.getId();
		this.arrivalTime = proc.getArrivalTime();
		this.durationTime = proc.getDurationTime();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public int getDurationTime() {
		return durationTime;
	}
	public void setDurationTime(int durationTime) {
		this.durationTime = durationTime;
	}
}
