package com.zertones.model.common;

import java.io.Serializable;

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
@Table(name = "COM_FEE_DETAILS")
public class FeeDetails extends BaseModel implements Serializable
{

	@Id
	@Column(name = "FEE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	f_id;

	@Column(name = "TOTAL_FEE")
	private Integer	total_fee;

	@Column(name = "CATEGORY")
	private String	category;

	@Column(name = "OUTSTANDING")
	private Integer	outstanding;

	@Column(name = "PAID")
	private Integer	paid;

	@Column(name = "REMAINING")
	private Integer	remaining;

	@Column(name = "INSTALLMENT1")
	private Integer	installment1;

	@Column(name = "INSTALLMENT2")
	private Integer	installment2;

	@Column(name = "INSTALLMENT3")
	private Integer	installment3;

	@Column(name = "INSTALLMENT4")
	private Integer	installment4;

	@ManyToOne
	@JoinColumn(name = "STUDENT_ID", nullable = false)
	private Student	student;

	public Integer getF_id()
	{
		return f_id;
	}

	public void setF_id(Integer f_id)
	{
		this.f_id = f_id;
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public Student getStudent()
	{
		return student;
	}

	public void setStudent(Student student)
	{
		this.student = student;
	}

	public Integer getTotal_fee()
	{
		return total_fee;
	}

	public void setTotal_fee(Integer total_fee)
	{
		this.total_fee = total_fee;
	}

	public Integer getOutstanding()
	{
		return outstanding;
	}

	public void setOutstanding(Integer outstanding)
	{
		this.outstanding = outstanding;
	}

	public Integer getPaid()
	{
		return paid;
	}

	public void setPaid(Integer paid)
	{
		this.paid = paid;
	}

	public Integer getRemaining()
	{
		return remaining;
	}

	public void setRemaining(Integer remaining)
	{
		this.remaining = remaining;
	}

	public Integer getInstallment1()
	{
		return installment1;
	}

	public void setInstallment1(Integer installment1)
	{
		this.installment1 = installment1;
	}

	public Integer getInstallment2()
	{
		return installment2;
	}

	public void setInstallment2(Integer installment2)
	{
		this.installment2 = installment2;
	}

	public Integer getInstallment3()
	{
		return installment3;
	}

	public void setInstallment3(Integer installment3)
	{
		this.installment3 = installment3;
	}

	public Integer getInstallment4()
	{
		return installment4;
	}

	public void setInstallment4(Integer installment4)
	{
		this.installment4 = installment4;
	}

	@Override
	public String toString()
	{
		return "FeeDetails [f_id=" + f_id + ", total_fee=" + total_fee + ", category=" + category + ", outstanding="
				+ outstanding + ", paid=" + paid + ", remaining=" + remaining + ", installment1=" + installment1
				+ ", installment2=" + installment2 + ", installment3=" + installment3 + ", installment4=" + installment4
				+ ", student=" + student + "]";
	}

}
