package br.upf.topicos.industria.managed;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
 
import org.primefaces.context.RequestContext;

@ManagedBean
public class Login {
     
    private String username;
     
    private String password;
 
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
   
    public void login(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = false;
        
        // RN_06 – Tamanho campo Usuário
        if(username.length() > 5 && username.length() < 13){
        	loggedIn = true;
        	
        }else{
        	loggedIn = false;
        	message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro de Usuário", 
        			"O tamanho do campo Usuário deve conter de 6 a 12 caracteres!");
        }
        
        if(username != null && username.equals("usuario") && password != null && password.equals("william20")) {
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem Vindo", username);
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
        }
         
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);
    }

    // RN_07 - Formato campo Usuário
    public void validaUser(FacesContext context, UIComponent toValidate, Object value) {
    	FacesMessage message = null;
        boolean valida = false;
        if(value != null){      
           for (char letra : ((String) value).toCharArray()) { 
              if(letra < '0' || letra > '9') { 
                 valida = true;
                 break; 
            }  
         }
         ((UIInput) toValidate).setValid(!valida);
        message = new FacesMessage("O Campo Usuário deve conter apenas letras!");
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        context.addMessage(toValidate.getClientId(context), message);
        }
    }
}