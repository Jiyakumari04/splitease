package com.Splitease.splitease.dto;

import lombok.Data;

import java.util.List;

@Data
public class Grouprequest {
    private  String name;
    private Long createdBy;
    private List<Long> memberIds;
}
