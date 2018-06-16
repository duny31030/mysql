package Main;
import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;
import java.sql.*;
import java.awt.color.*;

import Main.MainUI;  

public class XueShengGuanLiUI extends JFrame implements ActionListener  
{  
	
	public static String InputId = "1";
	
     //定义组件
	JButton jb=new JButton();
    JButton jb1=new JButton();
    JButton jb2=new JButton(); 
    JButton jb3=new JButton();
    JButton jb4=new JButton();
    JTextField jtf=null;
    JPanel jp,jp1,jp2,jp3,jp4,jp5=null;  
    JLabel jlb1,jlb2,jlb3,jlb4,jlb5,jlb6,jlb7,jlb8=null;  

    public static void main(String[] args)
    {  
    	XueShengGuanLiUI ui = new XueShengGuanLiUI();  
    }  


//        private MainUI MainUI() {
//			// TODO Auto-generated method stub
//			return null;
//		}


	//****************************事件判断**********************

    //构造函数  
    public  XueShengGuanLiUI()    //不能申明为void!!!!!否则弹不出新界面  
    {  
    	// 创建组件
    	jb1=new JButton("添加");  
        jb1.setForeground(Color.BLUE);
        jb2=new JButton("查询");  
        jb2.setForeground(Color.BLUE);
    	jb3=new JButton("删除");
    	jb3.setForeground(Color.RED);
    	jb4=new JButton("修改");
    	jb4.setForeground(Color.BLUE);
    	
    	jp=new JPanel();
        jp1=new JPanel();  
        jp2=new JPanel();  
        jp3=new JPanel();  
        jp4=new JPanel();
        jp5=new JPanel();
        
        jlb1=new JLabel("学生学号：");
        jtf=new JTextField(15);  
        jlb3=new JLabel("最新公告："); 
        jlb3.setForeground(Color.red);
        jlb4=new JLabel("系统将于明日进行临时维护");
        
        
        jp.add(jlb1);
        jp.add(jtf);
        jp1.add(jb1);            
        jp2.add(jb2);       
        jp3.add(jb3);           
        jp4.add(jb4);                                            
        jp5.add(jlb3);
        jp5.add(jlb4); 
        
        this.add(jp);
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);  
        this.add(jp5);              

        //设置布局管理器  
        this.setLayout(new GridLayout(7,3,40,30));   // (行，列，行间距，列间距)
        this.setTitle("学生成绩管理系统");  
        this.setSize(400,500);  
        this.setLocation(200, 200);       

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        this.setVisible(true); 
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb3.addActionListener(this);
        jb4.addActionListener(this);


    }

    public void actionPerformed(ActionEvent e) 
    {  
    	if(jtf.getText().length() != 0)
    		InputId = jtf.getText();
    	if(e.getSource() == jb1)   // 执行添加操作
        {
           //关闭当前界面  
//          dispose();      	 
    		new InsertStu();
        }else if(e.getSource() == jb2)   // 执行查询操作
        {
        	//关闭当前界面  
//        	  dispose();  	
        	new SelectStu();
         }else if(e.getSource() == jb3)   // 执行删除操作
         {
        	 //关闭当前界面  
//        	  dispose();
    	    String url = "jdbc:mysql://localhost:3306/SC";
    		String user = "root";
    		String password = "123456";
        	 try 
        	 {
        		 Connection connect = DriverManager.getConnection(url, user, password);
     		  	 Statement stmt = connect.createStatement();   // 创建Statement对象
                 String sql="delete from stu where stu_id=";//生成一条sql语句
                 sql += InputId;
                 stmt.executeUpdate(sql);//执行sql语句
                 JOptionPane.showMessageDialog(null,"删除成功！","提示消息",JOptionPane.WARNING_MESSAGE);           
                 
                 connect.close();
        	 }catch (SQLException e1) {
     			// TODO Auto-generated catch block
     			e1.printStackTrace();
     		}
         }else if(e.getSource() == jb4)   // 执行修改操作
         {
        	 //关闭当前界面  
//    		  dispose();  
        	 new UpdateStu();
         }
    }
    public static String getInputId()
    {
    	return InputId;
    }
} 
