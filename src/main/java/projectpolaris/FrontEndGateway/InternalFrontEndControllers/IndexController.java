package projectpolaris.FrontEndGateway.InternalFrontEndControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    private String indexController(Model model){
        model.addAttribute("message", "Ever felt peckish, a bit hungry, but not hungry enough to actually have to eat? Just hungry enough to feel that pain. And have you then ever opened your fridge to grab something, looked at everything in it, and then closed it not having taken anything? You're kind of hungry, but don't have any appetite. You want to eat, but you can't find anything you want to eat. Not even the smell of freshly cooked food is enough to rouse your appetite. And so you don't eat, and have to go on, feeling that pain, feeling that push to eat something, you keep going to the fridge only to look with apathy at what's inside, and then close it again.\n" +
                "\n" +
                "\n" +
                "The state of mind I'm talking about is similar. You want to do something, anything, but nothing is interesting enough. But not only that, it feels like you don't want to do anything. It feels like laying in bed, and just staring at a wall, or celling is what you want to do. But if you do, you're going to start feeling restless. You'll feel like you have to move, do something. There are so many things you want to do, or that you could do just for the fun of it. But no, there's nothing that interest you. Any activity seems either too difficult, too mundane, too annoying, not stimulating enough, it would take too much time, it doesn't last long enough, too complex, etc. So you end up standing up in order to lay back down again.\n" +
                "\n" +
                "Now, you can force yourself to do something, but it doesn't fill you with joy. Nothing does. The only thing you feel consistently is frustration. And not the kind that turns into anger. No, that would be too easy to deal with. Anger can at the very least motivate you to action. If you feel angry, you want to do something about it, express it in some form or another. You're just plain frustrated. \n" +
                "\n" +
                "There doesn't seem to be any reason for you being like this. There's nothing that overly worries you, nothing to keep you awake at night, nothing going wrong, nothing that feels too inadequate, you're quite happy with the direction in which your life's headed. This state seemingly appears out of nowhere, at random, with nothing that could be considered a cause, and doesn't last for a consistent amount of time. There doesn't seem to be any similarity between these... episodes.\n" +
                "\n" +
                "\n" +
                "\n" +
                "If you're out for a walk while in this state, you might think you're staring at nothing in particular. Your eyes are fixated, but on a point, and not an object. But then you find out you have been staring at something all along, and you only notice it when something drastically changes, like the window you've been looking at suddenly opens, or someone looks out of it.\n" +
                "\n" +
                "\n" +
                "\n" +
                "And all of this could be fine if it was just that. Yeah, you're frustrated for no particular reason, but at least you can think to entertain yourself. But it's not just that. You can't think, or rather, you can't perceive your thoughts. You have them, you're sure of it, but you can't recall them. Not even after you've just had them. The reason you're certain you were thinking, is because you remember thinking, but that's all. You don't remember what it was, or how it made you feel, you just remember your mind was preoccupied with something, and if it came from the previous thought, or it was something entirely new. Sometimes you think about something for quite a bit, and  sometimes thoughts exit your mind as quickly as they entered. And no matter how hard you try, you can't recall anything other than there having been something to recall.\n" +
                "\n" +
                "It's as if you're laying in the backseat of a car that's driving somewhere. Your eyes are closed, and the only thing you can hear is the engine running. You don't know where you are, or where you're headed. You're simply there, along for the ride. But there's nothing fun about this ride. You want to open your eyes, see what's out there, but you can't. They just won't, not because you've been blindfolded, or something like that, but because they simply won't. You're drowsy, but you want to stand up. But your body doesn't want to move. You're between wakefulness and sleep. Both and neither at the same time. You want to speak to the driver, but your mouth won't move. All this frustrates you, but it's being kept in check by the lethargy, making sure it doesn't build up into anger. All you can do if lay there, and feel how the car moves. \n" +
                "Each turn the car makes is like a thought exiting, and a new one entering. The straight parts of the journey are you thinking about something. You know the car made turns. You know that it spent quite a bit of time not making any turns, you can even remember some of the sequences of turns. First left, drive a bit, left again, quick right, drive a bit longer, turn left again...\n" +
                "But you don't remember all of them, or where they were made, or even where the journey started.\n" +
                "\n" +
                "\n" +
                "\n" +
                "Usually it's not this extreme, you can still function normally, your mind is just a bit fogged, your interests faded a bit, you want to do a bit more of everything, and also a bit less. But the frustration is a constant. You ask your friend if you want to meet. You want to do something with them. But once you've met, you feel unsatisfied. You want to be alone again. You talk to them about some subject or another, but you can't express yourself precisely enough. You hear your own words, and think: \"No, that's not quite what I mean\". So you get frustrated. And of course your friend doesn't understand what you actually mean, you failed to adequately express yourself. So you get frustrated at their not getting it. But never enough for you to change the subject. Never enough for you to call it a day.\n" +
                "\n" +
                "\n" +
                "\n" +
                "Nothing feels satisfactory, nothing scratches that itch quite right. Not even discovering something new, something you might otherwise enjoy, or be at least interested in, nothing manages to excite or calm you enough.\n" +
                "\n" +
                "\n" +
                "\n" +
                "This is what this song feels like. I first found this song outside of this... lethargic hyperactivity, narcoleptic restlessness, whatever you'd like to call it. And I didn't like it. It feels too frantic, and the slower parts feel too long. But when I listend to in that state, the frustration lessened. It was still there, but not as much. It's as if both sides got what they wanted. The ps2 startup-like sound, as well as the \"oh\"s and \"ah\"s playing in the same pattern, constantly being clearly audible, and them being so soothing managed to satisfy the need for something calming. But at the same time, everything else playing quite frantically managed to keep the hyperactive side preoccupied. So both sides, both needs were met at the same time. And so, the frustration wasn't as severe as before.\n" +
                "\n" +
                "\n" +
                "\n" +
                "Quite the song.");
        return "index.html";
    }
}