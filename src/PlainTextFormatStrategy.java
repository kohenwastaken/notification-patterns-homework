public class PlainTextFormatStrategy implements MessageFormatStrategy {

    @Override
    public String format(String message) {
        return message;
    }
}