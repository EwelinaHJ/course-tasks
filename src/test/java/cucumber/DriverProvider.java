package cucumber;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverProvider {

    public static WebDriver driver = null;

    public static WebDriver getDriverInstance() {
        if (driver == null) {
            driver = new ChromeDriver();
            }
            return driver;
        }

    }



