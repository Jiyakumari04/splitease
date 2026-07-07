package com.Splitease.splitease.controller;

import com.Splitease.splitease.Service.SettlementService;
import com.Splitease.splitease.model.Settlement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups/{groupId}/settlements")
@RequiredArgsConstructor
public class SettlementController {
    private final SettlementService settlementService;

    @PostMapping
    public ResponseEntity<Settlement> settle(@PathVariable Long groupId,
                                             @RequestBody SettlementRequest req) {
        return ResponseEntity.ok(settlementService.recordSettlement(
                groupId, req.getFromUserId(), req.getToUserId(), req.getAmount()));
    }

    @GetMapping
    public ResponseEntity<List<Settlement>> history(@PathVariable Long groupId) {
        return ResponseEntity.ok(settlementService.getGroupHistory(groupId));
    }
}
