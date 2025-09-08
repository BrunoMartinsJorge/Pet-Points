package br.com.petpoints.back.shared.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class StandardException {
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String path;
}
