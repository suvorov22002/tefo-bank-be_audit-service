package com.pkf.cbs.historizationservice;

import com.tefo.library.commonutils.basestructure.dto.AuditTrailRequest;
import com.tefo.library.commonutils.validation.MandatoryField;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AuditTraceDto {

    @MandatoryField
    private String id;
    @MandatoryField
    private String index;
    private AuditTrailRequest auditTrailRequest;

}
