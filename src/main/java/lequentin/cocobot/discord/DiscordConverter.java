package lequentin.cocobot.discord;

import discord4j.core.object.entity.Message;
import lequentin.cocobot.domain.User;

public class DiscordConverter {

    private static final String KNOWN_USER_TAG = "dannyp"; // Replace with the actual tag if available

    public lequentin.cocobot.domain.Message toDomain(Message discordMessage) {
        discord4j.core.object.entity.User discordUser = discordMessage.getAuthor().orElse(null);

        User author;
        if (discordUser != null) {
            // If the author's tag matches the known user tag, map them to "Dan"
            if (discordUser.getTag().equals(KNOWN_USER_TAG)) {
                author = new User("Dan");
            } else {
                author = new User(discordUser.getUsername());
            }
        } else {
            // Handle the case where the author is not present (user has left the server)
            author = new User("Unknown");
        }

        // Assuming getContent() and getTimestamp() are the correct methods
        return new lequentin.cocobot.domain.Message(author, discordMessage.getTimestamp(), discordMessage.getContent());
    }
}
