package com.zertones.core;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

import com.zertones.controller.WebURIConstants;

@ControllerAdvice
public class MyExceptionHandler extends Exception
{

	@ExceptionHandler(value = WebException.class)
	public String handleWbException(WebException e)
	{
		ModelAndView m = new ModelAndView(e.getViewName());
		m.addObject("message", e.getMessage());
		return "redirect:" + WebURIConstants.LIST_NOTIFICATIONS;
	}

	@ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
	public String handelMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e)
	{
		ModelAndView m = new ModelAndView("admin.noticelist");
		m.addObject("errorMsg", "Invalid Parameter " + e.getPropertyName());
		return "redirect:" + WebURIConstants.LIST_NOTIFICATIONS;
	}
}
