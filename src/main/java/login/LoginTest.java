package login; /**
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class LoginTest {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "D:\\Ddrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();// 浏览器最大化
        baseUrl = "https://www.rencaijia.com/login/";
        driver.get(baseUrl);
    }
    //验证访问人才加平台登录页面测试
    @Test
    public void loginpage() throws Exception {
          String loginname=driver.findElement(By.cssSelector(".tips>a")).getText();
          assertEquals(loginname,"去登录");
          System.out.println("成功访问人才加平台登录页面！");
          noInputvalueclickButton();

    }
    //未输入值，直接点击登录按钮测试

    public void noInputvalueclickButton() throws InterruptedException {
          driver.findElement(By.className("submit-btn")).click();
          String pleaseenterphonenumber=driver.findElement(By.xpath(".//*[@id='loginForm']/div[1]/span")).getText();
          String pleaseinputpassword=driver.findElement(By.xpath(".//*[@id='loginForm']/div[2]/span")).getText();
          assertEquals(pleaseenterphonenumber,"*请输入手机号");
          assertEquals(pleaseinputpassword,"*请输入密码");
          System.out.println("完成了人才未输入值直接点击登录按钮测试！");
          usernameInput();
    }
    //仅输入用户名后点击登录按钮测试
    public void usernameInput() throws InterruptedException {
        driver.findElement(By.name("username")).sendKeys("17875469790");
        driver.findElement(By.className("submit-btn")).click();
        String pleaseinputpassword=driver.findElement(By.xpath(".//*[@id='loginForm']/div[2]/span")).getText();
        assertEquals(pleaseinputpassword,"*请输入密码");
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("119");
        driver.findElement(By.className("submit-btn")).click();
        Thread.sleep(50);
        String pleaseenterphonenumber=driver.findElement(By.xpath(".//*[@id='loginForm']/div[1]/span")).getText();
        assertEquals(pleaseenterphonenumber,"*手机号或邮箱未注册");
        System.out.println("完成了人才仅输入用户名后点击登录按钮测试！");
        passwordInput();
    }
    //仅输入密码后点击登录按钮测试
    public void passwordInput() throws InterruptedException {
        driver.findElement(By.className("submit-btn")).click();
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("********");
        driver.findElement(By.className("submit-btn")).click();
        String pleaseenterphonenumber=driver.findElement(By.xpath(".//*[@id='loginForm']/div[1]/span")).getText();
        assertEquals(pleaseenterphonenumber,"*请输入手机号");
        System.out.println("完成了人才仅输入密码后点击登录按钮测试！");
        usernameCorrectPasswordError();
    }
    //输入正确用户名和错误密码测试

    public void usernameCorrectPasswordError() throws InterruptedException {
        driver.findElement(By.name("username")).sendKeys("17875469790");
        driver.findElement(By.name("password")).sendKeys("******");
        driver.findElement(By.className("submit-btn")).click();
        Thread.sleep(50);
        String pleaseinputpassword=driver.findElement(By.xpath(".//*[@id='loginForm']/div[2]/span")).getText();
        assertEquals(pleaseinputpassword,"*用户名或者密码错误");
        System.out.println("完成了人才输入正确用户名和错误密码测试!");
        signIn();
    }
    //正确登录
    public void signIn() throws InterruptedException {
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("username")).sendKeys("13900000003");
        driver.findElement(By.name("password")).sendKeys("123456");
        driver.findElement(By.className("submit-btn")).click();
        Thread.sleep(100);
        String usercenter=driver.findElement(By.linkText("个人中心")).getText();
        assertEquals(usercenter,"个人中心");
        System.out.println("成功登录人才加用户默认进入"+usercenter);
        signout();
    }
    //退出人才用户登录
    public void signout() throws InterruptedException {
        Thread.sleep(20);
        driver.findElement(By.xpath("html/body/header/div[1]/div/div[2]/div[1]/div")).click();
        driver.findElement(By.xpath("html/body/header/div[1]/div/div[2]/div[1]/ul/li[4]/a")).click();
        System.out.println("完成人才用户退出测试！");
        Thread.sleep(50);
        employerLoginPage();
    }
    /****************************************雇主页面************************************************/
    //进入雇主登录页面

    public void employerLoginPage() throws InterruptedException {
        driver.findElement(By.cssSelector(".tips>a")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String employename=driver.findElement(By.xpath(".//*[@id='loginForm']/h1")).getText();
        System.out.println("进入雇主登录页面测试!");
        noInputvalueclickButton2();
    }
    //未输入值，直接点击登录按钮测试
    public void noInputvalueclickButton2() throws InterruptedException {
        driver.findElement(By.className("submit-btn")).click();
        String pleaseenterphonenumber2=driver.findElement(By.xpath(".//*[@id='loginForm']/div[1]/span")).getText();
        String pleaseinputpassword2=driver.findElement(By.xpath(".//*[@id='loginForm']/div[2]/span")).getText();
        assertEquals(pleaseenterphonenumber2,"*请输入用户名");
        assertEquals(pleaseinputpassword2,"*请输入密码");
        System.out.println("完成了雇主未输入值直接点击登录按钮测试！");
        usernameInput2();
    }

    //仅输入用户名后点击登录按钮测试
    public void usernameInput2() throws InterruptedException {
        driver.findElement(By.name("username")).sendKeys("qiye58");
        driver.findElement(By.className("submit-btn")).click();
        String pleaseinputpassword=driver.findElement(By.xpath(".//*[@id='loginForm']/div[2]/span")).getText();
        assertEquals(pleaseinputpassword,"*请输入密码");
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("xxxxxxxx");
        driver.findElement(By.className("submit-btn")).click();
        Thread.sleep(50);
        String pleaseenterphonenumber=driver.findElement(By.xpath(".//*[@id='loginForm']/div[1]/span")).getText();
        assertEquals(pleaseenterphonenumber,"*用户名未注册");
        System.out.println("完成了雇主仅输入用户名后点击登录按钮测试！");
        passwordInput2();
    }
    //仅输入密码后点击登录按钮测试
    public void passwordInput2() throws InterruptedException {
        driver.findElement(By.className("submit-btn")).click();
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("********");
        driver.findElement(By.className("submit-btn")).click();
        String pleaseenterphonenumber=driver.findElement(By.xpath(".//*[@id='loginForm']/div[1]/span")).getText();
        assertEquals(pleaseenterphonenumber,"*请输入用户名");
        System.out.println("完成了雇主仅输入密码后点击登录按钮测试！");
        usernameCorrectPasswordError2();
    }
    //输入正确用户名和错误密码测试

    public void usernameCorrectPasswordError2() throws InterruptedException {
        driver.findElement(By.name("username")).sendKeys("qiye58");
        driver.findElement(By.name("password")).sendKeys("******");
        driver.findElement(By.className("submit-btn")).click();
        Thread.sleep(50);
        String pleaseinputpassword=driver.findElement(By.xpath(".//*[@id='loginForm']/div[2]/span")).getText();
        assertEquals(pleaseinputpassword,"*用户名或者密码错误");
        System.out.println("完成了雇主输入正确用户名和错误密码测试!");
        signIn2();
    }
    //正确登录
    public void signIn2() throws InterruptedException {
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("username")).sendKeys("qiye58");
        driver.findElement(By.name("password")).sendKeys("123456");
        driver.findElement(By.className("submit-btn")).click();
        Thread.sleep(100);
        String usercenter=driver.findElement(By.linkText("企业中心")).getText();
        assertEquals(usercenter,"企业中心");
        System.out.println("成功登录雇主账号默认进入"+usercenter);
        signout2();
    }
    //退出人才用户登录
    public void signout2() throws InterruptedException {
        Thread.sleep(20);
        driver.findElement(By.xpath("html/body/header/div[1]/div/div[2]/div[1]/div")).click();
        driver.findElement(By.xpath("html/body/header/div[1]/div/div[2]/div[1]/ul/li[1]/a")).click();
        System.out.println("完成人才用户退出测试！");
        Thread.sleep(50);

    }
    @After
    public void tearDown() throws Exception {
        driver.close();
        driver.quit();

    }
}