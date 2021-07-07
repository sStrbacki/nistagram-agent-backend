package rs.ac.uns.ftn.nistagram.campaign.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.nistagram.campaign.http.NistagramCampaignClient;
import rs.ac.uns.ftn.nistagram.campaign.model.CampaignCDto;
import rs.ac.uns.ftn.nistagram.campaign.model.LongTermCampaignCDto;
import rs.ac.uns.ftn.nistagram.campaign.model.OneTimeCampaignCDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/campaign")
@AllArgsConstructor
public class NistagramCampaignController {

    private final NistagramCampaignClient nistagramClient;

    @GetMapping
    public LocalDateTime getAll() {
        var a = new ArrayList<Object>();

//        trainings.removeIf(training -> training.id === trId);

        return LocalDateTime.now();
//        return nistagramClient.getAll();
    }

    @PostMapping("one-term")
    public OneTimeCampaignCDto create(@RequestBody OneTimeCampaignCDto campaign) {
        System.out.println("Here");
        return nistagramClient.createOneTermCampaign(campaign);
    }

    @PostMapping("long-term")
    public LongTermCampaignCDto create(@RequestBody LongTermCampaignCDto campaign) {
        return nistagramClient.createLongTermCampaign(campaign);
    }
}
