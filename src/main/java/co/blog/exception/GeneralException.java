package co.blog.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GeneralException extends Exception {

    public GeneralException (String message) {
        super(message);
    }

    public GeneralException (String message, Throwable throwable) {
        super(message, throwable);
    }

}
