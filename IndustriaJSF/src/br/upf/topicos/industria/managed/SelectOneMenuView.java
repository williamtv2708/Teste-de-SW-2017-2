package br.upf.topicos.industria.managed;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
 
@ManagedBean
public class SelectOneMenuView {
     
	private String option;  
    private List<String> options;
    FacesMessage message = null;
    
    @PostConstruct
    public void init() {
    	//options
    	// pegar o valor selecionado
        options = new ArrayList<String>();
        for(int i = 0; i < 7; i++) {
        	if(i <= 1){
        		options.add(i + " Dia");
        	}else{
        		options.add(i + " Dias");
        	}
        	// testar o valor selecionado pra mostrar a mensagem de alerta de selecao menor que 3
        	if(i < 4){
        		message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", "Deseja selecionar um valor para folgas menor que 4?");
        	}
        }
    }

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}
}