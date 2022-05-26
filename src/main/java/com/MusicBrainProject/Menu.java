package com.MusicBrainProject;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final RequestService requestService;

    public Menu() {
        requestService = new RequestService();
    }

    public void print() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("For how many random words do you wanna search (between 5 and 20)");
            int number = getInput();
            if (number == Integer.MIN_VALUE) {
                System.out.println("Wrong number format, try again.");
            }
            else if (number > 20 | number < 5) {
                System.out.println("Number is too big or too small, try again");
            } else {
                System.out.println(number);
                List<Result> results = requestService.getSongInformation(number);
                results.stream().forEach(System.out::println);
                System.out.println("Do you wanna make another search? no if you wanna exit");
                String response = scanner.next();
                if (response.toLowerCase().equals("no")) {
                    break;
                }
            }
        }
    }
    public int getInput() {
        Scanner scanner = new Scanner(System.in);
        int number = Integer.MIN_VALUE;
        try {
            number = scanner.nextInt();
        } catch (InputMismatchException e) {
            return -1;
        }
        return number;
    }

}
