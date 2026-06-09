package gof.structural.facade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

public class FacadeEverydayDemo {

	public static void main(String[] args) throws IOException {
		var url = URI.create("http://www.google.com").toURL();

		// Decorator pattern example
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));

		String inputLine;
		while ((inputLine = bufferedReader.readLine()) != null) {
			System.out.println(inputLine);
		}

	}

}
