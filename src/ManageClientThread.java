import java.util.*;


public class ManageClientThread {

	public static HashMap<String, QQdealmsg> map = new HashMap<String,QQdealmsg>();
	
	//��hashmap����ӿͻ���ͨ���߳�
	public static void addClientThread(String uid,QQdealmsg clientThread)
	{
		map.put(uid, clientThread);
	}
	
	//�õ�hashmap���ض��߳�
	
	public static QQdealmsg getClientThread(String uid)
	{
		return (QQdealmsg) map.get(uid);
	}
	
	public static String getonlinefriend()
	{
		Iterator<String> it = map.keySet().iterator();
		String friendlist="";
		while(it.hasNext())
		{
			friendlist += it.next() + " "; 
		}
		return friendlist;
	}

}
