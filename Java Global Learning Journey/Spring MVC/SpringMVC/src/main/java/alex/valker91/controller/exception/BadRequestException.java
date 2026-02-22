package alex.valker91.controller.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class BadRequestException extends RuntimeException {

    private final List<String> errors;

    public BadRequestException(List<String> errors) {
        this.errors = errors;
    }
}
