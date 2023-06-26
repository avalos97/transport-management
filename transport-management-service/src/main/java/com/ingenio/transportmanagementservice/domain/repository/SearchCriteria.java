package com.ingenio.transportmanagementservice.domain.repository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCriteria{

    private String key;
    private String operation;
    private Object value;
}
