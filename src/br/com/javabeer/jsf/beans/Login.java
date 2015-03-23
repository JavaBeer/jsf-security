package br.com.javabeer.jsf.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.javabeer.jsf.dao.LoginDAO;

@ManagedBean(name="login")
@SessionScoped
public class Login{
	
	private String usuario;
	private String senha;
	private String mensagem;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public String validarUsuarioESenha() {
		boolean validar = LoginDAO.validar(usuario, senha);
		if (validar) {
			HttpSession session = SessionBean.getSession();
			session.setAttribute("usuario", usuario);
			return "inicio";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Usuário e Senha Invalidos",
							"Por favor entre com dados válidos"));
			return "login";
		}
	}
	
	public String logout(){
		HttpSession session  = SessionBean.getSession();
		session.invalidate();
		return "login";
	}
	
}
