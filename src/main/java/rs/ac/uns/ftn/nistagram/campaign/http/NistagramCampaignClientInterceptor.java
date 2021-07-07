package rs.ac.uns.ftn.nistagram.campaign.http;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;

public class NistagramCampaignClientInterceptor implements RequestInterceptor {

    @Value("${nistagram.api.api-key-header}")
    private String API_KEY_HEADER;
    @Value("${nistagram.api.api-key}")
    private String API_KEY;


    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(API_KEY_HEADER, API_KEY);
    }
}
