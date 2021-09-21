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
public class AddInfoTest {
    private static WebDriver d;
    private String realname;
    private String phone;
    private String birthday;
    private String expect;

    HashMap<String, String> params = new HashMap<String, String>();
    CsvTestData td = new CsvTestData("C:\\Users\\HP\\Desktop\\大二下\\软件质量和检测\\软件测试大作业\\06-填写、修改、查看注册信息自动化测试\\填写\\SearchData.csv"); // replace with your csv file location


    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\HP\\Desktop\\大二下\\软件质量和检测\\lab5\\selenium_example\\geckodriver.exe");
        d = new FirefoxDriver();
        d.get("http://localhost:8080/2021_SOFTTest_final_war_exploded/login.jsp");
        d.findElement(By.name("username")).sendKeys("Whb0811");
        d.findElement(By.name("upwd")).sendKeys("Whb123456@");
//        Thread.sleep(1000);
        d.findElement(By.className("btn")).click();
//        Thread.sleep(2000);
        d.findElement(By.id("addinfo")).click();
        params.putAll(td.readcsvData());
    }

    @Parameters
    public static Collection perpareData() throws IOException {
        String FILE_PATH="C:\\Users\\HP\\Desktop\\大二下\\软件质量和检测\\软件测试大作业\\06-填写、修改、查看注册信息自动化测试\\填写\\填写补充信息用例.csv";
        String [] nextLine;
        String [][] strs=new String[25][4];
        try {
            CSVReader reader = new CSVReader(new FileReader(FILE_PATH));
            int i=0;
            while ((nextLine = reader.readNext()) != null) {
                String[] str =  {nextLine[0],nextLine[1],nextLine[2],nextLine[3]};
                strs[i]=str;
                i++;
            }
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Arrays.asList(strs);
    }

    public AddInfoTest(String realname, String phone, String birthday, String expect) {
        this.realname = realname;
        this.phone = phone;
        this.birthday = birthday;
        this.expect = expect;
    }

    @Test
    public void testpwd() throws Exception{
        d.get(params.get("AppURL"));
        d.findElement(By.name("realname")).sendKeys(realname);
        d.findElement(By.name("phone")).sendKeys(phone);
        d.findElement(By.name("birthday")).sendKeys(birthday);
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
