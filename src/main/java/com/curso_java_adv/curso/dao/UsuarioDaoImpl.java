package com.curso_java_adv.curso.dao;

import com.curso_java_adv.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository //Funcionalidad de poder acceder al repositorio de la BD
@Transactional //La forma en que va a crear y ejecutar las consultas SQL
public class UsuarioDaoImpl implements UsuarioDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void deleteUsuario(Integer Id) {
        Usuario user = entityManager.find(Usuario.class, Id);
        entityManager.remove(user);
        return;
    }

    @Override
    public void registerUsuario(Usuario user) {
        entityManager.merge(user);
        return;
    }

    @Override
    public Usuario obtenerUsuarioPorCredenciales(Usuario user) {
        String query = "FROM Usuario WHERE Email = :email ";
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("email", user.getEmail())
                .getResultList();

        if (lista.isEmpty()){
            return null;
        }

        Argon2 argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon.verify(lista.get(0).getContraseña(), user.getContraseña())){
            return lista.get(0);
        }

        return null;
    }
}
