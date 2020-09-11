package tests;

import static org.junit.Assert.*;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generator;
import suporte.Screenshot;
import suporte.Web;

import java.util.concurrent.TimeUnit;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths ="InformacoesUsuarioTestData.csv" )  //arquivos que vao ser repositorios dos testes

public class InformacoesUsuarioTest {
    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();
    @Before
    public void setUp(){
        navegador = Web.createChrome();

        // Clicar no link que possui o texto "sign in"
        navegador.findElement(By.linkText("Sign in")).click();

        // Identificando o formulario de login
        WebElement formularioSignInBox = navegador.findElement(By.id("signinbox"));

        // Digitar no campo com name "login" que está dentro no formulário de id "signinbox
        // o texto "julio0001"
        formularioSignInBox.findElement(By.name("login")).sendKeys("julio0001");

        // Digitar no campo com name "password" que está dentro do formulário de id "signinbox"
        // o texto "123456"0
        formularioSignInBox.findElement(By.name("password")).sendKeys("123456");

        // Clicar no link com o texto "SIGN IN"
        navegador.findElement(By.linkText("SIGN IN")).click();

        // Validar que dentro do elemento com class "me" está o texto "Hi, julio"
        WebElement me = navegador.findElement(By.className("me"));
        String textoNoElementoMe = me.getText();
        assertEquals("Hi, Julio",textoNoElementoMe);

        // clicar no link com class "me"
        navegador.findElement(By.className("me")).click();

        // clicar em um link com texto "MORE DATA ABOUT YOU"
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
    }

    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(@Param(name="tipo")String tipo, @Param(name="contato")String contato, @Param(name="mensagem")String mensagemEsperada) {

        // Rolar scrool até elemento requerido
        WebElement element= navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]"));
        ((JavascriptExecutor)navegador).executeScript("arguments[0].scrollIntoView();", element);

        // Clicar no botão através do seu xpath //butoon[@data-target="addmoredata"]
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        // Indentificar a popup onde está o formulário de id "addmoredata"
        WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));

        // na combo de name "type" escolhe a opção "Phone"
        WebElement campoType = popupAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(tipo);  //crie uma interface escpecifica para combo box usando o element type

        // no campo de name "contact" digitar "+5511999999999"
        popupAddMoreData.findElement(By.name("contact")).sendKeys(contato);

        // Clicar no link de text "SAVE" que está na popup
        popupAddMoreData.findElement(By.linkText("SAVE")).click();

        // na mensagem de id "toast-container"  validar que o texto é "Your contact has been added!"
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals(mensagemEsperada,mensagem);
    }

    @Test
    public void removerUmContatoDeUmUsuario() {

        // Rolar scrool até elemento requerido
        WebElement element= navegador.findElement(By.xpath("//span[text()=\"+5511999999999\"]/following-sibling::a"));
        ((JavascriptExecutor)navegador).executeScript("arguments[0].scrollIntoView();", element);

        // Clicar no elemento pelo seu xpath //span[text()="+5511999999999"]/following-sibling::a
        navegador.findElement(By.xpath("//span[text()=\"+5511999999999\"]/following-sibling::a")).click();

        // Confirmar a janela javascript
        navegador.switchTo().alert().accept(); // aceita/confirma numa janela javascript

        // Validar mensagem apresentada "Rest in peace,dear phone!"
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals("Rest in peace, dear phone!",mensagem);

        // printando a tela para comprovar a validação
        String screenshotArquivo = "/home/lsilvaadmin/IdeaProjects/webdriverJava/screenshot/" + Generator.dataHotaParaArquivo() + test.getMethodName() + ".png";
        Screenshot.tirar(navegador, screenshotArquivo);

        // Aguardar até 10 segundos para que a janela desapareça
        WebDriverWait aguardar = new WebDriverWait(navegador, 10);  //espera expricita
        aguardar.until(ExpectedConditions.stalenessOf(mensagemPop)); //stalenessOF elemento deixou de existir

        // Rolar scrool até elemento requerido
        WebElement elemento= navegador.findElement(By.linkText("Logout"));
        ((JavascriptExecutor)navegador).executeScript("arguments[0].scrollIntoView();", elemento);

        // Clicar no link com text "logout"
        navegador.findElement(By.linkText("Logout")).click();

    }

    @After
    public void tearDown(){
        navegador.quit();
    }
}