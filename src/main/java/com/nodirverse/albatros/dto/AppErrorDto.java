package com.nodirverse.albatros.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class AppErrorDto {
    private final String friendlyMessage;
    private final Object developerMessage;
    private final String errorPath;
    private final Integer errorCode;

    public AppErrorDto(String friendlyMessage, Integer errorCode) {
        this(friendlyMessage, friendlyMessage, errorCode);
    }

    public AppErrorDto(String friendlyMessage, String errorPath, Integer errorCode) {
        this(friendlyMessage, friendlyMessage, errorPath, errorCode);
    }

    public AppErrorDto(String friendlyMessage, Object developerMessage, String errorPath, Integer errorCode) {
        this.friendlyMessage = friendlyMessage;
        this.developerMessage = developerMessage;
        this.errorPath = errorPath;
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return """
                \n
                *friendlyMessage : %s*
                *errorPath : %s*
                *errorCode : %s*
                *developerMessage* : `%s`
                """.formatted(friendlyMessage, errorPath, errorCode, developerMessage);
    }
}
