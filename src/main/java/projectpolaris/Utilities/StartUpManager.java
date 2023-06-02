package projectpolaris.Utilities;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;

//No need to use AtComponent annotation because this will be called directly from main.
// (And phase at which it will be called annotations do not work)

@Log4j2
public class StartUpManager implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    FancyWritings fancyWritings = new FancyWritings();

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {

        /* manually getting all the properties from application.properties because
         At ApplicationEnvironmentPreparedEvent annotations do not work yet */

        // fancy writings properties
        String doPrint = event.getEnvironment().getProperty("fancy-writings.do-print");

        // managing startup
        manageStartUp(doPrint);
    }

    public void manageStartUp(String doPrint) {
        fancyWritings.printMotivationalThingOnStartUp(doPrint);
    }
}