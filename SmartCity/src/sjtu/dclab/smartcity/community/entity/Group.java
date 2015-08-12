package sjtu.dclab.smartcity.community.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 2015年5月6日 下午3:08:41
 *
 * @author changyi yuan
 */
public class Group implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = -6413640677063576153L;

    @JsonProperty("groupId")
    private long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("image")
    private String image;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "id=" + id + ",name=" + name + ",image=" + image;
    }
}

