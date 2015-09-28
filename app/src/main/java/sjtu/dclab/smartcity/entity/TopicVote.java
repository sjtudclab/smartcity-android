package sjtu.dclab.smartcity.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import sjtu.dclab.smartcity.entity.deserializer.TopicVoteDeserializer;

/**
 * Created by Huiyi on 2015/3/30.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "vote_id")
@JsonDeserialize(using = TopicVoteDeserializer.class)
public class TopicVote {

    @JsonProperty(value = "vote_id")
    private long voteId;

    @JsonProperty(value = "topic_id")
    private long topicId;

    @JsonProperty(value = "user_id")
    private long userId;

    @JsonProperty(value = "option_id")
    private long optionId;

    public long getVoteId() {
        return voteId;
    }

    public void setVoteId(long voteId) {
        this.voteId = voteId;
    }

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getOptionId() {
        return optionId;
    }

    public void setOptionId(long optionId) {
        this.optionId = optionId;
    }
}