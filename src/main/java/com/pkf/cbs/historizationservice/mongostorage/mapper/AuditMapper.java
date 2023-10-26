package com.pkf.cbs.historizationservice.mongostorage.mapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pkf.cbs.historizationservice.mongostorage.dto.AuditTraceDto;
import com.pkf.cbs.historizationservice.mongostorage.model.AuditTrace;
import com.tefo.library.commonutils.basestructure.dto.AuditTrailRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class AuditMapper {



    public abstract List<AuditTraceDto> toDtoList(List<AuditTrace> auditTraces);

    @Mapping(target = "auditTrailRequest", expression = "java(resolveAuditTrace(auditTrace.getSource()))")
    public abstract AuditTraceDto toDto(AuditTrace auditTrace);

    protected AuditTrailRequest resolveAuditTrace(String auditTrace){

        try{
            ObjectMapper om = new ObjectMapper();
            return  om.readValue(auditTrace, AuditTrailRequest.class);
        }
        catch (JsonProcessingException e){
            return null;
        }


    }
}
