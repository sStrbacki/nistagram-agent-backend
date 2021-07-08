package rs.ac.uns.ftn.nistagram.campaign.model.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CampaignVDto {
    private long id;
    private String name;
    @JsonDeserialize(using = AgentDateDeserializer.class)
    @JsonSerialize(using = AgentDateSerializer.class)
    private LocalDateTime createdOn;
    private long contentId;
    private List<AdvertisementVDto> advertisements;
}
