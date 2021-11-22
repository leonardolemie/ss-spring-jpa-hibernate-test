package com.school.registration.dto.in;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class StudentRequest {

    //todo add validators
    private String name;
    private List<Integer> courseIds;
}
