package com.zertones.model.common;

import java.io.Serializable;
import java.util.List;

public class BatchList implements Serializable
{

	List<BatchDetails> batchDetails;

	public List<BatchDetails> getBatchDetails()
	{
		return batchDetails;
	}

	public void setBatchDetails(List<BatchDetails> batchDetails)
	{
		this.batchDetails = batchDetails;
	}

	@Override
	public String toString()
	{
		return "BatchList [batchDetails=" + batchDetails + "]";
	}

}
