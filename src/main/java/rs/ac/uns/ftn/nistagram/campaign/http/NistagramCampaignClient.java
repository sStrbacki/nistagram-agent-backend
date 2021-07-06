package rs.ac.uns.ftn.nistagram.campaign.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import rs.ac.uns.ftn.nistagram.campaign.model.LongTermCampaign;
import rs.ac.uns.ftn.nistagram.campaign.model.OneTimeCampaign;

@FeignClient(
        name = "nistagram-campaign-api-service",
        url = "http://localhost:9090/api/campaigns/external",
        configuration = NistagramCampaignClientConfiguration.class
)
public interface NistagramCampaignClient {

    @PostMapping("one-time")
    void createOneTermCampaign(@RequestBody OneTimeCampaign campaign);

    @PostMapping("long-term")
    void createLongTermCampaign(@RequestBody LongTermCampaign campaign);
}
