package com.MusicBrainProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.List;


public class MusicBrainProjectApplication {

	public static void main(String[] args) {
		RequestService requestService = new RequestService();
		List<String> randoms;
		try {
			randoms = requestService.getRandomWords(15);
			List<Result> songInformations = requestService.getSongInformation(randoms);
			for (Result r : songInformations) {
				System.out.println(r.getRandomWord() + " - " + r.getSongInformation());
			}
		} catch (IOException | InterruptedException e) {
			System.out.println(e.getMessage());
		}

	}

}
