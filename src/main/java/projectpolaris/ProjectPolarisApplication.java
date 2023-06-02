package projectpolaris;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.annotation.KafkaListener;
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

	@KafkaListener(id = "Midir", topics = "UtsuP")
	public void listen(String in) {
		log.info(in);
	}

//	@Bean
//	public ApplicationRunner runner(KafkaTemplate<String, String> template) {
//		return args -> {
//			for (int i = 0; i < 10; i++) {
//				Thread.sleep(6000); //six sec
//				template.send("UtsuP", i + ":" +
//						"\"Ang kahalagahan ng tiyaga at pagtitiyaga sa buhay ay tulad ng paghahanda ng maalamat na ibon na lilipad at tatawid sa malawak na kalangitan. Sa pamamagitan ng puspusang pagsisikap at matatag na paninindigan, malalampasan natin ang mga hamon at mararating natin ang mga pangarap na hindi natin inakala na maabot.\"");
//			}
//		};
//	}
}
