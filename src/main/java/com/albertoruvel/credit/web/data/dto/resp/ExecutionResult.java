package com.albertoruvel.credit.web.data.dto.resp;

public class ExecutionResult {
    private String message;
    private boolean ok;

    public ExecutionResult(String message, boolean ok) {
        this.message = message;
        this.ok = ok;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}
