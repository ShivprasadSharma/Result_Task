package com.zertones.core;

public class SecurityException extends Exception
{
	private static final long serialVersionUID = -3289536691157876117L;

	public SecurityException(String message)
	{
		super("Security Exception : " + message);
	}

}
