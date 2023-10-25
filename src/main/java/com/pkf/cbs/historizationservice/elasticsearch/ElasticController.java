package com.pkf.cbs.historizationservice.elasticsearch;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/elasticsearch")
public class ElasticController {

    private final ElasticSearchClient elasticSearchClient;

    @GetMapping("/insert/{index}")
    public void processData(@PathVariable("index") String index) {
        elasticSearchClient.createIndex(index);
    }

}
