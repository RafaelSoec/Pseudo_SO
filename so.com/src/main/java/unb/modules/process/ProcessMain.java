package unb.modules.process;

import java.util.List;

import unb.modules.process.dtos.ProcessDTO;
import unb.modules.process.interfaces.SchedulingAlgorithm;
import unb.modules.process.scheduling.AlgorithmFIFO;

public class ProcessMain {
	private List<ProcessDTO> process;

	public ProcessMain() {
	}
	
	public ProcessMain(List<ProcessDTO> process) {
		this.process = process;
	}
	
	public void toSchedullerProcess(){
		SchedulingAlgorithm algorithm = new AlgorithmFIFO();
		
		algorithm.execute(process);
	}

}
