package com.zertones.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "EDUCATIONAL_DTL")
@JsonInclude(Include.NON_NULL)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class EducationDetails extends BaseModel
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EDU_ID")
	private Integer					edu_id;

	@Column(name = "STANDARED_CLASS")
	private String					standard;

	@Column(name = "UNIVERSITY_NAME")
	private String					universityName;

	@Column(name = "OBTAIN_MARKS")
	private double					marksObtain;

	@Column(name = "PERSENTAGE_MARKS")
	private double					persentage;

	@Column(name = "NUMBER_OF_BACKLOG")
	private Integer					noOfBacklog;

	@Column(name = "IS_YEAR_DOWN")
	private Boolean					isYearDown;

	@ManyToOne
	@JoinColumn(name = "CLIENT_ID", nullable = true)
	private ComClientName			clientName;

	@ManyToOne
	@JoinColumn(name = "SEM_ID", nullable = true)
	private Semester				semester;

	@Column(name = "IS_BACKLOG")
	private Boolean					isBacklog;

	@ManyToOne
	@JoinColumn(name = "RES_ID", nullable = true)
	private ResultModelForPlacement	resultModel;

	public Boolean getIsYearDown()
	{
		return isYearDown;
	}

	public void setIsYearDown(Boolean isYearDown)
	{
		this.isYearDown = isYearDown;
	}

	public Integer getNoOfBacklog()
	{
		return noOfBacklog;
	}

	public void setNoOfBacklog(Integer noOfBacklog)
	{
		this.noOfBacklog = noOfBacklog;
	}

	public ComClientName getClientName()
	{
		return clientName;
	}

	public void setClientName(ComClientName clientName)
	{
		this.clientName = clientName;
	}

	public Integer getEdu_id()
	{
		return edu_id;
	}

	public void setEdu_id(Integer edu_id)
	{
		this.edu_id = edu_id;
	}

	public String getStandard()
	{
		return standard;
	}

	public void setStandard(String standard)
	{
		this.standard = standard;
	}

	public String getUniversityName()
	{
		return universityName;
	}

	public void setUniversityName(String universityName)
	{
		this.universityName = universityName;
	}

	public double getMarksObtain()
	{
		return marksObtain;
	}

	public void setMarksObtain(double marksObtain)
	{
		this.marksObtain = marksObtain;
	}

	public double getPersentage()
	{
		return persentage;
	}

	public void setPersentage(double persentage)
	{
		this.persentage = persentage;
	}

	public ResultModelForPlacement getResultModel()
	{
		return resultModel;
	}

	public void setResultModel(ResultModelForPlacement resultModel)
	{
		this.resultModel = resultModel;
	}

	public Semester getSemester()
	{
		return semester;
	}

	public void setSemester(Semester semester)
	{
		this.semester = semester;
	}

	public Boolean getIsBacklog()
	{
		return isBacklog;
	}

	public void setIsBacklog(Boolean isBacklog)
	{
		this.isBacklog = isBacklog;
	}

	@Override
	public String toString()
	{
		return "EducationDetails [edu_id=" + edu_id + ", standard=" + standard + ", universityName=" + universityName
				+ ", marksObtain=" + marksObtain + ", persentage=" + persentage + ", clientName=" + clientName
				+ ", semester=" + semester + ", isBacklog=" + isBacklog + ", resultModel=" + resultModel + "]";
	}

}
