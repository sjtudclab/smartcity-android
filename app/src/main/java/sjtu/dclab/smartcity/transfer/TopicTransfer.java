package sjtu.dclab.smartcity.transfer;

import sjtu.dclab.smartcity.entity.TopicOption;
import java.util.Collection;

public class TopicTransfer {
    private long topic_id;
    private String title;
    private String content;
    private String desc;
    private long vote_count;
    private String start_time;
    private String end_time;
    private long topic_type_id;
    private long parent_topic_id;
    private String attachment;
    private long created_by;
    private String submit_time;
    private Collection<TopicOption> options;

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreated_by() {
        return created_by;
    }

    public void setCreated_by(long created_by) {
        this.created_by = created_by;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public Collection<TopicOption> getOptions() {
        return options;
    }

    public void setOptions(Collection<TopicOption> options) {
        this.options = options;
    }

    public long getParent_topic_id() {
        return parent_topic_id;
    }

    public void setParent_topic_id(long parent_topic_id) {
        this.parent_topic_id = parent_topic_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getSubmit_time() {
        return submit_time;
    }

    public void setSubmit_time(String submit_time) {
        this.submit_time = submit_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(long topic_id) {
        this.topic_id = topic_id;
    }

    public long getTopic_type_id() {
        return topic_type_id;
    }

    public void setTopic_type_id(long topic_type_id) {
        this.topic_type_id = topic_type_id;
    }

    public long getVote_count() {
        return vote_count;
    }

    public void setVote_count(long vote_count) {
        this.vote_count = vote_count;
    }
}
