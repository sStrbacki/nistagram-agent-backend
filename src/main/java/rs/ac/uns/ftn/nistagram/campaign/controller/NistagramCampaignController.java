package rs.ac.uns.ftn.nistagram.campaign.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.nistagram.campaign.http.NistagramCampaignClient;
import rs.ac.uns.ftn.nistagram.campaign.model.LongTermCampaign;

@Controller
@RequestMapping("api/campaign")
@AllArgsConstructor
public class NistagramCampaignController {

    private final NistagramCampaignClient nistagramClient;

    @GetMapping
    public ResponseEntity<String> test() {
        try {
            nistagramClient.createLongTermCampaign(new LongTermCampaign());
        }
        catch (Exception ignore) {}

        return ResponseEntity.ok("Tested.");
    }
}
