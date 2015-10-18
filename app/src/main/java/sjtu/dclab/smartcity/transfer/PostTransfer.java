package sjtu.dclab.smartcity.transfer;

/**
 * Created by DCLab on 10/13/2015.
 */
public class PostTransfer {
    private String title, type, content, posterName, userName;
    private int communityId, id, posterid, replyNums, goodNums;

    public PostTransfer() {
    }

    public PostTransfer(String title, String type, String content, String posterName, String userName, int communityId, int id, int posterid, int replyNums, int goodNums) {
        this.title = title;
        this.type = type;
        this.content = content;
        this.posterName = posterName;
        this.userName = userName;
        this.communityId = communityId;
        this.id = id;
        this.posterid = posterid;
        this.replyNums = replyNums;
        this.goodNums = goodNums;
    }

    public String getPosterName() {
        return posterName;
    }

    public void setPosterName(String posterName) {
        this.posterName = posterName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCommunityId() {
        return communityId;
    }

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosterid() {
        return posterid;
    }

    public void setPosterid(int posterid) {
        this.posterid = posterid;
    }

    public int getReplyNums() {
        return replyNums;
    }

    public void setReplyNums(int replyNums) {
        this.replyNums = replyNums;
    }

    public int getGoodNums() {
        return goodNums;
    }

    public void setGoodNums(int goodNums) {
        this.goodNums = goodNums;
    }
}
