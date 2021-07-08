package rs.ac.uns.ftn.nistagram.campaign.model.create;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class LongTermCampaignCDto extends CampaignCDto {

    private List<LocalTime> exposureMoments;
    private LocalDate startsOn;
    private LocalDate endsOn;
}
