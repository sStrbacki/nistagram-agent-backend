package rs.ac.uns.ftn.nistagram.campaign.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public abstract class Campaign {

    private String name;
    private Type type;
    private List<LocalTime> exposureMoments;
    private TargetGroup targetedGroup;
    // TODO
    // private List<Advertisement> advertisements;

    public enum Type {
        POST, STORY
    }
}
