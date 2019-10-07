package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import suporte.Generator;
import suporte.Screenshot;

import java.util.concurrent.TimeUnit;

public class googleSearchScreenshot {
    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();

    @Before
    public void setup() {
        //Abrindo o navegador em modo headless
        ChromeOptions semcabeca = new ChromeOptions();  //instância com options
        semcabeca.addArguments("--headless");   //opção headless do chrome driver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Gui\\Desktop\\automation_drivers\\chromedriver.exe"); //driver do chrome
        navegador = new ChromeDriver(semcabeca);    //() opção do driver para abrir em headless

        navegador.manage().window().maximize();     //maximizar a tela
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //espera para carregar todos elementos

        //Acessar o site
        navegador.get("http://www.google.com.br");
    }

    @Test
    public void obterPrintPesquisaGoogle() {
        //Digitar um texto na caixa Pesquisar pelo seu xpath //*[@id="tsf"]/div[2]/div/div[1]/div/div[1]/input
        WebElement pesquisar = navegador.findElement(By.name("q"));
        pesquisar.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div/div[1]/div/div[1]/input")).sendKeys("time now");

        //Apertar a tecla enter
        pesquisar.sendKeys(Keys.ENTER);

        //Clicar em shopping pelo xpath //*[@id="hdtb-msb-vis"]/div[2]/a
        //pesquisar.findElement(By.xpath("//*[@id=\"hdtb-msb-vis\"]/div[2]/a")).click();
        //pesquisar.findElement(By."Shopping")).click();

        //Clicar em preço do menor para o maior pelo xpath //*[@id="lb"]/div/g-menu/g-menu-item[3]/div
        //pesquisar.findElement(By.xpath("//*[@id=\"lb\"]/div/g-menu/g-menu-item[3]/div")).click();

        //Clicar no primeiro resultado pelo xpath //*[@id="rso"]/div[1]/div[2]/div/div/div[2]/div/div[1]
        //pesquisar.findElement(By.xpath("//*[@id=\"rso\"]/div[1]/div[2]/div/div/div[2]/div/div[1]")).click();

        //Tirar um screenshot da tela de pesquisa
        String screenshotArquivo = "C:\\Users\\Gui\\Desktop\\testReports_selenium\\" + Generator.datahoraParaAqruivo() + test.getMethodName() + ".jpg";
        Screenshot.tirar(navegador, screenshotArquivo);
    }

    @After
    public void finalizar(){
        //Fechar o navegador
        navegador.quit();
    }
}
