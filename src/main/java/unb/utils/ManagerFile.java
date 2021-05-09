package unb.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ManagerFile {
	public static void generateFile(final String path, final String nameFile, final String message) {
		try {
			File dir = new File(path);
			dir.mkdirs();

			FileWriter arq = new FileWriter(dir + "/" + nameFile);
			PrintWriter writeArq = new PrintWriter(arq);
			writeArq.printf(message);
			arq.close();
		} catch (IOException e) {
			throw new RuntimeException("Falha ao gerar arquivo.");
		}
	}

	public static void deleteFolder(final String path) {
		File index = new File(path);
		File[] files = index.listFiles();

		if (index.exists()) {
			for (File file : files) {
				file.delete();
			}
			index.delete();
		}
	}

	public static void deleteFile(final String path, final String nameFile) {
		File index = new File(path);
		File[] files = index.listFiles();

		if (index.exists()) {
			for (File file : files) {
				if (file.getName().equals(nameFile)) {
					file.delete();
				}
			}
		}
	}

	public static void writeFile(final String path, final String nameFile, final String message) {
		StringBuilder text = new StringBuilder();
		text.append(readFile(path, nameFile));
		text.append(message);

		generateFile(path, nameFile, text.toString());
	}

	public static String readFile(final String path, final String nameFile) {
		try {
			StringBuilder text = new StringBuilder();
			File arq = new File(path + "/" + nameFile);
			Scanner myReader = new Scanner(arq);

			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				text.append(data);
				text.append("\n");
			}
			myReader.close();

			return text.toString();
		} catch (IOException e) {
			throw new RuntimeException("Falha ao ler arquivo.");
		}
	}
}
