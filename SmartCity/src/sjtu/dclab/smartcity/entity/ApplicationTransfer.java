package sjtu.dclab.smartcity.entity;

/**
 *2015年4月2日 上午10:35:48
 *@author changyi yuan
 */
public class ApplicationTransfer {
    private long applicationId;
    private long userId;
    private String name;
    private String image;
    private String message;

    public ApplicationTransfer(){

    }

    public ApplicationTransfer(long applicatinoId,long userId, String name, String image,
                               String message) {
        super();
        this.applicationId = applicatinoId;
        this.userId = userId;
        this.name = name;
        this.image = image;
        this.message = message;
    }
    public long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }

    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
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