package calc_app_new;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CalcAppNew {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "/home/pavilion/Downloads/chromedriver-linux64/chromedriver");

        // Initialize ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Open the HTML calculator application
        driver.get("http://127.0.0.1:5500/index.html");
        
        WebElement num1Input = driver.findElement(By.id("num1"));
        WebElement num2Input = driver.findElement(By.id("num2"));
        WebElement outputElement = driver.findElement(By.id("result"));

        // Test Addition
        num1Input.sendKeys("5");
        num2Input.sendKeys("10");
        driver.findElement(By.xpath("//button[text()='Add']")).click();

        // Verify the result
        String displayedOutput = outputElement.getAttribute("value");
        if (displayedOutput.equals("15")) {
            System.out.println("Test Addition Passed! Output is correct.");
        } else {
            System.out.println("Test Addition Failed! Expected output: 15, Actual output: " + displayedOutput);
        }

        // Close the browser
//        driver.quit();

	}

}
