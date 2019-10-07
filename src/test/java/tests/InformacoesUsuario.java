package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class InformacoesUsuario {
    private WebDriver navegador;

    @Before
    public void setup() {
        //Abrindo o navegador (must have)
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Gui\\Desktop\\automation_drivers\\chromedriver.exe");     //driver do browser (chrome)
        navegador = new ChromeDriver();
        navegador.manage().window().maximize();     //maximizar a tela
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);      //espera para carregar todos os elementos

        //Navegando para a pagina do TaskIt (must have)
        navegador.get("http://www.juliodelima.com.br/taskit");
    }


    @Test //Test user access "Julio0001"
    public void testAdicionarUmaInformacaoAdicionaldoUsuario() {
        //Clicar no link que possui o texto "Sign In"
        WebElement linkSignIn = navegador.findElement(By.linkText("Sign in"));
        linkSignIn.click();

        //Clicar no campo com o name "login" que esta dentro do formulário de id "signinbox"
        //Digitar no campo com o name "login" que esta dentro do formulário de id "signinbox" o texto "julio0001"
        WebElement formularioSignInBox = navegador.findElement(By.id("signinbox"));
        formularioSignInBox.findElement(By.name("login")).sendKeys("julio0001");

        //Clicar no campo com o name "password" que esta dentro do formulário de id "signinbox"
        //Digitar no campo com o name "password" que esta dentro do formulário de id "signinbox" o texto "123456"
        formularioSignInBox.findElement(By.name("password")).sendKeys("123456");

        //Clicar no link(botão) com o texto "SIGN IN" que esta dentro do formulário de id "signinbox"
        navegador.findElement(By.linkText("SIGN IN")).click();

        //Validar que dentro do elemento com class "me" está o texto "Hi, Julio"
        //WebElement me = navegador.findElement(By.className("me"));
        //String textoElementoMe = me.getText();

        //Validação
        //assertEquals("Hi, Julio", textoElementoMe);

        //Clicar em um link que possui a class "me"
        navegador.findElement(By.className("me")).click();

        //Clicar em um link que possui o texto "MORE DATA ABOUT YOU"
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();

        //Clicar no botão através do seu xpath //button[@data-target='addmoredata']
        navegador.findElement(By.xpath("//button[@data-target='addmoredata']")).click();

        //Identificar a popup onde está o formulário de id "addmoredata"

        //Na combo de name "type" escolher a opção "Phone"
        //No campo de name "contact" digitar "+5511999999999"
        //Clicar no link de text "save" que está na popup
        //Na mensagem de id "toast-container" validar que o texto é "Your contact has been added!
    }

        @After
        public void tearDown(){
            //Fechar o navegador
            navegador.quit();

            //Fechar apenas a aba
//        navegador.close();
        }
}
