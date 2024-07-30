package authorization.dto;

import org.springframework.http.HttpStatus;

public record AuthMessage(HttpStatus status, String message) {
}
