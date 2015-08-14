package sjtu.dclab.smartcity.entity.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import sjtu.dclab.smartcity.entity.TopicVote;

import java.io.IOException;

/**
 * Created by Huiyi on 2015/3/30.
 */
public class TopicVoteDeserializer extends JsonDeserializer<TopicVote> {
    @Override
    public TopicVote deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        TopicVote vote = new TopicVote();

        vote.setOptionId(node.get("option_id").asLong());
        vote.setUserId(node.get("user_id").asLong());
        vote.setTopicId(node.get("topic_id").asLong());

        return vote;
    }
}