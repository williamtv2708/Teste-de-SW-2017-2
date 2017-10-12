package br.upf.topicos.industria.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import br.upf.topicos.industria.entidades.Grupo;
import br.upf.topicos.industria.util.GerirFactory;

@FacesConverter(value = "grupoConverter")
public class GrupoConverter implements Converter {
	@Override
	public Grupo getAsObject(FacesContext fc, UIComponent uic, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				EntityManager em = GerirFactory.getEntityManager();
				Grupo ret = em.find(Grupo.class, Integer.parseInt(value));
				em.close();
				return ret;
			} catch (NumberFormatException e) {
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Erro de Conversão da Gruoi", "Grupo inválido."));
			}
		} else
			return null;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return String.valueOf(((Grupo) object).getId());
		} else
			return null;
	}
}