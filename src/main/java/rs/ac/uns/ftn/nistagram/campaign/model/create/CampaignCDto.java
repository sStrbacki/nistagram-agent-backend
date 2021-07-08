package rs.ac.uns.ftn.nistagram.campaign.model.create;

import lombok.Getter;
import lombok.Setter;
import rs.ac.uns.ftn.nistagram.campaign.model.TargetGroup;

import java.util.List;

@Getter
@Setter
public class CampaignCDto {

    private String name;
    private Type type;
    private TargetGroup targetedGroup;
    private List<AdvertisementCDto> advertisements;

    public enum Type {
        POST, STORY
    }
}
