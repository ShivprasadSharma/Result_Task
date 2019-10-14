package com.zertones.service.common;

import com.zertones.model.common.ContactUsModel;

public interface JavaSendMailService
{

	public void sendMail(String to, String token, String user);

	public void contactUs(ContactUsModel contactUsModel, int instCode);

	public void sendVerifiedStudentPasswordToMail(String to, String token, String user, String Ccode);

	public void sendMailToDeclineStudent(String to, String user);

	public void sendnewuser(String to, String token, String user);
}
