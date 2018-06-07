package com.nci.dcs.monitor.server.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;

/**
 * CcServiceMonitor entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_MONITOR_SERVER", schema = "SQJZ")
public class ServiceMonitor implements java.io.Serializable {

	/**
	 * @name 
	 * @author caolj
	 * @date 2016年6月17日 下午3:20:17
	 * @message:
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private Long totalMemory;//可用内存
	private Long freeMemory;//剩余内存
	private Long maxMemory;//最大可使用内存
	private String osName;//操作系统
	private Long totalMemorySize;//总物理内存
	private Long freePhysicalMemorySize;//剩余物理内存
	private Long usedMemory;//已使用物理内存
	private Long totalThread;//线程总数
	private String cpuRatio;//cpu使用率
	private String ip;//服务器地址
	private Date createTime;//录入日期
	private String wlncRatio;//物理内存使用率

	// Constructors

	/** default constructor */
	public ServiceMonitor() {
	}

	/** minimal constructor */
	public ServiceMonitor(String id) {
		this.id = id;
	}

	/** full constructor */
	public ServiceMonitor(String id, Long totalMemory,
			Long freeMemory, Long maxMemory, String osName,
			Long totalMemorySize, Long freePhysicalMemorySize,
			Long usedMemory, Long totalThread, String cpuRatio,
			String ip, Date createTime, String wlncRatio) {
		this.id = id;
		this.totalMemory = totalMemory;
		this.freeMemory = freeMemory;
		this.maxMemory = maxMemory;
		this.osName = osName;
		this.totalMemorySize = totalMemorySize;
		this.freePhysicalMemorySize = freePhysicalMemorySize;
		this.usedMemory = usedMemory;
		this.totalThread = totalThread;
		this.cpuRatio = cpuRatio;
		this.ip = ip;
		this.createTime = createTime;
		this.wlncRatio = wlncRatio;
	}

	// Property accessors
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

	@Column(name = "TOTAL_MEMORY", precision = 22, scale = 0)
	public Long getTotalMemory() {
		return this.totalMemory;
	}

	public void setTotalMemory(Long totalMemory) {
		this.totalMemory = totalMemory;
	}

	@Column(name = "FREE_MEMORY", precision = 22, scale = 0)
	public Long getFreeMemory() {
		return this.freeMemory;
	}

	public void setFreeMemory(Long freeMemory) {
		this.freeMemory = freeMemory;
	}

	@Column(name = "MAX_MEMORY", precision = 22, scale = 0)
	public Long getMaxMemory() {
		return this.maxMemory;
	}

	public void setMaxMemory(Long maxMemory) {
		this.maxMemory = maxMemory;
	}

	@Column(name = "OS_NAME", length = 60)
	public String getOsName() {
		return this.osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	@Column(name = "TOTAL_MEMORY_SIZE", precision = 22, scale = 0)
	public Long getTotalMemorySize() {
		return this.totalMemorySize;
	}

	public void setTotalMemorySize(Long totalMemorySize) {
		this.totalMemorySize = totalMemorySize;
	}

	@Column(name = "FREE_PHYSICAL_MEMORY_SIZE", precision = 22, scale = 0)
	public Long getFreePhysicalMemorySize() {
		return this.freePhysicalMemorySize;
	}

	public void setFreePhysicalMemorySize(Long freePhysicalMemorySize) {
		this.freePhysicalMemorySize = freePhysicalMemorySize;
	}

	@Column(name = "USED_MEMORY", precision = 22, scale = 0)
	public Long getUsedMemory() {
		return this.usedMemory;
	}

	public void setUsedMemory(Long usedMemory) {
		this.usedMemory = usedMemory;
	}

	@Column(name = "TOTAL_THREAD", precision = 22, scale = 0)
	public Long getTotalThread() {
		return this.totalThread;
	}

	public void setTotalThread(Long totalThread) {
		this.totalThread = totalThread;
	}

	@Column(name = "CPU_RATIO", length = 60)
	public String getCpuRatio() {
		return this.cpuRatio;
	}

	public void setCpuRatio(String cpuRatio) {
		this.cpuRatio = cpuRatio;
	}

	@Column(name = "IP", length = 60)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", length = 7)
	@JSON(format = DateTimeFmtSpec.DATETIME)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "WLNC_RATIO", length = 60)
	public String getWlncRatio() {
		return this.wlncRatio;
	}

	public void setWlncRatio(String wlncRatio) {
		this.wlncRatio = wlncRatio;
	}

}