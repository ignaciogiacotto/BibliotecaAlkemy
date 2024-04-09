package com.miapp.bibilioteca;

public class Libro {
	private String titulo;
	private String autor;
	private String ISBN;
	private String genero;
	private boolean disponible;
	
	
	public Libro(String titulo,  String autor, String ISBN, String genero, boolean disponible) {
		this.titulo = titulo;
		this.autor = autor;
		this.ISBN = ISBN;
		this.genero = genero;
		this.disponible = disponible;
	}
	
	public Libro() {
		
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String ISBN) {
		this.ISBN =  ISBN;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

    public void prestar() {
        if (disponible) {
            disponible = false;
        } else {
            System.out.println("El libro ya esta prestado.");
        }
    }

    public void devolver() {
        if (!disponible) {
            disponible = true;
        } else {
            System.out.println("El libro ya esta disponible.");
        }
    }
	
	@Override
	public String toString() {
		return "Libro [titulo=" + titulo + ", autor=" + autor + ", ISBN=" + ISBN + ", genero=" + genero
				+ ", disponible=" + disponible + "]";
	}
	
	
}
