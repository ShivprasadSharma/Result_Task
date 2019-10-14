package com.zertones.model.DataTransferObjectModel;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class TpoDrive implements Serializable
{

	private int		reInfoId;

	private boolean	applayStatus;
	private boolean	markInsert;
	private boolean	checkAllreadyApplay;

	public boolean isCheckAllreadyApplay()
	{
		return checkAllreadyApplay;
	}

	public void setCheckAllreadyApplay(boolean checkAllreadyApplay)
	{
		this.checkAllreadyApplay = checkAllreadyApplay;
	}

	private List<TpoApplayCriteria> list = new LinkedList<>();

	public int getReInfoId()
	{
		return reInfoId;
	}

	public boolean isMarkInsert()
	{
		return markInsert;
	}

	public void setMarkInsert(boolean markInsert)
	{
		this.markInsert = markInsert;
	}

	public void setReInfoId(int reInfoId)
	{
		this.reInfoId = reInfoId;
	}

	public boolean isApplayStatus()
	{
		return applayStatus;
	}

	public void setApplayStatus(boolean applayStatus)
	{
		this.applayStatus = applayStatus;
	}

	public List<TpoApplayCriteria> getList()
	{
		return list;
	}

	public void setList(List<TpoApplayCriteria> list)
	{
		this.list = list;
	}

	@Override
	public String toString()
	{
		return "TpoDrive [reInfoId=" + reInfoId + ", applayStatus=" + applayStatus + ", list=" + list + "]";
	}

}
