package pk.imbilalbutt.bussiness.dto.response;

import java.io.Serializable;

public class ResponseDTO implements Serializable {

    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
