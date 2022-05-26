package com.MusicBrainProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.List;


public class MusicBrainProjectApplication {

	public static void main(String[] args) {
		final Menu menu= new Menu();
		menu.print();

	}

}
