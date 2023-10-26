package com.pkf.cbs.historizationservice.mongostorage.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.io.JsonStringEncoder;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pkf.cbs.historizationservice.mongostorage.model.AuditTrace;
import com.pkf.cbs.historizationservice.mongostorage.repository.AuditTraceRepository;
import com.tefo.library.commonutils.basestructure.dto.AuditTrailRequest;
import com.tefo.library.commonutils.constants.ExceptionMessages;
import com.tefo.library.commonutils.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
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

            ObjectMapper om = new ObjectMapper();
            auditTrace.setSource(om.writeValueAsString(auditTrailRequest));
            auditTrace.setLocalDate(new Date());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        return auditTraceRepository.save(auditTrace);

    }

    @Override
    public List<AuditTrace> getAuditTraceByIndex(String index) {

        return auditTraceRepository.findByIndex(index);

    }

    @Override
    public List<AuditTrace> getAllAuditTraceByIndex() {
        return auditTraceRepository.findAll();
    }

    @Override
    public Page<AuditTrace> getAllPaginatedAudit(Pageable pageable) {
        return auditTraceRepository.findAll(pageable);
    }

    @Override
    public AuditTrace getUniqueAudit(String id) {
        return auditTraceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ressource not found"));
    }

    private String encodeJson(String jsonString) {
        JsonStringEncoder jsonEncoder = JsonStringEncoder.getInstance();
        return new String(jsonEncoder.quoteAsString(jsonString));
    }
}
