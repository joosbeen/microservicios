package academy.digitallap.store.serviceshopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient // Para que pueda ser leido en eureka
@SpringBootApplication
@EnableFeignClients // Para poder consultar otro microservicios
@EnableHystrix // Permitira controlar las excepciones de microservicios caidos
@EnableHystrixDashboard
public class ServiceShoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceShoppingApplication.class, args);
	}

}
