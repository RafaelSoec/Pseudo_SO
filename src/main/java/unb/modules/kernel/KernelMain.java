package unb.modules.kernel;

import unb.modules.inputoutput.InputOutputMain;
import unb.modules.kernel.enums.KernelExecutionEnum;
import unb.modules.memory.MemoryMain;
import unb.modules.process.ProcessMain;

public class KernelMain {
	private Integer code;
	private String fileName;

	public KernelMain() {
	}

	public KernelMain(String[] args) {
		this.getArgs(args);
	}

	public void execute() {
		this.execute(this.code, this.fileName);
	}

	public void execute(Integer code, String nameFile) {
		if (KernelExecutionEnum.PROCESS.getCode() == code) {
			ProcessMain proc = new ProcessMain();
			proc.executeProcess(nameFile);

		} else if (KernelExecutionEnum.MEMORY.getCode() == code) {
			MemoryMain memory = new MemoryMain();
			memory.executeMemoryProcess(nameFile);
		} else if (KernelExecutionEnum.INPUT_OUTPUT.getCode() == code) {
			InputOutputMain es = new InputOutputMain();
			es.executeManageInputOutput(nameFile);

		} else {
			throw new RuntimeException("Falha ao executar o kernel.");
		}
	}

	private void getArgs(String[] args) {
		if (args.length >= 2) {
			try {
				this.code = Integer.valueOf(args[0]);
			} catch (Exception e) {
				throw new RuntimeException(
						"O primeiro argumento precisa ser um número.\n Onde 1 = PROCESS, 2 =  MEMORY, 3 =  INPUT OUTPUT");
			}

			try {
				this.fileName = args[1];
				if (!this.fileName.contains(".txt") && !this.fileName.contains(".docx")
						&& !this.fileName.contains(".doc")) {
					throw new RuntimeException("O arquivo precisa ter uma extensão válida.");
				}
			} catch (Exception e) {
				throw new RuntimeException("Não foi possível identificar o segundo argumento.");
			}
		} else {
			throw new RuntimeException("Argumentos inválidos.");
		}
	}
}
