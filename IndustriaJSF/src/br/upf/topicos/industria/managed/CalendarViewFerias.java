package br.upf.topicos.industria.managed;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
 
@ManagedBean
public class CalendarViewFerias {
	private Date date;
	
	public void minDate(){
		// RF_03 - Data inicial de férias
		//verificar a data da escala e setar que nao pode ser menor que ela pra retornar na validação deste campo no cadastro
		
	}
	
	public void maxDate(){
		// RF_04 - Data final de férias
		//verificar a data minDate() e setar que nao pode ser maior que 31 dias pra retornar na validação deste campo no cadastro
		
	}
	
	public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
     
    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
         
        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }
	
	public Date getDate() {
        return date;
    }
 
    public void setDate(Date date) {
        this.date = date;
    }
}