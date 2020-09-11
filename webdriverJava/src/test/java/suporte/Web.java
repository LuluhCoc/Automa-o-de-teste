package suporte;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Web {
    public static final String AUTOMATE_USERNAME = "lucianosilva3";
    public static final String AUTOMATE_ACCESS_KEY = "jM1pXzQxHsqgsAZUWEpL";
    public static final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static WebDriver createChrome(){
        // abrindo navegador
        System.setProperty("webdriver.chrome.driver","/home/lsilvaadmin/IdeaProjects/Teste/drives/chromedriver");
        WebDriver navegador = new ChromeDriver();
        navegador.manage().window().maximize();
        navegador.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //navegando para a pagina Taskit
        navegador.get("http://www.juliodelima.com.br/taskit/");

        return navegador;
    }
    public static WebDriver creatBrowserStack(){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("os_version", "10");
        caps.setCapability("resolution", "1280x800");
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "60.0");
        caps.setCapability("os", "Windows");

        WebDriver navegador = null;
        try {
            navegador = new RemoteWebDriver(new URL(URL), caps);
            navegador.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

            //navegando para a pagina Taskit
            navegador.get("http://www.juliodelima.com.br/taskit/");
        } catch (MalformedURLException e) {
            System.out.println("Houveram problemas com a URL: " + e.getMessage());
        }



        return navegador;
    }
}
