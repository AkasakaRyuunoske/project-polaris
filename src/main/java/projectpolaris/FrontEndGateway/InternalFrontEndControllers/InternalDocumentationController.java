package projectpolaris.FrontEndGateway.InternalFrontEndControllers;

import ch.qos.logback.core.model.Model;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//#Todo 1. Add if-else logic when choosing end?, server_name?, package...
//#Todo 2. Add Kafka integration. For every call create a message in kafka

@Log4j2
@Controller
@RequestMapping("/internal")
public class InternalDocumentationController {

                // Generic Controllers
    @GetMapping("/")
    private String internalIndex(Model model){
        return "index.html";
    }

    @GetMapping("/documentation/")
    private String internalDocumentation(Model model){
        return "index.html";
    }

    @GetMapping("/documentation/{end}/")
    private String internalDocumentationEndRedirect(@PathVariable String end, Model model){
        log.info("end requested is: " + end);
        //#Todo must redirect to appropriate controller based on which end? is asked
        return "index.html";
    }

                // Back End

    @GetMapping("/documentation/back-end/")
    private String internalDocumentationBackEnd(Model model){

        return "index.html";
    }

                // Front End

    @GetMapping("/documentation/front-end/")
    private String internalDocumentationFrontEnd(Model model){

        return "index.html";
    }
                // Data End

    @GetMapping("/documentation/data-end/")
    private String internalDocumentationDataEnd(Model model){

        return "index.html";
    }

                // Versions And Patches
}
