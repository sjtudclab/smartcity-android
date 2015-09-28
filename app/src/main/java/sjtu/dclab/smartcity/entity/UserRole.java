package sjtu.dclab.smartcity.entity;

/**
 *2015年4月7日 下午12:27:20
 *@author changyi yuan
 */
public class UserRole {
    private int id;
    private Role role;
    private String userRoleDescription;
    private String userRoleDescriptionDetail;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
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