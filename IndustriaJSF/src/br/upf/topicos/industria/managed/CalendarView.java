package br.upf.topicos.industria.managed;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
 
@ManagedBean
public class CalendarView {
	public Date date;
	public Date dateInit;
	public Date dateEnd;
	
	FacesMessage message = null;
	
	public DateFormat validaDateFerias() throws ParseException{
		// RF_03 - Data inicial de férias
		//verificar a data da escala e setar que nao pode ser menor que ela pra retornar na validação deste campo no cadastro
		// RF_04 - Data final de férias
		//verificar a data minDate() e setar que nao pode ser maior que 31 dias pra retornar na validação deste campo no cadastro
		if(dateInit.after(date)){
			if(dateInit.before(dateEnd)){
				// a data da escala é no mínimo a atual
				// a data inicial é maior que a data da escala
				// a data final é maior que a data inicial
				
				// contador de dias
				DateFormat df = new SimpleDateFormat ("dd/MM/yyyy");
				df.setLenient(false);
				Date d1 = df.parse ("dateInit");
//				System.out.println (d1);
				Date d2 = df.parse ("dateEnd");
//				System.out.println (d2);
				long dt = (d2.getTime() - d1.getTime()) + 3600000; // 1 hora para compensar horário de verão
				if(dt / 86400000L < 32){
					// a diferença entre as datas é menor que 32 dias
//					System.out.println (dt / 86400000L); // passaram-se 67111 dias
					return df;
				}else{
					message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro entre as datas de férias", "A diferença ultrapassa 31 dias.");
				}
			}else{
				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro na Final Inicial de Férias", "A data final deve ser maior que a data inicial.");
			}
		}else{
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro na Data Inicial de Férias", "A data inicial deve ser maior ou igual que a data da escala.");
		}
		return null;
	}
	
	
	public Date maxDate(){
		return null;
	}
	
	public Date atualDate(){
		// RF_06 - Validação data do Cadastro de Funcionário
		// pegar a data atual aqui
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("MM/yyyy");
		formatador.format( data );
		return data;
	}
	
	public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data Selecionada", format.format(event.getObject())));
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
	
	public FacesMessage getMessage() {
		return message;
	}
	
	public void setMessage(FacesMessage message) {
		this.message = message;
	}
}