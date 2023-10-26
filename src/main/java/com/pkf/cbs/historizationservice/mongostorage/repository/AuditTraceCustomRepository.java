package com.pkf.cbs.historizationservice.mongostorage.repository;

import com.pkf.cbs.historizationservice.mongostorage.model.AuditTrace;

import java.util.List;
import java.util.Optional;

public interface AuditTraceCustomRepository {

    List<AuditTrace> findAuditTraceByIndex(String index);
}
