// 1 - Pacote
package site;

// 2 - Bibliotecas


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

// 3- classe
public class BuscaSeleniumPuro{
    // 3.1 - atributos
    String url = "https://www.iterasys.com.br";
    driver.get(url);
    WebDriver driver;




    // 3.2 - funções e metodos
    @Before
    public void iniciar(){
        System.setProperty("webdriver.chrome.driver","/home/lsilvaadmin/IdeaProjects/Teste/drives/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    @After
    public void finalizar(){
        driver.quit();
    }

    @Test
    public void buscaCurso(){
        driver.get(url);
        driver.findElement(By.id("searchtext")).sendKeys("TestLink" + Keys.ENTER);
        driver.findElement(By.cssSelector("span.comprar")).click();
        Assert.assertEquals("TestLink",driver.findElement(By.cssSelector("span.item-title")).getText());
        Assert.assertEquals("R$ 79,99", driver.findElement(By.cssSelector("span.new-price")).getText());
    }
}
