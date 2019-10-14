package com.zertones.model.sims;

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

@Entity
@Table(name = "MENTOR_STUDENTS")
public class MentorStudent extends BaseModel implements Serializable
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MID")
	private Integer	mid;

	@ManyToOne
	@JoinColumn(name = "ID", nullable = false)
	private Mentor	mentor;

	@ManyToOne
	@JoinColumn(name = "STUDENT_ID", nullable = false)
	private Student	student;

	public Integer getMid()
	{
		return mid;
	}

	public void setMid(Integer mid)
	{
		this.mid = mid;
	}

	public Mentor getMentor()
	{
		return mentor;
	}

	public void setMentor(Mentor mentor)
	{
		this.mentor = mentor;
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
		return "MentorStudent [mid=" + mid + ", student=" + student + "]";
	}

}
