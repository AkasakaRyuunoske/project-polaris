package projectpolaris;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import projectpolaris.Utilities.StartUpManager;

@SpringBootApplication
@EnableAutoConfiguration
@Log4j2
public class ProjectPolarisApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(ProjectPolarisApplication.class)
				.listeners(new StartUpManager()).run();

		//#Default constructor, might be useful.
//		SpringApplication.run(ProjectPolarisApplication.class, args);

		//#Todo Enable Eureka
//		new SpringApplicationBuilder(ProjectPolarisApplication.class).web(WebApplicationType.NONE).run(args);
	}
}
