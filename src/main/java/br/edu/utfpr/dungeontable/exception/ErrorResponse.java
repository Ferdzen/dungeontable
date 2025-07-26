package br.edu.utfpr.dungeontable.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
    private Integer code;
    private String codeDescription;
    private String message;
    private String stackTrack;
    private LocalDateTime data;

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setCodeDescription(String codeDescription) {
        this.codeDescription = codeDescription;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStackTrack(String stackTrack) {
        this.stackTrack = stackTrack;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public ErrorResponse(Integer code, String codeDescription, String message, String stackTrack, LocalDateTime data){
        this.code = code;
        this.codeDescription = codeDescription;
        this.message = message;
        this.stackTrack = stackTrack;
        this.data = data;
    }
}
