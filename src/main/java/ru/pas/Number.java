package ru.pas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Number {
    private final long number;
    private final List<Integer> digits;
    private final Map<String, Boolean> properties;

    Number(long number) {
        this.number = number;
        this.digits = listDigits();
        this.properties = new HashMap<>();
        setProperties();
    }

    private void setProperties() {
        this.properties.put("even", isEven());
        this.properties.put("odd", !isEven());
        this.properties.put("buzz", isBuzz());
        this.properties.put("duck", isDuck());
        this.properties.put("palindromic", isPalindromic());
        this.properties.put("gapful", isGapful());
        this.properties.put("spy", isSpy());
        this.properties.put("square", isSquare(this.number));
        this.properties.put("sunny", isSunny());
    }

    public boolean getProperty(String property) {
        return this.properties.get(property.toLowerCase());
    }

    private List<Integer> listDigits() {
        List<Integer> digits = new ArrayList<>();
        long num = this.number;
        while (num > 0) {
            digits.add(0, (int) (num % 10));
            num /= 10;
        }
        return digits;
    }

    public List<Integer> getDigits() {
        return this.digits;
    }

    private int getNumberDigits() {
        return getDigits().size();
    }

    private int getDigit(int fromLeft) {
        return this.digits.get(fromLeft);
    }

    private boolean isPalindromic() {
        int numDigits = getNumberDigits();
        for (int i = 0; i < numDigits / 2; i++) {
            int left = getDigit(i);
            int right = getDigit(numDigits - 1 - i);
            if (left != right) {
                return false;
            }
        }
        return true;
    }

    private boolean isDuck() {
        return this.digits.contains(0);
    }

    private boolean divisibleBySeven() {
        return number % 7 == 0;
    }

    private boolean endsWithSeven() {
        return number % 10 == 7;
    }

    private boolean isBuzz() {
        return divisibleBySeven() || endsWithSeven();
    }

    private boolean isEven() {
        return number % 2 == 0;
    }

    private boolean isGapful() {
        if (getNumberDigits() < 3) {
            return false;
        }
        int gap = 10 * getDigit(0) + getDigit(getNumberDigits() - 1);
        return number % gap == 0;
    }

    private boolean isSpy() {
        int sumOfDigits = getDigits().stream().reduce(0, (a, b) -> a + b);
        int productOfDigits = getDigits().stream().reduce(1, (a, b) -> a * b);
        return sumOfDigits == productOfDigits;
    }

    private static boolean isSquare(long number) {
        return Math.sqrt(number) == Math.floor(Math.sqrt(number));
    }

    private boolean isSunny() {
        return isSquare(this.number + 1);
    }

    public void printCard() {
        StringBuilder card = new StringBuilder("Properties of " + this.number + "\n");
        for (String property : this.properties.keySet()) {
            card.append(property).append(": ").append(properties.get(property)).append("\n");
        }
        System.out.println(card);
    }

    public void printLine() {
        StringBuilder line = new StringBuilder(this.number + " is");
        for (String property : this.properties.keySet()) {
            if (this.properties.get(property)) {
                line.append(" ").append(property).append(',');
            }
        }
        if (line.charAt(line.length() - 1) == ',') {
            line.delete(line.length() - 1, line.length());
        }
        System.out.println(line);
    }
}