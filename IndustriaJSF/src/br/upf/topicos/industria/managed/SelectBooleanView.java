package br.upf.topicos.industria.managed;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

 
@ManagedBean
public class SelectBooleanView {
 
    private boolean value;  
 
    public boolean isValue() {
        return value;
    }
 
    public void setValue(boolean value) {
        this.value = value;
    }
     
    public void activeFerias() {
    	if(value){
    		FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "True", "oi"));
    	}
    }
}