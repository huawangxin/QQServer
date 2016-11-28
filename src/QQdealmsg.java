import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Iterator;

import com.qq.common.*;
public class QQdealmsg extends Thread{

	Socket s ;
	public QQdealmsg (Socket s)
	{
		this.s = s;
	}
	
	public void notifyOther(String meID)
	{
		HashMap<String, QQdealmsg> map = ManageClientThread.map;
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext())
		{
			//取出在线好友
			String onlineID = it.next().toString();
			try {
				ObjectOutputStream oos = new ObjectOutputStream(ManageClientThread.getClientThread(onlineID).s.getOutputStream());
				Message msg = new Message();
				msg.setMsg_type(MassageType.message_reply_onlinefriend);
				msg.setReciever(onlineID);
				msg.setData(meID);
				oos.writeObject(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void run()
	{
		while(true)
		{
			
			try {
				//服务器线程接受客户端信息
				ObjectInputStream ois = new ObjectInputStream (s.getInputStream());
				Message msg = (Message)ois.readObject();
				//System.out.println(msg.getSender() + " to " + msg.getReciever() + " " + msg.getData());
				
				//数据转发
				if(msg.getMsg_type().equals(MassageType.message_data))
				{
				QQdealmsg recieverThread = ManageClientThread.getClientThread(msg.getReciever());
				ObjectOutputStream oos = new ObjectOutputStream(recieverThread.s.getOutputStream());
				System.out.println("QQdealmsg转发" + msg.getMsg_type() + " "+msg.getSender() + " "  +msg.getReciever() + " " + msg.getData());
				oos.writeObject(msg);
				}else if(msg.getMsg_type().equals(MassageType.message_get_onlinefriend))
				{
					Message new_msg = new Message();
					new_msg.setReciever(msg.getSender());
					new_msg.setMsg_type(MassageType.message_reply_onlinefriend);
					System.out.println("QQdealmsg 回复好友查询");
					new_msg.setData(ManageClientThread.getonlinefriend());
					
					
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(new_msg);
					
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
