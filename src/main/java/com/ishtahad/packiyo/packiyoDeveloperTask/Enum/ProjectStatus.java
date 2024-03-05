package com.ishtahad.packiyo.packiyoDeveloperTask.Enum;

import lombok.Getter;

@Getter
public enum ProjectStatus {
    SUCCESSFUL("Active",1),
    FAILED("Inactive",0);

    private final int status;

    ProjectStatus(String active,int status) {
        this.status = status;
    }
}
