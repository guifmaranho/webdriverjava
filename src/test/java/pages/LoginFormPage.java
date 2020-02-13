package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginFormPage extends BasePage{
    public LoginFormPage(WebDriver navegador) {
        super(navegador);
    }

    public LoginFormPage typeLogin(String login){
        navegador.findElement(By.id("signinbox")).findElement(By.name("login")).sendKeys("julio0001");

        return this;
    }

    public LoginFormPage typePassword(String password){
        navegador.findElement(By.id("signinbox")).findElement(By.name("password")).sendKeys("123456");

        return this;
    }

    public SecretaPage clickSignIn(){
        navegador.findElement(By.linkText("SIGN IN")).click();

        return new SecretaPage(navegador);
    }
}
