package com.example.capstone.DTO;

public class GoogleAuthResponse {
    private String token_type;
    private String access_token;
    private String scope;
    private String login_hint;
    private int expires_in;
    private String id_token;
    private SessionState session_state;
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getLogin_hint() {
		return login_hint;
	}
	public void setLogin_hint(String login_hint) {
		this.login_hint = login_hint;
	}
	public int getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
	public String getId_token() {
		return id_token;
	}
	public void setId_token(String id_token) {
		this.id_token = id_token;
	}
	public SessionState getSession_state() {
		return session_state;
	}
	public void setSession_state(SessionState session_state) {
		this.session_state = session_state;
	}
}




