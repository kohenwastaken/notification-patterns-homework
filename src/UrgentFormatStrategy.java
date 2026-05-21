public class UrgentFormatStrategy implements MessageFormatStrategy {

    @Override
    public String format(String message) {
        return "[URGENT] " + message;
    }
}