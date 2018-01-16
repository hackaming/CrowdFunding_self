package com.crowdfunding.sjtu.controller;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class NewTest {
	AuditControllerTest oc = null;
	
  @Test(dataProvider = "dp")
  public void f(Integer n, String s) {
	  System.out.println(oc.toString());
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println(oc.toString());
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println(oc.toString());
  }


  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { 1, "a" },
      new Object[] { 2, "b" },
    };
  }
  @BeforeClass
  public void beforeClass() {
	  System.out.println(oc.toString());
  }

  @AfterClass
  public void afterClass() {
	  System.out.println(oc.toString());
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println(oc.toString());
  }

  @AfterTest
  public void afterTest() {
	  System.out.println(oc.toString());
  }

  @BeforeSuite
  public void beforeSuite() {
	  this.oc = new AuditControllerTest();
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println(oc.toString());
  }

}
