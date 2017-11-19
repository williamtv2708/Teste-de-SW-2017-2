package br.upf.topicos.industria.managed;

import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
 
@ManagedBean
public class Login {
     
    private String username;
    private String password;
    
    FacesMessage message = null;
    
    public void erroKey() {
    	message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao fazer tentar Login", "Usuário Inválido");
    }
    
    public void erroUser() {
    	message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao fazer tentar Login", "Senha Inválida");
    }
    
    public String getUsername() {
    	return username;
    }
    
    public void setUsername(String username) {
    	this.username = username;
    }
    
    public String getPassword() {
    	return password;
    }
    
    public void setPassword(String password) {
    	this.password = password;
    }
    
	public FacesMessage getMessage() {
		return message;
	}
	
	public void setMessage(FacesMessage message) {
		this.message = message;
	}
}