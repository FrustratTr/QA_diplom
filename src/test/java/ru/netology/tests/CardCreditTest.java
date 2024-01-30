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
    void creditAllFieldsValidRusS() {
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
    void creditAllFieldsValidEng() {
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
    void CreditAllFieldEmpty() {
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
    void CreditEmptyCardNumberRestFieldValid() {
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
    void CreditEmptyMonthRestFieldValid() {
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
    void CreditEmptyYearRestFieldValid() {
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
    void CreditEmptyHolderRestFieldValid() {
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
    void CreditEmptyCVCRestFieldValid() {
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
    void CreditNonExistentCardRestFieldValid() {
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
    void CreditNonExistentShortCardRestFieldValid() {
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
    void CreditMonthAboveRangeRestFieldValid() {
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
    void CreditMonthBelowRangeRestFieldValid() {
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
    void CreditPreviousMonthRestFieldValid() {
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
    void CreditYearAboveRangeRestFieldValid() {
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
    void CreditYearBelowRangeRestFieldValid() {
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
    void CreditHolderWithHyphenatedRestFieldValidRus() {
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
    void CreditHolderWithHyphenatedRestFieldValidEng() {
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
    void CreditHolderWithHieroglyphsRestFieldValidCN() {
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
    void CreditHolderWithSpecialSymbolRestFieldValid() {
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
    void CreditHolderWithNumberRestFieldValid() {
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
    void CreditOneNumberInCVCRestFieldValid() {
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
    void CreditTwoNumberInCVCRestFieldValid() {
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