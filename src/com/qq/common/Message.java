/*
 * msg_type 1--密码正确
 *          2--密码错误
 *          3--消息数据
 *          4--在线好友查询
 */
package com.qq.common;

public class Message implements java.io.Serializable{
	
private String msg_type;
private String sender;
private String reciever;
private String data;

public String getSender() {
	return sender;
}

public void setSender(String sender) {
	this.sender = sender;
}

public String getReciever() {
	return reciever;
}

public void setReciever(String reciever) {
	this.reciever = reciever;
}

public String getData() {
	return data;
}

public void setData(String data) {
	this.data = data;
}

public String getMsg_type() {
	return msg_type;
}

public void setMsg_type(String msg_type) {
	this.msg_type = msg_type;
}

}
