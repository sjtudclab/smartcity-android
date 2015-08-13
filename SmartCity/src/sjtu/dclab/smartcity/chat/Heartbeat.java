package sjtu.dclab.smartcity.chat;
/**
 *2015年6月14日 下午12:58:44
 *@author changyi yuan
 */
public class Heartbeat {
	private String clientId;
	private long userId;
	private String command;
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
}
