package ru.netology.tests;

import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import org.junit.jupiter.api.*;
import ru.netology.pages.CardPage;

import io.qameta.allure.selenide.AllureSelenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.pages.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CardTest {



    @BeforeEach
    void setup() {
        open(System.getProperty("SUT.url", "http://localhost:8080/"));
        SQLHelper.clearDB();

    }

    @BeforeAll
    static void setUpAll() {

        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    // Positive scenarios

    @Test
    @DisplayName("Все поля заполнены валидными данными(CreditPage, RUS)")
    void paymentFormShouldHaveAllValidFieldRUS() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderRus());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationGood();
        String actual = SQLHelper.getPaymentStatus();
        String expected = "APPROVED";
        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("Все поля заполнены валидными данными(CreditPage, ENG)")
    void paymentFormShouldHaveAllValidFieldENG() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationGood();
        String actual = SQLHelper.getPaymentStatus();
        String expected = "APPROVED";
        assertEquals(expected, actual);
    }

    // Negative scenarios

    @Test
    @DisplayName("Все поля не заполнены")
    void paymentFormShouldHaveAllEmptyField() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Поле с картой пустое, остальное заполнено валидными данными")
    void paymentFormShouldHaveEmptyNumberCardRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();


        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Поле с месяцем пустое, остальное заполнено валидными данными")
    void paymentFormShouldHaveEmptyMonthRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();


        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Поле с годом пустое, остальное заполнено валидными данными")
    void paymentFormShouldHaveEmptyYearRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Поле с владельцем пустое, остальное заполнено валидными данными")
    void paymentFormShouldHaveEmptyHolderRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("CVC поле пустое, остальное заполнено валидными данными")
    void paymentFormShouldHaveEmptyCVCRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("В поле карта указана несуществующая карта, остальное заполнено валидными данными")
    void paymentFormShouldHaveNonExistentCardRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getInvalidFullCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationError();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Поле карта заполнено не полностью, остальное заполнено валидными данными")
    void paymentFormShouldHaveNonExistentShortCardRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getInvalidShortCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationInvalidFormat();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Месяц указан свыше допустимого, остальное заполнено валидными данными")
    void paymentFormShouldHaveMonthAboveRangeRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getInvalidMonthAboveYear());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorExpirationDate();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Месяц указан ниже допустимого, остальное заполнено валидными данными")
    void paymentFormShouldHaveMonthBelowRangeRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getInvalidMonthZero());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorExpirationDate();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Месяц указан предыдущий, остальное заполнено валидными данными")
    void paymentFormShouldHavePreviousMonthRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getInvalidPreviousMonth());
        paymentPage.setYear(DataHelper.getCurrentYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorExpiredCard();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Год указан выше допустимого, остальные поля заполнены валидными данными")
    void paymentFormShouldHaveYearAboveRangeRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getInvalidYearAboveRange());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorExpirationDate();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Год указан ниже актуального, остальные поля заполнены валидными данными")
    void paymentFormShouldHaveYearBelowRangeRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getInvalidYearBelowRange());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorExpiredCard();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Поле Владелец содержит данные через дефис, остальное заполнено валидными данными(CreditPage, RUS)")
    void paymentFormShouldHaveHolderWithHyphenatedRestFieldValidRUS() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getInvalidHolderWithHyphenatedRus());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Поле Владелец содержит данные через дефис, остальное заполнено валидными данными(CreditPage, ENG)")
    void paymentFormShouldHaveHolderWithHyphenatedRestFieldValidENG() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getInvalidHolderWithHyphenatedENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Поле Владелец содержит иероглифы, остальное заполнено валидными данными(CreditPage, CN)")
    void paymentFormShouldHaveHolderWithHieroglyphsRestFieldValidCN() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getInvalidHolderCN());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Поле Владелец содержит спец.символы, остальное заполнено валидными данными")
    void paymentFormShouldHaveHolderWithSpecialSymbolRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getRandomSymbol());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Поле владелец заполнено числами, остальное заполнено валидными данными")
    void paymentFormShouldHaveHolderWithNumberRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getRandomNumber());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }
    @Test
    @DisplayName("Одно число в CVC, остальное заполнено валидными данными")
    void paymentFormShouldHaveOneNumberInCVCRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderRus());
        paymentPage.setCVC(DataHelper.getInvalidCVCCodeOneNumber());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationInvalidFormat();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Два числа в CVC, остальное заполнено валидными данными")
    void paymentFormShouldHaveTwoNumberInCVCRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        PaymentPage paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderRus());
        paymentPage.setCVC(DataHelper.getInvalidCVCCodeTwoNumber());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationInvalidFormat();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

}