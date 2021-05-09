package unb.modules.process.interfaces;

import java.util.List;

import unb.modules.process.dtos.Procedure;
import unb.modules.process.dtos.ResultSchedullingProcess;
import unb.modules.process.enums.TypeSchedullingAlgorithmEnum;

public interface SchedulingAlgorithm {
	ResultSchedullingProcess execute(TypeSchedullingAlgorithmEnum type, List<Procedure> procedures);

	ResultSchedullingProcess preemptiveExecution(List<Procedure> procedures);
	
	ResultSchedullingProcess nonPreemptiveExecution(List<Procedure> procedures);
}
