package com.albertoruvel.credit.web.data.dto.resp;

public class TokenValidationResult {
    private boolean validl;
    private String message;

    public TokenValidationResult(boolean validl, String message) {
        this.validl = validl;
        this.message = message;
    }

    public boolean isValidl() {
        return validl;
    }

    public void setValidl(boolean validl) {
        this.validl = validl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
