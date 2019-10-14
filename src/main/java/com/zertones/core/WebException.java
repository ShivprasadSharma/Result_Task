package com.zertones.core;

import org.springframework.ui.Model;

public class WebException extends RuntimeException
{

	/**
	 * Auth: Zerton Team
	 */
	private static final long	serialVersionUID	= 1L;

	private Integer				errorCode;
	private String				viewName;
	private Model				model;

	public WebException(String exceptionMsg, Integer errorCode, String viewName)
	{
		super(exceptionMsg);
		// this.exceptionMsg = exceptionMsg;
		this.errorCode = errorCode;
		this.viewName = viewName;
	}

	// public String getExceptionMsg()
	// {
	// return exceptionMsg;
	// }
	//
	// public void setExceptionMsg(String exceptionMsg)
	// {
	// this.exceptionMsg = exceptionMsg;

	public Integer getErrorCode()
	{
		return errorCode;
	}

	public void setErrorCode(Integer errorCode)
	{
		this.errorCode = errorCode;
	}

	public String getViewName()
	{
		return viewName;
	}

	public void setViewName(String viewName)
	{
		this.viewName = viewName;
	}

}
