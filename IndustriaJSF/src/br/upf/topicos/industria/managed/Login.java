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
    
    RequestContext context = RequestContext.getCurrentInstance();
    FacesMessage message = null;
    
    // validação total
    public void login(){
    	rn06();
    	rn03();
    	rn02();
    	
    	if(validaUser == 2 && validaPassword == 2){
    		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem Vindo", username);
    	}
    }

    // RN_06 – Tamanho campo Usuário
    public void rn06() {
        if(username.length() > 5 && username.length() < 13){
        	validaUser = validaUser ++;
        }else{
        	message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro de Usuário", "O tamanho do campo Usuário deve conter de 6 a 12 caracteres!");
        }
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
    
    // RN_02 – Formato de senha - tamanho da senha
    public void rn02() {
    	if(password.length() > 5 && password.length() < 13){
    		validaPassword = validaPassword ++;
    	}else{
    		message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro de Senha", "O tamanho do campo Senha deve conter de 6 a 12 caracteres!");
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
	
	public RequestContext getContext() {
		return context;
	}
	
	public void setContext(RequestContext context) {
		this.context = context;
	}
	
	public FacesMessage getMessage() {
		return message;
	}
	
	public void setMessage(FacesMessage message) {
		this.message = message;
	}
}