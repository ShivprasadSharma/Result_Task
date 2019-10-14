package com.zertones.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.zertones.core.SampleClass;

public class Responses
{

	public static ResponseEntity<?> booleanSucessfullyResponse(String messgae, boolean status)
	{
		return new ResponseEntity<>(new SampleClass(HttpStatus.OK, messgae, status), HttpStatus.ACCEPTED);

	}

	public static ResponseEntity<?> booleanFailureyResponse(String messgae, boolean status)
	{
		return new ResponseEntity<>(new SampleClass(HttpStatus.OK, messgae, status), HttpStatus.ACCEPTED);

	}
}
