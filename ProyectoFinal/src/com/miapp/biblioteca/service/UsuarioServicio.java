package com.miapp.biblioteca.service;

import java.util.ArrayList;
import java.util.Iterator;

import com.miapp.bibilioteca.Libro;
import com.miapp.bibilioteca.Usuario;

public class UsuarioServicio {
	
	private ArrayList<Usuario> usuarios;
	
    public UsuarioServicio(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
	
    public void crearUsuario(String nombre, String id) {
        Usuario nuevoUsuario = new Usuario(nombre, id);
        usuarios.add(nuevoUsuario);
    }
    
	public ArrayList<Usuario> obtenerTodosLosUsuarios(){
		return usuarios;
	}
	
	public void actualizarUsuario(String id, String nuevoNombre) {
		for (Usuario usuario : usuarios) {
			if (usuario.getId().equals(id)) {
				usuario.setNombre(nuevoNombre);
				return;
			}
		}
	}
	
	public void eliminarUsuarios(String id) {
		Iterator<Usuario> it = usuarios.iterator();
		
		while (it.hasNext()) {
			Usuario usuario = it.next();
			if (usuario.getId().equals(id)) {
				if (usuario.getLibrosPrestados().isEmpty()) {
					it.remove();
					System.out.println("Usuario eliminado exitosamente");
				}else {
					
					System.out.println("El usuario tiene libros prestados, no puede ser eliminado.");
				}
				return;
			}
		}
		System.out.println("No se encontro ningun usuario con ese ID.");
	}
	
    public void prestarLibro(Usuario usuario, Libro libro) {
        usuario.solicitarPrestamo(libro);
    }

    public void devolverLibro(Usuario usuario, Libro libro) {
        usuario.devolverLibro(libro);
    }

}
