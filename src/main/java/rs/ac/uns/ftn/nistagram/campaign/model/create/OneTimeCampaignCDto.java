package rs.ac.uns.ftn.nistagram.campaign.model.create;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OneTimeCampaignCDto extends CampaignCDto {

    private LocalDateTime exposureMoment;
}
