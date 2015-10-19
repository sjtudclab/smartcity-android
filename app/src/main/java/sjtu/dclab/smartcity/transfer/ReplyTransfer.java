package sjtu.dclab.smartcity.transfer;

/**
 * Created by Yang on 2015/10/19.
 */
public class ReplyTransfer {
    private int bbs_post_id;
    private int bbs_reply_id;
    private int bbs_replier_id;
    private long bbs_reply_time;
    private String bbs_reply_content;
    private int bbs_another_reply_id;

    public int getBbs_another_reply_id() {
        return bbs_another_reply_id;
    }

    public void setBbs_another_reply_id(int bbs_another_reply_id) {
        this.bbs_another_reply_id = bbs_another_reply_id;
    }

    public int getBbs_post_id() {
        return bbs_post_id;
    }

    public void setBbs_post_id(int bbs_post_id) {
        this.bbs_post_id = bbs_post_id;
    }

    public int getBbs_replier_id() {
        return bbs_replier_id;
    }

    public void setBbs_replier_id(int bbs_replier_id) {
        this.bbs_replier_id = bbs_replier_id;
    }

    public String getBbs_reply_content() {
        return bbs_reply_content;
    }

    public void setBbs_reply_content(String bbs_reply_content) {
        this.bbs_reply_content = bbs_reply_content;
    }

    public int getBbs_reply_id() {
        return bbs_reply_id;
    }

    public void setBbs_reply_id(int bbs_reply_id) {
        this.bbs_reply_id = bbs_reply_id;
    }

    public long getBbs_reply_time() {
        return bbs_reply_time;
    }

    public void setBbs_reply_time(long bbs_reply_time) {
        this.bbs_reply_time = bbs_reply_time;
    }
}
