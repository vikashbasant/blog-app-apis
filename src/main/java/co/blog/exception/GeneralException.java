package co.blog.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GeneralException extends Exception{
    private String statusCode;
    private String message;
    private transient Object errorMessages;

    public GeneralException (String errorCode, String message, String errorMessages){
        this.statusCode = errorCode;
        this.message = message;
        this.errorMessages = errorMessages;
    }


    public GeneralException (String errorCode, String message){
        this.statusCode = errorCode;
        this.message = message;
    }

}
