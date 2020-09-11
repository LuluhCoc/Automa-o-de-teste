package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MePage extends BasePage {
    public MePage(WebDriver navegador) {
        super(navegador);
    }

    public MePage clicarNaAbaMoreDataAboutYou(){
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();

        return new MePage(navegador);
    }

    public MePage RolarScrool(){
        WebElement element= navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]"));
        ((JavascriptExecutor)navegador).executeScript("arguments[0].scrollIntoView();", element);

        return new MePage(navegador);
    }

    public AddContactPage clicarBotaoAddMoreDataAboutYou(){
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        return new AddContactPage(navegador);
    }
}
