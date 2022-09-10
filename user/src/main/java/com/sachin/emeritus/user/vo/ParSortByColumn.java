package com.sachin.emeritus.user.vo;

import java.util.Arrays;

public enum ParSortByColumn {

    CREATION_TIMESTAMP("creationTimeStamp"),
    TITLE("title"),
    REQUEST_TYPE("requestType"),
    ACTION_TYPE("actionType"),
    ASSIGNED_TO("assignedTo"),
    STATUS("status");

    private final String columnName;

    ParSortByColumn(String columnName) {
        this.columnName = columnName;
    }

    public static ParSortByColumn parSortByColumn(final String column) {
        return Arrays.stream(ParSortByColumn.values())
                .filter(value -> value.getColumnName().equalsIgnoreCase(column))
                .findFirst()
                .orElse(ParSortByColumn.CREATION_TIMESTAMP);
    }

    public String getColumnName() {
        return columnName;
    }
}
