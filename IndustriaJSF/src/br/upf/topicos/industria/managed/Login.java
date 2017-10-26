package br.upf.topicos.industria.managed;

import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
 
import org.primefaces.context.RequestContext;

@ManagedBean
public class Login {
     
    private String username;
    private String password;
    public int validaUser = 0;
    public int validaPassword = 0;
    
//    RequestContext context = RequestContext.getCurrentInstance();
    FacesMessage message = null;
    
    // validação total
    public void login(){
    	rn03();
    }

    // RN_03 – Usuário Cadastrado previamente
    public void rn03() {
    	if(username != null && username.equals("usuario")) {
    		validaUser = validaUser ++;
    	} else {
    		message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro ao fazer tentar Login", "Usuário Inválido");
    	}
    	if(password != null && password.equals("william20")) {
    		validaPassword = validaPassword ++;
    	} else {
    		message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro ao fazer tentar Login", "Senha Inválida");
    	}
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
    
	public int getValidaUser() {
		return validaUser;
	}
	
	public void setValidaUser(int validaUser) {
		this.validaUser = validaUser;
	}
	
	public int getValidaPassword() {
		return validaPassword;
	}
	
	public void setValidaPassword(int validaPassword) {
		this.validaPassword = validaPassword;
	}
	
	public FacesMessage getMessage() {
		return message;
	}
	
	public void setMessage(FacesMessage message) {
		this.message = message;
	}
}