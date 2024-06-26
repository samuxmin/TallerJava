//package org.tallerjava.moduloGestionCliente.interfase.seguridad.identitystore.basedatos;
package org.tallerjava.moduloGestionCliente.infraestructura.seguridad.identitystore.basedatos;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.tallerjava.moduloGestionCliente.dominio.clases.Usuario;
import org.tallerjava.moduloGestionCliente.infraestructura.seguridad.HashFunctionUtil;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;

@ApplicationScoped
//@Vetoed
public class ValidadorCredencialesDB implements IdentityStore{
	@PersistenceContext
	private EntityManager em;

	@Override
	public CredentialValidationResult validate(Credential credential) {
		System.out.println("** IdentityStore en base de datos");
		CredentialValidationResult resultado = CredentialValidationResult.INVALID_RESULT;
		
		UsernamePasswordCredential credencial = (UsernamePasswordCredential)credential;
		String usr = credencial.getCaller();
		String pass = credencial.getPasswordAsString();
		Usuario usuario = findUsuario(usr);
		if (usuario != null) {
			System.out.println("encontre usuario: " + usuario.getCi());
			if (HashFunctionUtil.convertToHas(pass).equals(usuario.getPassword())) {
				System.out.println("contraseña correcta");
				//al proceso de autorización le interasa saber los grupos a los que pertenece el usuari
				List<String> grupos = new ArrayList<>();
				grupos.add("usuario");
				if(usuario.getCi().equals("adminCI")){
					grupos.add("admin");
				}
				System.out.println("grupos: " + grupos);
				resultado =  new CredentialValidationResult
						(usr, new HashSet<>(grupos));
			} else {
				System.out.println("password incorrecta");
			}
		} else {
			
			System.out.println("No existe usuario.");
		}
		System.out.println("resultado: " + resultado.toString());

		return resultado;
	
	}
	
	private Usuario findUsuario(String ci) {
		return em.find(Usuario.class, ci);
	}
	
}
