package ar.edu.utn.dds.k3003.exceptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
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
        } else if (ex.getMessage()!= null && mensajeDeNotFound(ex)){
            status = HttpStatus.NOT_FOUND;
        } else{
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(status).body(ex.getMessage());
    }

    private Boolean mensajeDeNotFound(RuntimeException ex){
        return 
            ex.getMessage().toLowerCase().contains("no existe") || 
            ex.getMessage().toLowerCase().contains("inexistente") ||
            ex.getMessage().toLowerCase().contains("404")||
            ex.getMessage().toLowerCase().contains("not_found");
    }

    public ResponseStatusException manejarThrowExceptionConTCausa(RuntimeException ex) {
        HttpStatus status;

        if (ex.getCause() instanceof IllegalArgumentException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex.getCause() instanceof NullPointerException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (ex.getCause() instanceof SecurityException) {
            status = HttpStatus.FORBIDDEN;
        } else if (ex.getMessage()!= null && mensajeDeNotFound(ex)){
            status = HttpStatus.NOT_FOUND;
        } else{
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseStatusException(status , ex.getMessage(),ex);
    }

    public ResponseStatusException manejarThrowException(RuntimeException ex) {
        HttpStatus status;

        if (ex.getCause() instanceof IllegalArgumentException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex.getCause() instanceof NullPointerException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (ex.getCause() instanceof SecurityException) {
            status = HttpStatus.FORBIDDEN;
        } else if (ex.getMessage()!= null && mensajeDeNotFound(ex)){
            status = HttpStatus.NOT_FOUND;
        } else{
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseStatusException(status , ex.getMessage());
    }
}