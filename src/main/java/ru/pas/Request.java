package ru.pas;

import java.util.List;

public class Request {
    private final String[] parts;
    public static List<String> propertiesList = List.of("BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD, SQUARE, SUNNY".split(", "));
    public static String propertiesString = "Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, EVEN, ODD]\n";

    public Request(String input) {
        this.parts = input.split("\\s+");
        makeRequest();
    }

    private void makeRequest() {
        switch (this.parts.length) {
            case 1 -> oneNumber();
            case 2 -> listNumbers();
            case 3 -> oneProperty();
            case 4 -> twoProperties();
            default -> System.out.println("You have entered more than 4 parameters!");
        }
    }
    private void oneNumber() {
        long number = getNumber(this.parts[0]);
        if (number > 0) {
            new Number(number).printCard();
        } else {
            printErrorMessage(1);
        }
    }
    private void printErrorMessage(int level, String... names) {
        switch (level) {
            case 1 -> System.out.println("The first parameter should be a natural number or zero.\n");
            case 2 -> System.out.println("The second parameter should be a natural number.\n");
            case 3 -> {
                System.out.printf("The property [%s] is wrong.\n", names[0].toUpperCase());
                System.out.println(propertiesString);
            }
            case 4 -> {
                System.out.printf("The properties [%s, %s] are wrong.\n", names[0].toUpperCase(), names[1].toUpperCase());
                System.out.println(propertiesString);
            }
            case 5 -> {
                System.out.printf("The request contains mutually exclusive propertiesList: [%s, %s]\n",
                        names[0].toUpperCase(), names[1].toUpperCase());
                System.out.println("There are no numbers with these propertiesList.\n");
            }
        }
    }
    private void listNumbers() {
        long first = getNumber(this.parts[0]);
        long second = getNumber(this.parts[1]);
        if (first >= 0 && second > 0) {
            new NumberList(first, second);
        } else if (first < 0) {
            printErrorMessage(1);
        } else {
            printErrorMessage(2);
        }
    }
    private void oneProperty() {
        long first = getNumber(this.parts[0]);
        long second = getNumber(this.parts[1]);
        if (first >= 0 && second > 0 && isValidProperty(this.parts[2])) {
            new NumberList(first, second, parts[2]);
        } else if (first < 0) {
            printErrorMessage(1);
        } else if (second < 1) {
            printErrorMessage(2);
        } else {
            printErrorMessage(3, parts[2]);
        }
    }
    private void twoProperties() {
        long first = getNumber(this.parts[0]);
        long second = getNumber(this.parts[1]);
        if (first >= 0 && second > 0 && isValidProperty(this.parts[2]) && isValidProperty(this.parts[3])
                && !mutuallyExclusiveProperties()) {
            new NumberList(first, second, parts[2], parts[3]);
        } else if (first < 0) {
            printErrorMessage(1);
        } else if (second < 1) {
            printErrorMessage(2);
        } else if (!(isValidProperty(this.parts[2]) || isValidProperty(this.parts[3]))) {
            printErrorMessage(4, parts[2], parts[3]);
        } else if (!isValidProperty(this.parts[2])) {
            printErrorMessage(3, this.parts[2]);
        } else if (!isValidProperty(this.parts[3])) {
            printErrorMessage(3, this.parts[3]);
        } else {
            printErrorMessage(5, this.parts[2], this.parts[3]);
        }
    }
    private boolean mutuallyExclusiveProperties() {
        return this.parts[2].equals("even") && this.parts[3].equals("odd") ||
                this.parts[2].equals("odd") && this.parts[3].equals("even") ||
                this.parts[2].equals("duck") && this.parts[3].equals("spy") ||
                this.parts[2].equals("spy") && this.parts[3].equals("duck") ||
                this.parts[2].equals("square") && this.parts[3].equals("sunny") ||
                this.parts[2].equals("sunny") && this.parts[3].equals("square");
    }
    private long getNumber(String part) {
        try {
            long number = Long.parseLong(part);
            return number;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    private boolean isValidProperty(String part) { return this.propertiesList.contains(part.toUpperCase()); }
}

