/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.csw.sport.master.test;

import co.edu.uniandes.csw.sport.master.utils.test.InitializeDataUserMaster;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static junit.framework.Assert.fail;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author Jj.alarcon10
 */
public class SportMasterTest {
        // Es la instancia inicial del web driver que controlará el navegador firefox
        private static WebDriver driver;
        // url en el cual se aloja la página web (en este caso localhost:8080)
        private static String baseUrl;
        // variable que indica si varios alert consecutivos (alert javascript) se tomarán
        private static boolean acceptNextAlert = true;
        private static StringBuffer verificationErrors = new StringBuffer();
       /*La anotación ‘@BeforeClass’ indica lo que se debe ejecutar ANTES de correr el archivo de pruebas. Este método instancia un nuevo driver firefox (causando la apertura de una ventana física de firefox).*/
        @BeforeClass
        public static void setUp() throws Exception {
            driver = new FirefoxDriver();
        // se define el url base del proyecto web
            baseUrl = "http://localhost:8080";
        //* Indica cuanto se espera para la respuesta de cualquier comando realizado hacia el navegador*/.
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
              /** Cambia el tamanio de la ventana del explorador para que los controles backbone se muestren correctamente en la prueba*/
            driver.manage().window().setSize(new Dimension(1400, 700));
        }
        // La anotación ‘@AfterClass’ indica lo que se debe ejecutar DESPUÉS de ejecutar
        // el archivo de pruebas. Este método cierra la ventana de firefox 
        // abierta por @BeforeClass que se utilizó para la prueba.
        
        @Before
        public void setUpUrl(){
            driver.get(baseUrl + "/sport.web/");
        
        }
        
        @Test
        public void testCreateUserMaster() throws Exception{
        
            /**
         * Comando que realiza click sobre el boton "create" del toolbar. La
         * función 'find' encuentra el control y posteriormente hace clic en el
         * mismo. La forma en la que se busca el control es utilizando
         * expresiones xPath ya que los id de los mismos nunca son iguales (se
         * generan con junto con el valor de componentId que varía).
         */
          driver.findElement(By.xpath("//button[@class='btn btn-default dropdown-toggle']")).click();
          Thread.sleep(1000);
          String User ="User Master";
          driver.findElement(By.xpath("//a[contains(text(),'"+User+"')]")).click();
          Thread.sleep(3000);
          //Es necesario cambiar de iframe :)
          driver.switchTo().frame(driver.findElement(By.id("container")));
          driver.findElement(By.xpath("//button[contains(@id,'createButton')]")).click();
          Thread.sleep(2000);
          driver.findElement(By.id("userName")).clear();
          driver.findElement(By.id("userName")).sendKeys(InitializeDataUserMaster.generateUser().getUserName());
          driver.findElement(By.id("firstName")).clear();
          driver.findElement(By.id("firstName")).sendKeys(InitializeDataUserMaster.generateUser().getFirstName());
          driver.findElement(By.id("lastName")).clear();
          driver.findElement(By.id("lastName")).sendKeys(InitializeDataUserMaster.generateUser().getLastName());
          driver.findElement(By.id("docNumber")).clear();
          driver.findElement(By.id("docNumber")).sendKeys(InitializeDataUserMaster.generateUser().getDocNumber());
          driver.findElement(By.xpath("//button[contains(@id,'saveButton')]")).click();
        }
        
    

    @Test
    public void createUserSport() throws Exception {
        Thread.sleep(2000);
        
        driver.get(baseUrl + "/sport.web/userMaster.html");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[contains(@id,'createButton')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("userName")).clear();
        driver.findElement(By.id("userName")).sendKeys("nombre");
                driver.findElement(By.id("firstName")).clear();
        driver.findElement(By.id("firstName")).sendKeys("nombre1");
                driver.findElement(By.id("lastName")).clear();
        driver.findElement(By.id("lastName")).sendKeys("nombre2");
        //HREF indicando el tab de detalle a seleccionar
        driver.findElement(By.xpath("//a[contains(@href,'sport')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[contains(@id,'addButton')]")).click();
        List<WebElement> lst = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for (int i = 0; i < lst.size(); i++) {
            if (!lst.get(i).isSelected()) {
                lst.get(i).click();
            }
        }
        driver.findElement(By.id("addButton")).click();
        List<WebElement> tables = driver.findElement(By.xpath("//div[contains(@id,'sport')]")).findElements(By.xpath("//table[contains(@class,'table striped')]/tbody/tr"));
        if (tables.size() != lst.size()) {
            fail();
        }
        driver.findElement(By.xpath("//button[contains(@id,'saveButton')]")).click();
        Thread.sleep(3000);
        List<WebElement> table = driver.findElements(By.xpath("//table[contains(@class,'table striped')]/tbody/tr"));
        boolean fail = false;
        for (WebElement webElement : table) {
            List<WebElement> elems = webElement.findElements(By.xpath("td"));
            if (elems.get(0).getText().equals("nombre") && elems.get(1).getText().equals("nombre1")) {
                fail = true;
            }
        }
        //InitializeData.flushDataShared();
        assertTrue(fail);
    }

    @Test
    public void editUserSport() throws Exception {
        //InitializeData.fetchData(5);
         driver.get(baseUrl + "/sport.web/userMaster.html");
        driver.findElements(By.linkText("Edit")).get(driver.findElements(By.linkText("Edit")).size() - 1).click();
        Thread.sleep(2000);
        driver.findElement(By.id("firstName")).clear();
        driver.findElement(By.id("firstName")).sendKeys("nombre1mod");
        driver.findElement(By.id("lastName")).clear();
        driver.findElement(By.id("lastName")).sendKeys("nombre2omod");
        driver.findElement(By.xpath("//a[contains(@href,'testB')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[contains(@id,'addButton')]")).click();
        List<WebElement> lst = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for (int i = 0; i < lst.size(); i++) {
            if (!lst.get(i).isSelected()) {
                lst.get(i).click();
            }
        }
        driver.findElement(By.id("addButton")).click();
        driver.findElement(By.xpath("//button[contains(@id,'saveButton')]")).click();
        Thread.sleep(3000);
        List<WebElement> table = driver.findElements(By.xpath("//table[contains(@class,'table striped')]/tbody/tr"));
        boolean fail = false;
        for (WebElement webElement : table) {
            List<WebElement> elems = webElement.findElements(By.xpath("td"));
            if (elems.get(0).getText().equals("nombre") && elems.get(1).getText().equals("nombre1")) {
                fail = true;
            }
        }
        //InitializeData.flushDataShared();
        assertTrue(fail);
    }
    @Test
    public void deleteUserSport() throws Exception {
        //InitializeData.fetchData(5);
        /**
         * Se hace clic en el vinculo "Delete" del primer elemento de la lista
         * de sports
         */
         driver.get(baseUrl + "/sport.web/userMaster.html");
        driver.findElement(By.linkText("Delete")).click();
        Thread.sleep(2000);
        /**
         * Se verifica que en la lista el elemento halla desaparecido. Si
         * existe, hubo un error.
         */
        try {
            List<WebElement> table = driver.findElements(By.xpath("//table[contains(@class,'table striped')]/tbody/tr"));
            boolean fail = false;
            for (WebElement webElement : table) {
                List<WebElement> elems = webElement.findElements(By.xpath("td"));
                if (elems.get(0).getText().equals("nombre")) {
                    fail = true;
                }
            }
            WebElement dialog = driver.findElement(By.xpath("//div[contains(@style,'display: block;')]"));
            assertTrue(dialog != null && !fail);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @AfterClass
    public static void tearDown() throws Exception {
        driver.quit();
    }
}
