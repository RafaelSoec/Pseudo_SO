package unb.modules.inputoutput.enums;
/** Jean Rodrigues Magalhães - 15/0079923
 *
 * Enum com os nomes de cada algoritmo
 *
 * */
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
