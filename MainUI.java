package Main;
import javax.swing.*;  
import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.sql.*;

public class MainUI extends JFrame implements ActionListener {  

    //定义组件   
    JButton jb1,jb2,jb3=null;  
    JRadioButton jrb1,jrb2=null;  
    JPanel jp1,jp2,jp3,jp4=null;  
    JTextField jtf=null;  
    JLabel jlb1,jlb2,jlb3=null;  
    JPasswordField jpf=null;  
    ButtonGroup bg=null;  

    //设定用户名和密码  
    final String stu_name="王小明";  
    final String stu_pwd="1"; 
    final String stu_num="14140301"; 
    final String tea_name="王老师";  
    final String tea_pwd="1";  
    final String tea_num="00001"; 

    public static void main(String[] args) {  

        MainUI mUI=new MainUI();  
    }  
    public MainUI()  
    {
    	MysqlJdbc();
         // 创建组件  
        jb1=new JButton("登录");  
        jb2=new JButton("重置"); 
        jb3=new JButton("退出");

        // 设置监听  
        jb1.addActionListener(this);  
        jb2.addActionListener(this);  
        jb3.addActionListener(this);  
        
        // 添加教师、学生单选框
        jrb1=new JRadioButton("教师");  
        jrb2=new JRadioButton("学生");  
        bg=new ButtonGroup();  
        bg.add(jrb1);  
        bg.add(jrb2);  
        jrb2.setSelected(true);  //初始页面默认选择权限为 学生

        jp1=new JPanel();  
        jp2=new JPanel();  
        jp3=new JPanel();  
        jp4=new JPanel();                 

        jlb1=new JLabel("用户名：");  
        jlb2=new JLabel("密    码：");  
        jlb3=new JLabel("权    限：");  

        jtf=new JTextField(10);  
        jpf=new JPasswordField(10);  
        //加入到JPanel中  
        jp1.add(jlb1);  
        jp1.add(jtf);  

        jp2.add(jlb2);  
        jp2.add(jpf);  

        jp3.add(jlb3);      //添加标签
        jp3.add(jrb1);  
        jp3.add(jrb2);  

        jp4.add(jb1);       //添加按钮
        jp4.add(jb2);  
        jp4.add(jb3);

        //加入JFrame中  
        this.add(jp1);  
        this.add(jp2);  
        this.add(jp3);  
        this.add(jp4);  

        this.setLayout(new GridLayout(4,1));            //选择GridLayout布局管理器        
        this.setTitle("学生成绩管理系统");          
        this.setSize(300,200);         
        this.setLocation(400, 200);           
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //设置当关闭窗口时，保证JVM也退出 
        this.setVisible(true);  
        this.setResizable(true);  
        

    }  
    
    public void MysqlJdbc()
    {
    	try {
    	      Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
    	      //Class.forName("org.gjt.mm.mysql.Driver");
    	     System.out.println("Success loading Mysql Driver!");
    	    }
    	    catch (Exception e) {
    	      System.out.print("Error loading Mysql Driver!");
    	      e.printStackTrace();
    	    }
    	    try {
    	      Connection connect = DriverManager.getConnection(
    	          "jdbc:mysql://localhost:3306/test","root","123456");
    	           //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码

    	      System.out.println("Success connect Mysql server!");
    	      Statement stmt = connect.createStatement();
//    	      ResultSet rs = stmt.executeQuery("select * from user");
//    	                                                              //user 为你表的名称
////    	      String getName = rs.getString("name");
////    	      System.out.println("name = " + getName);
//    	      rs.next();
//    	      String getName = new String(rs.getString("name"));
//    	      System.out.println("name = " + getName);
    	//while (rs.next()) {
//    	        System.out.println(rs.getString("name"));
//    	      }
    	    }
    	    
    	    catch (Exception e) {
    	      System.out.print("get data error!");
    	      e.printStackTrace();
    	    }
    }
    
    
    
    public void actionPerformed(ActionEvent e) {            //事件判断

        if(e.getActionCommand()=="登录")  
        {  
            //如果选中教师登录  
            if(jrb1.isSelected())  
            {  
                  tealogin();                               //连接到教师的方法 页面
            }else if(jrb2.isSelected()) //学生在登录系统  
            {  
                  stulogin();                               //连接到学生的方法 页面
            }  

        }else if(e.getActionCommand()=="重置")  
        {  
                  clear();   // 清楚文本框  
        }             

    }  

     //学生登录判断方法  
    public void stulogin()  
    {  	
    	int flag = 0;
    	
    	String url = "jdbc:mysql://localhost:3306/test";
    	String user = "root";
    	String passwd = "123456";
    	try {
			Connection connect = DriverManager.getConnection(url, user, passwd);
			Statement stmt = connect.createStatement();
  	      	ResultSet rs = stmt.executeQuery("select * from user");
  	      	while(rs.next())
  	      	{
  	      		String getStuName = new String(rs.getString("name"));
  	      		String getStuPasswd = new String(rs.getString("password"));
  	      		if(getStuName.equals(jtf.getText()) && getStuPasswd.equals(jpf.getText()))
  	      		{
  	      			flag = 1;
  	      			break;
  	      		}
  	      	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
        if(flag == 1)  
        {            
            JOptionPane.showMessageDialog(null,"登录成功！","提示消息",JOptionPane.WARNING_MESSAGE);           
            dispose();        
            clear();            
            StdUI ui=new StdUI();       //创建新界面  
        }
        else
        {  
        	JOptionPane.showMessageDialog(null,"用户名或者密码错误！\n请重新输入","提示消息",JOptionPane.ERROR_MESSAGE);  
            //清空输入框  
            clear();  
        }
    }  
    //教师登录判断方法  
    public void tealogin()  
    {  
        if(tea_name.equals(jtf.getText())&&tea_pwd.equals(jpf.getText()))  
        {  

             JOptionPane.showMessageDialog(null,"登录成功！","提示消息",JOptionPane.WARNING_MESSAGE);  
             clear();         
             dispose();             
             TerUI ui=new TerUI();  //创建一个新界面  
        }else if(jtf.getText().isEmpty() && jpf.getText().isEmpty())  
        {  
            JOptionPane.showMessageDialog(null,"请输入用户名和密码！","提示消息",JOptionPane.WARNING_MESSAGE);  
        }else if(jtf.getText().isEmpty())  
        {  
            JOptionPane.showMessageDialog(null,"请输入用户名！","提示消息",JOptionPane.WARNING_MESSAGE);  
        }else if(jpf.getText().isEmpty())  
        {  
            JOptionPane.showMessageDialog(null,"请输入密码！","提示消息",JOptionPane.WARNING_MESSAGE);  
        }else  
        {  
            JOptionPane.showMessageDialog(null,"用户名或者密码错误！\n请重新输入","提示消息",JOptionPane.ERROR_MESSAGE);  
            clear();  //清空输入框  
        }  
    }  
    //清空文本框和密码框  
    public  void clear()  
    {  
        jtf.setText("");  
        jpf.setText("");  
    }  

} 
