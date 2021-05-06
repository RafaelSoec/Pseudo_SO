package unb.modules.process.scheduling;

import java.util.List;

import unb.modules.process.dtos.ProcessDTO;
import unb.modules.process.interfaces.SchedulingAlgorithm;

public class AlgorithmFIFO implements SchedulingAlgorithm {

	@Override
	public void execute(List<ProcessDTO> procedures) {
		// TODO Auto-generated method stub
		
		for(ProcessDTO procedure: procedures) {
			System.out.println("FIFO in execution - Duration Time: " + procedure.getDurationTime() + " Arrival Time: " + procedure.getArrivalTime());
		}
	}

}