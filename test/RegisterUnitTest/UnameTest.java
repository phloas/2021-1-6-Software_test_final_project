package RegisterUnitTest;
import static org.junit.Assert.assertEquals;
import au.com.bytecode.opencsv.CSVReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class UnameTest {
    private static final String FILE_PATH="C:\\Users\\HP\\Desktop\\大二下\\软件质量和检测\\软件测试大作业\\06-注册功能开发\\单元测试\\注册用户名测试用例.csv";
    private String uname;
    private String expect;

    public UnameTest(String uname, String expect) {
        this.uname = uname;
        this.expect = expect;
    }

    @Parameterized.Parameters
    public static Collection perpareData() {
        List<String[]> temp = new ArrayList<String[]>();
        String[] nextLine;
        int i = 0;
        try {

            CSVReader reader = new CSVReader(new FileReader(FILE_PATH));
            while((nextLine = reader.readNext()) != null) {
                if(i == 0){
                    nextLine[0] = nextLine[0].substring(1);
                }
                temp.add(nextLine);
                i++;
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return temp;
    }

    @Test
    public void test() throws Exception {
        regiser_function fun = new regiser_function();
        assertEquals(expect,fun.isUserName(uname));
    }

}
