package com.zertones.model.master;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.crypto.codec.Base64;

import com.zertones.model.BaseModel;

@Entity
@Table(name = "MARKLIST_FILE")
public class ResultFile extends BaseModel implements Serializable
{

	@Id
	@Column(name = "RESULT_FILE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int		resultid;

	@Column(name = "COUESR_NAME")
	private String	CourseName;

	@Column(name = "URL")
	private String	url;

	@Column(name = "FILE")
	private byte[]	Resultfile;

	public int getResultid()
	{
		return resultid;
	}

	public void setResultid(int resultid)
	{
		this.resultid = resultid;
	}

	public String getCourseName()
	{
		return CourseName;
	}

	public void setCourseName(String courseName)
	{
		CourseName = courseName;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public byte[] getResultfile()
	{
		return Resultfile;
	}

	public void setResultfile(byte[] resultfile)
	{
		Resultfile = resultfile;
	}

	@Override
	public String toString()
	{
		return "ResultFile [resultid=" + resultid + ", CourseName=" + CourseName + ", url=" + url + ", Resultfile="
				+ Arrays.toString(Resultfile) + "]";
	}

	public String getByteArrayString()
	{
		if (this.Resultfile != null)
		{
			return new String(Base64.encode(this.Resultfile));
		} else
		{
			return "";
		}
	}

}
