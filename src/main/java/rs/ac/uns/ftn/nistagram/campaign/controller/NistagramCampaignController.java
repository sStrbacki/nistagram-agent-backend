package rs.ac.uns.ftn.nistagram.campaign.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.nistagram.campaign.http.NistagramCampaignClient;
import rs.ac.uns.ftn.nistagram.campaign.model.create.CampaignCDto;
import rs.ac.uns.ftn.nistagram.campaign.model.create.LongTermCampaignCDto;
import rs.ac.uns.ftn.nistagram.campaign.model.create.OneTimeCampaignCDto;
import rs.ac.uns.ftn.nistagram.campaign.model.view.CampaignVDto;

import java.util.List;

@RestController
@RequestMapping("api/campaign")
@AllArgsConstructor
public class NistagramCampaignController {

    private final NistagramCampaignClient nistagramClient;

    @GetMapping
    public List<CampaignVDto> getAll() {
        return nistagramClient.getAll();
    }

    @PostMapping("one-term")
    public OneTimeCampaignCDto create(@RequestBody OneTimeCampaignCDto campaign) {
        return nistagramClient.createOneTermCampaign(campaign);
    }

    @PostMapping("long-term")
    public LongTermCampaignCDto create(@RequestBody LongTermCampaignCDto campaign) {
        return nistagramClient.createLongTermCampaign(campaign);
    }
}
