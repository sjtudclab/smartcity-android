package sjtu.dclab.smartcity.model;

/**
 * Mail
 *
 * @author Jian Yang
 * @date 2015/7/9
 */
public class Mail {
    private int id;
    private String content;
    private String submitedDate;
    private String status;
    private IDAndName from;
    private IDAndName to;

    @Override
    public String toString() {

        return "{" + "id = " + id + ", content = " + content + ", submitedDate = " + submitedDate + ", status = " + status
                + ", from = " + from + ", to = " + to + "}";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubmitedDate() {
        return submitedDate;
    }

    public void setSubmitedDate(String submitedDate) {
        this.submitedDate = submitedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public IDAndName getFrom() {
        return from;
    }

    public void setFrom(IDAndName from) {
        this.from = from;
    }

    public IDAndName getTo() {
        return to;
    }

    public void setTo(IDAndName to) {
        this.to = to;
    }
}
