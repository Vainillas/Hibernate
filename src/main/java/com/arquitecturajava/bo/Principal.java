package com.arquitecturajava.bo;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import com.arquitecturajava.bo.Libro;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Principal {
    public static void main(String[] args) {
        Session session = null;
        try {
            SessionFactory factoria = new Configuration().configure()
                    .buildSessionFactory();
            session = factoria.openSession();

            Query<Libro> consulta = session.createQuery(" from Libro libro where libro.categoria=:categoria ", Libro.class);
            consulta.setParameter("categoria", "Educacional");
            List<Libro> lista = consulta.list();
            for (Libro l : lista) {
                System.out.println("////////////////");
                System.out.println(l.getIsbn());
                System.out.println(l.getTitulo());
                System.out.println(l.getCategoria());
            }
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }
}
