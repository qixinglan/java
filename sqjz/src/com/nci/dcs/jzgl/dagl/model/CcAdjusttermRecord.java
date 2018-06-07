package com.nci.dcs.jzgl.dagl.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;

@Entity
@Table(name = "CC_ADJUSTTERM_RECORD", schema = "SQJZ")
public class CcAdjusttermRecord implements java.io.Serializable {

	private String id;
	private String fxryId;
	private Date newStartDate;
	private Date newEndDate;
	private String recordOrgId;
	private Date recordTime;	
    private Date dateSAdjust;
    private Date dateEAdjust;
    
	// Constructors
	/** default constructor */
	public CcAdjusttermRecord() {
	}
	
	/** full constructor */
	public CcAdjusttermRecord(String id, String fxryId, Date dateSAdjust,
			Date dateEAdjust, Date newStartDate, Date newEndDate,
			String recordOrgId, Date recordTime) {
		this.id = id;
		this.fxryId = fxryId;
		this.dateSAdjust= dateSAdjust;
		this.dateEAdjust = dateEAdjust;
		this.newStartDate = newStartDate;
		this.newEndDate = newEndDate;
		this.recordOrgId = recordOrgId;
		this.recordTime = recordTime;
	}
	

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "FXRY_ID", length = 32)
	public String getFxryId() {
		return this.fxryId;
	}

	public void setFxryId(String fxryId) {
		this.fxryId = fxryId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "NEW_START_DATE", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getNewStartDate() {
		return this.newStartDate;
	}

	public void setNewStartDate(Date oldStartDate) {
		this.newStartDate = oldStartDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "NEW_END_DATE", length = 7)
	 @JSON(format=DateTimeFmtSpec.DATE)
	public Date getNewEndDate() {
		return this.newEndDate;
	}

	public void setNewEndDate(Date oldEndDate) {
		this.newEndDate = oldEndDate;
	}

	@Column(name = "RECORD_ORG_ID", length = 32)
	public String getRecordOrgId() {
		return this.recordOrgId;
	}

	public void setRecordOrgId(String recordOrgId) {
		this.recordOrgId = recordOrgId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RECORD_TIME", length = 7)
    @JSON(format=DateTimeFmtSpec.DATETIME)
	public Date getRecordTime() {
		return this.recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	
	
    @Temporal(TemporalType.DATE)
    @Column(name="START_DATE", length=7)
    @JSON(format=DateTimeFmtSpec.DATE)
    public Date getDateSAdjust() {
        return this.dateSAdjust;
    }
    
    public void setDateSAdjust(Date dateSAdjust) {
        this.dateSAdjust = dateSAdjust;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="END_DATE", length=7)
    @JSON(format=DateTimeFmtSpec.DATE)
    public Date getDateEAdjust() {
        return this.dateEAdjust;
    }
    
    public void setDateEAdjust(Date dateEAdjust) {
        this.dateEAdjust = dateEAdjust;
    }
}