package StudentManegementSystem;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ArrayList<User> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("欢迎来到学生生管理系统");
            System.out.println("请选择你的操作：1.登录 2.注册 3.忘记密码 4.退出系统");
            String choose = sc.next();
            switch(choose){
                case "1" ->login(list);
                case "2" ->register(list);
                case "3" ->forgetPassword(list);
                case "4" ->{
                    System.out.println("谢谢使用，再见");
                    System.exit(0);
                }
                default -> System.out.println("没有这个选项");

            }
        }   //循环部分

    }
    private static void forgetPassword(ArrayList<User> list) {
        System.out.println("忘记密码");
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你的用户名");
        String username = sc.next();
        for (User user : list) {
            if (username.equals(user.getUsername())) {
                System.out.println("请输入手机号码");
                String number=sc.next();
                if (number.equals(user.getPhoneNumber())){
                    System.out.println("核实身份成功请输入验证码");
                    String code = getCode();
                    System.out.println("验证码："+code);
                    String inputcode = sc.next();
                    if (inputcode.equals(code)){
                        System.out.println("请输入新的密码");
                        String newpwd = sc.next();
                        System.out.println("再次输入新密码");
                        String nnpwd = sc.next();
                        if (newpwd.equals(nnpwd)){
                            System.out.println("修改成功");
                        }
                        else{
                            System.out.println("修改失败，你牛逼");
                        }
                    }
                }
            }
        }


    }// 忘记密码
    private static void login(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        String userName;
        String userPassWord;
        String randomCode,Code;
        // 键入用户名
        while (true) {
            System.out.println("请输入用户名");
            userName = sc.next();
            boolean flag = contain(list,userName);
            if (flag){
                System.out.println("用户名正确");
                break;
            }
            else {
                System.out.println("用户名不存在，重新输入");
            }
        }
        // 键入密码
        while (true) {
            System.out.println("请输入密码");
            userPassWord = sc.next();
            User user;
            int t=0;
            for (User value : list) {
                user = value;
                if (user.getPassword().equals(userPassWord)) {
                    System.out.println("密码正确");
                    t++;
                    break;
                }
            }
            if (t==1){
                break;
            }else{
                System.out.println("密码错误，重新输入");
            }
        }
        // 键入验证码 长度位5 4个英文字符+1个数字
        while (true) {
            randomCode = getCode();
            System.out.println("你的验证码是"+randomCode+"注意区分大小写");
            Code = sc.next();
            if (Code.equals(randomCode)){
                System.out.println("验证成功");
                break;
            }
            else {
                System.out.println("验证失败，重新输入新的验证码");
            }
        }

        System.out.println("费劲九九八十一难，恭喜你终于登录成功");

    }   // 登录账号
    private static void register(ArrayList<User> list) {
        System.out.println("注册");

        Scanner sc = new Scanner(System.in);
        String userName;
        String userPassWord;
        String userpersonid;
        String userPhoneNumber;

        // 录入用户名
        while (true) {
            System.out.println("请输入用户名 长度为3-15 可以大小写英文字符+数字 不可以纯数字");
            userName =sc.next();
            boolean flag1 = checkName(userName);
            if (!flag1){
                System.out.println("用户名格式不正确，请重新输入");
                continue;
            }       //格式判断

             boolean flag2 = contain(list,userName);
            if (flag2){
                System.out.println("用户名"+userName+"已经存在，请重新输入新的用户名");
            }
            else{
                System.out.println("该用户名唯一，可以使用");
                break;
            }

        }

        // 录入密码
        while (true) {
            System.out.println("请输入用户密码");
            userPassWord = sc.next();
            System.out.println("请再次输入用户密码");
            String userPassWordAgain = sc.next();

            if (!(userPassWord.equals(userPassWordAgain))){
                System.out.println("俩次密码输入不一致，请重新输入");
            }
            else{
                System.out.println("密码可以使用");
                break;
            }
        }   // 这里密码不标准有待改进

        // 录入用户身份证号码
        while (true) {
            System.out.println("请输入用户身份证号码");
            userpersonid = sc.next();
            boolean flag = checkPersonId(userpersonid);

            if (flag){
                System.out.println("身份证号码无误");
                break;
            }else {
                System.out.println("格式错误，请重新输入身份证号码");
            }

        }

        // 录入手机号码
        while (true) {
            System.out.println("请输入用户手机号码");
            userPhoneNumber = sc.next();
            boolean flag = checkPhoneNumber(userPhoneNumber);
            if (flag){
                System.out.println("手机号码无误");
                break;
            }
            else {
                System.out.println("手机号码格式有误，请重新输入");
            }
        }

        //添加用户信息 再添加到集合中
        User u =new User(userName,userPassWord,userpersonid,userPhoneNumber);

        // 用户对象存到用户集合里
        list.add(u);
        System.out.println("注册成功");

        // 遍历集合
        printList(list);

    }   // 注册账号

    private static void printList(ArrayList<User> list) { System.out.println("遍历集合");
        for (User user : list) {
            System.out.println("用户名" + user.getUsername() + ", 手机号" + user.getPhoneNumber());
        }
    }       // 打印用户名和手机号

    private static boolean checkPhoneNumber(String userPhoneNumber) {
        //长度11位
        int len =userPhoneNumber.length();
        if (!(len==11)){
            System.out.println("手机号码长度小于或大于11位，请重新输入");
            return false;
        }
        //不以0开头
        if (userPhoneNumber.charAt(0)=='0'){
            return false;
        }
        //必须都是数字
        for (int i = 0; i < userPhoneNumber.length(); i++) {
            char slice = userPhoneNumber.charAt(i);
            if (slice<'0'||slice>'9'){
                return false;
            }
        }

        return true;
    }// 核对手机号码
    private static boolean checkPersonId(String userpersonid) {

            // 长度18位
            if (userpersonid.length()!=18){
                return false;
            }
            // 不能0开头
            boolean flag = userpersonid.startsWith("0");
            if (flag){
                return false;
            }
            // 前17位都是数字
            for (int i = 0; i < userpersonid.length()-1; i++) {
                char slice = userpersonid.charAt(i);
                if (!(slice>='0'&&slice<='9')){
                    return false;
                }
            }
            // 最后一位可以是数字或X(x)
            char slice = userpersonid.charAt(userpersonid.length()-1);
        return (slice >= '0' && slice <= '9') || (slice == 'x' || slice == 'X');
    }// 核对身份证号码
    private static boolean contain(ArrayList<User> list, String userName) {
        for (User uu : list) {
            String rightUsername = uu.getUsername();
            if (rightUsername.equals(userName)) {
                return true;
            }
        }
        return false;
    }// 判断用户名唯一性
    private static boolean checkName(String userName) {
        int len = userName.length();
        if (len<3 || len>15){
            System.out.println("用户名长度不够或超出长度");
            return false;
        }//长度

        for (int i = 0; i < userName.length(); i++) {
            char c= userName.charAt(i);
            if(!( (c>'a'&&c<'z') || (c>'A'&&c<'Z') || (c>'0'&&c<'9') )){
                return false;
            }
        }//是否含有字符或数字 （可以纯字符 不可以纯数字）

        for (int i = 0; i < userName.length(); i++) {
            char c= userName.charAt(i);
            if((c>'a'&&c<'z') || (c>'A'&&c<'Z') ){
                break;
            }
        }

        return true;
    }// 核对用户名
    private static String getCode(){
        ArrayList<Character> list=new ArrayList<>();
        Random r =new Random();
        StringBuilder sb = new StringBuilder();
        for(int i=0 ;i<26;i++){
            list.add((char)('a'+i));
            list.add((char)('A'+i));
        }

        for (int i = 0; i < 4; i++) {
            char c = list.get(r.nextInt(list.size()));
            sb.append(c);
        }       // 随机4个字符

        sb.append(r.nextInt(10));       // 随机一个数字

        char[] arr = sb.toString().toCharArray(); // 转换成数组并交换数字的位置
        char temp;
        int index=r.nextInt(arr.length-1);
        temp=arr[arr.length-1];
        arr[arr.length-1]=arr[index];
        arr[index]=temp;

        return new String(arr);      // 转换成字符串
    }// 生成验证码

}

// 水印 @骑猪看夕阳