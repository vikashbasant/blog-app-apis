package co.blog.payloads;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response implements Serializable {
    private static final long serialVersionUID = 7104462920542626419L;
    private String status;
    private String statusCode;
    private String message;
    private transient Object data;
    private Object errorMessage;
    private String successRule;
    @JsonProperty("response_message")
    private String responseMessage = "Request Processed Successfully";
    @JsonProperty("response_type")
    private String responseType;

}
