package com.curso_java_adv.curso.dao;

import com.curso_java_adv.curso.models.Usuario;

import java.util.List;

public interface UsuarioDao {

    List<Usuario> getUsuarios();

    void deleteUsuario(Integer Id);

    void registerUsuario(Usuario user);

    Usuario obtenerUsuarioPorCredenciales(Usuario user);
}
