package rs.ac.uns.ftn.nistagram.campaign.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class LongTermCampaignCDto extends CampaignCDto {

    private LocalDate startsOn;
    private LocalDate endsOn;
}
