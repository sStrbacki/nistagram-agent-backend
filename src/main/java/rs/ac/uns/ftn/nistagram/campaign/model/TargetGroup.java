package rs.ac.uns.ftn.nistagram.campaign.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TargetGroup {
    private int minAge;
    private int maxAge;
    private Gender gender;
}
