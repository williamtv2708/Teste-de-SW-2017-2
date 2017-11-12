package br.upf.topicos.industria.managed;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@ManagedBean
public class Cadastro {

	public String funcName;
	public Date date;
	public Date dateInit;
	public Date dateEnd;
	FacesMessage message = null;
	
	public void enviar(ActionEvent actionEvent) {
		clickDate();
    }
	
	public void avancar(ActionEvent actionEvent) {
		clickDate();
	}
     
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void addMessageError(String title, String msg) {
    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
    	FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public DateFormat validaDateFerias() throws ParseException{
		return null;
	}
	
	public DateFormat maxDate(){
		// validar aqui a data máxima, referente a data inicial.
		if(dateInit.before(dateEnd)){
			// a data da escala é no mínimo a atual
			// a data inicial é maior que a data da escala
			// a data final é maior que a data inicial
			
			// contador de dias
			DateFormat df = new SimpleDateFormat ("dd/MM/yyyy");
			df.setLenient(false);
			Date d1 = null;
			Date d2 = null; 
			try {
				d1 = df.parse ("dateInit");
				d2 = df.parse ("dateEnd");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			long dt = (d2.getTime() - d1.getTime()) + 3600000; // 1 hora para compensar horário de verão
			if(dt / 86400000L < 32){
				// a diferença entre as datas é menor que 32 dias
				// aprovar aqui o próximo passo a validação final
				return df;
			}else{
				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro entre as datas de férias", "A diferença ultrapassa 31 dias.");
			}
		}else{
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro na Final Inicial de Férias", "A data final deve ser maior que a data inicial.");
		}
		
		return null;
	}
	
	// RF_06 - Validação data do Cadastro de Funcionário
	public Date atualDate(){
		// pega a data atual
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		formatador.format(data);
		return data;
	}
	
	public Date dateInit(){
		// pegar a data da escala e setar como mínima pra férias
		return getDate();
	}
	
	public Date dateInitEnd(){
		// pegar a data da escala e setar como mínima pra férias
		return getDateInit();
	}
	
	public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data Selecionada", format.format(event.getObject())));
    }
	
    public void clickDate() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("form:mesAno");
    }
    
    public void clickDateInit() {
    	RequestContext requestContext = RequestContext.getCurrentInstance();
    	requestContext.update("form:initFerias");
    }
    
    public void clickDateEnd() {
    	RequestContext requestContext = RequestContext.getCurrentInstance();
    	requestContext.update("form:endFerias");
    }
	
	public String getFuncName() {
		return funcName;
	}
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	public FacesMessage getMessage() {
		return message;
	}
	public void setMessage(FacesMessage message) {
		this.message = message;
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
}