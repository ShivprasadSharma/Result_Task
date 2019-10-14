package com.zertones.model.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.zertones.model.BaseModel;

@Entity
@Table(name = "COM_LIST_DETAILS")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "listDtlId")
public class ComListDetails extends BaseModel implements java.io.Serializable, Comparable<ComListDetails>
{
	private static final long	serialVersionUID	= 4206965755706395257L;

	@Id
	@Column(name = "LIST_DTL_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer				listDtlId;

	@Column(name = "LIST_DTL_VALUE", columnDefinition = "TEXT")
	private String				value;

	@Column(name = "LIST_DTL_DESC", columnDefinition = "TEXT")
	private String				dtlDescription;

	@ManyToOne
	@JoinColumn(name = "LIST_ID", nullable = false)
	private ComListMaster		comListMaster;

	/*
	 * @Column(name = "CREATED_DT")
	 *
	 * @Temporal(TemporalType.TIMESTAMP) private Date createdDate = new Date();
	 *
	 * @Column(name = "CREATED_BY") private String createdBy;
	 *
	 * @Column(name = "UPDATED_DT")
	 *
	 * @Temporal(TemporalType.TIMESTAMP) private Date updatedDate;
	 *
	 * @Column(name = "UPDATED_BY") private String updatedBy;
	 *
	 * @Column(name = "RECORD_STATUS") private String recordStatus = "A";
	 */

	public Integer getListDtlId()
	{
		return listDtlId;
	}

	public void setListDtlId(Integer listDtlId)
	{
		this.listDtlId = listDtlId;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public ComListMaster getComListMaster()
	{
		return comListMaster;
	}

	public String getDtlDescription()
	{
		return dtlDescription;
	}

	public void setDtlDescription(String dtlDescription)
	{
		this.dtlDescription = dtlDescription;
	}

	public void setComListMaster(ComListMaster comListMaster)
	{
		this.comListMaster = comListMaster;
	}

	@Override
	public String toString()
	{
		return "ComListDetails [listDtlId=" + listDtlId + ", value=" + value + ", dtlDescription=" + dtlDescription
				+ "]";
	}

	@Override
	public int compareTo(ComListDetails o)
	{
		return this.listDtlId - o.getListDtlId();
	}

	/*
	 * @Override public Date getCreatedDate() { return createdDate; }
	 *
	 * @Override public void setCreatedDate(Date createdDate) { this.createdDate =
	 * createdDate; }
	 *
	 * @Override public String getCreatedBy() { return createdBy; }
	 *
	 * @Override public void setCreatedBy(String createdBy) { this.createdBy =
	 * createdBy; }
	 *
	 * @Override public Date getUpdatedDate() { return updatedDate; }
	 *
	 * @Override public void setUpdatedDate(Date updatedDate) { this.updatedDate =
	 * updatedDate; }
	 *
	 * @Override public String getUpdatedBy() { return updatedBy; }
	 *
	 * @Override public void setUpdatedBy(String updatedBy) { this.updatedBy =
	 * updatedBy; }
	 *
	 * @Override public String getRecordStatus() { return recordStatus; }
	 *
	 * @Override public void setRecordStatus(String recordStatus) {
	 * this.recordStatus = recordStatus; }
	 */

}
