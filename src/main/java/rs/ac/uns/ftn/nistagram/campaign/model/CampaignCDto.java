package rs.ac.uns.ftn.nistagram.campaign.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class CampaignCDto {

    private String name;
    private Type type;
    private TargetGroup targetedGroup;
    private List<Advertisement> advertisements;

    public enum Type {
        POST, STORY
    }
}
