package com.Splitease.splitease.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="groups")
@Data
@NoArgsConstructor @AllArgsConstructor
public class Group {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne
    @JoinColumn(name="created_by")
    private User createdBy;
    @ManyToOne
    @JoinTable(name= "group_members",
        joinColumns = @JoinColumn(name="group_id"),
        inverseJoinColumns = @JoinColumn(name= "user_id"))
    private Set<User> members= new HashSet<>();
}
