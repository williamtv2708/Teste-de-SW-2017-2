package br.upf.topicos.industria.managed;

import java.util.Date;

import javax.faces.bean.ManagedBean;
 
@ManagedBean
public class UserView {
     
	public Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}