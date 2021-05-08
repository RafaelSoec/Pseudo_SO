package unb.modules.process.interfaces;

import java.util.List;

import unb.modules.process.dtos.Procedure;
import unb.modules.process.dtos.ResultSchedullingProcess;

public interface SchedulingAlgorithm {
	ResultSchedullingProcess preemptiveExecution(List<Procedure> procedures);
	
	ResultSchedullingProcess nonPreemptiveExecution(List<Procedure> procedures);
}
