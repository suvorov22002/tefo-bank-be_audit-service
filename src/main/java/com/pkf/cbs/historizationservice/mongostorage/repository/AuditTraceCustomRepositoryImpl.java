package com.pkf.cbs.historizationservice.mongostorage.repository;

import com.pkf.cbs.historizationservice.mongostorage.model.AuditTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Collation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Repository
public class AuditTraceCustomRepositoryImpl implements AuditTraceCustomRepository{

    private final MongoTemplate mongoTemplate;

    @Override
    public List<AuditTrace> findAuditTraceByIndex(String index) {

        Pattern caseInsensitivePattern = Pattern.compile(index, Pattern.CASE_INSENSITIVE);
        Criteria nameNotBankCriteria = Criteria.where(AuditTrace.Fields.index).regex(caseInsensitivePattern);
        Query query = buildBaseQuery();
        query.addCriteria(nameNotBankCriteria);
        return mongoTemplate.find(query, AuditTrace.class);

    }

    private Query buildBaseQuery() {
        Sort.Order dateOrder = new Sort.Order(Sort.Direction.DESC, AuditTrace.Fields.localDate);

        Query query = new Query()
                .with(Sort.by(dateOrder));
        query.collation(Collation.of("en").strength(Collation.ComparisonLevel.secondary()));
        return query;
    }


}
