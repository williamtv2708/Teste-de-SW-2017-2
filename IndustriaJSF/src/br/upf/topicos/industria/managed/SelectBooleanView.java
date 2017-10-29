package br.upf.topicos.industria.managed;

import javax.faces.bean.ManagedBean;

 
@ManagedBean
public class SelectBooleanView {
 
    private boolean value;  
 
    public boolean isValue() {
        return value;
    }
 
    public void setValue(boolean value) {
        this.value = value;
    }
}