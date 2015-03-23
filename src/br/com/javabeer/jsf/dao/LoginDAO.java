package br.com.javabeer.jsf.dao;

import org.hibernate.Session;

import br.com.javabeer.jsf.entity.Usuario;
import br.com.javabeer.jsf.utils.HibernateUtil;

public class LoginDAO {
	public static boolean validar(String usuario, String senha) {
		Usuario u = new Usuario();
		Session session = HibernateUtil.getSessionFactory().openSession();
		u = (Usuario) session.createQuery("from Usuario where usuario=:usuario and senha=:senha").setParameter("usuario", usuario).setParameter("senha", senha).uniqueResult();
		System.out.println(usuario + senha);
		if (u == null) {
			return false;
		} else {
			return true;
		}
	}
}
