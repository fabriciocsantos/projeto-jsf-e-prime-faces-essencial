package project.erp.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import project.erp.util.FacesMessages;

@Named
@ViewScoped
public class PerfilUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesMessages messages;

	private String login;
	private String senha;

	FacesMessage msg = null;

	public void tempoEspera() {
		// Simula demora no processamento
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

	}

	public void verificarDisponibilidadeLogin() {
		tempoEspera();

		if ("Admin".equalsIgnoreCase(this.login)) {
			msg = new FacesMessage("Login correto.");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
		} else {
			msg = new FacesMessage("Login incorreto.");
			msg.setSeverity(FacesMessage.SEVERITY_WARN);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void cadastrar() {
		tempoEspera();

		if ("Admin".equalsIgnoreCase(this.login)) {
			if ("Admin".equalsIgnoreCase(this.senha)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Acesso Permitido!"));
				System.out.println("Login: " + this.login + " ----- " + "Senha: " + this.senha + "--PERMITIDO--");
			} else {
				FacesContext.getCurrentInstance().addMessage(null, msg = new FacesMessage("Credenciais incorretas!"));
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				System.out.println("Login: " + this.login + " ----- " + "Senha: " + this.senha + "--NEGADO--");
			}
		}else {
			msg = new FacesMessage("Login incorreto!");
			msg.setSeverity(FacesMessage.SEVERITY_WARN);
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public FacesMessages getMessages() {
		return messages;
	}

	public void setMessages(FacesMessages messages) {
		this.messages = messages;
	}

}