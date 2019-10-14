package com.zertones.service.common;

import javax.mail.internet.MimeMessage;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zertones.model.common.ContactUsModel;
import com.zertones.model.common.InstituteInfoMaster;

@Transactional
@Service("javaSendMailService")
public class JavaSendMailServiceImpl implements JavaSendMailService
{

	/*
	 * private MailSender mailSender;
	 *
	 * @Autowired
	 *
	 * @Qualifier("mailRelated") public void setMailSender(MailSender mailSender) {
	 * this.mailSender = mailSender; }
	 */

	@Autowired
	private JavaMailSender		mailSender;

	@Autowired
	protected SessionFactory	sessionFactory;

	@Override
	public void sendMail(String to, String token, String user)
	{
		/*
		 * SimpleMailMessage msg = new SimpleMailMessage();
		 * msg.setFrom("zertones@gmail.com"); msg.setTo(to);
		 * msg.setSubject("New Password Request Link"); msg.setText("Dear " + user +
		 * ",\n We have received a request to reset your Student Connect account password. "
		 * + "Clink Hear to reset your Password." +
		 * "\n If you did not request a new password. Click here" +
		 * "\n \n Zerton Care Team");
		 */

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper msghelper = new MimeMessageHelper(message);
		try
		{
			msghelper.setTo(to);
			msghelper.setSubject("New Password Request Link");
			msghelper.setText("Dear " + user

					+ "<html><body ><br> <font style='color:#000000'>We have received a request to reset your Student Connect account password.</font> <br><form action='http://172.104.47.51/Zerton/identify/user/clg/"

					+ token + "' method='GET'> <input type='hidden' name='authToken' value='" + token
					+ "'> <br> To Reset Your Password : <button type='submit' style='background:none;border:none;padding:0!important; color:gren'>Click Here</button> <br></form> </body></html> "

					+ "<html><body><p>   <form id='notMe' action='http://172.104.47.51/Zerton/identify/u/clg/auth/0' method='POST'> <input type='hidden' name='authToken' value='"

					+ token + "'>If you did not request a new password :  Plase Contact to system admin.</form> </p>"
					+ "<p>Thanks</p> <p>Zerton Care Team</p></body></html> ", true);
			// <button type='submit' style='background:none;border:none;padding:0!important;
			// color:#FF0000'>Click Here</button>
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		mailSender.send(message);
	}

	@Override
	public void sendnewuser(String to, String token, String user)
	{

		System.out.println("comclientservicessssssss" + to + "token" + token + "user" + user);
		String password = "admin";
		MimeMessage message1 = mailSender.createMimeMessage();
		MimeMessageHelper msghelper1 = new MimeMessageHelper(message1);
		StringBuffer emailMessage1 = new StringBuffer("<br/>");

		emailMessage1.append("Dear " + user + ",");
		emailMessage1.append("<br/>");
		emailMessage1.append("<br/>");
		emailMessage1.append("UserName:-" + to);
		emailMessage1.append("<br/>");
		emailMessage1.append("Password:-" + password);
		emailMessage1.append("<br/>");
		emailMessage1.append("<br/>");
		emailMessage1.append("Thanks and Regards ");
		emailMessage1.append("<br/>");
		emailMessage1.append("Zerton Engg Services Pvt. Ltd.");
		try
		{
			msghelper1.setSubject("Successfully Created Account");
			msghelper1.setTo(to);
			msghelper1.setText("" + emailMessage1, true);

		} catch (Exception e)
		{
			System.out.println("messagessssssss" + e.getMessage());
		}
		System.out.println("maildenser" + mailSender);
		mailSender.send(message1);
	}

	@Override
	public void contactUs(ContactUsModel contactUsModel, int instCode)
	{

		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(InstituteInfoMaster.class);
		criteria.add(Restrictions.eq("instCode", instCode));
		InstituteInfoMaster infoMaster = (InstituteInfoMaster) criteria.uniqueResult();
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper msghelper = new MimeMessageHelper(message);
		StringBuffer emailMessage = new StringBuffer("<br/>");
		emailMessage.append("Contact person:: " + contactUsModel.getName());
		emailMessage.append("<br/>");
		emailMessage.append("Message::" + contactUsModel.getMessage());
		emailMessage.append("<br/>");
		emailMessage.append("<br/>");
		emailMessage.append("Thanks and Regards ");
		emailMessage.append("<br/>");
		emailMessage.append(contactUsModel.getName());
		emailMessage.append("<br/>");
		emailMessage.append(contactUsModel.getContactno());
		try
		{

			msghelper.setTo(infoMaster.getEmail());

			msghelper.setSubject("Enquiry From Mobile App.");
			msghelper.setText("" + emailMessage, true);

		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		mailSender.send(message);
	}

	@Override
	public void sendVerifiedStudentPasswordToMail(String to, String token, String user, String Ccode)
	{
		System.out.println("/./././././." + to + "...." + token + "..." + user);
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper msghelper = new MimeMessageHelper(message);
		try
		{
			msghelper.setTo(to);
			msghelper.setSubject("Verified Your Account");

			String myvar = "<!DOCTYPE html>" + "<html>" + "<head>"
					+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" + "<style>" + "* {"
					+ "    box-sizing: border-box;" + "}" + "" + "/* Add a gray background color with some padding */"
					+ "body {" + "    font-family: Arial;" + "    padding: 20px;" + "    background: #f1f1f1;" + "}"
					+ "" + "/* Header/Blog Title */" + ".header {" + "    padding: 2px;" + "    font-size: 15px;"
					+ "    text-align: center;" + "    color:white;" + "    background: #80E2A7;" + "}" + ""
					+ "/* Create two unequal columns that floats next to each other */" + "/* Left column */"
					+ ".leftcolumn {   " + "    float: left;" + "    width: 75%;" + "}" + "" + "/* Right column */"
					+ ".rightcolumn {" + "    float: left;" + "    width: 25%;" + "    padding-left: 20px;" + "}" + ""
					+ "/* Fake image */" + ".fakeimg {" + "    background-color: #aaa;" + "    width: 100%;"
					+ "    padding: 20px;" + "}" + "" + "/* Add a card effect for articles */" + ".card {"
					+ "     background-color: white;" + "     padding: 20px;" + "     margin-top: 20px;" + "}" + ""
					+ "/* Clear floats after the columns */" + ".row:after {" + "    content: \"\";"
					+ "    display: table;" + "    clear: both;" + "}" + "" + "/* Footer */" + ".footer {"
					+ "    padding: 12px;" + "    text-align: center;" + "    font-size: 10px;"
					+ "    background: #80E2A7;" + "   " + "}" + ""
					+ "/* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other */"
					+ "@media screen and (max-width: 800px) {" + "    .leftcolumn, .rightcolumn {   "
					+ "        width: 100%;" + "        padding: 0;" + "    }" + "}" + "</style>" + "</head>" + "<body>"
					+ "" + "<div class=\"header\">" + ""
					+ "  <h2><img src=\"https://res.cloudinary.com/dcptr5ivh/image/upload/v1540539878/KJcoemr/zerton_logo.png\"alt=\"loading...\" width=\"50\" height=\"30\">         Zerton Engineering Services Pvt Ltd</h2>"
					+ "</div>" + "" + "" + "    <div class=\"card\">"
					+ "      <h2 style=\"text-align: center;\">Welcome to ZertonES</h2>" + "      " + "      <p>Hi "
					+ token + ",</p>" + "<p>Congratulations,Your Account Has Been Activated.</p>"
					+ "      <p>Thanks for trying out Zerton Engineering Service."
					+ "         Your Student account verification done successfully from your college.Your Login credentials are below.<br>"
					+ "         <br>" + "         <b>Username : " + to + "</b><br>" + "         <b>Password : "
					+ "admin" + "</b>" + "         <br>" + "         <br>"
					+ "         <h5>If you have any questions, feel free to Email our customer success team.</h5>"
					+ "       " + "      </p>" + "    </div>" + "    " + "<div class=\"footer\">"
					+ " @ 2015 by Zerton Engineering Services.<br>"
					+ " Call T: +91 9860830863 / Email:zertones@gmail.com<br>"
					+ "Address : Jai Ganesh Vishwa, Visharant Wadi, Pune" + Ccode + ", Maharashtra 411015" + "</div>"
					+ "" + "</body>" + "</html>";

			message.setContent(message, "html/text");
			msghelper.setText(myvar, true);
			// <button type='submit' style='background:none;border:none;padding:0!important;
			// color:#FF0000'>Click Here</button>
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		mailSender.send(message);
	}

	@Override
	public void sendMailToDeclineStudent(String to, String user)
	{
		// TODO Auto-generated method stub
		// System.out.println("/./././././." + to + "...." + user);
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper msghelper = new MimeMessageHelper(message);
		try
		{
			msghelper.setTo(to);
			msghelper.setSubject(" Failed To Verified Your Account");

			String myvar = "<!DOCTYPE html>" + "<html>" + "<head>"
					+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" + "<style>" + "* {"
					+ "    box-sizing: border-box;" + "}" + "" + "/* Add a gray background color with some padding */"
					+ "body {" + "    font-family: Arial;" + "    padding: 20px;" + "    background: #f1f1f1;" + "}"
					+ "" + "/* Header/Blog Title */" + ".header {" + "    padding: 2px;" + "    font-size: 15px;"
					+ "    text-align: center;" + "    color:white;" + "    background: #80E2A7;" + "}" + ""
					+ "/* Create two unequal columns that floats next to each other */" + "/* Left column */"
					+ ".leftcolumn {   " + "    float: left;" + "    width: 75%;" + "}" + "" + "/* Right column */"
					+ ".rightcolumn {" + "    float: left;" + "    width: 25%;" + "    padding-left: 20px;" + "}" + ""
					+ "/* Fake image */" + ".fakeimg {" + "    background-color: #aaa;" + "    width: 100%;"
					+ "    padding: 20px;" + "}" + "" + "/* Add a card effect for articles */" + ".card {"
					+ "     background-color: white;" + "     padding: 20px;" + "     margin-top: 20px;" + "}" + ""
					+ "/* Clear floats after the columns */" + ".row:after {" + "    content: \"\";"
					+ "    display: table;" + "    clear: both;" + "}" + "" + "/* Footer */" + ".footer {"
					+ "    padding: 12px;" + "    text-align: center;" + "    font-size: 10px;"
					+ "    background: #80E2A7;" + "   " + "}" + ""
					+ "/* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other */"
					+ "@media screen and (max-width: 800px) {" + "    .leftcolumn, .rightcolumn {   "
					+ "        width: 100%;" + "        padding: 0;" + "    }" + "}" + "</style>" + "</head>" + "<body>"
					+ "" + "<div class=\"header\">" + ""
					+ "  <h2><img src=\"https://res.cloudinary.com/dcptr5ivh/image/upload/v1540539878/KJcoemr/zerton_logo.png\"alt=\"loading...\" width=\"50\" height=\"30\">         Zerton Engineering Services Pvt Ltd</h2>"
					+ "</div>" + "" + "" + "    <div class=\"card\">"
					+ "      <h2 style=\"text-align: center;\">Welcome to ZertonES</h2>" + "      " + "      <p>Hi "
					+ user + ",</p>" + "      <p>Thanks for trying out Zerton Engineering Service.<br>"
					+ "        Sorry to say you ,Your Student account verification can NOT done successfully from Your college ,because college has denied your request.college believes you are not student of this institute.So your Login credentials are reserved. if you want to activate your account,contact to your college admin and register again.<br>"
					+ "         " + "	" + "         <br>" + "         <br>"
					+ "         <h5>If you have any questions, feel free to Email our customer success team.</h5>"
					+ "       " + "      </p>" + "    </div>" + "    " + "<div class=\"footer\">"
					+ " @ 2015 by Zerton Engineering Services.<br>"
					+ " Call T: +91 9860830863 / Email:zertones@gmail.com<br>"
					+ "Address : Jai Ganesh Vishwa, Visharant Wadi, Pune, Maharashtra 411015" + "</div>" + ""
					+ "</body>" + "</html>";

			message.setContent(message, "html/text");
			msghelper.setText(myvar, true);
			// <button type='submit' style='background:none;border:none;padding:0!important;
			// color:#FF0000'>Click Here</button>
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		mailSender.send(message);
	}
}
