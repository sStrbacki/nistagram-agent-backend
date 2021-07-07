package rs.ac.uns.ftn.nistagram.campaign.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OneTimeCampaignCDto extends CampaignCDto {

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime exposureMoment;
}
