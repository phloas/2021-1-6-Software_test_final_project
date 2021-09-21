package LoginUnitTest;

import bean.User;
import util.JDBC;

public class login_function {

    public String login(String uname,String upwd){
        JDBC jdbc = new JDBC();
        User user = jdbc.findUser(uname);
        if(isnull(uname)|| isnull(upwd)){
            return "用户名或密码为空";
        }else if(user == null){
            return "用户名或密码错误";
        } else if(equalPwd(user.getUpwd(),upwd)){
            return "登录成功";
        }else {
            return "用户名或密码错误";
        }
    }
    public boolean equalPwd(String pwd1,String pwd2){
        if(pwd1.equals(pwd2)){
            return true;
        }else{
            return false;
        }
    }

    public  boolean isnull(String str) {
        if (str.equals("")) {
            return true;
        }
        return false;
    }

}
