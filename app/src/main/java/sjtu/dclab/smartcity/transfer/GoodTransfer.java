package sjtu.dclab.smartcity.transfer;

/**
 * Created by DCLab on 10/8/2015.
 */
public class GoodTransfer {
    private String imagePath, id, category, type, title, desc;

    public GoodTransfer() {
    }

    public GoodTransfer(String id, String category, String type, String title, String desc, String imagePath) {
        this.imagePath = imagePath;

        this.id = id;
        this.category = category;
        this.type = type;
        this.title = title;
        this.desc = desc;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
