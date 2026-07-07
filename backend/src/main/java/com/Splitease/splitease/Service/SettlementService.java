package com.Splitease.splitease.Service;

import com.Splitease.splitease.model.Settlement;
import com.Splitease.splitease.repository.GroupRepository;
import com.Splitease.splitease.repository.SettlementRepository;
import com.Splitease.splitease.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SettlementService {
    private final SettlementRepository settlementRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public Settlement recordSettlement(Long groupId, Long fromUserId, Long toUserId, BigDecimal amount) {
        Settlement settlement = new Settlement();
        settlement.setGroup(groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found")));
        settlement.setFromUser(userRepository.findById(fromUserId)
                .orElseThrow(() -> new RuntimeException("User not found")));
        settlement.setToUser(userRepository.findById(toUserId)
                .orElseThrow(() -> new RuntimeException("User not found")));
        settlement.setAmount(amount);
        settlement.setSettledAt(LocalDateTime.now());
        return settlementRepository.save(settlement);
    }

    public List<Settlement> getGroupHistory(Long groupId) {
        return settlementRepository.findByGroupId(groupId);
    }
}