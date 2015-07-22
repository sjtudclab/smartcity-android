package sjtu.dclab.smartcity.entity;

/**
 * Created by Yang on 2015/7/22.
 */
public class Contact {
    private int contactId;
    private String name;
    private String image;

    public long getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
