package sjtu.dclab.smartcity.transfer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import sjtu.dclab.smartcity.entity.Role;
import sjtu.dclab.smartcity.entity.UserRole;

import java.util.Collection;

/**
 *2015年3月30日 下午3:23:38
 *@author changyi yuan
 */
public class ManagementCitizenTransfer {
    private long id;
    private String name;
    private long userId;
    private String image;

    @JsonInclude(Include.NON_NULL)
    private Collection<Role> roles;

    @JsonInclude(Include.NON_NULL)
    private Collection<UserRole> userRoles;

    public ManagementCitizenTransfer(){

    }

    public ManagementCitizenTransfer(long id, String name, long userId,
                                     String image, Collection<Role> roles) {
        super();
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.image = image;
        this.roles = roles;
    }

    public ManagementCitizenTransfer(long id, String name, long userId,
                                     String image, Collection<Role> roles, Collection<UserRole> userRoles) {
        super();
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.image = image;
        this.roles = roles;
        this.userRoles = userRoles;
    }

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
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Collection<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Collection<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

}