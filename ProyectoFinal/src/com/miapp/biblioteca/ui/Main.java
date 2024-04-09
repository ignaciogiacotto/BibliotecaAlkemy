package com.miapp.biblioteca.ui;

import java.util.ArrayList;
import java.util.Scanner;

import com.miapp.bibilioteca.Libro;
import com.miapp.bibilioteca.Usuario;
import com.miapp.biblioteca.service.LibroServicio;
import com.miapp.biblioteca.service.UsuarioServicio;

public class Main {
	private static ArrayList<Libro> libros = new ArrayList<>(); // Variable de clase para almacenar libros. No borrar
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
	public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        LibroServicio libroServicio = new LibroServicio(libros);
        UsuarioServicio usuarioServicio = new UsuarioServicio(usuarios);

        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = scan.nextInt();
            scan.nextLine();

            switch (opcion) {
                case 1:
                    gestionarUsuarios(scan, usuarioServicio);
                    break;
                case 2:
                    gestionarLibros(scan, libroServicio);
                    break;
                case 0:
                    System.out.println("Saliendo de la aplicacion.");
                    break;
                default:
                    System.out.println("Opcion invalida. Intente nuevamente.");
                    break;
            }
        } while (opcion != 0);

        scan.close();
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n----- MENU PRINCIPAL -----");
        System.out.println("1. Gestion de usuarios");
        System.out.println("2. Gestion de libros");
        System.out.println("0. Salir");
        System.out.println("Ingrese una opcion:");
    }

    private static void gestionarUsuarios(Scanner scan, UsuarioServicio usuarioServicio) {
        int opcion;
        do {
            mostrarMenuUsuarios();
            opcion = scan.nextInt();
            scan.nextLine(); 

            switch (opcion) {
                case 1:
                    // Crear un nuevo usuario
                    System.out.println("Ingrese el nombre del usuario:");
                    String nombre = scan.nextLine();
                    System.out.println("Ingrese el ID del usuario:");
                    String id = scan.nextLine();
                    usuarioServicio.crearUsuario(nombre, id);
                    System.out.println("Usuario creado exitosamente.");
                    break;
                case 2:
                    // Mostrar todos los usuarios
                    mostrarUsuarios(usuarioServicio.obtenerTodosLosUsuarios());
                    break;
                case 3:
                    // Actualizar un usuario existente
                    System.out.println("Ingrese el ID del usuario que desea actualizar:");
                    String idActualizar = scan.nextLine();
                    System.out.println("Ingrese el nuevo nombre del usuario:");
                    String nuevoNombre = scan.nextLine();
                    usuarioServicio.actualizarUsuario(idActualizar, nuevoNombre);
                    System.out.println("Usuario actualizado exitosamente.");
                    break;
                case 4:
                    // Eliminar un usuario
                    System.out.println("Ingrese el ID del usuario que desea eliminar:");
                    String idEliminar = scan.nextLine();
                    usuarioServicio.eliminarUsuarios(idEliminar);
                    break;
                case 0:
                    System.out.println("Volviendo al menu principal.");
                    break;
                default:
                    System.out.println("Opcion invalida. Intente nuevamente.");
                    break;
            }
        } while (opcion != 0);
    }

    private static void mostrarMenuUsuarios() {
        System.out.println("\n----- MENU DE USUARIOS -----");
        System.out.println("1. Crear un nuevo usuario");
        System.out.println("2. Mostrar lista de usuarios");
        System.out.println("3. Actualizar un usuario");
        System.out.println("4. Eliminar un usuario");
        System.out.println("0. Volver al menú principal");
        System.out.println("Ingrese una opcion:");
    }

    private static void mostrarUsuarios(ArrayList<Usuario> usuarios) {

        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios en la lista.");
        } else {
            System.out.println("Lista de usuarios:");
            for (Usuario usuario : usuarios) {
                System.out.println(usuario);
            }
        }
    }
    
    private static void gestionarLibros(Scanner scan, LibroServicio libroServicio) {
        int opcion;
        do {
            mostrarMenuLibros();
            opcion = scan.nextInt();
            scan.nextLine();

            switch (opcion) {
                case 1:
                	//Crear un nuevo libro
                    System.out.println("Ingrese el nombre del libro:");
                    String nombre = scan.nextLine();
                    System.out.println("Ingrese el autor del libro:");
                    String autor = scan.nextLine();
                    System.out.println("Ingrese el ISBN del libro:");
                    String isbn = scan.nextLine();
                    System.out.println("Ingrese el género del libro:");
                    String genero = scan.nextLine();
                    libroServicio.crearLibro(nombre, autor, isbn, genero);
                    System.out.println("Libro agregado exitosamente.");
                    break;
                case 2:
                	//Mostrar la lista de libros existentes
                    mostrarLibros(libroServicio.obtenerTodosLosLibros());
                    break;
                case 3:
                    System.out.println("Ingrese el ISBN del libro a modificar:");
                    String isbnActualizar = scan.nextLine();

                    // Buscar el libro por ISBN en la lista de libros
                    boolean libroEncontrado = false;
                    for (Libro libro : libros) {
                        if (libro.getISBN().equals(isbnActualizar)) {
                            libroEncontrado = true;
                            System.out.println("Ingrese el nuevo nombre:");
                            String nombreActualizar = scan.nextLine();
                            System.out.println("Ingrese el nuevo autor:");
                            String autorActualizar = scan.nextLine();
                            System.out.println("Ingrese el nuevo género:");
                            String generoActualizar = scan.nextLine();

                            libroServicio.actualizarLibro(isbnActualizar, nombreActualizar, autorActualizar, generoActualizar);
                            break;
                        }
                    }


                    if (!libroEncontrado) {
                        System.out.println("No se encontro ningun libro con este ISBN.");
                    }
                    break;
                case 4: 
                	//Eliminar un libro
                	System.out.println("Ingrese el ISBN del libro que desea eliminar");
                	String isbnEliminar = scan.nextLine();
                	libroServicio.eliminarLibro(isbnEliminar);
                	System.out.println("Libro eliminado exitosamente.");
                case 5:
                    // Buscar libros por título, autor o género
                    System.out.println("Ingrese el criterio de busqueda (titulo/autor/genero):");
                    String filtro = scan.nextLine();
                    System.out.println("Ingrese el termino de busqueda:");
                    String termino = scan.nextLine();

                    ArrayList<Libro> librosEncontrados = libroServicio.buscarLibros(filtro, termino);
                    mostrarLibros(librosEncontrados);
                    break;

                case 6:
                    // Prestar un libro a un usuario
                    System.out.println("Ingrese el ISBN del libro que desea prestar:");
                    String isbnPrestamo = scan.nextLine();

                    Libro libroPrestamo = null;
                    for (Libro libro : libros) {
                        if (libro.getISBN().equals(isbnPrestamo)) {
                            libroPrestamo = libro;
                            break;
                        }
                    }

                    if (libroPrestamo == null) {
                        System.out.println("No se encontro ningun libro con este ISBN.");
                    } else {
                        System.out.println("Ingrese el ID del usuario al que desea prestar el libro:");
                        String idUsuarioPrestamo = scan.nextLine();

                        Usuario usuarioPrestamo = null;
                        for (Usuario usuario : usuarios) {
                            if (usuario.getId().equals(idUsuarioPrestamo)) {
                                usuarioPrestamo = usuario;
                                break;
                            }
                        }

                        if (usuarioPrestamo == null) {
                            System.out.println("No se encontro ningun usuario con este ID.");
                        } else {
                            if (libroPrestamo.isDisponible()) {
                                usuarioPrestamo.getLibrosPrestados().add(libroPrestamo);
                                libroPrestamo.setDisponible(false);
                                System.out.println("Libro prestado exitosamente a " + usuarioPrestamo.getNombre());
                            } else {
                                System.out.println("El libro no esta disponible para prestamo.");
                            }
                        }
                    }
                    break;

                case 7:
                	//Devolver un libro
                    System.out.println("Ingrese el ISBN del libro que desea devolver:");
                    String isbnDevolver = scan.nextLine();
                    libroServicio.devolverLibro(isbnDevolver, usuarios);
                    break;

                case 0:
                    System.out.println("Volviendo al menu principal.");
                    break;
                default:
                    System.out.println("Opcion invalida. Intente nuevamente.");
                    break;
            }
        } while (opcion != 0);
    }
    
    private static void mostrarMenuLibros() {
        System.out.println("\n----- MENU DE LIBROS -----");
        System.out.println("1. Agregar un nuevo libro");
        System.out.println("2. Mostrar lista de libros");
        System.out.println("3. Actualizar un libro");
        System.out.println("4. Eliminar un libro");
        System.out.println("5. Buscar libros");
        System.out.println("6. Prestar un libro a un usuario");
        System.out.println("7. Devolver un libro a la biblioteca");
        System.out.println("0. Volver al menu principal");
        System.out.println("Ingrese una opcion:");
    }
    
    private static void mostrarLibros(ArrayList<Libro> libros) {

        if (libros.isEmpty()) {
            System.out.println("No hay libros en la lista.");
        } else {
            System.out.println("Lista de libros:");
            for (Libro libro : libros) {
                System.out.println(libro);
            }
        }
    }
    
    
}

	

	    	
