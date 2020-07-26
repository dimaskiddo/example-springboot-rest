package id.dimaskiddo.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class Responses {

    public ResponseEntity<String> responseSuccess(String msg) {
        if (msg.length() == 0) {
            msg = "Success";
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body("{\"status\": " + HttpStatus.OK.value()
                        + ", \"message\": \""+ msg +"\"}");
    }

    public ResponseEntity<String> responseSuccessWithData(Object data) {
        try {
            ObjectMapper objMap = new ObjectMapper();
            String jsonStr = objMap.writeValueAsString(data);

            return ResponseEntity.status(HttpStatus.OK)
                    .body("{\"status\": " +  HttpStatus.OK.value()
                            + ", \"message\": \"Success\", \"data\": " + jsonStr + "}");
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Failed Retriving Data"
            );
        }
    }

    public ResponseEntity<String> responseCreated() {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("{\"status\": " +  HttpStatus.CREATED.value()
                        +", \"message\": \"Created\"}");
    }

    public ResponseEntity<String> responseUpdated() {
        return ResponseEntity.status(HttpStatus.OK)
                .body("{\"status\": " + HttpStatus.OK.value()
                        +", \"message\": \"Updated\"}");
    }
}
