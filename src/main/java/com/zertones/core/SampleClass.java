package com.zertones.core;

import org.springframework.http.HttpStatus;

public class SampleClass
{

	private HttpStatus	status;
	private HttpStatus	reqStatus;
	private String		msg;
	private Object		data;

	public SampleClass(HttpStatus status, String msg, Object data)
	{
		this.msg = msg;
		this.status = status;
		this.data = data;
	}

	public SampleClass(HttpStatus status, String msg, Object data, HttpStatus reqStatus)
	{
		this.msg = msg;
		this.status = status;
		this.data = data;
		this.reqStatus = reqStatus;
	}

	public HttpStatus getReqStatus()
	{
		return reqStatus;
	}

	public void setReqStatus(HttpStatus reqStatus)
	{
		this.reqStatus = reqStatus;
	}

	@Override
	public String toString()
	{

		return " {status=" + status + ", msg=" + msg + ", data=" + data + ", reqStatus=" + reqStatus + "}";

	}

	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}

	public String getMsg()
	{
		return msg;
	}

	public HttpStatus getStatus()
	{
		return status;
	}

	public void setStatus(HttpStatus status)
	{
		this.status = status;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

}
