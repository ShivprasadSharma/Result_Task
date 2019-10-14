package com.zertones.model;

import java.util.function.Predicate;

public class SimplePredicate<T> implements Predicate<T>
{

	public String varibl1;

	@Override
	public boolean test(T varibl)
	{
		if (varibl1.equals(varibl))
		{
			return true;
		}
		return false;
	}
}
