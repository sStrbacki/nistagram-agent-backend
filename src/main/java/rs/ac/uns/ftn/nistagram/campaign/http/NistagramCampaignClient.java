package rs.ac.uns.ftn.nistagram.campaign.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import rs.ac.uns.ftn.nistagram.campaign.model.CampaignCDto;
import rs.ac.uns.ftn.nistagram.campaign.model.LongTermCampaignCDto;
import rs.ac.uns.ftn.nistagram.campaign.model.OneTimeCampaignCDto;

import java.util.List;

@FeignClient(
        name = "nistagram-campaign-api-service",
        url = "${nistagram.api.root}",
        configuration = NistagramCampaignClientInterceptor.class
)
public interface NistagramCampaignClient {

    @GetMapping
    List<CampaignCDto> getAll();

    @PostMapping("one-time")
    OneTimeCampaignCDto createOneTermCampaign(@RequestBody OneTimeCampaignCDto campaign);

    @PostMapping("long-term")
    LongTermCampaignCDto createLongTermCampaign(@RequestBody LongTermCampaignCDto campaign);
}
