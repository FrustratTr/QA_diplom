package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CardPage {
    private final SelenideElement heading = $(".heading").shouldBe(Condition.text("Путешествие дня"));
    private final SelenideElement buttonBuy = $$("button").findBy(Condition.text("Купить"));
    private final SelenideElement buttonBuyInCredit = $$("button").findBy(Condition.text("Купить в кредит"));

    public void cardPage() {
        heading.shouldBe(Condition.visible);
    }

    public PaymentPage buy() {
        buttonBuy.click();
        return new PaymentPage();
    }

    public CreditPage buyInCredit() {
        buttonBuyInCredit.click();
        return new CreditPage();
    }
}
