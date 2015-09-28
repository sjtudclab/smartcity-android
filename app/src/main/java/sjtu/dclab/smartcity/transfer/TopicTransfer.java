package sjtu.dclab.smartcity.transfer;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import sjtu.dclab.smartcity.entity.TopicOption;
import sjtu.dclab.smartcity.entity.deserializer.TopicDeserializer;

import java.util.Collection;

/**
 * Created by Yang on 2015/8/14.
 * 投票，沿用了服务器端的类名
 */

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "topic_id")
@JsonDeserialize(using = TopicDeserializer.class)

public class TopicTransfer {
    @JsonProperty(value = "topic_id")
    private long topicId;

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "content")
    private String content;

    @JsonProperty(value = "desc")
    private String desc;

    @JsonProperty(value = "vote_count")
    private long voteCount;

    @JsonProperty(value = "start_time")
    private String startTime;

    @JsonProperty(value = "end_time")
    private String endTime;

    @JsonProperty(value = "topic_type_id")
    private long topicTypeId;

    @JsonProperty(value = "parent_topic_id")
    private long parentTopicId;

    @JsonProperty(value = "attachment")
    private String attachment;

    @JsonProperty(value = "created_by")
    private long createdBy;

    @JsonProperty(value = "submit_time")
    private String submitTime;

    @JsonProperty(value = "options")
    private Collection<TopicOption> options;

    public long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(long voteCount) {
        this.voteCount = voteCount;
    }

    public long getTopicTypeId() {
        return topicTypeId;
    }

    public void setTopicTypeId(long topicTypeId) {
        this.topicTypeId = topicTypeId;
    }

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

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Collection<TopicOption> getOptions() {
        return options;
    }

    public void setOptions(Collection<TopicOption> options) {
        this.options = options;
    }

    public long getParentTopicId() {
        return parentTopicId;
    }

    public void setParentTopicId(long parentTopicId) {
        this.parentTopicId = parentTopicId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }
}
