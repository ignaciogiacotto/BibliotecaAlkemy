package com.miapp.biblioteca.service;

import java.util.ArrayList;
import java.util.Iterator;

import com.miapp.bibilioteca.Libro;
import com.miapp.bibilioteca.Usuario;

public class LibroServicio {
	
	private ArrayList<Libro> biblioteca;
	
	public LibroServicio(ArrayList<Libro> biblioteca) {
		this.biblioteca = biblioteca;
	}
	
	public void crearLibro(String titulo, String autor, String ISBN, String genero) {
		
		Libro nuevoLibro = new Libro(titulo, autor, ISBN, genero, true);
		biblioteca.add(nuevoLibro);
	}
	
	public ArrayList<Libro> obtenerTodosLosLibros(){
		return biblioteca;
	}
	
	public void actualizarLibro(String isbn, String nuevoTitulo, String nuevoAutor, String nuevoGenero) {
	    boolean encontrado = false; // Flag para indicar si se encontró el libro

	   
	    for (Libro libro : biblioteca) {
	        if (libro.getISBN().equals(isbn)) {
	            encontrado = true;
	            break;
	        }
	    }
	    if (!encontrado) {
	        System.out.println("No se encontró ningún libro con el ISBN proporcionado.");
	        return; 
	    }
	    
	    for (Libro libro : biblioteca) {
	        if (libro.getISBN().equals(isbn)) {
	            libro.setTitulo(nuevoTitulo);
	            libro.setAutor(nuevoAutor);
	            libro.setGenero(nuevoGenero);
	            System.out.println("Libro actualizado exitosamente.");
	            break;
	        }
	    }
	}

	public void eliminarLibro(String ISBN){
		Iterator<Libro> it = biblioteca.iterator();
		
		while (it.hasNext()) {
			if (it.next().getISBN().equals(ISBN)) {
				it.remove();
			}
		}
	}
	
	public ArrayList<Libro> buscarLibros (String filtro, String termino){
		ArrayList<Libro> librosEncontrados = new ArrayList<>();
		
		for (Libro libro : biblioteca) {
			if (filtro.equalsIgnoreCase("titulo") && libro.getTitulo().toLowerCase().contains(termino.toLowerCase())) {
				librosEncontrados.add(libro);
			}else if (filtro.equalsIgnoreCase("autor") && libro.getAutor().toLowerCase().contains(termino.toLowerCase())) {
				librosEncontrados.add(libro);
			}else if (filtro.equalsIgnoreCase("genero") && libro.getGenero().toLowerCase().contains(termino.toLowerCase())) {
				librosEncontrados.add(libro);
			}
		}
		
		return librosEncontrados;
	
	}
	
	public void devolverLibro(String isbn, ArrayList<Usuario> usuarios) {
	    for (Libro libro : biblioteca) {
	        if (libro.getISBN().equals(isbn)) {
	            if (!libro.isDisponible()) {
	                libro.setDisponible(true);


	                for (Usuario usuario : usuarios) {
	                    if (usuario.tieneLibroPrestado(libro)) {
	                        usuario.devolverLibro(libro);
	                        break;
	                    }
	                }
	            } else {
	                System.out.println("El libro ya esta disponible.");
	            }
	            return;
	        }
	    }
	    System.out.println("No se encontró ningún libro con el ISBN proporcionado.");
	}

}
