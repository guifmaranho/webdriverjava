package tests;

import static org.junit.Assert.*;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generator;
import suporte.Screenshot;

import java.util.concurrent.TimeUnit;

@RunWith(DataDrivenTestRunner)
@DataLoader(filePaths = "InformacoesUsuarioTest.csv")

public class InformacoesUsuarioTest {
    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();

    @Before
    public void setup() {
        //Abrindo o navegador (must have)
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Gui\\Documents\\automation_tools\\automation_drivers\\chromedriver.exe");     //driver do browser (chrome)
        navegador = new ChromeDriver();
        navegador.manage().window().maximize();     //maximizar a tela
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);      //espera para carregar todos os elementos

        //Navegando para a pagina do TaskIt (must have)
        navegador.get("http://www.juliodelima.com.br/taskit");

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
        WebElement me = navegador.findElement(By.className("me"));
        String textoElementoMe = me.getText();

        //Validação
        assertEquals("Hi, Julio", textoElementoMe);
        //}

        //Clicar em um link que possui a class "me"
        navegador.findElement(By.className("me")).click();

        //Clicar em um link que possui o texto "MORE DATA ABOUT YOU"
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
    }


    @Test //Test user access "Julio0001"
    public void testAdicionarUmaInformacaoAdicionaldoUsuario() {
        //Clicar no botão através do seu xpath //button[@data-target='addmoredata']
        navegador.findElement(By.xpath("//button[@data-target='addmoredata']")).click();

        //Identificar a popup onde está o formulário de id "addmoredata"
        WebElement popupAddmoredata = navegador.findElement(By.id("addmoredata"));

        //Na combo de name "type" escolher a opção "Phone"
        WebElement campoType = popupAddmoredata.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText("Phone");

        //No campo de name "contact" digitar "+5511999999999"
        popupAddmoredata.findElement(By.name("contact")).sendKeys("+5511999999999");

        //Clicar no link de text "save" que está na popup
        popupAddmoredata.findElement(By.linkText("SAVE")).click();

        //Na mensagem de id "toast-container" validar que o texto é "Your contact has been added!
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals("Your contact has been added!", mensagem);

    }

    @Test
    public void removerUmContatodeUmUsuario(){
        //Clicar no elemento pelo seu xpath //span[text()='+5511999999999']/following-sibling::a
        navegador.findElement(By.xpath("//span[text()='+5511999999999']/following-sibling::a")).click();

        //Confirmar a janela javascript
        navegador.switchTo().alert().accept();

        //Validar mensagem apresentada "Rest in peace, dear phone!"
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals("Rest in peace, dear phone!", mensagem);

        // Tirar screenshot de referência
        String screenshotArquivo = "C:\\Users\\Gui\\Desktop\\testReports_selenium\\" + Generator.datahoraParaAqruivo() + test.getMethodName() + ".jpg";
        Screenshot.tirar(navegador, screenshotArquivo);

        //Aguardar até 10s para que a janela desapareça
        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));

        //Clicar no link com o texto "Logout"
        navegador.findElement(By.linkText("Logout")).click();
    }

    @After
    public void tearDown(){
        //Fechar o navegador
        navegador.quit();

        //Fechar apenas a aba
//        navegador.close();
    }
}
