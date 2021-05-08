package unb.modules.process.enums;

public enum SchedullingAlgorithmEnum {
	FIFO("FIFO"),
	SJF("SJF"),
	ROUND_ROBIN("RR");

	private String name;
	SchedullingAlgorithmEnum(String name) {
		this.setName(name);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
