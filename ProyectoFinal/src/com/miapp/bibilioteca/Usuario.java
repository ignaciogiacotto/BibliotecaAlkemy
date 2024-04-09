package com.miapp.bibilioteca;

import java.util.ArrayList;

public class Usuario {

	private String nombre;
	private String id;
	private ArrayList<Libro> librosPrestados;
	
	
    public Usuario(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
        this.librosPrestados = new ArrayList<>();
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<Libro> getLibrosPrestados() {
		return librosPrestados;
	}
	
    public boolean tieneLibroPrestado(Libro libro) {
        return librosPrestados.contains(libro);
    }

	public void setLibrosPrestados(ArrayList<Libro> librosPrestados) {
		this.librosPrestados = librosPrestados;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario [nombre=").append(nombre).append(", id=").append(id).append(", librosPrestados=");
        if (librosPrestados.isEmpty()) {
            sb.append("Ninguno");
        } else {
            sb.append("\n");
            for (Libro libro : librosPrestados) {
                sb.append("- ").append(libro.getTitulo()).append("\n");
            }
        }
        sb.append("]");
        return sb.toString();
    }
	
    public void solicitarPrestamo(Libro libro) {
        if (libro.isDisponible()) {
            libro.setDisponible(false);
            librosPrestados.add(libro);
            System.out.println("Prestamo exitoso. El libro '" + libro.getTitulo() + "' ha sido prestado a " + nombre + ".");
        } else {
            System.out.println("El libro '" + libro.getTitulo() + "' no está disponible para préstamo.");
        }
    }

    public void devolverLibro(Libro libro) {
        if (librosPrestados.contains(libro)) {
            librosPrestados.remove(libro);
            System.out.println("Devolucion exitosa. El libro '" + libro.getTitulo() + "' ha sido devuelto por " + nombre + ".");
        } else {
            System.out.println("El usuario no tiene prestado el libro '" + libro.getTitulo() + "'.");
        }
    }

	
	
}
