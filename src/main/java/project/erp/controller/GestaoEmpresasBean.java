package project.erp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import project.erp.model.Empresa;
import project.erp.model.RamoAtividade;
import project.erp.model.TipoEmpresa;
import project.erp.repository.Empresas;
import project.erp.repository.RamoAtividades;
import project.erp.service.CadastroEmpresaService;
import project.erp.util.FacesMessages;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Empresas empresas;

	@Inject
	private FacesMessages messages;

	@Inject
	private RamoAtividades ramoAtividades;

	@Inject
	private CadastroEmpresaService cadastroEmpresasService;

	private List<Empresa> listaEmpresas;

	private String termoPesquisa;

	private Converter ramoAtividadeConverter;

	private Empresa empresa;
	
	public static final List<Interesse> INTERESSES = new ArrayList<>();
	
	static {
		INTERESSES.add(new Interesse("Esportes", "esportes"));
		INTERESSES.add(new Interesse("Computação", "computacao"));
		INTERESSES.add(new Interesse("Cinema", "cinema"));
		INTERESSES.add(new Interesse("Leitura", "leitura"));
	}
	
	private String nome;
	private String profissao;
	private Interesse interesse;

	public void prepararNovaEmpresa() {
		empresa = new Empresa();
	}

	public void prepararEdicao() {
		ramoAtividadeConverter = new RamoAtividadeConverter(Arrays.asList(empresa.getRamoAtividade()));
	}

	public void salvar() {
		cadastroEmpresasService.salvar(empresa);

		atualizarRegistros();

		messages.info("Empresas salva com sucesso!");

		PrimeFaces.current().ajax().update("frm:empresasDataTable", "frm:messages");

	}

	public void excluir() {
		cadastroEmpresasService.excluir(empresa);

		empresa = null;

		atualizarRegistros();

		messages.info("Empresa excluída com sucesso");

	}

	public void pesquisar() {
		listaEmpresas = empresas.pesquisar(termoPesquisa);
		if (listaEmpresas.isEmpty()) {
			messages.info("Sua consulta não retornou registros");
		}
	}

	@PostConstruct
	public void listenerPreRenderView() {
		listaEmpresas = empresas.todas();
		System.out.println("--Funcionando--");
	}

	public void todasEmpresas() {
		listaEmpresas = empresas.todas();
	}

	public List<RamoAtividade> completarRamoAtividade(String termo) {
		List<RamoAtividade> listaRamoAtividades = ramoAtividades.pesquisar(termo);

		ramoAtividadeConverter = new RamoAtividadeConverter(listaRamoAtividades);

		return listaRamoAtividades;
	}

	public void atualizarRegistros() {
		if (jaHouvePesquisa()) {
			pesquisar();
		} else {
			listenerPreRenderView();
		}

	}

	public void tempoEspera() {
		// Simula demora no processamento
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

	}

	private boolean jaHouvePesquisa() {
		return termoPesquisa != null && !"".equals(termoPesquisa);
	}
	
	public void atualizar() {
		System.out.println("Profissão: " + this.profissao);
		System.out.println("Interesse: " + this.interesse.getDescricao());
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Perfil atualizado!"));
	};
	
	public List<Interesse> getInteresses(){
		return INTERESSES;
	};
	
	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}

	public Converter getRamoAtividadeConverter() {
		return ramoAtividadeConverter;
	}

	public void setRamoAtividadeConverter(Converter ramoAtividadeConverter) {
		this.ramoAtividadeConverter = ramoAtividadeConverter;
	}

	public TipoEmpresa[] getTiposEmpresa() {
		return TipoEmpresa.values();
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public boolean isEmpresaSelecionada() {
		return empresa != null && empresa.getId() != null;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public Interesse getInteresse() {
		return interesse;
	}

	public void setInteresse(Interesse interesse) {
		this.interesse = interesse;
	}


}