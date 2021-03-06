package nl.utwente.bpsd.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.util.List;

/**
 * @author Sophie
 * @since 21-6-2017.
 */
public class AddVisitIT extends SeleniumBaseIT {
    public AddVisitIT() throws MalformedURLException {
        super();
    }

    @Test
    @Category(SeleniumBaseIT.class)
    public void editPetNameAndAddAVisit() {
        driver.get(BASE_URL+"/owners/find");

        driver.findElement(By.name("lastName")).submit();
        waitForPageToLoad();

        //Go to first owner
        WebElement table = driver.findElement(By.tagName("table"));
        List<WebElement> cells = table.findElements(By.xpath(".//tr/td"));
        cells.get(0).findElement(By.xpath("a")).click();
        waitForPageToLoad();

        //Go to edit page of first pet
        driver.findElement(By.xpath("//table//tr/td/table/tbody//a[contains(text(),'Edit')]")).click();
        waitForPageToLoad();

        //Edit Name of pet
        fillTextField(By.name("name"), "foobar");
        driver.findElement(By.name("name")).submit();


        waitForPageToLoad();
        Assert.assertNotNull(driver.findElement(By.xpath("//table//tr/td/dl/dd[contains(text(), 'foobar')]")));

        driver.findElement(By.xpath("//table//tr/td/table/tbody//a[contains(text(),'Add')]")).click();
        waitForPageToLoad();


        fillTextField(By.name("date"), "2017/12/12");
        fillTextField(By.name("description"), "Foobar check for disease");
        driver.findElement(By.name("date")).submit();
        waitForPageToLoad();
        Assert.assertNotNull(driver.findElement(By.xpath("//table//tr/td/table/tbody//td[contains(text(), '2017-12-12')]")));

        setTestFinished();
    }

}
