package com.jpmc.theater.cucumber;

import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ScenarioScope
public class TestContext {
    private Map<String, Object> scenarioContext;

    public TestContext() {
        scenarioContext = new HashMap<>();
    }

    public Map getContext() {
        return scenarioContext;
    }
}
