package com.k001.selfgorwth.oauth.payload;

public class JwtAuthenticationResponse {
    private String accessToken;
    private String tockenType = "Bearer";

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTockenType() {
        return tockenType;
    }

    public void setTockenType(String tockenType) {
        this.tockenType = tockenType;
    }
}
