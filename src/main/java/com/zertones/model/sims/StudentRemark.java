package com.zertones.model.sims;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.zertones.model.BaseModel;

@Entity
@Table(name = "STUDENT_REMARK")
public class StudentRemark extends BaseModel implements Serializable
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "remarkid")
	private Integer						studremarkid;

	@Column(name = "STUDENTID")
	private Integer						studentid;

	@Column(name = "MENTORID")
	private String						mid;

	private String[]					remarkname;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "remarks", cascade = CascadeType.ALL)
	private Set<StudentRemarkOption>	remarkOptions	= new HashSet<>();

	public Integer getStudremarkid()
	{
		return studremarkid;
	}

	public void setStudremarkid(Integer studremarkid)
	{
		this.studremarkid = studremarkid;
	}

	public Integer getStudentid()
	{
		return studentid;
	}

	public void setStudentid(Integer studentid)
	{
		this.studentid = studentid;
	}

	public String getMid()
	{
		return mid;
	}

	public void setMid(String mid)
	{
		this.mid = mid;
	}

	public String[] getRemarkname()
	{
		return remarkname;
	}

	public void setRemarkname(String[] remarkname)
	{
		this.remarkname = remarkname;
	}

	public Set<StudentRemarkOption> getRemarkOptions()
	{
		return remarkOptions;
	}

	public void setRemarkOptions(Set<StudentRemarkOption> remarkOptions)
	{
		this.remarkOptions = remarkOptions;
	}

	@Override
	public String toString()
	{
		return "StudentRemark [studremarkid=" + studremarkid + ", studentid=" + studentid + ", mid=" + mid
				+ ", remarkname=" + Arrays.toString(remarkname) + ", remarkOptions=" + remarkOptions + ", Status="
				+ "]";
	}

}
