package com.zertones.model.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zertones.model.BaseModel;
import com.zertones.model.sims.Student;

@Entity
@Table(name = "PARENT_CALL_DTL")
public class Parentcallrecord extends BaseModel implements Serializable
{

	@Id
	@Column(name = "CAL_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	call_id;

	@Column(name = "CALL_DATE")
	private Date	cal_Date;

	@Column(name = "REMARK")
	private String	remark;

	@ManyToOne
	@JoinColumn(name = "STUDENT_ID", nullable = false)
	private Student	student;

	public Integer getCall_id()
	{
		return call_id;
	}

	public void setCall_id(Integer call_id)
	{
		this.call_id = call_id;
	}

	public Date getCal_Date()
	{
		return cal_Date;
	}

	public void setCal_Date(Date cal_Date)
	{
		this.cal_Date = cal_Date;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public Student getStudent()
	{
		return student;
	}

	public void setStudent(Student student)
	{
		this.student = student;
	}

	@Override
	public String toString()
	{
		return "Parentcallrecord [call_id=" + call_id + ", cal_Date=" + cal_Date + ", remark=" + remark + ", student="
				+ student + "]";
	}

}
