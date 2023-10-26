package com.pkf.cbs.historizationservice.model;

import com.tefo.library.commonutils.basestructure.dto.AuditTrailRequest;
import com.tefo.library.commonutils.validation.MandatoryField;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "audit_trace")
@FieldNameConstants
public class AuditTrace extends BaseEntity{

    @Id
    private String id;
    @MandatoryField
    private String index;
    private String source;
}
