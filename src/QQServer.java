/*
 *  服务器端口
 */
import java.io.*;
import java.net.*;
import com.qq.common.*;

public class QQServer {
	ObjectOutputStream oos = null;
	Message msg = null;
	public QQServer()
	{
		try {
			ServerSocket serverSocket = new ServerSocket(9999);
			 System.out.println(" 等待客户链接");
			 
			 while(true)
			 {
			 Socket socket = serverSocket.accept();
			 System.out.println("链接完成");
			 
			 ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			 QQUser usr = (QQUser) ois.readObject();
			 System.out.println(usr.getName() + " " + usr.getPasswd());
			 
			 oos = new ObjectOutputStream(socket.getOutputStream());
			 msg = new Message();	 
			 if(usr.getPasswd().equals("1234"))		
			 {			 
			  
			 //确认登录
			 msg.setMsg_type("1"); //1--> 密码正确
			 oos.writeObject(msg);
			
			 //单开一个线程，保持通信
			 QQdealmsg dealMsg = new QQdealmsg(socket);
			//将线程加入hashmap
			 ManageClientThread.addClientThread(usr.getName(), dealMsg);
			 //通知其他在线好友
			 dealMsg.notifyOther(usr.getName());
			 dealMsg.start();
			 }
			 else 
			 {
				 msg.setMsg_type("2"); //2--》密码错误
				 oos.writeObject(msg);
			 }
			 }
			
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			
		}
	}
	/*public static void main(String[] args) {
		QQServer app = new QQServer();

	}*/

}
