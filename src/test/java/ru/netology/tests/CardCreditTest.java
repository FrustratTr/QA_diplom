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
import ru.netology.pages.CreditPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CardCreditTest {

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

    @Test
    @DisplayName("Все поля заполнены валидными данными(CreditPage, RUS)")
    void creditFormShouldHaveAllValidFieldRUS() {
        CardPage start = new CardPage();
        start.cardPage();
        var CreditPage = start.buyInCredit();

        CreditPage.setCardNumber(DataHelper.getValidWorkCard());
        CreditPage.setMonth(DataHelper.getValidMonth());
        CreditPage.setYear(DataHelper.getValidYear());
        CreditPage.setHolder(DataHelper.getValidHolderRus());
        CreditPage.setCVC(DataHelper.getValidCVCCode());
        CreditPage.buttonContinueClick();
        CreditPage.checkNotificationGood();
        String actual = SQLHelper.getCreditStatus();
        String expected = "APPROVED";
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Все поля заполнены валидными данными(CreditPage, ENG)")
    void creditFormShouldHaveAllValidFieldENG() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage CreditPage = start.buyInCredit();

        CreditPage.setCardNumber(DataHelper.getValidWorkCard());
        CreditPage.setMonth(DataHelper.getValidMonth());
        CreditPage.setYear(DataHelper.getValidYear());
        CreditPage.setHolder(DataHelper.getValidHolderENG());
        CreditPage.setCVC(DataHelper.getValidCVCCode());
        CreditPage.buttonContinueClick();
        CreditPage.checkNotificationGood();
        String actual = SQLHelper.getCreditStatus();
        String expected = "APPROVED";
        assertEquals(expected, actual);
    }
    @Test
    @DisplayName("Все поля не заполнены")
    void CreditFormShouldHaveAllEmptyField() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.buttonContinueClick();
        creditPage.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Поле с картой пустое, остальное заполнено валидными данными")
    void CreditFormShouldHaveEmptyNumberCardRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Поле с месяцем пустое, остальное заполнено валидными данными")
    void CreditFormShouldHaveEmptyMonthRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Поле с годом пустое, остальное заполнено валидными данными")
    void CreditFormShouldHaveEmptyYearRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Поле с владельцем пустое, остальное заполнено валидными данными")
    void CreditFormShouldHaveEmptyHolderRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("CVC поле пустое, остальное заполнено валидными данными")
    void CreditFormShouldHaveEmptyCVCRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("В поле карта указана несуществующая карта, остальное заполнено валидными данными")
    void CreditFormShouldHaveNonExistentCardRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getInvalidFullCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationError();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Поле карта заполнено не полностью, остальное заполнено валидными данными")
    void CreditFormShouldHaveNonExistentShortCardRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getInvalidShortCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationInvalidFormat();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Месяц указан свыше допустимого, остальное заполнено валидными данными")
    void CreditFormShouldHaveMonthAboveRangeRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getInvalidMonthAboveYear());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorExpirationDate();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Месяц указан ниже допустимого, остальное заполнено валидными данными")
    void CreditFormShouldHaveMonthBelowRangeRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getInvalidMonthZero());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorExpirationDate();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Месяц указан предыдущий, остальное заполнено валидными данными")
    void CreditFormShouldHavePreviousMonthRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getInvalidPreviousMonth());
        creditPage.setYear(DataHelper.getCurrentYear());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorExpiredCard();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Год указан выше допустимого, остальные поля заполнены валидными данными")
    void CreditFormShouldHaveYearAboveRangeRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getInvalidYearAboveRange());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorExpirationDate();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Год указан ниже актуального, остальные поля заполнены валидными данными")
    void CreditFormShouldHaveYearBelowRangeRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getInvalidYearBelowRange());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorExpiredCard();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }
    @Test
    @DisplayName("Поле Владелец содержит данные через дефис, остальное заполнено валидными данными(CreditPage, RUS)")
    void CreditFormShouldHaveHolderWithHyphenatedRestFieldValidRUS() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getInvalidHolderWithHyphenatedRus());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Поле Владелец содержит данные через дефис, остальное заполнено валидными данными(CreditPage, ENG)")
    void CreditFormShouldHaveHolderWithHyphenatedRestFieldValidENG() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getInvalidHolderWithHyphenatedENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Поле Владелец содержит иероглифы, остальное заполнено валидными данными(CreditPage, CN)")
    void CreditFormShouldHaveHolderWithHieroglyphsRestFieldValidCN() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getInvalidHolderCN());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Поле Владелец содержит спец.символы, остальное заполнено валидными данными")
    void CreditFormShouldHaveHolderWithSpecialSymbolRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getRandomSymbol());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Поле владелец заполнено числами, остальное заполнено валидными данными")
    void CreditFormShouldHaveHolderWithNumberRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getRandomNumber());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Одно число в CVC, остальное заполнено валидными данными")
    void CreditFormShouldHaveOneNumberInCVCRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderRus());
        creditPage.setCVC(DataHelper.getInvalidCVCCodeOneNumber());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationInvalidFormat();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }

    @Test
    @DisplayName("Два числа в CVC, остальное заполнено валидными данными")
    void CreditFormShouldHaveTwoNumberInCVCRestFieldValid() {
        CardPage start = new CardPage();
        start.cardPage();
        CreditPage creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderRus());
        creditPage.setCVC(DataHelper.getInvalidCVCCodeTwoNumber());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationInvalidFormat();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual, "Запись в БД не должна создаваться");
    }


}