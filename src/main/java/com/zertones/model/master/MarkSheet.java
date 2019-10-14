package com.zertones.model.master;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.zertones.model.BaseModel;
import com.zertones.model.sims.Student;

@Entity
@Table(name = "COM_MARK_SHEET")
public class MarkSheet extends BaseModel implements Serializable
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SEQ_NO")
	private int				seq_no;

	@Column(name = "SEAT_NO")
	private String			seat_no;

	@Column(name = "TW_MARKS_OBTAINED")
	private String			tw_obtained;

	@Column(name = "PR_MARKS_OBTAINED")
	private String			pr_obtained;

	@Column(name = "OL_MARKS_OBTAINED")
	private String			ol_obtained;

	@Column(name = "TH_MARKS_OBTAINED")
	private String			th_obtained;

	@Column(name = "TOTAL_TH_MARKS_OBTAINED")
	private String			total_obtained;

	@Column(name = "GRAND_TOTAL_MARKS_OBTAINED")
	private String			grand_tota;

	@Column(name = "GRADE")
	private String			grade;

	@Column(name = "EARN_CRIDET")
	private String			earn_credit;

	@Column(name = "GP")
	private String			gp;

	@Transient
	private String			subCode;

	@Transient
	private String			prn_no;

	@Column(name = "Exam_Year")
	private Integer			year;

	@Column(name = "SEMISTER")
	private Integer			semister;

	@Column(name = "Aca_Year")
	private String			academiyr;

	@ManyToOne
	@JoinColumn(name = "STUDENT_ID")
	private Student			student;

	@ManyToOne
	@JoinColumn(name = "SUB_ID")
	private AcademicSubject	subject;

	@Column(name = "PATTEN_ID")
	private Integer			pattern;

	public Integer getPattern()
	{
		return pattern;
	}

	public void setPattern(Integer pattern)
	{
		this.pattern = pattern;
	}

	public String getAcademiyr()
	{
		return academiyr;
	}

	public void setAcademiyr(String academiyr)
	{
		this.academiyr = academiyr;
	}

	public AcademicSubject getSubject()
	{
		return subject;
	}

	public Integer getYear()
	{
		return year;
	}

	public void setYear(Integer year)
	{
		this.year = year;
	}

	public void setSubject(AcademicSubject subject)
	{
		this.subject = subject;
	}

	public Student getStudent()
	{
		return student;
	}

	public void setStudent(Student student)
	{
		this.student = student;
	}

	public String getPrn_no()
	{
		return prn_no;
	}

	public void setPrn_no(String prn_no)
	{
		this.prn_no = prn_no;
	}

	public String getSubCode()
	{
		return subCode;
	}

	public void setSubCode(String subCode)
	{
		this.subCode = subCode;
	}

	public int getSeq_no()
	{
		return seq_no;
	}

	public void setSeq_no(int seq_no)
	{
		this.seq_no = seq_no;
	}

	public String getSeat_no()
	{
		return seat_no;
	}

	public void setSeat_no(String seat_no)
	{
		this.seat_no = seat_no;
	}

	public String getTw_obtained()
	{
		return tw_obtained;
	}

	public void setTw_obtained(String tw_obtained)
	{
		this.tw_obtained = tw_obtained;
	}

	public String getPr_obtained()
	{
		return pr_obtained;
	}

	public void setPr_obtained(String pr_obtained)
	{
		this.pr_obtained = pr_obtained;
	}

	public String getOl_obtained()
	{
		return ol_obtained;
	}

	public void setOl_obtained(String ol_obtained)
	{
		this.ol_obtained = ol_obtained;
	}

	public String getTh_obtained()
	{
		return th_obtained;
	}

	public void setTh_obtained(String th_obtained)
	{
		this.th_obtained = th_obtained;
	}

	public String getTotal_obtained()
	{
		return total_obtained;
	}

	public void setTotal_obtained(String total_obtained)
	{
		this.total_obtained = total_obtained;
	}

	public String getGrand_tota()
	{
		return grand_tota;
	}

	public void setGrand_tota(String grand_tota)
	{
		this.grand_tota = grand_tota;
	}

	public String getGrade()
	{
		return grade;
	}

	public void setGrade(String grade)
	{
		this.grade = grade;
	}

	public String getEarn_credit()
	{
		return earn_credit;
	}

	public void setEarn_credit(String earn_credit)
	{
		this.earn_credit = earn_credit;
	}

	public String getGp()
	{
		return gp;
	}

	public void setGp(String gp)
	{
		this.gp = gp;
	}

	public Integer getSemister()
	{
		return semister;
	}

	public void setSemister(Integer semister)
	{
		this.semister = semister;
	}

}
