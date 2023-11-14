package lequentin.cocobot.discord;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


class DiscordConverterTest {

    private final DiscordConverter converter = new DiscordConverter();

   @Test
void shouldNotConvertMessageToDomainWhenMessageHasNoAuthor() {
    // Mock the discord4j.core.object.entity.Message
    Message discordMessage = mock(Message.class);
    when(discordMessage.getAuthor()).thenReturn(Optional.empty());

    // Execute and expect a RuntimeException
    assertThrows(RuntimeException.class, () -> converter.toDomain(discordMessage));
}


   @Test
void shouldConvertMessageToDomain() {
    // Mock the discord4j.core.object.entity.Message
    Message discordMessage = mock(Message.class);
    // Mock the discord4j.core.object.entity.User
    User discordUser = mock(User.class);
    when(discordUser.getUsername()).thenReturn("messageAuthor");
    when(discordMessage.getAuthor()).thenReturn(Optional.of(discordUser));
    // Assuming getTimestamp() and getContent() are valid methods and return non-null values
    when(discordMessage.getTimestamp()).thenReturn(Instant.now());
    when(discordMessage.getContent()).thenReturn("message content");

    // Execute the method to test
    lequentin.cocobot.domain.Message result = converter.toDomain(discordMessage);

    // Assertions
    assertNotNull(result.getAuthor());
    assertEquals("messageAuthor", result.getAuthor().getUsername());
    assertEquals("message content", result.getText());
    assertNotNull(result.getCreatedAt());
}

}
