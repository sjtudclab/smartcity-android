package sjtu.dclab.smartcity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by Huiyi on 2015/3/30.
 */
public class TopicOption {
    private long id;
    private String name;

    @JsonIgnore
    private Topic topic;

    private int voteCount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
}