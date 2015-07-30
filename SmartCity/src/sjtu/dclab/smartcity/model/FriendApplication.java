package sjtu.dclab.smartcity.model;

/**
 * FriendApplication
 *
 * @author Jian Yang
 * @date 2015/7/30
 */
public class FriendApplication {
    private int applicationId, userId;
    private String name, image, message;

    @Override
    public String toString() {
        return "{" + "applicationId=" + applicationId + ", userId=" + userId +
                ", name=" + name + ", image=" + image + ", message=" + message + "}";
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
