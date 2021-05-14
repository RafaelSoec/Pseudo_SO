package unb.modules.kernel.enums;

public enum KernelExecutionEnum {
    PROCESS(1),
    MEMORY(2),
    INPUT_OUTPUT(3);

    private Integer code;
    KernelExecutionEnum(Integer code) { 
    	this.setCode(code); 
    }
    
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}


}
