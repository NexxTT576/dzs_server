package com.mdy.sharp.container.invoker;

import java.io.Serializable;

public class ActionSession implements Serializable {
    private static final long serialVersionUID = 4615070485377539422L;
    private String sessionId;
    private String principal;

    public String getPrincipal() {
        return this.principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public String toString() {
        return "ActionSession [sessionId=" + this.sessionId + ", principal=" + this.principal + "]";
    }
}
