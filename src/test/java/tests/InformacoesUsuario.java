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
@DataLoader(filePaths = "InformacoesUsuarioTest.csv")

public class InformacoesUsuario {
    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();

    @Before
    public void setup() throws InterruptedException {

        navegador = Web.createChrome(); //chama a classe Web

        //Clicar no link que possui o texto "Sign In"
        WebElement linkSignIn = navegador.findElement(By.linkText("Sign in"));
        linkSignIn.click();

        //Clicar no campo com o name "login" que esta dentro do formulário de id "signinbox"
        WebElement formularioSignInBox = navegador.findElement(By.id("signinbox"));

        //Digitar no campo com o name "login" que esta dentro do formulário de id "signinbox" o texto "julio0001"
        formularioSignInBox.findElement(By.name("login")).sendKeys("julio0001");

        //Digitar no campo com o name "password" que esta dentro do formulário de id "signinbox" o texto "123456"
        formularioSignInBox.findElement(By.name("password")).sendKeys("123456");
        Thread.sleep(1000);

        //Clicar no link(botão) com o texto "SIGN IN" que esta dentro do formulário de id "signinbox"
        navegador.findElement(By.linkText("SIGN IN")).click();

        //Validar que dentro do elemento com class "me" está o texto "Hi, Julio"
        WebElement me = navegador.findElement(By.className("me"));
        String textoElementoMe = me.getText();

        //Validação
        assertEquals("Hi, Julio", textoElementoMe);

        //Clicar em um link que possui a class "me"
        navegador.findElement(By.className("me")).click();
        Thread.sleep(1000);

        //Clicar em um link que possui o texto "MORE DATA ABOUT YOU"
        //navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
        //navegador.findElement(By.linkText("More data about you")).click();
        navegador.findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/ul/li[3]/a")).click();
        //navegador.findElement(By.xpath("//a[text()='More data about you']")).click();
    }


    @Test //Test user access "Julio0001"
    public void testAdicionarUmaInformacaoAdicionaldoUsuario(@Param(name="tipo")String tipo, @Param(name="contato")String contato, @Param(name="mensagem")String mensagemEsperada) throws InterruptedException {
        //Clicar no botão através do seu xpath //button[@data-target='addmoredata']
        navegador.findElement(By.xpath("//button[@data-target='addmoredata']")).click();

        //Identificar a popup onde está o formulário de id "addmoredata"
        WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));

        //Na combo de name "type" escolher a opção "Phone"
        WebElement campoType = popupAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(tipo);

        //No campo de name "contact" digitar "+5511999999999"
        popupAddMoreData.findElement(By.name("contact")).sendKeys(contato);
        Thread.sleep(1000);

        //Clicar no link de text "save" que está na popup
        //popupAddMoreData.findElement(By.linkText("save")).click();
        popupAddMoreData.findElement(By.xpath("//text()[.='Save']/ancestor::a[1]\t")).click();

        //Na mensagem de id "toast-container" validar que o texto é "Your contact has been added!"
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals(mensagemEsperada, mensagem);

    }

    @Test
    public void removerUmContatoDeUmUsuario (@Param(name="telefone")String telefone, @Param(name="mensagem")String mensagemEsperada) throws InterruptedException {
        //Clicar no elemento pelo seu xpath //span[text()='+5511996666666']/following-sibling::a
        navegador.findElement(By.xpath("//span[text()='"+ telefone +"']/following-sibling::a")).click();

        //Confirmar a janela javascript
        navegador.switchTo().alert().accept();

        //Validar que a mensagem apresentada for Rest in peace, dear phone!
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals(mensagem, mensagem);

        String screenshotArquivo = "C:\\Users\\Gui\\Desktop\\tests\\" + Generator.datahoraParaAqruivo() + test.getMethodName() + ".png";
        Screenshot.tirar(navegador, screenshotArquivo);
        //Screenshot.tirar(navegador, "C:\\Users\\Gui\\Desktop\\" + Generator.datahoraParaAqruivo() + "removerUmContatoDeUsuario.png");

        //Aguardar até 10s para que a janela desapareça
        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));

        //Clicar no link com o texto logout
        navegador.findElement(By.linkText("Logout")).click();
    }

        @After
        public void tearDown(){
            //Fechar o navegador
            //navegador.quit();

            //Fechar apenas a aba
//        navegador.close();
        }
}
