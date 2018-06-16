package Main;
import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;  
import javax.swing.JButton;
import java.awt.color.*;
import javax.swing.JOptionPane;
import Main.MainUI;  

public class StdUI extends JFrame implements ActionListener  
{  
	
         //定义组件  
        JButton jb1=new JButton();
        JButton jb2=new JButton(); 
        JPanel jp1,jp2,jp3,jp4,jp5=null;  
        JLabel jlb1,jlb2,jlb3,jlb4,jlb5,jlb6,jlb7,jlb8=null;  

        public static void main(String[] args)
        {  
          StdUI  ui=new StdUI();  
        }  


//        private MainUI MainUI() {
//			// TODO Auto-generated method stub
//			return null;
//		}


		//****************************事件判断**********************

        //构造函数  
        public  StdUI()    //不能申明为void!!!!!否则弹不出新界面  
        {  

            //创建组件  
            jb1=new JButton("课程表");  
            jb1.setForeground(Color.BLUE);
            jb2=new JButton("成绩查询");  
            jb2.setForeground(Color.BLUE);

            jp1=new JPanel();  
            jp2=new JPanel();  
            jp3=new JPanel();  
            jp4=new JPanel();
            jp5=new JPanel();
            
            jlb1=new JLabel("姓名：");  
            jlb2=new JLabel("学号：");
            jlb7=new JLabel("学院：");
            jlb3=new JLabel("最新公告："); 
            jlb3.setForeground(Color.red);
            jlb4=new JLabel("系统将于明日进行临时维护");
//            String stun = new MainUI().getStu();
            
            jlb5=new JLabel(MainUI.getStuname());
            jlb6=new JLabel(MainUI.getStuid());
            jlb8=new JLabel(MainUI.getStucollege());
            
            jp1.add(jlb1); 
            jp1.add(jlb5);
            
            jp2.add(jlb2);  
            jp2.add(jlb6);
//            jp1.add(jlb7);
//            jp1.add(jlb8);
            
            jp3.add(jlb7);
            jp3.add(jlb8);
            
            jp4.add(jb1);  
            jp4.add(jb2);  
                                
            
            jp5.add(jlb3);
            jp5.add(jlb4); 
            
            
            this.add(jp1);
            this.add(jp2);
            this.add(jp3);
            this.add(jp4);  
            this.add(jp5);  
            

            //设置布局管理器  
            this.setLayout(new GridLayout(5,3,40,30));   // (行，列，行间距，列间距)
            this.setTitle("学生成绩管理系统");  
            this.setSize(400,300);  
            this.setLocation(200, 200);       

            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

            this.setVisible(true); 
            jb1.addActionListener(this);
            jb2.addActionListener(this);


        }

        public void actionPerformed(ActionEvent e) {  
             if(e.getSource() == jb1){
                //关闭当前界面  
//                 dispose();  
                 new KeChengBiaoUI();
                }else if(e.getSource() == jb2){
                    //关闭当前界面  
//                    dispose();  
                    new ChengJiBiaoUI();
                }

        }  
} 