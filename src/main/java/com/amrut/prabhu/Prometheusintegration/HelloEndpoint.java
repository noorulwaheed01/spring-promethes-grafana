package com.amrut.prabhu.Prometheusintegration;

import org.jspecify.annotations.Nullable;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Endpoint(id = "hello-0310")
public class HelloEndpoint implements HealthIndicator {

    @ReadOperation
    public Map<String, String> hello() {
        return Map.of("message", "Hello from custom actuator 👋");
    }

    @Override
    public @Nullable Health health() {
        return Health.up().build();
    }
}