package com.tortaza.app.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.tortaza.app.helper.Message;
import com.tortaza.app.models.Rol;
import com.tortaza.app.models.Usuario;

import com.tortaza.app.services.IUsuarios;
import com.tortaza.app.services.IUsuariosService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@Service
public class UsuarioService implements IUsuariosService, UserDetailsService {

    @Autowired
    private IUsuarios dato;

    @Override
    public List<Usuario> listar() {
        return (List<Usuario>) dato.findAll();
    }

    @Override
    public boolean validU(Usuario u) {
        boolean ins = false;
        for (var us : dato.findAll()) {
            if (u.getCorreo().equals(us.getCorreo())) {
            	PasswordEncoder password=new BCryptPasswordEncoder();
            	ins=password.matches(u.getContrasena(), us.getContrasena());
                return ins;
            }
        }
        return ins;
    }

    @Override
    public int guardar(Usuario u) {
    	int res =0;
    	PasswordEncoder password=new BCryptPasswordEncoder();
		String passwordCifrado=password.encode(u.getContrasena());
        // aqui se guarda al usuario
        u.setContrasena(passwordCifrado);
        System.out.println(passwordCifrado);
        Usuario usuario = dato.save(u);
        if (!usuario.equals(null)) {
            res = 1;
        }
        return res;

    }
    public Model verificarCorreo(Usuario us,Model model) {

		for (Usuario usuario : dato.findAll()) {
			if (usuario.getCorreo().equals(us.getCorreo())) {
				model.addAttribute("emailerror", new Message("El correo ya existe ", "success"));

			}
			if (usuario.getDni().equals(us.getDni())) {
				model.addAttribute("dnierror", new Message("El dni ya existe ", "success"));
			}
		}
		return model;
	}

	public boolean verificarDni(Usuario us) {

		boolean a = false;
		for (Usuario usuario : dato.findAll()) {
			if (usuario.getDni().equals(us.getDni())) {
				a = true;
			}
			

		}
		return a;
	}
   

    @Override
    public void eliminar(int id) {
        // TODO Auto-generated method stub

    }

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Integer id) {
		// TODO Auto-generated method stub
		return dato.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Usuario findByCorreo(String correo) {
		return dato.findByCorreo(correo);
	}
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = findByCorreo(username);
		Collection<GrantedAuthority> roles = new ArrayList<>();
		
		for (Rol rol : usuario.getItemsRoles()) {
			roles.add(new SimpleGrantedAuthority("ROLE_"+rol.getRol()));
		}
		
		if (usuario!=null) {
			User user=new User(username,usuario.getContrasena(),roles);
			return user;
		}
		
		throw new UsernameNotFoundException("!Usuario no encontrado??");
	}

}
