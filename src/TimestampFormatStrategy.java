import java.time.LocalDateTime;

public class TimestampFormatStrategy implements MessageFormatStrategy {

    @Override
    public String format(String message) {
        return "[" + LocalDateTime.now() + "] " + message;
    }
}