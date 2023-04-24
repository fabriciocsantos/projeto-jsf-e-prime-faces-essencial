package project.erp.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import project.erp.model.Empresa;
import project.erp.model.RamoAtividade;
import project.erp.model.TipoEmpresa;
import project.erp.repository.Empresas;
import project.erp.repository.RamoAtividades;
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
    
    private List<Empresa> listaEmpresas;
    
    private String termoPesquisa;
    
    private Converter ramoAtividadeConverter;
    
    public void pesquisar(){
    	listaEmpresas = empresas.pesquisar(termoPesquisa);
    	if(listaEmpresas.isEmpty()) {
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

    
    
}