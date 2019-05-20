package main;

import org.junit.Test;
import org.junit.Assert;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class MainTest {


    public static int rnd(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    @Test
    public void getLetterFromBase() {
        char c = 'a';
        int i = 11;
        for (; i < 36 + 1; ++i, ++c) {
            Assert.assertEquals(c, Main.getLetterFromBase(i));
        }
    }

    private String generateNumber(int radix) {
        StringBuilder result = new StringBuilder();
        if (radix <= 10) {
            for (int i = 0; i < rnd(1, 10); ++i) {
                result.append(String.valueOf(rnd(0, radix)));
            }
        } else
            for (int i = 0; i < rnd(1, 5); ++i) {
                result.append(rnd(0, 1) % 2 == 0 ? (char) ('a' + rnd(0, radix - 10)) : String.valueOf(rnd(0, radix)));
            }

        if (result.indexOf("0") == 0)
            result.insert(0, 1);

        return result.toString();
    }

    @Test
    public void convert() {
        for (int i = 0; i < 100; ++i) {
            int radixFrom = rnd(2, 36);
            int radixTo = rnd(2, 36);

            //генерируем число в сс radixFrom
            String number = generateNumber(radixFrom);

            System.out.print("from : " + radixFrom + " to : " + radixTo  + "    " + number);

            String temp = Main.convert(number, radixFrom, radixTo);

            System.out.println(" -> " + temp);

            Assert.assertEquals(Integer.parseInt(number, radixFrom), Integer.parseInt(temp, radixTo));

        }


    }
}