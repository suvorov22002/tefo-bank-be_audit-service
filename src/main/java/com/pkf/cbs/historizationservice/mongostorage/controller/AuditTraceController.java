package com.pkf.cbs.historizationservice.mongostorage.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pkf.cbs.historizationservice.mongostorage.dto.AuditTraceDto;
import com.pkf.cbs.historizationservice.mongostorage.mapper.AuditMapper;
import com.pkf.cbs.historizationservice.mongostorage.service.AuditTraceService;
import com.tefo.library.commonutils.pagination.PageDto;
import com.tefo.library.commonutils.pagination.PaginationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/audit-service/api/v1/audit-trace")
public class AuditTraceController {

    private final AuditTraceService auditTraceService;
    private final AuditMapper auditMapper;

    @GetMapping("/all")
    public ResponseEntity<List<AuditTraceDto>> retrieveAllAuditTrace(){
        return ResponseEntity.ok(auditMapper.toDtoList(auditTraceService.getAllAuditTraceByIndex()));
    }

    @GetMapping("/indexes/{index}")
    public ResponseEntity<List<AuditTraceDto>> retrieveAuditWithIndex(@PathVariable String index) {

        return ResponseEntity.ok(auditMapper.toDtoList(auditTraceService.getAuditTraceByIndex(index)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditTraceDto> retrieveAuditById(@PathVariable String id) throws JsonProcessingException {

        return ResponseEntity.ok(auditMapper.toDto(auditTraceService.getUniqueAudit(id)));
    }

    @GetMapping()
    public ResponseEntity<PageDto<AuditTraceDto>> retrieveAllPaginatedAudit(Pageable pageable) {

        return ResponseEntity.ok(
                PaginationUtils.convertEntityPageToDtoPage((auditTraceService.getAllPaginatedAudit(pageable)),
                        auditEntities ->
                                auditEntities.stream()
                                        .map(auditMapper::toDto)
                                        .toList()
                )
        );
    }
}
