package com.mxc.automatedtest;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FirstTest extends BrowserStackJUnitTest{

  public static String IMAGE_INJECTION_SCRIPT = "browserstack_executor: {\"action\": \"cameraImageInjection\", \"arguments\": {\"imageUrl\": \"%s\" }}";

  @ParameterizedTest
  @MethodSource("devices")
  void test(int taskId) throws IOException, InterruptedException {

    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    desiredCapabilities.setCapability("browserstack.enableCameraImageInjection", "true");

    createConnection(taskId);      

    new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='Document detector']"))).click();

    AndroidElement checkPermissions = (AndroidElement) new WebDriverWait(driver, 30).until(
      ExpectedConditions.elementToBeClickable(MobileBy.id("clCamera")));
      checkPermissions.click();

    driver.findElement(By.xpath("//*[@text='Allow']")).click();

    AndroidElement nextStep = (AndroidElement) new WebDriverWait(driver, 30).until( //need Refactor
      ExpectedConditions.elementToBeClickable(By.xpath("//*[@text=concat('Let', \"'\", 's start')]")));
      nextStep.click();

    AndroidElement selectDocumentPreviousStep = (AndroidElement) new WebDriverWait(driver, 30).until( //need Refactor
      ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='Ok, Got it']")));
      selectDocumentPreviousStep.click(); 

    AndroidElement selectDocument = (AndroidElement) new WebDriverWait(driver, 30).until(
      ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='CRLV']")));
      selectDocument.click();
    
      Thread.sleep(2000);

      //SDK Opened
      //Front Document

    AndroidElement popUpAcceptFront = (AndroidElement) new WebDriverWait(driver, 30).until(
      ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='OK, understood']")));
      popUpAcceptFront.click();

      AndroidElement takePhotoFront = (AndroidElement) new WebDriverWait(driver, 30).until(
        ExpectedConditions.elementToBeClickable(MobileBy.id("bTakePhoto")));
        takePhotoFront.click();

    Thread.sleep(4000);
  }
}