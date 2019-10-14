package com.zertones.model.master;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zertones.model.ComAttendance;
import com.zertones.model.ComClientName;

@Entity
@Table(name = "TEACHER_SUBJECT")
public class ComStaffSubject
{
	@Id
	@Column(name = "STAFF_SUB_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer				staff_sub_id;

	@Column(name = "EMPLOYEE_NO")
	private Integer				employee_no;

	@ManyToOne
	@JoinColumn(name = "CLIENT_ID", nullable = true)
	private ComClientName		clientName;

	@ManyToOne
	@JoinColumn(name = "SUB_ID", nullable = false)
	private AcademicSubject		academicSubject;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "comStaffSubject", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<ComAttendance>	comAttendance	= new HashSet<>();

	public Integer getEmployee_no()
	{
		return employee_no;
	}

	public void setEmployee_no(Integer employee_no)
	{
		this.employee_no = employee_no;
	}

	public Integer getStaff_sub_id()
	{
		return staff_sub_id;
	}

	public void setStaff_sub_id(Integer staff_sub_id)
	{
		this.staff_sub_id = staff_sub_id;
	}

	public AcademicSubject getAcademicSubject()
	{
		return academicSubject;
	}

	public void setAcademicSubject(AcademicSubject academicSubject)
	{
		this.academicSubject = academicSubject;
	}

	public Set<ComAttendance> getComAttendance()
	{
		return comAttendance;
	}

	public void setComAttendance(Set<ComAttendance> comAttendance)
	{
		this.comAttendance = comAttendance;
	}

	public ComClientName getClientName()
	{
		return clientName;
	}

	public void setClientName(ComClientName clientName)
	{
		this.clientName = clientName;
	}

	@Override
	public String toString()
	{
		return "ComStaffSubject [staff_sub_id=" + staff_sub_id + ", employee_no=" + employee_no + ", clientName="
				+ clientName + ", academicSubject=" + academicSubject + "]";
	}

}
