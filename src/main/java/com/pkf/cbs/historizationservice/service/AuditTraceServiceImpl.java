package com.pkf.cbs.historizationservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pkf.cbs.historizationservice.AuditTraceDto;
import com.pkf.cbs.historizationservice.model.AuditTrace;
import com.pkf.cbs.historizationservice.repository.AuditTraceRepository;
import com.tefo.library.commonutils.basestructure.dto.AuditTrailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AuditTraceServiceImpl implements AuditTraceService{

    private final AuditTraceRepository auditTraceRepository;

    @Override
    public AuditTrace createAuditTrace(AuditTrailRequest auditTrailRequest) {

        AuditTrace auditTrace = new AuditTrace();
        try{

            String index = null == auditTrailRequest.getIndex() ? "auditget" : auditTrailRequest.getIndex().toLowerCase();
            auditTrace.setIndex(index);
            auditTrace.setId(String.valueOf(auditTrailRequest.getUuid()));
            auditTrace.setSource(new ObjectMapper().writeValueAsString(auditTrailRequest));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        return auditTraceRepository.save(auditTrace);

    }

    @Override
    public List<AuditTrace> getAuditTraceByIndex(String index) {
        return null;
    }

    @Override
    public Page<AuditTrace> getPaginetedAuditByIndex(String index) {
        return null;
    }
}
