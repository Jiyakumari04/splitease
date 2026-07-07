package com.Splitease.splitease.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="settlements")
@Data @NoArgsConstructor
@AllArgsConstructor
public class Settlement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne @JoinColumn(name = "from_user")
    private User fromUser;

    @ManyToOne @JoinColumn(name = "to_user")
    private User toUser;

    private BigDecimal amount;

    @Column(name="settles_at")
    private LocalDateTime settlementAt;


    public void setSettledAt(LocalDateTime now) {
    }
}
