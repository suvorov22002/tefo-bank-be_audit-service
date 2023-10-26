package com.pkf.cbs.historizationservice.service;

import com.pkf.cbs.historizationservice.AuditTraceDto;
import com.pkf.cbs.historizationservice.model.AuditTrace;
import com.pkf.cbs.historizationservice.repository.AuditTraceRepository;
import com.tefo.library.commonutils.basestructure.dto.AuditTrailRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AuditTraceService {

    AuditTrace createAuditTrace(AuditTrailRequest auditTrailRequest);

    List<AuditTrace> getAuditTraceByIndex(String index);

    Page<AuditTrace> getPaginetedAuditByIndex(String index);
}
