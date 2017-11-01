package br.upf.topicos.industria.managed;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
 
@ManagedBean
public class SelectOneMenuView {
     
    private String console;
   
    @PostConstruct
    public void init() {
        // itens da selecao
    	SelectItemGroup g0 = new SelectItemGroup("0");
    	g0.setSelectItems(new SelectItem[] {new SelectItem("BMW", "BMW")});
    	
    	SelectItemGroup g1 = new SelectItemGroup("1");
    	g1.setSelectItems(new SelectItem[] {new SelectItem("Chrysler", "Chrysler")});
    	
    	SelectItemGroup g2 = new SelectItemGroup("2");
    	g2.setSelectItems(new SelectItem[] {new SelectItem("BMW", "BMW")});
    	
    	SelectItemGroup g3 = new SelectItemGroup("3");
    	g3.setSelectItems(new SelectItem[] {new SelectItem("Chrysler", "Chrysler")});
    	
    	SelectItemGroup g4 = new SelectItemGroup("4");
    	g4.setSelectItems(new SelectItem[] {new SelectItem("BMW", "BMW")});
    	
    	SelectItemGroup g5 = new SelectItemGroup("5");
    	g5.setSelectItems(new SelectItem[] {new SelectItem("Chrysler", "Chrysler")});

    	SelectItemGroup g6 = new SelectItemGroup("6");
    	g6.setSelectItems(new SelectItem[] {new SelectItem("Chrysler", "Chrysler")});
        
    	// ver como faz pra pegar o valor selecionado
    	
    	// testar o valor selecionado pra mostrar a mensagem de alerta de selecao menor q 3
    	
    }
 
    public String getConsole() {
        return console;
    }
 
    public void setConsole(String console) {
        this.console = console;
    }
}