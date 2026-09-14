package com.automation.hooks;

import java.lang.reflect.Type;

import io.cucumber.java.DefaultDataTableCellTransformer;
import io.cucumber.java.DefaultDataTableEntryTransformer;
import io.cucumber.java.DefaultParameterTransformer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataTableConfigurer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Instructs Cucumber to use Jackson to automatically convert Data Table rows 
     * into POJOs (like your Product class) by matching the table headers to the variable names.
     */
    @DefaultDataTableCellTransformer
    @DefaultDataTableEntryTransformer
    @DefaultParameterTransformer
    public Object transform(Object fromValue, Type toValueType) {
        return objectMapper.convertValue(fromValue, objectMapper.constructType(toValueType));
    }
}