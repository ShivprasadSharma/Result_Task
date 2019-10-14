package com.zertones.service.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zertones.model.ComChatMessages;
import com.zertones.model.DeviceDetails;
import com.zertones.model.common.Grievance_List;
import com.zertones.model.common.Group_Poll;
import com.zertones.model.common.Groups;
import com.zertones.model.common.Notification;
import com.zertones.model.common.Polls;
import com.zertones.model.common.RecruitmentInfo;
import com.zertones.service.DeviceService;

@Service("pushNotificationService")
@Scope("prototype")
@PropertySource(value =
{ "classpath:application.properties" })
public class PushNotificationServiceImpl implements PushNotificationService
{
	private DeviceService		deviceService;
	String						deviceRegistrationId;
	int							responseCode	= -1;
	String						responseBody	= null;
	Integer						successmsg		= 0;

	@Value("${API_URL_FCM}")
	private String				API_URL_FCM;

	@Value("${AUTH_KEY_FCM}")
	private String				AUTH_KEY_FCM;

	@Autowired
	private NotificationService	notificationservice;

	Notification				n				= new Notification();

	@Autowired(required = true)
	@Qualifier(value = "deviceService")
	public void setDeviceService(DeviceService deviceService)
	{
		this.deviceService = deviceService;
	}

