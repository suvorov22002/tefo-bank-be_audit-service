package com.pkf.cbs.historizationservice.mongostorage.dto;

import com.tefo.library.commonutils.basestructure.dto.AuditTrailRequest;
import com.tefo.library.commonutils.validation.MandatoryField;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class AuditTraceDto {

    @MandatoryField
    private String id;
    @MandatoryField
    private String index;
    private AuditTrailRequest auditTrailRequest;
    private Date localDate;

}
