package tests;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import suporte.Web;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioPageObjectsTest.csv")

public class InformacoesUsuarioPageObjectsTest {
    private WebDriver nagegador;

    @Before
    public void setUp(){

        nagegador = Web.creatBrowserStack();

    }

    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(
            @Param(name="login")String login,
            @Param(name="senha")String senha,
            @Param(name="tipo")String tipo,
            @Param(name="contato")String contato,
            @Param(name="mensagemEsperada")String mensagemEsperada) {
        String textoToast = new LoginPage(nagegador)
                .clicarSignIn()
                .fazerLogin(login, senha)
                .clicarMe()
                .clicarNaAbaMoreDataAboutYou()
                .RolarScrool()
                .clicarBotaoAddMoreDataAboutYou()
                .adicionarContato(tipo,contato)
                .capturarTextoToast();

        assertEquals(mensagemEsperada,textoToast);

    }

    @After
    public void tearDown(){
        nagegador.quit();
    }
}
