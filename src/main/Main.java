package main;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    /**
     * Checks if input is correct according params. Otherwise, requires re-entering the data.
     *
     * @param sentence  Information of data, that should be inputted.
     * @param lowBorder The lowest number, that user can enter.
     * @param upBorder  The largest number, that user can enter.
     * @return The correct number, that satisfy params.
     */
    public static int input(String sentence, int lowBorder, int upBorder) {
        Scanner in = new Scanner(System.in);
        int temp;

        while (true) {
            System.out.println(sentence);

            while (!in.hasNextInt()) {
                in.next();
                System.out.println(sentence);
            }

            temp = in.nextInt();
            if (temp >= lowBorder && temp <= upBorder)
                return temp;
        }
    }

    /**
     * Calculate the largest letter, that can be in part of number in base radix.
     *
     * @param base Radix of a number
     * @return The largest letter, that can be in part of number in base radix
     */
    public static char getLetterFromBase(int base) {
        return (char) ((int) 'a' + base - 11);
    }

    /**
     * Checks if input is correct according params. Otherwise, requires re-entering the data.
     *
     * @param sentence Information of data, that should be inputted.
     * @param fromBase The radix of number
     * @return The number in fromBase radix.
     */
    public static String inputNumber(String sentence, int fromBase) {
        String numberPattern = (fromBase <= 10) ?
                "[0-" + (fromBase - 1) + "]+" :
                "[0-9|a-" + getLetterFromBase(fromBase) + "]+";
        Pattern pattern = Pattern.compile(numberPattern);

        Scanner in = new Scanner(System.in);
        Matcher matcher;
        String input;

        do {
            System.out.println(sentence);
            input = in.next().toLowerCase();
            matcher = pattern.matcher(input);
        } while (!matcher.matches());

        return input;
    }

    /**
     * Converts number from "from" radix to "to" radix
     *
     * @param number The number, that should be converted.
     * @param from   The from radix.
     * @param to     The to radix.
     * @return The converted number in "to" radix.
     */
    public static String convert(String number, int from, int to) {
        if (from == to)
            return number;

        int temp = 0;
        try {
            temp = (Integer.parseInt(number, from));
        }catch (NumberFormatException ex)
        {
            System.out.println(ex.getMessage());
        }

        if (to == 10)
            return String.valueOf(temp);

        StringBuilder result = new StringBuilder();
        int order = 0;
        while (temp > 0) {
            result.insert(0, temp % to >= 10 ? Character.toString((char)('a' + temp % to - 10)) : temp % to);
            temp /= to;
            order += 1;
        }

        return String.valueOf(result.toString());
    }

    public static void main(String[] args) {

        int fromBase = input("1. Enter a FROM base of your number [2 ; 36] : ", 2, 36);
        String number = inputNumber("2. Enter a number, that you want to convert : ", fromBase);

        int toBase
                = input("Enter a TO base of your number [2 ; 36]", 2, 36);
        String result = convert(number, fromBase, toBase);
        System.out.println(result);

    }
}
