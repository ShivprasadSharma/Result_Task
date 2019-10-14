package com.zertones.system.utility;

import java.util.Comparator;

import com.zertones.model.DataTransferObjectModel.ComClientNameDTO;

public class ComClientNameDTORollNoComparator implements Comparator<ComClientNameDTO>
{

	@Override
	public int compare(ComClientNameDTO o1, ComClientNameDTO o2)
	{
		// TODO Auto-generated method stub
		return o1.getRollNoInt() - o2.getRollNoInt();
	}

}
