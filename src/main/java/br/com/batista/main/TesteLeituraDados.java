package br.com.batista.main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TesteLeituraDados {

	public static void main(String[] args) throws IOException {

		Scanner scanner = new Scanner(new File("listCarros.csv"));
		
		while(scanner.hasNextLine()) {
			String linha = scanner.nextLine();
			System.out.println(linha);
		}
		
	}

}
