/*
 *  �������˿�
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
			 System.out.println(" �ȴ��ͻ�����");
			 
			 while(true)
			 {
			 Socket socket = serverSocket.accept();
			 System.out.println("�������");
			 
			 ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			 QQUser usr = (QQUser) ois.readObject();
			 System.out.println(usr.getName() + " " + usr.getPasswd());
			 
			 oos = new ObjectOutputStream(socket.getOutputStream());
			 msg = new Message();	 
			 if(usr.getPasswd().equals("1234"))		
			 {			 
			  
			 //ȷ�ϵ�¼
			 msg.setMsg_type("1"); //1--> ������ȷ
			 oos.writeObject(msg);
			
			 //����һ���̣߳�����ͨ��
			 QQdealmsg dealMsg = new QQdealmsg(socket);
			//���̼߳���hashmap
			 ManageClientThread.addClientThread(usr.getName(), dealMsg);
			 //֪ͨ�������ߺ���
			 dealMsg.notifyOther(usr.getName());
			 dealMsg.start();
			 }
			 else 
			 {
				 msg.setMsg_type("2"); //2--���������
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
