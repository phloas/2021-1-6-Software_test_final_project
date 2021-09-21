package AutoFunctionTest_up_check_add;
import static org.junit.Assert.assertEquals;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import au.com.bytecode.opencsv.CSVReader;

@RunWith(Parameterized.class)
public class UpdateTest_email {
    private static WebDriver d;
    private String email;
    private String expect;


    HashMap<String, String> params = new HashMap<String, String>();
    CsvTestData td = new CsvTestData("C:\\Users\\HP\\Desktop\\大二下\\软件质量和检测\\软件测试大作业\\06-填写、修改、查看注册信息自动化测试\\修改\\SearchData_email.csv"); // replace with your csv file location


    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\HP\\Desktop\\大二下\\软件质量和检测\\lab5\\selenium_example\\geckodriver.exe");
        d = new FirefoxDriver();
        d.get("http://localhost:8080/2021_SOFTTest_final_war_exploded/login.jsp");
        d.findElement(By.name("username")).sendKeys("Whb0811");
        d.findElement(By.name("upwd")).sendKeys("Whb123456@");
        Thread.sleep(1000);
        d.findElement(By.className("btn")).click();
        Thread.sleep(2000);
        d.findElement(By.id("update")).click();
        Thread.sleep(1000);
        d.findElement(By.id("updateemail")).click();
        params.putAll(td.readcsvData());
    }

    @Parameters
    public static Collection perpareData() throws IOException {
        String FILE_PATH="C:\\Users\\HP\\Desktop\\大二下\\软件质量和检测\\软件测试大作业\\06-填写、修改、查看注册信息自动化测试\\修改\\修改注册信息用例（邮箱）.csv";
        CSVReader reader = new CSVReader(new FileReader(FILE_PATH));
        String [] nextLine;
        String [][] strs=new String[4][2];
        int i=0;
        while ((nextLine = reader.readNext()) != null) {
            String[] str =  {nextLine[0],nextLine[1]};
            strs[i]=str;
            i++;
        }
        reader.close();
        return Arrays.asList(strs);
    }


    public UpdateTest_email(String email,String expect) {
        super();
        this.email = email;
        this.expect = expect;
    }

    @Test
    public void testemail() throws Exception{
        d.get(params.get("AppURL"));
        d.findElement(By.name("email")).sendKeys(email);
        d.findElement(By.className("btn")).click();
        String a = d.findElement(By.xpath("/html/body/div/div/h1")).getText();
        assertEquals(expect,a);
        d.quit();
    }

    @After
    public void tearDown() throws Exception {
        d.quit();
    }
}
