
package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

public class StudentRegistrationTest {

    WebDriver driver;


@BeforeMethod
public void setUp() {

    org.openqa.selenium.chrome.ChromeOptions options =
            new org.openqa.selenium.chrome.ChromeOptions();

    options.addArguments("--headless=new");
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");

    driver = new ChromeDriver(options);

    driver.manage().window().maximize();

    File file = new File("index.html");

    driver.get(file.toURI().toString());
}


    // Test successful student registration
    @Test
    public void testSuccessfulRegistration() {

        driver.findElement(By.id("studentName"))
              .sendKeys("Rahul");

        driver.findElement(By.id("email"))
              .sendKeys("rahul@gmail.com");

        driver.findElement(By.id("usn"))
              .sendKeys("4MC23CS001");

        WebElement department =
                driver.findElement(By.id("department"));

        Select select = new Select(department);

        select.selectByVisibleText("Computer Science");

        driver.findElement(By.id("submitBtn")).click();

        String actualMessage =
                driver.findElement(By.id("message")).getText();

        Assert.assertEquals(
                actualMessage,
                "Student Registration Successful!"
        );
    }

    // Test empty field validation
    @Test
    public void testEmptyFieldsValidation() {

        driver.findElement(By.id("submitBtn")).click();

        String actualMessage =
                driver.findElement(By.id("message")).getText();

        Assert.assertEquals(
                actualMessage,
                "Please fill all fields!"
        );
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
