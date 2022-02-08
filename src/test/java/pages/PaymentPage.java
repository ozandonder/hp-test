package pages;

import org.openqa.selenium.By;
import utils.StrongerDriver;


public class PaymentPage extends StrongerDriver {
    private static final By INSTANT_TRANSFER_BUTTON = By.xpath("//h3[contains(text(),'Anında Havale')]");
    private static final By CONTINUE_STEP_BUTTON = By.id("continue_step_btn");
    private static final By AKBANK_RADIO_BUTTON = By.xpath("//*[@class='sardesPaymentPage-MoneyTransfer-bank_description']//*[contains(text(),'Akbank')]");
    private static final By ISBANK_RADIO_BUTTON = By.xpath("//*[@class='sardesPaymentPage-MoneyTransfer-bank_description']//*[contains(text(),'İş Bankası')]");
    private static final By KUVEYT_RADIO_BUTTON = By.xpath("//*[@class='sardesPaymentPage-MoneyTransfer-bank_description']//*[contains(text(),'Kuveyt Türk')]");
    private static final By FIBABANK_RADIO_BUTTON = By.xpath("//*[@class='sardesPaymentPage-MoneyTransfer-bank_description']//*[contains(text(),'Fibabanka')]");
    private static final By VAKIFBANK_RADIO_BUTTON = By.xpath("//*[@class='sardesPaymentPage-MoneyTransfer-bank_description']//*[contains(text(),'Vakıfbank')]");
    private static final By ALBARAKA_RADIO_BUTTON = By.xpath("//*[@class='sardesPaymentPage-MoneyTransfer-bank_description']//*[contains(text(),'AlBaraka Türk')]");
    private static By element;

    public void clickContinueOrderButton() {
        clickToBy(CONTINUE_STEP_BUTTON);
    }

    public PaymentPage payWithInstantTransfer(String bankName) {
        visibilityOfElement(INSTANT_TRANSFER_BUTTON);
        clickToByWithJs(INSTANT_TRANSFER_BUTTON);
        switch (bankName) {
            case "Akbank":
                element = AKBANK_RADIO_BUTTON;
                break;
            case "İş Bankası":
                element = ISBANK_RADIO_BUTTON;
                break;
            case "Kuveyt Türk":
                element = KUVEYT_RADIO_BUTTON;
                break;
            case "Fibabanka":
                element = FIBABANK_RADIO_BUTTON;
                break;
            case "Vakıfbank":
                element = VAKIFBANK_RADIO_BUTTON;
                break;
            case "AlBaraka Türk":
                element = ALBARAKA_RADIO_BUTTON;
                break;
        }
        visibilityOfElement(element);
        clickToByWithJs(element);
        return this;
    }
}