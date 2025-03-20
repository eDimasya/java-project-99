package hexlet.code.app.exception;

public class UserAlreadyAddedException extends RuntimeException {
    public UserAlreadyAddedException(String email) {
        super("User with email: " + email + " already added!");
    }
}
