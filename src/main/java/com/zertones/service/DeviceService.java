package com.zertones.service;

import java.util.List;

import com.zertones.model.DeviceDetails;
import com.zertones.model.common.Group_Poll;
import com.zertones.model.common.Notification;
import com.zertones.model.common.Polls;

public interface DeviceService
{
	public void addDevice(DeviceDetails n) throws Exception;

	// public void updateNotification(Notification n);
	public List<DeviceDetails> listDevices();

	public List<DeviceDetails> listDevices(Notification notification);

	public List<DeviceDetails> listDevices(Integer userId);

	public List<DeviceDetails> listOfDevices(Notification notification);

	public DeviceDetails getDeviceById(Integer id);

	public int removeDevice(Integer id);

	public List<DeviceDetails> getDevicesId(Integer clientID);

	public List<DeviceDetails> listDevicesComman(Polls poll);

	public List<DeviceDetails> getDiviceforTPO(List<Integer> idList);

	public List<DeviceDetails> listDevicesforGPoll(Group_Poll gpolls1);

}
