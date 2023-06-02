package projectpolaris.Utilities;

import org.springframework.stereotype.Component;
import lombok.extern.log4j.Log4j2;


/**
 * Prints some quotes (motivational or just useless).
 * To allow to print and decide what is defined in application.properties.
 * <p>
 * Must not be allowed to print in Production build.
 */
@Component
@Log4j2
public class FancyWritings {

    public void printMotivationalThingOnStartUp(String doPrint) {
        if (doPrint.equals("true")) {
            log.info("\n" + "\n" +
                    "\"Воля может и должна быть предметом гордости гораздо больше, нежели талант.\n" +
                    " Если талант — это развитие природных склонностей, \n" +
                    " то твёрдая воля — это ежеминутно одерживаемая победа над инстинктами,\n" +
                    " над влечениями, которые воля обуздывает и подавляет, над препятствиями\n" +
                    " и преградами, которые она осиливает, над всяческими трудностями,\n" +
                    " которые она героически преодолевает. \" (Оноре де Бальзак)"
                    + "\n");
        }

        if (doPrint.equals("Chaos")) {
            log.info("\n \n" +
                    "\"Distorted by the power of his peers, " +
                    "He accepts his worst enemy: internal chaos.\"(Tempest Double)");
        }

        if (doPrint.equals("Yume?")) {
            log.info("\n \n" +
                    " NEE ANATA WA ITSUMO YUME WO MITE MASU KA?\n" +
                    " OWARI KAKE NO KONO SEKAI DE \n" +
                    " FUSAGA RE TA MICCHI WO HIRAKU MONO NI NARU \n" +
                    " 'MESSEJII' WO DAITAAAAAAA\n");
        }

        if (doPrint.equals("Blaise")) {
            log.info("\n \n" +
                    "\"all humanity's problems stem from mans " +
                    "inability to sit quietly in a room alone.\" (Blaise Pascal)");
        }

        if (doPrint.equals("F")) {
            log.info("\n \n " +
                    "\"Bakit hindi mo subukan bago mo sabihin na hindi mo kaya.\" (A random Girl)");
        }

        // Todo add this cringy quote --> one learns when one has to; one learns
        //                                when one needs a way out; one learns at all costs. One stands over oneself with a whip;
        //                                one flays oneself at the slightest opposition.
        if (doPrint.equals("list")) {
            log.info("\n \n " +
                    "\"Ну а хуле, деревья хуярят свои корни ебучие в землю глубоко чтоб ветром не снесло, "
                    + "рыбы плывут против течения чтоб дальше размножаться "
                    + "а мне тупо похуй я блядь List<List<List<List<Object>>>>.\" (Mokson)");
        }

        if (doPrint.equals("Fruts of Labor")) {
            log.info("\n \n " +
                    "\"Man, I'm gonna tell you something, discipline is key to success, being able to do the hard work when you don't feel like it, "
                    + "motivation yeah is good to have but don't rely on it, most people these days are too emotional, they do whatever they feel like it,"
                    + "emotions are all over the place, if you are emotional you are a man all over the place, don't be like that, one skill that is great"
                    + "to master is social awareness, being conscious why you feel that way, looking at them with some of interest, we need to be a role model"
                    + "for our future generations, they are the ones that will look up for us as a reference, why not give them the best of us?"
                    + "and as you said be happy, you gotta be having fun hopefully through all of life, that doesn't mean YOLO"
                    + "do whatever the fck you want, it means as you're doing the work enjoy it, enjoy the fruits of your labor,"
                    + "enjoy them and keep sowing for your future self to have even better fruits."
                    + "hope you can learn from this \" (Random Youtube Comment)");
        }
    }
}