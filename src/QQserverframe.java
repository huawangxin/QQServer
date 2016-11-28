import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
/*
 *   服务器端界面
 */


public class QQserverframe extends JFrame implements ActionListener{

	JTabbedPane tabbedPane = null;
	JPanel panel1,panel2,panel3,panel4 = null;
	JButton close_button,logout_button,start_button = null;
	
	public QQserverframe ()
	{
		//总体设计
		setTitle("qq服务器端管理");
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/manager.png"));
		Container c = getContentPane();
		tabbedPane = new JTabbedPane ();
		panel1 = new JPanel(new FlowLayout());
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		
		tabbedPane.add(panel1,"在线用户管理");
		tabbedPane.add(panel2,"部门管理");
		tabbedPane.add(panel3,"用户管理");
		tabbedPane.add(panel4,"日志管理");
		//第一个tabbedpane处理	
		start_button = new JButton("启动服务器");
		start_button.addActionListener(this);
		close_button = new JButton("关闭服务器");
		close_button.addActionListener(this);
		logout_button = new JButton("强制下线");
		panel1.add(start_button);
		panel1.add(close_button);
		panel1.add(logout_button);
		
		
		
		
		c.add(tabbedPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,300);
		show();
	}
	public static void main(String[] args) {
		QQserverframe app = new QQserverframe();
        
	}
	
	class myThread implements Runnable
	{

		
		public void run() {
			System.out.println("thread id " + Thread.currentThread().getId());
			new QQServer();
		}
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		Thread thread = new Thread(new myThread());
		if(arg0.getSource() == start_button)
		{
			
			
			System.out.println("thread id " + Thread.currentThread().getId());
			thread.start();
			//new QQServer();
			JOptionPane.showMessageDialog(null, "启动服务器","info",JOptionPane.PLAIN_MESSAGE);
		}
		else if(arg0.getSource() == close_button)
		{
			//thread.stop();
			JOptionPane.showMessageDialog(null, "停止服务器","info",JOptionPane.PLAIN_MESSAGE);
		}
	}

}
