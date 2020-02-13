package suporte;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Web {
    public static WebDriver createChrome() throws InterruptedException {
        //Abrindo o navegador (must have)
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Gui\\IdeaProjects\\webdriverjava\\drivers\\CHR\\chromedriver_v79.exe");     //driver do browser (chrome)
        WebDriver navegador = new ChromeDriver();
        navegador.manage().window().maximize();     //maximizar a tela
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);      //espera para carregar todos os elementos

        //Navegando para a pagina do TaskIt (must have)
        navegador.get("http://www.juliodelima.com.br/taskit");
        Thread.sleep(1000);

        return navegador;
    }
}
