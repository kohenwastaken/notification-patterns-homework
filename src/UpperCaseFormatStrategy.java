public class UpperCaseFormatStrategy implements MessageFormatStrategy {

    @Override
    public String format(String message) {
        return message.toUpperCase();
    }
}