package NetworkRelated;

import java.io.Serializable;

public class NodeInfo implements Serializable{
	private String strNodeName;
	private Double cpuUsage;
	/**
	 * @return the strNodeName
	 */
	public String getStrNodeName() {
		return strNodeName;
	}
	/**
	 * @param strNodeName the strNodeName to set
	 */
	public void setStrNodeName(String strNodeName) {
		this.strNodeName = strNodeName;
	}
	/**
	 * @return the cpuUsage
	 */
	public Double getCpuUsage() {
		return cpuUsage;
	}
	/**
	 * @param cpuUsage the cpuUsage to set
	 */
	public void setCpuUsage(Double cpuUsage) {
		this.cpuUsage = cpuUsage;
	}
	
}
