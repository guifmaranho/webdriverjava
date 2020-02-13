package tests;

import org.easetech.easytest.annotation.Param;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import suporte.Web;

public class InformacoesUsuarioPageObjectsTest {
    private WebDriver navegador;

    @Before
    public void setup() throws InterruptedException {
        navegador = Web.createChrome();
    }

    @Test
    public void testAdicionarUmaInformacaoAdicionaldoUsuario() throws InterruptedException {
        new LoginPage(navegador).clickSignIn().typeLogin("julio0001").typePassword("123456").clickSignIn();
    }

    @After
    public void tearDown() {
        //navegador.quit();
    }
}
