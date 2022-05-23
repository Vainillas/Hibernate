package com.arquitecturajava.bo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    private String id;
    private String descripcion;
    public int hashCode() {
        return id.hashCode();
    }
    @Override
    public boolean equals (Object o) {
        String categoriaId= ((Categoria)o).getId();
        return id.equals(categoriaId);
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public static List<Categoria> buscarTodos() {
        SessionFactory factoriaSession =
                HibernateHelper.getSessionFactory();
        Session session = factoriaSession.openSession();
        List<Categoria> listaDeCategorias = session.createQuery(" from Categoria categoria").list();
        session.close();
        return listaDeCategorias;
    }
}
