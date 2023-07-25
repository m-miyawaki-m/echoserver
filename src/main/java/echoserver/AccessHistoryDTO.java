package echoserver;

import java.sql.Timestamp;

public class AccessHistoryDTO {
	private int connectionId;
	private int userId;
	private Timestamp loginTime;
	private Timestamp logoutTime;
	private String sourceIp;
	private String status;

	// コンストラクタ
	public AccessHistoryDTO(int connectionId, int userId, Timestamp loginTime, Timestamp logoutTime, String sourceIp,
			String status) {
		this.connectionId = connectionId;
		this.userId = userId;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		this.sourceIp = sourceIp;
		this.status = status;
	}

	public int getConnectionId() {
		return connectionId;
	}

	public void setConnectionId(int connectionId) {
		this.connectionId = connectionId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Timestamp getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	public Timestamp getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Timestamp logoutTime) {
		this.logoutTime = logoutTime;
	}

	public String getSourceIp() {
		return sourceIp;
	}

	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
