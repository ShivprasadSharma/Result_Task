package com.zertones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zertones.dao.DeviceDAO;
import com.zertones.model.DeviceDetails;
import com.zertones.model.common.Group_Poll;
import com.zertones.model.common.Notification;
import com.zertones.model.common.Polls;

@Service("deviceService")
public class DeviceServiceImpl implements DeviceService
{
	@Autowired
	private DeviceDAO deviceDAO;

	public void setDeviceDAO(DeviceDAO deviceDAO)
	{
		this.deviceDAO = deviceDAO;
	}

	@Override
	@Transactional
	public void addDevice(DeviceDetails deviceDetails) throws Exception
	{
		deviceDAO.addDevice(deviceDetails);
	}

	@Override
	@Transactional
	public List<DeviceDetails> listDevices()
	{
		return deviceDAO.listDevices();
	}

	@Override
	@Transactional
	public List<DeviceDetails> listDevices(Integer userId)
	{
		return deviceDAO.listDevices(userId);
	}

	@Override
	@Transactional
	public DeviceDetails getDeviceById(Integer id)
	{
		return deviceDAO.getDeviceById(id);
	}

	@Override
	@Transactional
	public int removeDevice(Integer id)
	{
		return deviceDAO.removeDevice(id);
	}

	@Override
	@Transactional
	public List<DeviceDetails> listDevices(Notification notification)
	{
		return deviceDAO.listDevices(notification);

	}

	@Override
	@Transactional
	public List<DeviceDetails> listOfDevices(Notification notification)
	{
		return deviceDAO.listOfDevices(notification);

	}

	@Override
	public List<DeviceDetails> getDevicesId(Integer clientID)
	{
		// TODO Auto-generated method stub
		return deviceDAO.getDevicesId(clientID);
	}

	@Override
	public List<DeviceDetails> listDevicesComman(Polls poll)
	{
		// TODO Auto-generated method stub
		return deviceDAO.listDevicesComman(poll);
	}

	@Override
	public List<DeviceDetails> getDiviceforTPO(List<Integer> idList)
	{
		return deviceDAO.getDiviceforTPO(idList);
	}

	@Override
	public List<DeviceDetails> listDevicesforGPoll(Group_Poll gpolls1)
	{
		// TODO Auto-generated method stub
		return deviceDAO.listDevicesforGPoll(gpolls1);
	}

}
