package com.arquitecturajava.bo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.query.Query;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import javax.persistence.Table;


@Entity
@Table(name = "libros")
public class Libro {
    @Id
    private String isbn;

    private String titulo;

    private String categoria;


    public Libro(String isbn, String titulo, String categoria) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.categoria = categoria;
    }

    public Libro(String isbn) {
        super();
        this.isbn = isbn;
    }

    public Libro() {
        super();
    }
    @Column(name="isbn")

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    @Column(name="titulo")

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    @Column(name="categoria")

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }


    public void insertar() {
        SessionFactory factoriaSession=HibernateHelper.getSessionFactory();
        Session session = factoriaSession.openSession();
        session.beginTransaction();
        session.save(this);
        session.getTransaction().commit();
    }


    public void borrar() {
        SessionFactory factoriaSession=HibernateHelper.getSessionFactory();
        Session session = factoriaSession.openSession();
        session.beginTransaction();
        session.delete(this);
        session.getTransaction().commit();
    }

    public void salvar() {
        SessionFactory factoriaSession=HibernateHelper.getSessionFactory();
        Session session = factoriaSession.openSession();
        session.beginTransaction();
        session.saveOrUpdate(this);
        session.getTransaction().commit();
    }

    public static List<Libro> buscarTodasLasCategorias() {
        SessionFactory factoriaSession=HibernateHelper.getSessionFactory();
        Session session = factoriaSession.openSession();
        String consulta="select distinct libros.categoria from Libro libros";
        List<Libro> listaDeCategorias = session.createQuery(consulta).list();
        session.close();
        return listaDeCategorias;
    }

    public static List<Libro> buscarTodos() {
        SessionFactory factoriaSession=HibernateHelper.getSessionFactory();
        Session session = factoriaSession.openSession();
        List<Libro> listaDeLibros = session.createQuery(" from Libro libro").list();
        session.close();
        return listaDeLibros;
    }

    public static List<Libro> buscarLibroPorCategoria(String categoria) {
        SessionFactory factoriaSession=HibernateHelper.getSessionFactory();
        Session session = factoriaSession.openSession();
        Query consulta=session.createQuery(" from Libro libro where libro.categoria=:categoria");
        consulta.setParameter("categoria", categoria);
        List<Libro> listaDeLibros = consulta.list();
        session.close();
        return listaDeLibros;
    }

    public static Libro buscarPorClave(String isbn) {
        SessionFactory factoriaSession=HibernateHelper.getSessionFactory();
        Session session = factoriaSession.openSession();
        Libro libro=(Libro) session.get(Libro.class,isbn);
        session.close();
        return libro;
    }
    public int hashCode() {
    return isbn.hashCode();
}

    public boolean equals (Object o) {
        String isbnLibro= ((Libro)o).getIsbn();
        return isbnLibro.equals(isbn);
    }
}

