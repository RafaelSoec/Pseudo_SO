package unb.modules.inputoutput.enums;

public enum InputOutputEnum {
	FCFS("FCFS"),
	SSF("SSF"),
	SCAN("SCAN");

	private String name;
	InputOutputEnum(String name) {
		this.setName(name);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
