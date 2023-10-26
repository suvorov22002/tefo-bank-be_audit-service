package com.pkf.cbs.historizationservice.rabbitmq;

import com.pkf.cbs.historizationservice.elasticsearch.ElasticSearchClient;
import com.pkf.cbs.historizationservice.mongostorage.service.AuditTraceService;
import com.tefo.library.commonutils.basestructure.dto.AuditTrailRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class AuditConsumer {

    private final ElasticSearchClient elasticSearchClient;
    private final AuditTraceService auditTraceService;

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumer(AuditTrailRequest object) {
        log.info("Consumed {} from queue", object);
        elasticSearchClient.insertDataPojos(object);
        auditTraceService.createAuditTrace(object);
    }

}
