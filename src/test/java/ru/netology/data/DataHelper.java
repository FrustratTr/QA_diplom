package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {

    // Card Number
    @Value
    public static class cardNumber {
        String card;
    }

    public static cardNumber getValidWorkCard() {
        return new cardNumber("4444 4444 4444 4441");
    }

    public static cardNumber getInvalidFullCard() {
        return new cardNumber("4444 4444 4444 4444");
    }

    public static cardNumber getInvalidShortCard() {
        return new cardNumber("4444 4444 4444 4");
    }

    // Month
    public static String getValidMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getInvalidMonthAboveYear() {
        return String.valueOf((Math.random() * 86) + 13);
    }

    public static String getInvalidMonthZero() {
        return "00";
    }

    public static String getInvalidPreviousMonth() {
        return LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }

    // Year
    public static String getValidYear() {
        int currentYear = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yy")));
        return String.valueOf((Math.random() * 5) + currentYear);
    }

    public static String getCurrentYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getInvalidYearAboveRange() {
        int currentYear = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yy")));
        int futureYear = 5 + currentYear;
        int range = 99 - futureYear;
        return String.valueOf((Math.random() * range) + futureYear);
    }

    public static String getInvalidYearBelowRange() {
        int currentYear = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yy"))) - 1;
        return String.valueOf((Math.random() * currentYear) + 0);
    }

    // Card Holder
    public static String generateValidHolderName(Locale locale) {
        Faker faker = new Faker(locale);
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getValidHolderRus() {
        return generateValidHolderName(new Locale("ru"));
    }

    public static String getValidHolderENG() {
        return generateValidHolderName(new Locale("en"));
    }
    public static String generateHyphenatedName(Locale locale) {
        Faker faker = new Faker(locale);
        return faker.name().firstName() + "-" + faker.name().lastName();
    }

    public static String getInvalidHolderWithHyphenatedRus() {
        return generateHyphenatedName(new Locale("ru"));
    }

    public static String getInvalidHolderWithHyphenatedENG() {
        return generateHyphenatedName(new Locale("en"));
    }

    public static String getInvalidHolderCN() {
        Faker faker = new Faker(new Locale("zh_CN"));
        return faker.name().firstName();
    }

    public static String getRandomSymbol() {
        String alphabet = "!@#$%^&*()~`№;:?[]{}/?|";
        return String.valueOf(alphabet.charAt((int) (Math.random() * alphabet.length())));
    }

    public static String getRandomNumber() {
        return String.valueOf((int) (Math.random() * 9999));
    }

    public static String getHolderOverFlowString() {
        Faker faker = new Faker();
        return faker.lorem().characters(21, 200);
    }

    // CVC/CVV
    public static String getValidCVCCode() {
        return String.valueOf((int) (Math.random() * 899) + 100);
    }

    public static String getInvalidCVCCodeOneNumber() {
        return String.valueOf((int) (Math.random() * 8) + 1);
    }

    public static String getInvalidCVCCodeTwoNumber() {
        return String.valueOf((int) (Math.random() * 89) + 10);
    }
}
