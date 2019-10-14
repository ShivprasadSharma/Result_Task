package com.zertones.system.utility;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.zertones.model.DataTransferObjectModel.ComClientNameDTO;

public class ComClientNameDtoChainedCompareter implements Comparator<ComClientNameDTO>
{

	private List<Comparator<ComClientNameDTO>> listComparators;

	@SafeVarargs
	public ComClientNameDtoChainedCompareter(Comparator<ComClientNameDTO>... comparators)
	{
		this.listComparators = Arrays.asList(comparators);
	}

	@Override
	public int compare(ComClientNameDTO o1, ComClientNameDTO o2)
	{
		for (Comparator<ComClientNameDTO> comparator : listComparators)
		{
			int result = comparator.compare(o1, o2);
			if (result != 0)
			{
				return result;
			}
		}
		return 0;
	}

}
