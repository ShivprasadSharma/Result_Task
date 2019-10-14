package com.zertones.core;

//These class is used to define business exception
public class BusinessException extends Exception
{
	private static final long	serialVersionUID	= 3126100527031581129L;
	String						message;
	int							status;
	Object						obj;

	public BusinessException(int statusCode, String message, Object obj)
	{
		this.message = message;
		this.status = statusCode;
		this.obj = obj;
		// super("Business Exception : " + message);
	}

}
