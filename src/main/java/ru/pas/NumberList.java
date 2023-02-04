package ru.pas;

import java.util.ArrayList;
import java.util.List;

public class NumberList {
    private List<Number> list;
    private long start;
    private long length;
    private String[] properties;

    public NumberList(long start, long length, String... properties) {
        this.start = start;
        this.length = length;
        this.properties = properties;
        this.list = getNumbers();
        this.list.forEach(Number::printLine);
        System.out.println();
    }

    private List<Number> getNumbers() {
        List<Number> numberList = new ArrayList<>();
        switch (this.properties.length) {
            case 0:
                for (long i = this.start; i < this.start + this.length; i++) {
                    numberList.add(new Number(i));
                }
                break;
            case 1:
                long i = this.start;
                while (numberList.size() < this.length) {
                    Number number = new Number(i);
                    if (number.getProperty(this.properties[0])) {
                        numberList.add(number);
                    }
                    i++;
                }
                break;
            case 2:
                long j = this.start;
                while (numberList.size() < this.length) {
                    Number number = new Number(j);
                    if (number.getProperty(this.properties[0]) && number.getProperty(this.properties[1])) {
                        numberList.add(number);
                    }
                    j++;
                }
                break;
            default:
                System.out.println("There are more than two properties");
                break;
        }
        return numberList;
    }

}