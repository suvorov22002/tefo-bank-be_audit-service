package com.pkf.cbs.historizationservice.mongostorage.repository;

import com.pkf.cbs.historizationservice.mongostorage.model.AuditTrace;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuditTraceRepository extends MongoRepository<AuditTrace, String>, AuditTraceCustomRepository {

    Optional<AuditTrace> findById(String id);

    default List<AuditTrace> findByIndex(String index) {
        return findAuditTraceByIndex(index);
    }
}
