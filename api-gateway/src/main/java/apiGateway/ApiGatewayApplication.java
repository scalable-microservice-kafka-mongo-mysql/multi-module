package apiGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
        /*
        Unsecure api gateway working like a charm
        All Microservices - product, inventory - /check, order - all working fine.
        Will now slowly bring auth to this microservice.
         */
    }
}
