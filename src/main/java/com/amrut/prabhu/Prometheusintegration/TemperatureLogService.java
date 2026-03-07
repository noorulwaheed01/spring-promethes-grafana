package com.amrut.prabhu.Prometheusintegration;

import com.amrut.prabhu.Prometheusintegration.infrastructure.CelsiusToFahrenheitRequest;
import com.amrut.prabhu.Prometheusintegration.infrastructure.CelsiusToFahrenheitResponse;
import com.amrut.prabhu.Prometheusintegration.infrastructure.TemperaturePortType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TemperatureLogService {

    private static final Logger logger = LoggerFactory.getLogger(TemperatureLogService.class);
    private final TemperaturePortType temperaturePortType;

    public TemperatureLogService(TemperaturePortType temperaturePortType) {
        this.temperaturePortType = temperaturePortType;
    }

    /**
     * Creates logs by calling the Temperature SOAP endpoint (CelsiusToFahrenheit)
     */
    public void createLogs() {
        try {
            logger.info("Starting SOAP endpoint call to TemperaturePortType...");
            
            // Call the SOAP endpoint with a sample temperature value
            double celsius = 37;
            logger.info("Calling CelsiusToFahrenheit with input: {} °C", celsius);
            
            CelsiusToFahrenheitResponse response = callTemperatureSoapEndpoint(celsius);
            double fahrenheit = response.getFahrenheit();
            
            logger.info("SOAP Response received: {} °C = {} °F", celsius, fahrenheit);
            logger.debug("Temperature conversion completed successfully");
            
        } catch (Exception e) {
            logger.error("Error calling SOAP endpoint - TemperaturePortType", e);
            throw new RuntimeException("Failed to call SOAP endpoint", e);
        }
    }

    /**
     * Creates logs with a custom temperature value
     * @param celsius the temperature in Celsius to convert
     */
    public void createLogs(double celsius) {
        try {
            logger.info("Starting SOAP endpoint call with custom temperature: {} °C", celsius);
            
            CelsiusToFahrenheitResponse response = callTemperatureSoapEndpoint(celsius);
            double fahrenheit = response.getFahrenheit();
            
            logger.info("SOAP Response: {} °C = {} °F", celsius, fahrenheit);
            logger.debug("Temperature conversion completed successfully");
            
        } catch (Exception e) {
            logger.error("Error calling SOAP endpoint - TemperaturePortType with input: {}", celsius, e);
            throw new RuntimeException("Failed to call SOAP endpoint", e);
        }
    }

    /**
     * Helper method to call the SOAP endpoint
     * @param celsius the temperature in Celsius
     * @return the conversion response
     */
    private CelsiusToFahrenheitResponse callTemperatureSoapEndpoint(double celsius) {
        CelsiusToFahrenheitRequest request = new CelsiusToFahrenheitRequest();
        request.setCelsius(celsius);
        return temperaturePortType.celsiusToFahrenheit(request);
    }
}
