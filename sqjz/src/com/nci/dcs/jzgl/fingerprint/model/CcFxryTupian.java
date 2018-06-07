package com.nci.dcs.jzgl.fingerprint.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * CcFxryTupian entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_FXRY_TUPIAN", schema = "SQJZ")
public class CcFxryTupian implements java.io.Serializable {

	// Fields

	private String id;
	private byte[] facePic;
	private String picStatus;
	private byte[] leftF1;
	private byte[] leftF2;
	private byte[] leftF3;
	private byte[] leftF4;
	private byte[] leftF5;
	private byte[] rightF1;
	private byte[] rightF2;
	private byte[] rightF3;
	private byte[] rightF4;
	private byte[] rightF5;
	private byte[] MLeft1;
	private byte[] MLeft2;
	private byte[] MLeft3;
	private byte[] MLeft4;
	private byte[] MLeft5;
	private byte[] MRight1;
	private byte[] MRight2;
	private byte[] MRight3;
	private byte[] MRight4;
	private byte[] MRight5;

	// Constructors

	/** default constructor */
	public CcFxryTupian() {
	}

	/** minimal constructor */
	public CcFxryTupian(String id) {
		this.id = id;
	}

	/** full constructor */
	public CcFxryTupian(String id, byte[] facePic, String picStatus,
			byte[] leftF1, byte[] leftF2, byte[] leftF3, byte[] leftF4,
			byte[] leftF5, byte[] rightF1, byte[] rightF2, byte[] rightF3,
			byte[] rightF4, byte[] rightF5, byte[] MLeft1, byte[] MLeft2,
			byte[] MLeft3, byte[] MLeft4, byte[] MLeft5, byte[] MRight1,
			byte[] MRight2, byte[] MRight3, byte[] MRight4, byte[] MRight5) {
		this.id = id;
		this.facePic = facePic;
		this.picStatus = picStatus;
		this.leftF1 = leftF1;
		this.leftF2 = leftF2;
		this.leftF3 = leftF3;
		this.leftF4 = leftF4;
		this.leftF5 = leftF5;
		this.rightF1 = rightF1;
		this.rightF2 = rightF2;
		this.rightF3 = rightF3;
		this.rightF4 = rightF4;
		this.rightF5 = rightF5;
		this.MLeft1 = MLeft1;
		this.MLeft2 = MLeft2;
		this.MLeft3 = MLeft3;
		this.MLeft4 = MLeft4;
		this.MLeft5 = MLeft5;
		this.MRight1 = MRight1;
		this.MRight2 = MRight2;
		this.MRight3 = MRight3;
		this.MRight4 = MRight4;
		this.MRight5 = MRight5;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Lob
	@Column(name = "FACE_PIC")
	public byte[] getFacePic() {
		return this.facePic;
	}

	public void setFacePic(byte[] facePic) {
		this.facePic = facePic;
	}

	@Column(name = "PIC_STATUS", length = 2)
	public String getPicStatus() {
		return this.picStatus;
	}

	public void setPicStatus(String picStatus) {
		this.picStatus = picStatus;
	}

	@Lob
	@Column(name = "LEFT_F1")
	public byte[] getLeftF1() {
		return this.leftF1;
	}

	public void setLeftF1(byte[] leftF1) {
		this.leftF1 = leftF1;
	}

	@Lob
	@Column(name = "LEFT_F2")
	public byte[] getLeftF2() {
		return this.leftF2;
	}

	public void setLeftF2(byte[] leftF2) {
		this.leftF2 = leftF2;
	}

	@Lob
	@Column(name = "LEFT_F3")
	public byte[] getLeftF3() {
		return this.leftF3;
	}

	public void setLeftF3(byte[] leftF3) {
		this.leftF3 = leftF3;
	}

	@Lob
	@Column(name = "LEFT_F4")
	public byte[] getLeftF4() {
		return this.leftF4;
	}

	public void setLeftF4(byte[] leftF4) {
		this.leftF4 = leftF4;
	}

	@Lob
	@Column(name = "LEFT_F5")
	public byte[] getLeftF5() {
		return this.leftF5;
	}

	public void setLeftF5(byte[] leftF5) {
		this.leftF5 = leftF5;
	}

	@Lob
	@Column(name = "RIGHT_F1")
	public byte[] getRightF1() {
		return this.rightF1;
	}

	public void setRightF1(byte[] rightF1) {
		this.rightF1 = rightF1;
	}

	@Lob
	@Column(name = "RIGHT_F2")
	public byte[] getRightF2() {
		return this.rightF2;
	}

	public void setRightF2(byte[] rightF2) {
		this.rightF2 = rightF2;
	}

	@Lob
	@Column(name = "RIGHT_F3")
	public byte[] getRightF3() {
		return this.rightF3;
	}

	public void setRightF3(byte[] rightF3) {
		this.rightF3 = rightF3;
	}

	@Lob
	@Column(name = "RIGHT_F4")
	public byte[] getRightF4() {
		return this.rightF4;
	}

	public void setRightF4(byte[] rightF4) {
		this.rightF4 = rightF4;
	}

	@Lob
	@Column(name = "RIGHT_F5")
	public byte[] getRightF5() {
		return this.rightF5;
	}

	public void setRightF5(byte[] rightF5) {
		this.rightF5 = rightF5;
	}

	@Lob
	@Column(name = "M_LEFT_1")
	public byte[] getMLeft1() {
		return this.MLeft1;
	}

	public void setMLeft1(byte[] MLeft1) {
		this.MLeft1 = MLeft1;
	}

	@Lob
	@Column(name = "M_LEFT_2")
	public byte[] getMLeft2() {
		return this.MLeft2;
	}

	public void setMLeft2(byte[] MLeft2) {
		this.MLeft2 = MLeft2;
	}

	@Lob
	@Column(name = "M_LEFT_3")
	public byte[] getMLeft3() {
		return this.MLeft3;
	}

	public void setMLeft3(byte[] MLeft3) {
		this.MLeft3 = MLeft3;
	}

	@Lob
	@Column(name = "M_LEFT_4")
	public byte[] getMLeft4() {
		return this.MLeft4;
	}

	public void setMLeft4(byte[] MLeft4) {
		this.MLeft4 = MLeft4;
	}

	@Lob
	@Column(name = "M_LEFT_5")
	public byte[] getMLeft5() {
		return this.MLeft5;
	}

	public void setMLeft5(byte[] MLeft5) {
		this.MLeft5 = MLeft5;
	}

	@Lob
	@Column(name = "M_RIGHT_1")
	public byte[] getMRight1() {
		return this.MRight1;
	}

	public void setMRight1(byte[] MRight1) {
		this.MRight1 = MRight1;
	}

	@Lob
	@Column(name = "M_RIGHT_2")
	public byte[] getMRight2() {
		return this.MRight2;
	}

	public void setMRight2(byte[] MRight2) {
		this.MRight2 = MRight2;
	}

	@Lob
	@Column(name = "M_RIGHT_3")
	public byte[] getMRight3() {
		return this.MRight3;
	}

	public void setMRight3(byte[] MRight3) {
		this.MRight3 = MRight3;
	}

	@Lob
	@Column(name = "M_RIGHT_4")
	public byte[] getMRight4() {
		return this.MRight4;
	}

	public void setMRight4(byte[] MRight4) {
		this.MRight4 = MRight4;
	}

	@Lob
	@Column(name = "M_RIGHT_5")
	public byte[] getMRight5() {
		return this.MRight5;
	}

	public void setMRight5(byte[] MRight5) {
		this.MRight5 = MRight5;
	}

}