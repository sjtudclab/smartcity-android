package sjtu.dclab.smartcity.model;

/**
 * Friend
 *
 * @author Jian Yang
 * @date 2015/7/30
 */
public class Friend {
    private int friendId;
    private String name, image;

    @Override
    public String toString() {
        return "{" + "friendId=" + friendId + ", name=" + name + ", image=" + image + "}";
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

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }
}
