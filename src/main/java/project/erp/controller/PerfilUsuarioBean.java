package project.erp.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class PerfilUsuarioBean {
	
	private String login;
	private String nome;
	
	public void tempoEspera() {
		// Simula demora no processamento
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

	}
	
	public void verificarDisponibilidadelogin() {

		FacesMessage msg = null;

		tempoEspera();

		if ("João".equalsIgnoreCase(this.login)) {
			msg = new FacesMessage("Login já em uso!");
			msg.setSeverity(FacesMessage.SEVERITY_WARN);
		} else {
			msg = new FacesMessage("Login Dísponivel!");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void cadastrar() {

		if ("João".equalsIgnoreCase(this.login)) {
			verificarDisponibilidadelogin();
		} else {
			tempoEspera();
			System.out.println("Login: " + this.login);
			System.out.println("Nome: " + this.nome);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastro Efetuado"));
		}
	}
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
