package Main;
import java.awt.*;  
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import java.awt.color.*;
import Main.MainUI;  

public class SelectStu extends JFrame implements ActionListener  
{  
	
	public static String getStuid = XueShengGuanLiUI.getInputId();
	public static String getStuna = "1";
	public static String getStucol = "1";
	public static String getStucla = "1";
     //定义组件  
    JButton jb1=new JButton();
    ButtonGroup bg=null;
    JPanel jp1,jp2,jp3,jp4,jp5,jp6=null;  
    JLabel jlb1,jlb2,jlb3,jlb4,jlb5,jlb6,jlb7,jlb8,jlb9,jlb10=null;  

    public static void main(String[] args)
    {  
    	SelectStu  ui=new SelectStu();  
    	
    }  


//        private MainUI MainUI() {
//			// TODO Auto-generated method stub
//			return null;
//		}


	//****************************事件判断**********************

    //构造函数  
    public  SelectStu()    //不能申明为void!!!!!否则弹不出新界面  
    {     	
    	// 根据学号进行查询
    	SelSql();
    	
        //创建组件   
    	jb1=new JButton("确定");
        jp1=new JPanel();  
        jp2=new JPanel();  
        jp3=new JPanel();  
        jp4=new JPanel();
        jp5=new JPanel();
        jp6=new JPanel();  
        
        jlb1=new JLabel("姓名：");  
        jlb2=new JLabel("学号：");
        jlb3=new JLabel("学院：");
        jlb4=new JLabel("班级：");
        jlb9=new JLabel("最新公告："); 
        jlb9.setForeground(Color.red);
        jlb10=new JLabel("系统将于明日进行临时维护");
        
        jlb5=new JLabel(getStuna);
        jlb6=new JLabel(getStuid);
        jlb7=new JLabel(getStucol);
        jlb8=new JLabel(getStucla);
        
     // 设置监听  
        jb1.addActionListener(this);  
        
        
        jp1.add(jlb1); 
        jp1.add(jlb5);
        
        jp2.add(jlb2);  
        jp2.add(jlb6);
        
        jp3.add(jlb3);
        jp3.add(jlb7);
        
        jp4.add(jlb4);  
        jp4.add(jlb8);  
                            
        
        jp5.add(jlb9);
        jp5.add(jlb10); 
        
        jp6.add(jb1);
        
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);  
        this.add(jp5);  
        this.add(jp6);

        //设置布局管理器  
        this.setLayout(new GridLayout(6,3,40,30));   // (行，列，行间距，列间距)
        this.setTitle("学生成绩管理系统");  
        this.setSize(400,600);  
        this.setLocation(200, 200);       

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        this.setVisible(true); 
        jb1.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {  
         if(e.getSource() == jb1){
            //关闭当前界面   
        	 SelectStu.this.dispose();
            }
    } 
    
    public void SelSql()
    {
    	String url = "jdbc:mysql://localhost:3306/SC";
		String user = "root";
		String password = "123456";
    	 try 
    	 {
    		 Connection connect = DriverManager.getConnection(url, user, password);
 		  	 Statement stmt = connect.createStatement();   // 创建Statement对象
 		  	 ResultSet rs = stmt.executeQuery("select * from stu");
             while(rs.next())
             {
            	 if(getStuid.equals(rs.getString("stu_id")))
            	 {
            		 SelectStu.getStuna = new String(rs.getString("name"));
            		 SelectStu.getStucol = new String(rs.getString("college"));
            		 SelectStu.getStucla = new String(rs.getString("class"));
            	 }
             }
             connect.close();
    	 }catch (SQLException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
    }
    
    
    
} 