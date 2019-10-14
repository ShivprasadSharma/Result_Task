package com.zertones.core;

public class SystemException extends Exception
{
	private static final long serialVersionUID = 1011751510619569741L;

	public SystemException(String message)
	{
		super("System Exception : " + message);
	}

}
