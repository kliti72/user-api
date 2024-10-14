package net.bcsoft.bcosft.exception;

public class PasswordNotCorrectException extends RuntimeException{

    @Override
    public String getMessage() {
        return "Password non corretta";
    }
}
