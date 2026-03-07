package com.amrut.prabhu.Prometheusintegration.infrastructure;

import jakarta.xml.ws.BindingProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SoapClientConfig {

    @Bean
    public TemperaturePortType multiCscPortType(
            @Value("${soap.temperature.url:http://localhost:8000/temperature}")
            String url) {
        TemperatureService service = new TemperatureService();
        TemperaturePortType port = service.getTemperaturePort();
        BindingProvider bp = (BindingProvider) port;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);
        return port;
    }
}