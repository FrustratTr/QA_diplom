package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class CreditPage {
    private final SelenideElement form = $(".form");
    private final SelenideElement cardNumberField = form.$("input[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthsField = form.$("input[placeholder='08']");
    private final SelenideElement yearsField = form.$("input[placeholder='22']");
    private final SelenideElement holderField = $(byXpath("/html/body/div[1]/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input"));
    private final SelenideElement cvcField = form.$("input[placeholder='999']");
    private final SelenideElement buttonContinue = form.$$("button").findBy(Condition.text("Продолжить"));
    private final SelenideElement notificationGood = $(".notification_status_ok");
    private final SelenideElement notificationError = $(".notification_status_error");

    public void setCardNumber(DataHelper.cardNumber number) {
        cardNumberField.setValue(String.valueOf(number));
    }

    public void setMonth(String month) {
        monthsField.setValue(month);
    }

    public void setYear(String year) {
        yearsField.setValue(year);
    }

    public void setHolder(String hold) {
        holderField.setValue(hold);
    }

    public void setCVC(String Cvc) {
        cvcField.setValue(Cvc);
    }

    public void buttonContinueClick() {
        buttonContinue.click();
    }

    public void checkNotificationGood() {
        notificationGood.shouldBe(Condition.visible);
        notificationGood.shouldHave(Condition.exactText("Успешно Операция одобрена Банком."));
    }

    public void checkNotificationError() {
        notificationError.shouldBe(Condition.visible);
        notificationError.shouldHave(Condition.exactText("Ошибка Ошибка! Банк отказал в проведении операции."));
    }

    public void checkNotification(Condition condition, String text) {
        $(".input__sub").shouldBe(Condition.visible).shouldHave(Condition.exactText(text));
    }

    public void checkNotificationInvalidFormat() {
        checkNotification(Condition.exactText("Неверный формат"), "Неверный формат");
    }

    public void checkNotificationRequiredField() {
        checkNotification(Condition.exactText("Поле обязательно для заполнения"), "Поле обязательно для заполнения");
    }

    public void checkNotificationErrorExpirationDate() {
        checkNotification(Condition.exactText("Неверно указан срок действия карты"), "Неверно указан срок действия карты");
    }

    public void checkNotificationErrorExpiredCard() {
        checkNotification(Condition.exactText("Истёк срок действия карты"), "Истёк срок действия карты");
    }

    public void checkNotificationErrorHolderNotFull() {
        checkNotification(Condition.exactText("Укажите имя и фамилию полностью"), "Укажите имя и фамилию полностью");
    }

    public void checkNotificationErrorInvalidCharacters() {
        checkNotification(Condition.exactText("Укажите имя и фамилию полностью"), "Укажите имя и фамилию полностью");
    }

    public void checkNotificationErrorOverFlow() {
        checkNotification(Condition.exactText("Имя и фамилия не должны превышать 20 символов"), "Имя и фамилия не должны превышать 20 символов");
    }
}
