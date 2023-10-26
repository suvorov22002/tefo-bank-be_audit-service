package com.pkf.cbs.historizationservice.mongostorage.service;

import com.pkf.cbs.historizationservice.mongostorage.model.AuditTrace;
import com.tefo.library.commonutils.basestructure.dto.AuditTrailRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuditTraceService {

    AuditTrace createAuditTrace(AuditTrailRequest auditTrailRequest);

    List<AuditTrace> getAuditTraceByIndex(String index);

    List<AuditTrace> getAllAuditTraceByIndex();

    Page<AuditTrace> getAllPaginatedAudit(Pageable pageable);

    AuditTrace getUniqueAudit(String id);
}
