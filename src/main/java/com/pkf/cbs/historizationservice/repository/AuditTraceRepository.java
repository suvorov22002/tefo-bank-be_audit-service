package com.pkf.cbs.historizationservice.repository;

import com.pkf.cbs.historizationservice.model.AuditTrace;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuditTraceRepository extends MongoRepository<AuditTrace, String> {

    Optional<AuditTrace> findByIndex(String index);
    Optional<AuditTrace> findById(String id);
}
