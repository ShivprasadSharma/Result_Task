package com.zertones.dao;

import javax.persistence.PrePersist;

public class AuditListener
{
	@PrePersist
	private void log(Object object)
	{
		System.out.println("Your log code here");
	}

}
