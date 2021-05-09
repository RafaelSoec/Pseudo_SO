package unb.modules.process.enums;

public enum TypeSchedullingAlgorithmEnum {
	NON_PREEMPTIVE("NON_PREEMPTIVE"),
	PREEMPTIVE("PREEMPTIVE");

	private String name;
	TypeSchedullingAlgorithmEnum(String name) {
		this.setName(name);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
