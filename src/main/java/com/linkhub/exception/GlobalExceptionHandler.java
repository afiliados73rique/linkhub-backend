package com.linkhub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Captura falhas de validação do @Valid (ex: @NotBlank, @Positive)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> tratarErroValidacao(MethodArgumentNotValidException ex) {
        Map<String, String> detalhes = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(erro ->
                detalhes.put(erro.getField(), erro.getDefaultMessage())
        );

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("erro", "Dados inválidos");
        resposta.put("detalhes", detalhes);

        return ResponseEntity.badRequest().body(resposta);
    }

    // Captura o 404 que você já lança no Service (buscarPorId)
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> tratarErroStatus(ResponseStatusException ex) {
        Map<String, String> resposta = new HashMap<>();
        resposta.put("erro", ex.getReason());

        return ResponseEntity.status(ex.getStatusCode()).body(resposta);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, String>> tratarCredenciaisInvalidas(BadCredentialsException ex) {
        Map<String, String> resposta = new HashMap<>();
        resposta.put("erro", "Usuário ou senha inválidos");

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resposta);
    }
}