package com.zertones.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zertones.model.master.AcademicSubject;

@Entity
@Table(name = "COM_RESULT_MARKSHEET")
public class ResultMarksheet extends BaseModel
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SEQ_NO")
	private int				seq_no;

	@Column(name = "SEAT_NO")
	private String			seat_no;

	@Column(name = "IN_MARKS_OBTAINES")
	private String			in_obtained;

	@Column(name = "TH_MARKS_OBTAINED")
	private String			th_obtained;

	@Column(name = "TOTALMARKS_OBTD")
	private String			to_obtained;

	@Column(name = "TWMARKS_OBTD")
	private String			tw_obtained;

	@Column(name = "PRMARKS_OBTD")
	private String			pr_obtained;

	@Column(name = "OLMARKS_OBTD")
	private String			ol_obtained;

	@Column(name = "TOTAL_TH_MARKS_PERCENTAGE")
	private String			total_percenatge;

	@Column(name = "CRD")
	private String			crd;

	@Column(name = "GP")
	private String			gp;

	@Column(name = "CP")
	private String			cp;

	@Column(name = "PATTEN_ID")
	private Integer			pattern;

	@Column(name = "SGPA")
	private String			sgpa;

	@Column(name = "BRANCH")
	private String			branch;

	@Column(name = "Exam_Year")
	private Integer			year;

	@Column(name = "GRADE")
	private String			grade;

	@Column(name = "SEMISTER")
	private Integer			semister;

	@Column(name = "Aca_Year")
	private String			academiyr;

	@Column(name = "GRAND_TOTAL_MARKS_OBTAINED")
	private String			grand_tota;

	@Column(name = "EARN_CRIDET")
	private String			earn_credit;

	@Column(name = "COLLEGE_NAME")
	private String			collegename;

	@ManyToOne
	@JoinColumn(name = "STUD_ID")
	private ResultStudent	studresult;

	@ManyToOne
	@JoinColumn(name = "SUB_ID")

	private AcademicSubject	subresult;

	private Boolean			updateStatus;

	public Boolean getUpdateStatus()
	{
		return updateStatus;
	}

	public void setUpdateStatus(Boolean updateStatus)
	{
		this.updateStatus = updateStatus;
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

	public String getIn_obtained()
	{
		return in_obtained;
	}

	public void setIn_obtained(String in_obtained)
	{
		this.in_obtained = in_obtained;
	}

	public String getTh_obtained()
	{
		return th_obtained;
	}

	public void setTh_obtained(String th_obtained)
	{
		this.th_obtained = th_obtained;
	}

	public String getTo_obtained()
	{
		return to_obtained;
	}

	public void setTo_obtained(String to_obtained)
	{
		this.to_obtained = to_obtained;
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

	public String getTotal_percenatge()
	{
		return total_percenatge;
	}

	public void setTotal_percenatge(String total_percenatge)
	{
		this.total_percenatge = total_percenatge;
	}

	public String getCrd()
	{
		return crd;
	}

	public void setCrd(String crd)
	{
		this.crd = crd;
	}

	public String getGp()
	{
		return gp;
	}

	public void setGp(String gp)
	{
		this.gp = gp;
	}

	public String getCp()
	{
		return cp;
	}

	public void setCp(String cp)
	{
		this.cp = cp;
	}

	public Integer getPattern()
	{
		return pattern;
	}

	public void setPattern(Integer pattern)
	{
		this.pattern = pattern;
	}

	public String getSgpa()
	{
		return sgpa;
	}

	public void setSgpa(String sgpa)
	{
		this.sgpa = sgpa;
	}

	public String getBranch()
	{
		return branch;
	}

	public void setBranch(String branch)
	{
		this.branch = branch;
	}

	public Integer getYear()
	{
		return year;
	}

	public void setYear(Integer year)
	{
		this.year = year;
	}

	public String getGrade()
	{
		return grade;
	}

	public void setGrade(String grade)
	{
		this.grade = grade;
	}

	public Integer getSemister()
	{
		return semister;
	}

	public void setSemister(Integer semister)
	{
		this.semister = semister;
	}

	public String getAcademiyr()
	{
		return academiyr;
	}

	public void setAcademiyr(String academiyr)
	{
		this.academiyr = academiyr;
	}

	public String getGrand_tota()
	{
		return grand_tota;
	}

	public void setGrand_tota(String grand_tota)
	{
		this.grand_tota = grand_tota;
	}

	public String getEarn_credit()
	{
		return earn_credit;
	}

	public void setEarn_credit(String earn_credit)
	{
		this.earn_credit = earn_credit;
	}

	public ResultStudent getStudresult()
	{
		return studresult;
	}

	public void setStudresult(ResultStudent studresult)
	{
		this.studresult = studresult;
	}

	public AcademicSubject getSubresult()
	{
		return subresult;
	}

	public void setSubresult(AcademicSubject subresult)
	{
		this.subresult = subresult;
	}

	public String getCollegename()
	{
		return collegename;
	}

	public void setCollegename(String collegename)
	{
		this.collegename = collegename;
	}

	@Override
	public String toString()
	{
		return "ResultMarksheet [seq_no=" + seq_no + ", seat_no=" + seat_no + ", in_obtained=" + in_obtained
				+ ", th_obtained=" + th_obtained + ", to_obtained=" + to_obtained + ", tw_obtained=" + tw_obtained
				+ ", pr_obtained=" + pr_obtained + ", ol_obtained=" + ol_obtained + ", total_percenatge="
				+ total_percenatge + ", crd=" + crd + ", gp=" + gp + ", cp=" + cp + ", pattern=" + pattern + ", sgpa="
				+ sgpa + ", branch=" + branch + ", year=" + year + ", grade=" + grade + ", semister=" + semister
				+ ", academiyr=" + academiyr + ", grand_tota=" + grand_tota + ", earn_credit=" + earn_credit
				+ ", collegename=" + collegename + ", studresult=" + studresult + ", subresult=" + subresult
				+ ", updateStatus=" + updateStatus + "]";
	}

}
