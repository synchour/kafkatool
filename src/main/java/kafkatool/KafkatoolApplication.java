package kafkatool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.core.env.AbstractEnvironment;

@SpringBootApplication
@EnableHystrix
@EnableCircuitBreaker
@EnableHystrixDashboard
public class KafkatoolApplication {

	public static void main(String[] args) {
		//System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "default");
		SpringApplication.run(KafkatoolApplication.class, args);
	}
}
