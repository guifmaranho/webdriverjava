package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import suporte.Web;

public class SecretaPage extends BasePage{

    public SecretaPage(WebDriver navegador) {
        super(navegador);
    }

    public mePage clicarMe(){
        navegador.findElement(By.className("me")).click();

        return new mePage(navegador);
    }
}
