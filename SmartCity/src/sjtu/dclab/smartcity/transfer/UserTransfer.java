package sjtu.dclab.smartcity.transfer;

import sjtu.dclab.smartcity.entity.Role;

import java.util.Collection;

/**
 *2015年3月11日 下午6:36:57
 *@author changyi yuan
 */
public class UserTransfer {
    private long id;
    private String username;
    private String image;
    private Collection<Role> roles;
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
    public Collection<Role> getRoles() {
        return roles;
    }
    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}