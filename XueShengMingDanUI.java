package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class XueShengMingDanUI extends JFrame  
{  

    public XueShengMingDanUI()  
    {  
        intiComponent();  
    }  

     //初始化窗体组件 
    private void intiComponent()  
    {  
        // 设置JTable的列名   
        String[] columnNames =  
        { "学号","姓名",  "专业", "班级"};  
        Object[][] obj=new Object[10][7];  
        
        // 连接数据库
        try 
    	{
        	String url = "jdbc:mysql://localhost:3306/SC";
        	String user = "root";
        	String password = "123456";
			Connection connect = DriverManager.getConnection(url, user, password);
			Statement stmt = connect.createStatement();
  	      	ResultSet rs = stmt.executeQuery("select * from stu");
  	      	int i = 0;
  	      	while(rs.next())
  	      	{
      			obj[i][0] = new String(rs.getString("stu_id"));
      			obj[i][1] = new String(rs.getString("name"));
      			obj[i][2] = new String(rs.getString("college"));  	 
      			obj[i][3] = new String(rs.getString("class"));
      			++i;	
  	      	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        JTable table=new JTable(obj, columnNames);  //JTable的其中一种构造方法 
        TableColumn column=null;                    //设置JTable的列默认的宽度和高度 
        int colunms = table.getColumnCount();  
        for(int i=0;i<colunms;i++)  
        {  
            column = table.getColumnModel().getColumn(i);  
            column.setPreferredWidth(100);           //将每一列的默认宽度设置为100
        } 
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //设置JTable自动调整列表的状态，此处设置为关闭                 
        JScrollPane scroll = new JScrollPane(table);  //用JScrollPane装载JTable，这样超出范围的列就可以通过滚动条来查看 
        scroll.setSize(300, 50);         

        add(scroll); 

        this.setLocation(450, 200); 
        this.setVisible(true);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.pack();  
    }  

    public static void main(String[] args)  
    {  
        new XueShengMingDanUI();  
    }  
}  