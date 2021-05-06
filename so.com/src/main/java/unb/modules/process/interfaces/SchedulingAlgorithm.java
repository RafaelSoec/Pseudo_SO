package unb.modules.process.interfaces;

import java.util.List;

import unb.modules.process.dtos.ProcessDTO;

public interface SchedulingAlgorithm {
	void execute(List<ProcessDTO> procedures);
}
