package africa.semicolon.promescuous.execptions;

public enum ExceptionMessage {
    USER_WITH_EMAIL_NOT_FOUND_EXCEPTION("user not found"),
    ACCOUNT_ACTIVATION_FAILED_EXCEPTION("Account activation was not successful"),
    INVALID_CREDENTIALS_EXCEPTION("Invalid  login parameters"),
    USER_NOT_FOUND_EXCEPTION("User not found");

    ExceptionMessage(String message) {
        this.message = message;
    }

    private String message;

    public String getMessage() {
        return message;
    }


}
