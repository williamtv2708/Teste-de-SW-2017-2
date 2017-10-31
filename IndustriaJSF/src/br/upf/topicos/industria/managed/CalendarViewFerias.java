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
	public Date dateInit;
	public Date dateEnd;
	
	public Date minDate(){
		// RF_03 - Data inicial de férias
		//verificar a data da escala e setar que nao pode ser menor que ela pra retornar na validação deste campo no cadastro
		
	}
	
	public Date atualDate(){
		// RF_03 - Data inicial de férias
		// pegar a data atual aqui
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		formatador.format( data );
		return data;
	}
	
	public Date maxDate(){
		// RF_04 - Data final de férias
		//verificar a data minDate() e setar que nao pode ser maior que 31 dias pra retornar na validação deste campo no cadastro
		
	}
	
	public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data Selecionada", format.format(event.getObject())));
    }
     
    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
         
        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }

	public Date getDateInit() {
		return dateInit;
	}

	public void setDateInit(Date dateInit) {
		this.dateInit = dateInit;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
}