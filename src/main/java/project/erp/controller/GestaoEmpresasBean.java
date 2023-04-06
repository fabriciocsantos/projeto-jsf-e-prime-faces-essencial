package project.erp.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import project.erp.model.Empresa;
import project.erp.model.TipoEmpresa;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Empresa empresa = new Empresa();
    
    public void salvar() {
    	System.out.println("Razão Social: " + empresa.getRazaoSocial()
    			+ " - Nome Fantasia: " + empresa.getNomeFantasia()
    			+ " - Tipo: " + empresa.getTipo());
    }
    
    public Empresa getEmpresa() {
		return empresa;
	} 
    
    public TipoEmpresa[]getTiposEmpresa(){
    	return TipoEmpresa.values();
    }
}