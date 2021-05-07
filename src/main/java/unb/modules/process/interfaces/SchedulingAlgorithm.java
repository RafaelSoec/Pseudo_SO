package unb.modules.process.interfaces;

import java.util.List;

import unb.modules.process.dtos.Procedure;
import unb.modules.process.dtos.ResultSchedullingProcess;

public interface SchedulingAlgorithm {
	//return time of execution for procedures
	ResultSchedullingProcess execute(List<Procedure> procedures);
}
