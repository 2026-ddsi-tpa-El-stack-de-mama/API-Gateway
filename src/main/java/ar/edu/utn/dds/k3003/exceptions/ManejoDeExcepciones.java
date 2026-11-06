package ar.edu.utn.dds.k3003.exceptions;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

public class ManejoDeExcepciones {
    public ResponseEntity<String> manejarRuntimeException(RuntimeException ex) {
        HttpStatus status;

        if (ex.getCause() instanceof IllegalArgumentException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex.getCause() instanceof NullPointerException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (ex.getCause() instanceof SecurityException) {
            status = HttpStatus.FORBIDDEN;
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity.status(status).body(ex.getMessage());
    }
}