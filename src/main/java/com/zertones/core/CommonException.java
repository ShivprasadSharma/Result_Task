package com.zertones.core;

public class CommonException extends Exception
{
	private static final long serialVersionUID = 4139846798147448025L;

	public CommonException(String message)
	{
		super("Common Exception : " + message);
	}
}
