/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.csw.sport.master.test;

import co.edu.uniandes.csw.sport.master.utils.test.InitializeDataUserMaster;
import java.util.concurrent.TimeUnit;
import static junit.framework.Assert.fail;
import org.junit.AfterClass;
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
          //La siguiente linea no funciona :(
          driver.findElement(By.xpath("//button[contains(@id,'createButton')]")).click();
          Thread.sleep(2000);
          driver.findElement(By.id("userName")).clear();
          driver.findElement(By.id("userName")).sendKeys(InitializeDataUserMaster.generateUser().getUserName());
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        @AfterClass
        public static void tearDown() throws Exception {
            // Se cierra el navegador.
            driver.quit();
           // Se verifica que se haya cerrado efectivamente el navegador.
            String verificationErrorString = verificationErrors.toString();
            if (!"".equals(verificationErrorString)) {
                fail(verificationErrorString);
            }
        }
}
