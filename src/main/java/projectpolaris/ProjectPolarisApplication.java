package projectpolaris;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@EnableAutoConfiguration
public class ProjectPolarisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectPolarisApplication.class, args);

		//#Todo Enable Eureka
//		new SpringApplicationBuilder(ProjectPolarisApplication.class).web(WebApplicationType.NONE).run(args);
	}
}
