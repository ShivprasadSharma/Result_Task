package com.zertones.model.common;

import java.io.Serializable;

public class EducationFields implements Serializable
{
	private boolean	ssc				= false;
	private boolean	hscAnddiploma	= false;
	private double	fe;
	private double	se;
	private double	te;
	private double	be;
	private boolean	digree			= false;
	private boolean	backLog			= false;

	public boolean isBackLog()
	{
		return backLog;
	}

	public void setBackLog(boolean backLog)
	{
		this.backLog = backLog;
	}

	public boolean isSsc()
	{
		return ssc;
	}

	public void setSsc(boolean ssc)
	{
		this.ssc = ssc;
	}

	public double getFe()
	{
		return fe;
	}

	public void setFe(double fe)
	{
		this.fe = fe;
	}

	public double getSe()
	{
		return se;
	}

	public void setSe(double se)
	{
		this.se = se;
	}

	public double getTe()
	{
		return te;
	}

	public void setTe(double te)
	{
		this.te = te;
	}

	public double getBe()
	{
		return be;
	}

	public void setBe(double be)
	{
		this.be = be;
	}

	public boolean isDigree()
	{
		return digree;
	}

	public void setDigree(boolean digree)
	{
		this.digree = digree;
	}

	public boolean isHscAnddiploma()
	{
		return hscAnddiploma;
	}

	public void setHscAnddiploma(boolean hscAnddiploma)
	{
		this.hscAnddiploma = hscAnddiploma;
	}

	@Override
	public String toString()
	{
		return "EducationFields [ssc=" + ssc + ", hscAnddiploma=" + hscAnddiploma + ", fe=" + fe + ", se=" + se
				+ ", te=" + te + ", be=" + be + ", digree=" + digree + ", backLog=" + backLog + "]";
	}

}
