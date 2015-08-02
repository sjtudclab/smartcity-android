package sjtu.dclab.smartcity.community.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 2015年3月11日 下午6:36:57
 *
 * @author changyi yuan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	@JsonProperty("id")
	private long id;

	@JsonProperty("username")
	private String username;

	@JsonProperty("image")
	private String image;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String imageUrl) {
		this.image = imageUrl;
	}

	@Override
	public String toString() {
		return "id=" + id + ",username=" + username + ",image=" + image;
	}
}
