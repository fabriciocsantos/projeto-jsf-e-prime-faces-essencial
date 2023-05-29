package project.erp.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class PerfilUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

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
		
		if ("joao".equalsIgnoreCase(this.login)) {
			msg = new FacesMessage("Login já está em uso.");
			msg.setSeverity(FacesMessage.SEVERITY_WARN);
		} else {
			msg = new FacesMessage("Login disponível.");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	
	public void cadastrar() {	
		verificarDisponibilidadeLogin();
		
		if ("joao".equalsIgnoreCase(this.login)) {
			msg = new FacesMessage("Login já está em uso.");
			msg.setSeverity(FacesMessage.SEVERITY_WARN);
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastro efetuado!"));
			System.out.println("Login: " + this.login + " ----- " + "Senha: " + this.senha);
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

}