	@Override
	public void sendNotification(Notification notification)
	{
		n = notification;
		if (notification.getDepartment() != null)
		{
			if (notification.getDepartment() == 0)
			{
				List<DeviceDetails> devices = deviceService.listDevices();
				n.setTotalStudent(devices.size());
				try
				{
					byte[] postData = getPostData(devices, notification);

					URL url = new URL(API_URL_FCM);
					HttpsURLConnection httpURLConnection = (HttpsURLConnection) url.openConnection();
					// set timeputs to 10 seconds
					httpURLConnection.setConnectTimeout(10000);
					httpURLConnection.setReadTimeout(10000);
					httpURLConnection.setUseCaches(false);
					httpURLConnection.setDoInput(true);
					httpURLConnection.setDoOutput(true);
					httpURLConnection.setRequestMethod("POST");
					httpURLConnection.setRequestProperty("Content-Type", "application/json");
					httpURLConnection.setRequestProperty("Content-Length", Integer.toString(postData.length));
					httpURLConnection.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);

					OutputStream out = httpURLConnection.getOutputStream();
					out.write(postData);
					out.close();
					responseCode = httpURLConnection.getResponseCode();
					// success
					if (responseCode == 200)
					{
						responseBody = convertStreamToString(httpURLConnection.getInputStream());
						successmsg++;
					}
					// failure
					else
					{
						responseBody = convertStreamToString(httpURLConnection.getErrorStream());
					}
				} catch (IOException ioe)
				{
					System.out.println("IO Exception in sending FCM request. regId: " + deviceRegistrationId);
					ioe.printStackTrace();
				} catch (Exception e)
				{
					System.out.println("Unknown exception in sending FCM request. regId: " + deviceRegistrationId);
					e.printStackTrace();
				}

				n.setTotalSendNotices(successmsg);
				notificationservice.updateNotification(n, null);
			} else
			{
				List<DeviceDetails> devices = deviceService.listDevices(notification);
				n.setTotalStudent(devices.size());
				try
				{
					byte[] postData = getPostData(devices, notification);

					URL url = new URL(API_URL_FCM);
					HttpsURLConnection httpURLConnection = (HttpsURLConnection) url.openConnection();
					// set timeputs to 10 seconds
					httpURLConnection.setConnectTimeout(10000);
					httpURLConnection.setReadTimeout(10000);
					httpURLConnection.setUseCaches(false);
					httpURLConnection.setDoInput(true);
					httpURLConnection.setDoOutput(true);
					httpURLConnection.setRequestMethod("POST");
					httpURLConnection.setRequestProperty("Content-Type", "application/json");
					httpURLConnection.setRequestProperty("Content-Length", Integer.toString(postData.length));
					httpURLConnection.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);

					OutputStream out = httpURLConnection.getOutputStream();
					out.write(postData);
					out.close();
					responseCode = httpURLConnection.getResponseCode();
					// success
					if (responseCode == 200)
					{
						responseBody = convertStreamToString(httpURLConnection.getInputStream());
						successmsg++;
					}
					// failure
					else
					{
						responseBody = convertStreamToString(httpURLConnection.getErrorStream());
					}
				} catch (IOException ioe)
				{
					System.out.println("IO Exception in sending FCM request. regId: " + deviceRegistrationId);
					ioe.printStackTrace();
				} catch (Exception e)
				{
					System.out.println("Unknown exception in sending FCM request. regId: " + deviceRegistrationId);
					e.printStackTrace();
				}

				n.setTotalSendNotices(successmsg);
				notificationservice.updateNotification(n, null);
			}
		} else
		{
			List<DeviceDetails> devices = deviceService.listDevices();
			n.setTotalStudent(devices.size());
			try
			{
				byte[] postData = getPostData(devices, notification);

				URL url = new URL(API_URL_FCM);
				HttpsURLConnection httpURLConnection = (HttpsURLConnection) url.openConnection();
				// set timeputs to 10 seconds
				httpURLConnection.setConnectTimeout(10000);
				httpURLConnection.setReadTimeout(10000);
				httpURLConnection.setUseCaches(false);
				httpURLConnection.setDoInput(true);
				httpURLConnection.setDoOutput(true);
				httpURLConnection.setRequestMethod("POST");
				httpURLConnection.setRequestProperty("Content-Type", "application/json");
				httpURLConnection.setRequestProperty("Content-Length", Integer.toString(postData.length));
				httpURLConnection.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);

				OutputStream out = httpURLConnection.getOutputStream();
				out.write(postData);
				out.close();
				responseCode = httpURLConnection.getResponseCode();
				// success
				if (responseCode == 200)
				{
					responseBody = convertStreamToString(httpURLConnection.getInputStream());
					successmsg++;
				}
				// failure
				else
				{
					responseBody = convertStreamToString(httpURLConnection.getErrorStream());
				}
			} catch (IOException ioe)
			{
				System.out.println("IO Exception in sending FCM request. regId: " + deviceRegistrationId);
				ioe.printStackTrace();
			} catch (Exception e)
			{
				System.out.println("Unknown exception in sending FCM request. regId: " + deviceRegistrationId);
				e.printStackTrace();
			}

			n.setTotalSendNotices(successmsg);
			notificationservice.updateNotification(n, null);
		}
		// notificationservice.updateNotification(n, image);
	}

	public static byte[] getPostData(List<DeviceDetails> devices, Notification notification)
	{
		JSONObject payloadObject = new JSONObject();
		HashMap<String, String> dataMap = new HashMap<>();
		dataMap.put("niticeDescription", notification.getNotificationDetails());

		if (notification.getNotificationToDate() != null)
		{
			dataMap.put("niticeToDate", notification.getNotificationToDate().toString());
		}
		if (notification.getNotificationFromDate() != null)
		{
			dataMap.put("niticeFromDate", notification.getNotificationFromDate().toString());
		}
		dataMap.put("niticeEventVenu", notification.getVenue());

		if (notification.getStudentIncharge() != null)
		{
			dataMap.put("niticeStudentIncharge", notification.getStudentIncharge().toString());
		}
		if (notification.getFacultyIncharge() != null)
		{
			dataMap.put("niticeFacultyincharge", notification.getFacultyIncharge().toString());
		}

		payloadObject.put("registration_ids", devices);

		JSONObject notice = new JSONObject();
		notice.put("title", notification.getNotificatiosHeadline());
		notice.put("data", dataMap);
		notice.put("body", notification.getNotificationDetails());
		payloadObject.put("notification", notice);

		return payloadObject.toString().getBytes();
	}

	public static String convertStreamToString(InputStream inStream) throws Exception
	{
		InputStreamReader inputStream = new InputStreamReader(inStream);
		BufferedReader bReader = new BufferedReader(inputStream);

		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = bReader.readLine()) != null)
		{
			sb.append(line);
		}

		return sb.toString();
	}

	@Override
	public void sendGroupNotification(Notification notification) throws IOException
	{
		n = notification;
		List<DeviceDetails> devices = deviceService.listOfDevices(notification);
		n.setTotalStudent(devices.size());
		try
		{

			byte[] postData = getPostData(devices, notification);

			URL url = new URL(API_URL_FCM);
			HttpsURLConnection httpURLConnection = (HttpsURLConnection) url.openConnection();
			// set timeputs to 10 seconds
			httpURLConnection.setConnectTimeout(10000);
			httpURLConnection.setReadTimeout(10000);
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("Content-Type", "application/json");
			httpURLConnection.setRequestProperty("Content-Length", Integer.toString(postData.length));
			httpURLConnection.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);

			OutputStream out = httpURLConnection.getOutputStream();
			out.write(postData);
			out.close();
			responseCode = httpURLConnection.getResponseCode();
			// success
			if (responseCode == 200)
			{
				responseBody = convertStreamToString(httpURLConnection.getInputStream());
				successmsg++;
			}
			// failure
			else
			{
				responseBody = convertStreamToString(httpURLConnection.getErrorStream());
			}
		} catch (IOException ioe)
		{
			System.out.println("IO Exception in sending FCM request. regId: " + deviceRegistrationId);
			ioe.printStackTrace();
		} catch (Exception e)
		{
			System.out.println("Unknown exception in sending FCM request. regId: " + deviceRegistrationId);
			e.printStackTrace();
		}

		// n.setTotalSendNotices(successmsg);
		// notificationservice.updateNotification(n);
	}

	@Override
	public void sendChatMsg(ComChatMessages comChatMessages)
	{
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public void sendGrievanceReplay(Grievance_List grievance)
	{
		List<DeviceDetails> devices = deviceService.getDevicesId(grievance.getClientID());
		try
		{
			byte[] postData = getGrievancePostData(devices, grievance);
			URL url = new URL(API_URL_FCM);
			HttpsURLConnection httpURLConnection = (HttpsURLConnection) url.openConnection();
			// set timeputs to 10 seconds
			httpURLConnection.setConnectTimeout(10000);
			httpURLConnection.setReadTimeout(10000);
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("Content-Type", "application/json");
			httpURLConnection.setRequestProperty("Content-Length", Integer.toString(postData.length));
			httpURLConnection.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);

			OutputStream out = httpURLConnection.getOutputStream();
			out.write(postData);
			out.close();
			responseCode = httpURLConnection.getResponseCode();
			// success
			if (responseCode == 200)
			{
				System.out.println("OK " + deviceRegistrationId);
				responseBody = convertStreamToString(httpURLConnection.getInputStream());
				successmsg++;
			}
			// failure
			else
			{
				responseBody = convertStreamToString(httpURLConnection.getErrorStream());
				System.out.println("Error: " + deviceRegistrationId);
			}
		} catch (IOException ioe)
		{
			System.out.println("IO Exception in sending FCM request. regId: " + deviceRegistrationId);
			ioe.printStackTrace();
		} catch (Exception e)
		{
			System.out.println("Unknown exception in sending FCM request. regId: " + deviceRegistrationId);
			e.printStackTrace();
		}

	}

	public static byte[] getGrievancePostData(List<DeviceDetails> devices, Grievance_List grievance_List)
	{
		System.out.println("In getGrievancePostData   " + devices);
		JSONObject payloadObject = new JSONObject();
		HashMap<String, String> dataMap = new HashMap<>();
		dataMap.put("desciption", grievance_List.getDescription());
		payloadObject.put("registration_ids", devices);

		JSONObject notice = new JSONObject();
		notice.put("title", "Grievance Repaly");
		notice.put("data", dataMap);
		notice.put("body", grievance_List.getReplay());
		payloadObject.put("notification", notice);

		return payloadObject.toString().getBytes();

	}

	@Override
	@Transactional
	public void sendPoll(Polls poll)
	{

		if (poll.getDepID() == 0 && poll.getYear() == 0)
		{
			List<DeviceDetails> devices = deviceService.listDevices();
			try
			{
				byte[] postData = getPoll_PostData(devices, poll);

				URL url = new URL(API_URL_FCM);
				HttpsURLConnection httpURLConnection = (HttpsURLConnection) url.openConnection();
				// set timeputs to 10 seconds
				httpURLConnection.setConnectTimeout(10000);
				httpURLConnection.setReadTimeout(10000);
				httpURLConnection.setUseCaches(false);
				httpURLConnection.setDoInput(true);
				httpURLConnection.setDoOutput(true);
				httpURLConnection.setRequestMethod("POST");
				httpURLConnection.setRequestProperty("Content-Type", "application/json");
				httpURLConnection.setRequestProperty("Content-Length", Integer.toString(postData.length));
				httpURLConnection.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);

				OutputStream out = httpURLConnection.getOutputStream();
				out.write(postData);
				out.close();
				responseCode = httpURLConnection.getResponseCode();
				// success
				if (responseCode == 200)
				{
					responseBody = convertStreamToString(httpURLConnection.getInputStream());
					successmsg++;
				}
				// failure
				else
				{
					responseBody = convertStreamToString(httpURLConnection.getErrorStream());
				}
			} catch (IOException ioe)
			{
				System.out.println("IO Exception in sending FCM request. regId: " + deviceRegistrationId);
				ioe.printStackTrace();
			} catch (Exception e)
			{
				System.out.println("Unknown exception in sending FCM request. regId: " + deviceRegistrationId);
				e.printStackTrace();
			}
		} else
		{
			List<DeviceDetails> devices = deviceService.listDevicesComman(poll);
			try
			{
				byte[] postData = getPoll_PostData(devices, poll);

				URL url = new URL(API_URL_FCM);
				HttpsURLConnection httpURLConnection = (HttpsURLConnection) url.openConnection();
				// set timeputs to 10 seconds
				httpURLConnection.setConnectTimeout(10000);
				httpURLConnection.setReadTimeout(10000);
				httpURLConnection.setUseCaches(false);
				httpURLConnection.setDoInput(true);
				httpURLConnection.setDoOutput(true);
				httpURLConnection.setRequestMethod("POST");
				httpURLConnection.setRequestProperty("Content-Type", "application/json");
				httpURLConnection.setRequestProperty("Content-Length", Integer.toString(postData.length));
				httpURLConnection.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);

				OutputStream out = httpURLConnection.getOutputStream();
				out.write(postData);
				out.close();
				responseCode = httpURLConnection.getResponseCode();
				// success
				if (responseCode == 200)
				{
					responseBody = convertStreamToString(httpURLConnection.getInputStream());
					successmsg++;
				}
				// failure
				else
				{
					responseBody = convertStreamToString(httpURLConnection.getErrorStream());
				}
			} catch (IOException ioe)
			{
				System.out.println("IO Exception in sending FCM request. regId: " + deviceRegistrationId);
				ioe.printStackTrace();
			} catch (Exception e)
			{
				System.out.println("Unknown exception in sending FCM request. regId: " + deviceRegistrationId);
				e.printStackTrace();
			}

		}

	}

	public static byte[] getPoll_PostData(List<DeviceDetails> devices, Polls polls)
	{
		JSONObject payloadObject = new JSONObject();
		HashMap<String, String> dataMap = new HashMap<>();
		dataMap.put("niticeDescription", "hsdbcjisc");

		payloadObject.put("registration_ids", devices);

		JSONObject notice = new JSONObject();
		notice.put("title", "Poll");
		notice.put("data", dataMap);
		notice.put("body", polls.getQuestion());
		notice.put("click_action", "PollActivity");

		payloadObject.put("notification", notice);

		return payloadObject.toString().getBytes();
	}

	@Override
	public void sendgrouprequestNotification(Groups group)
	{
		List<DeviceDetails> devices = deviceService.listDevices();

		try
		{
			byte[] postData = getgroupPostData(devices, group);

			URL url = new URL(API_URL_FCM);
			HttpsURLConnection httpURLConnection = (HttpsURLConnection) url.openConnection();
			// set timeputs to 10 seconds
			httpURLConnection.setConnectTimeout(10000);
			httpURLConnection.setReadTimeout(10000);
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("Content-Type", "application/json");
			httpURLConnection.setRequestProperty("Content-Length", Integer.toString(postData.length));
			httpURLConnection.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);

			OutputStream out = httpURLConnection.getOutputStream();
			out.write(postData);
			out.close();
			responseCode = httpURLConnection.getResponseCode();
			// success
			if (responseCode == 200)
			{
				responseBody = convertStreamToString(httpURLConnection.getInputStream());
				successmsg++;
			}
			// failure
			else
			{
				responseBody = convertStreamToString(httpURLConnection.getErrorStream());
			}
		} catch (IOException ioe)
		{
			System.out.println("IO Exception in sending FCM request. regId: " + deviceRegistrationId);
			ioe.printStackTrace();
		} catch (Exception e)
		{
			System.out.println("Unknown exception in sending FCM request. regId: " + deviceRegistrationId);
			e.printStackTrace();
		}

	}

	public static byte[] getgroupPostData(List<DeviceDetails> devices, Groups group)
	{

		JSONObject payloadObject = new JSONObject();
		HashMap<String, String> dataMap = new HashMap<>();
		dataMap.put("niticeDescription", "hsdbcjisc");

		payloadObject.put("registration_ids", devices);

		JSONObject notice = new JSONObject();
		notice.put("title", "New Group");
		notice.put("data", dataMap);
		notice.put("body", group.getGroupName());
		notice.put("click_action", "GroupRequestsActivity");

		payloadObject.put("notification", notice);

		return payloadObject.toString().getBytes();
	}

	@Override
	public void recruitmentInfoNotification(RecruitmentInfo recruitmentInfo)
	{

		List<DeviceDetails> devices = deviceService.listDevices();

		try
		{
			byte[] postData = getRecruitmentInfo(devices, recruitmentInfo);

			URL url = new URL(API_URL_FCM);
			HttpsURLConnection httpURLConnection = (HttpsURLConnection) url.openConnection();
			// set timeputs to 10 seconds
			httpURLConnection.setConnectTimeout(10000);
			httpURLConnection.setReadTimeout(10000);
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("Content-Type", "application/json");
			httpURLConnection.setRequestProperty("Content-Length", Integer.toString(postData.length));
			httpURLConnection.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);

			OutputStream out = httpURLConnection.getOutputStream();
			out.write(postData);
			out.close();
			responseCode = httpURLConnection.getResponseCode();
			// success
			if (responseCode == 200)
			{
				responseBody = convertStreamToString(httpURLConnection.getInputStream());
				successmsg++;
			}
			// failure
			else
			{
				responseBody = convertStreamToString(httpURLConnection.getErrorStream());
			}
		} catch (IOException ioe)
		{
			System.out.println("IO Exception in sending FCM request. regId: " + deviceRegistrationId);
			ioe.printStackTrace();
		} catch (Exception e)
		{
			System.out.println("Unknown exception in sending FCM request. regId: " + deviceRegistrationId);
			e.printStackTrace();
		}
	}

	public static byte[] getRecruitmentInfo(List<DeviceDetails> devices, RecruitmentInfo recruitmentInfo)
	{

		JSONObject payloadObject = new JSONObject();
		HashMap<String, String> dataMap = new HashMap<>();
		dataMap.put("niticeDescription", "hsdbcjisc");

		payloadObject.put("registration_ids", devices);

		JSONObject notice = new JSONObject();
		notice.put("title", "Recruitment Info");
		notice.put("data", dataMap);
		notice.put("body", recruitmentInfo.getCompanyName());
		notice.put("click_action", "UpComingDriveListActivity");
		payloadObject.put("notification", notice);

		return payloadObject.toString().getBytes();
	}

	@Override
	public void sendOfferLaterNotification(RecruitmentInfo recruitmentInfo, Integer[] studId)
	{
		// TODO Auto-generated method stub
		List<Integer> idList = new ArrayList<>();
		for (Integer integer : studId)
		{
			idList.add(integer);
		}
		List<DeviceDetails> devices = deviceService.getDiviceforTPO(idList);

		try
		{
			byte[] postData = getOfferLaterInfo(devices, recruitmentInfo);

			URL url = new URL(API_URL_FCM);
			HttpsURLConnection httpURLConnection = (HttpsURLConnection) url.openConnection();
			// set timeputs to 10 seconds
			httpURLConnection.setConnectTimeout(10000);
			httpURLConnection.setReadTimeout(10000);
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("Content-Type", "application/json");
			httpURLConnection.setRequestProperty("Content-Length", Integer.toString(postData.length));
			httpURLConnection.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);

			OutputStream out = httpURLConnection.getOutputStream();
			out.write(postData);
			out.close();
			responseCode = httpURLConnection.getResponseCode();
			// success
			if (responseCode == 200)
			{
				responseBody = convertStreamToString(httpURLConnection.getInputStream());
				successmsg++;
			}
			// failure
			else
			{
				responseBody = convertStreamToString(httpURLConnection.getErrorStream());
			}
		} catch (IOException ioe)
		{
			System.out.println("IO Exception in sending FCM request. regId: " + deviceRegistrationId);
			ioe.printStackTrace();
		} catch (Exception e)
		{
			System.out.println("Unknown exception in sending FCM request. regId: " + deviceRegistrationId);
			e.printStackTrace();
		}
	}

	public static byte[] getOfferLaterInfo(List<DeviceDetails> devices, RecruitmentInfo recruitmentInfo)
	{

		JSONObject payloadObject = new JSONObject();
		HashMap<String, String> dataMap = new HashMap<>();
		dataMap.put("niticeDescription", "hsdbcjisc");

		payloadObject.put("registration_ids", devices);

		JSONObject notice = new JSONObject();
		notice.put("title", "Offer Later");
		notice.put("data", dataMap);
		notice.put("body", recruitmentInfo.getCompanyName());
		notice.put("click_action", "DriveStatus");
		payloadObject.put("notification", notice);

		return payloadObject.toString().getBytes();
	}

	@Override
	public void sendGroupPoll(Group_Poll gpolls1)
	{
		// TODO Auto-generated method stub

		List<DeviceDetails> devices = deviceService.listDevicesforGPoll(gpolls1);
		try
		{
			byte[] postData = getgroupPoll_PostData(devices, gpolls1);

			URL url = new URL(API_URL_FCM);
			HttpsURLConnection httpURLConnection = (HttpsURLConnection) url.openConnection();
			// set timeputs to 10 seconds
			httpURLConnection.setConnectTimeout(10000);
			httpURLConnection.setReadTimeout(10000);
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("Content-Type", "application/json");
			httpURLConnection.setRequestProperty("Content-Length", Integer.toString(postData.length));
			httpURLConnection.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);

			OutputStream out = httpURLConnection.getOutputStream();
			out.write(postData);
			out.close();
			responseCode = httpURLConnection.getResponseCode();
			// success
			if (responseCode == 200)
			{
				responseBody = convertStreamToString(httpURLConnection.getInputStream());
				successmsg++;
			}
			// failure
			else
			{
				responseBody = convertStreamToString(httpURLConnection.getErrorStream());
			}
		} catch (IOException ioe)
		{
			System.out.println("IO Exception in sending FCM request. regId: " + deviceRegistrationId);
			ioe.printStackTrace();
		} catch (Exception e)
		{
			System.out.println("Unknown exception in sending FCM request. regId: " + deviceRegistrationId);
			e.printStackTrace();
		}

	}

	public static byte[] getgroupPoll_PostData(List<DeviceDetails> devices, Group_Poll gpolls1)
	{
		JSONObject payloadObject = new JSONObject();
		HashMap<String, String> dataMap = new HashMap<>();
		dataMap.put("niticeDescription", "hsdbcjisc");

		payloadObject.put("registration_ids", devices);

		JSONObject notice = new JSONObject();
		notice.put("title", "GroupPoll");
		notice.put("data", dataMap);
		notice.put("body", gpolls1.getQuestion());
		notice.put("click_action", "GroupPollActivity");

		payloadObject.put("notification", notice);

		return payloadObject.toString().getBytes();
	}

}
