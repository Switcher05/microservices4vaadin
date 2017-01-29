package microservices4vaadin.frontend.ui;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.gargoylesoftware.htmlunit.BrowserVersion;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class MyVaadinUITest {

    @LocalServerPort
    private int port;

    private HtmlUnitDriver webdriver;

    @Before
    final public void setUp() {
        webdriver = new HtmlUnitDriver(BrowserVersion.FIREFOX_45);
        webdriver.setJavascriptEnabled(true);
    }


    @Test
    public void test() throws InterruptedException {
        webdriver.get("https://localhost:" + port + "/ui");
        Thread.sleep(5000);

        assertEquals(webdriver.findElement(By.id("siteTitle")).getText(), "microservices4vaadin");

        webdriver.findElement(By.id("testButton")).click();
        Thread.sleep(1000);
    }


    @After
    final public void tearDown() {
        webdriver.quit();
    }

}
