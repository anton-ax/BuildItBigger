package com.example;


import java.util.Random;

public class RemoteJoke {

    private String[] jokesArray = new String[]{
            "Three tomatoes are walking down the street - a poppa tomato, a momma tomato, and a little baby tomato. \n" +
                    "\n" +
                    "Baby tomato starts lagging behind. Poppa tomato gets angry, goes over to the baby tomato, and smooshes him... and says, \"Catch up.\" ",
            "A guy walks into a bar with a giraffe and proceeds to get pissed. \n" +
                    "\n" +
                    "The giraffe passes out on the bar floor. The guy gets up to leave and the bartender says, \" OY! You can't leave that lying here!\". \n" +
                    "\n" +
                    "The guys says, \"That's no lion, it's a giraffe!\"",
            "A few decades ago we had Johnny Cash, Bob Hope and Steve Jobs. Now we have no Cash, no Hope and no Jobs. Please don't let Kevin Bacon die."
    };

    public String sayJoke() {
        int idx = new Random().nextInt(jokesArray.length);
        return jokesArray[idx];
    }
}
