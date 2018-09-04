package com.example.mark.myapplication;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;


public class Message {
	private String mssg_type;
	private String comm_type;
	private String value;
	private	String buff;


	public Message(String s) {
		String []ps=s.split(" ");
		this.mssg_type=ps[0];
		this.comm_type=ps[1];
		this.value=ps[2];
	}

	public Message(Mssg_Type mssg,Comm_Type comm,String val) {
		this.mssg_type=mssg.toString();
		this.comm_type=comm.toString();
		this.value=val;
		this.buff=this.mssg_type+" "+this.comm_type+" "+this.value;
	}


	public Message(String mssg,String comm,String val) {
		this.mssg_type=mssg;
		this.comm_type=comm;
		this.value=val;
		this.buff=this.mssg_type+" "+this.comm_type+" "+this.value+"\n";
	}

	public Message(String mssg,String comm,float val) {
		this.mssg_type=mssg;
		this.comm_type=comm;
		this.value=Float.toString(val);
		this.buff=this.mssg_type+" "+this.comm_type+" "+this.value+"\n";
	}

	public Message(Mssg_Type mssg,Comm_Type comm,float val) {
		this.mssg_type=mssg.toString();
		this.comm_type=comm.toString();
		this.value=Float.toString(val);
		this.buff=this.mssg_type+" "+this.comm_type+" "+this.value;
	}

	public String getMssgtype() {
		return mssg_type;
	}
	public String getCommtype() {
		return comm_type;
	}
	public String getValue() {
		return value;
	}

	public String getBuff() {
		return buff;
	}

}
