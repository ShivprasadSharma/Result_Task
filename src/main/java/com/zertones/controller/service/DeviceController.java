package com.zertones.controller.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zertones.controller.RestURIConstants;
import com.zertones.core.SampleClass;
import com.zertones.model.DeviceDetails;
import com.zertones.service.DeviceService;

@Controller
public class DeviceController
{
	private static final Logger	logger	= LoggerFactory.getLogger(DeviceController.class);
	private DeviceService		deviceService;

	@Autowired(required = true)
	@Qualifier(value = "deviceService")
	public void setDeviceService(DeviceService deviceService)
	{
		this.deviceService = deviceService;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = RestURIConstants.ADD_DEVICE, method = RequestMethod.POST)
	public @ResponseBody DeviceDetails AddUserDevice(@RequestBody DeviceDetails device)
	{
		logger.info("Adding Devices");
		try
		{
			deviceService.addDevice(device);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return device;
	}

	@RequestMapping(value = RestURIConstants.GET_DEVICE, method = RequestMethod.GET)
	public @ResponseBody DeviceDetails getUserDeviceDetails(@PathVariable("id") Integer id)
	{
		logger.info("Getting Devices");
		return deviceService.getDeviceById(id);
	}

	/*
	 * @RequestMapping(value = RestURIConstants.ADD_NOTIFICATION, method =
	 * RequestMethod.POST) public @ResponseBody Notification
	 * updateNotification(@RequestBody Notification notification) { logger.info(
	 * "Creating Notification...");
	 * notificationService.updateNotification(notification); return notification; }
	 */

	@RequestMapping(value = RestURIConstants.LIST_DEVICES, method = RequestMethod.GET)
	public @ResponseBody List<DeviceDetails> getAllNotification()
	{
		logger.info("getting All devices.");
		return deviceService.listDevices();
	}

	@RequestMapping(value = RestURIConstants.DELETE_DEVICE, method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<?> deleteEmployee(@PathVariable("id") Integer id)
	{
		logger.info("deleting device with id " + id);
		int result = deviceService.removeDevice(id);

		if (result != 1)
		{
			return new ResponseEntity<>(new SampleClass(HttpStatus.OK, "Device Remove successful", null),
					HttpStatus.ACCEPTED);

		} else
		{
			return new ResponseEntity<>(
					new SampleClass(HttpStatus.NOT_FOUND, "Error Occured While Removing Device..", null),
					HttpStatus.NOT_FOUND);
		}
	}

}
