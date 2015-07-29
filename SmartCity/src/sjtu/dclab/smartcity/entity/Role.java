package sjtu.dclab.smartcity.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Created by Huiyi on 2015/3/11.
 */
public class Role {
    private long id;
    private String name;
    private String description;

    @JsonInclude(Include.NON_NULL)
    private String userRoleDescription;

    @JsonInclude(Include.NON_NULL)
    private String userRoleDescriptionDetail;

    private RoleType roleType;

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserRoleDescription() {
        return userRoleDescription;
    }

    public void setUserRoleDescription(String userRoleDescription) {
        this.userRoleDescription = userRoleDescription;
    }

    public String getUserRoleDescriptionDetail() {
        return userRoleDescriptionDetail;
    }

    public void setUserRoleDescriptionDetail(String userRoleDescriptionDetail) {
        this.userRoleDescriptionDetail = userRoleDescriptionDetail;
    }
}