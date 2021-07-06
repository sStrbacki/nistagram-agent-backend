package rs.ac.uns.ftn.nistagram.campaign.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class LongTermCampaign extends Campaign {

    private LocalDate startsOn;
    private LocalDate endsOn;

}
