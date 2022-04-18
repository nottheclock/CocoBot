package lequentin.cocobot.domain;

public class SpacePunctuationSanitizer implements StringSanitizer {
    private final static String PUNCTUATION = "(,|;|:|\\/|\"|\\(|\\))";
    @Override
    public String sanitize(String text) {
        return text.replaceAll(String.format("%s%s*", PUNCTUATION, PUNCTUATION), " $1 ");
    }
}
