package br.upf.topicos.industria.managed;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
 
@ManagedBean
public class CalendarView {
	private Date date;
	
	public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data Selecionada", format.format(event.getObject())));
    }
     
	public Date atualDate(){
		// RF_06 - Validação data do Cadastro de Funcionário
		// pegar a data atual aqui e retornar
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		formatador.format( data );
		return data;
	}
	
	public void forMonthDate(){
		// RF_06 - Validação data do Cadastro de Funcionário
		// pegar quatro meses a frente da data atual aqui e retornar
		
	}
	
    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
         
        requestContext.update("form:ferias");
        requestContext.execute("PF('dlg').show()");
    }
	
	public Date getDate() {
        return date;
    }
 
    public void setDate(Date date) {
        this.date = date;
    }
}