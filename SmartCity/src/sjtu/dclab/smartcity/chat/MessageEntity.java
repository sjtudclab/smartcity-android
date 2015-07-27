package sjtu.dclab.smartcity.chat;
/**
 *2015年5月29日 下午7:20:22
 *@author changyi yuan
 */
public class MessageEntity {
	private String name;
	private String content;
	
	public MessageEntity(){
		
	}
	
	
	public MessageEntity(String name, String content) {
		super();
		this.name = name;
		this.content = content;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
