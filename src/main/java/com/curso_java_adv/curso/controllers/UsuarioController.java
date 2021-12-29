package com.curso_java_adv.curso.controllers;

import com.curso_java_adv.curso.dao.UsuarioDao;
import com.curso_java_adv.curso.models.Usuario;
import com.curso_java_adv.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired //Inyeccion de dependencias
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwt;

    @RequestMapping(value = "api/usuario/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable Integer id){
        Usuario user = new Usuario();
        user.setId(id);
        user.setNombre("Alberto");
        user.setApellido("Ospina");
        user.setEdad(23);
        user.setTelefono("311415");
        user.setEmail("alberto@gmail.com");
        return user;
    }

    private boolean verificarToken(String token){
        String idUsuario = jwt.getKey(token);
        return idUsuario != null;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(@RequestHeader(value = "Authentication") String token){
        if (!verificarToken(token)){ return null; }

        return usuarioDao.getUsuarios();
    }

    @RequestMapping(value = "usuario45")
    public Usuario editarUsuario(){
        Usuario user = new Usuario();
        user.setNombre("Alberto");
        user.setApellido("Ospina");
        user.setEdad(23);
        user.setTelefono("311415");
        user.setEmail("alberto@gmail.com");
        return user;
    }

    @RequestMapping(value = "api/usuario/{id}", method = RequestMethod.DELETE)
    public void eliminarUsuario(@PathVariable Integer id,
                                @RequestHeader(value = "Authentication") String token){
        if (!verificarToken(token)){ return; }

        usuarioDao.deleteUsuario(id);
        return;
    }

    @RequestMapping(value = "api/usuario", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario user){

        Argon2 argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon.hash(1,1024,1,user.getContraseña());
        user.setContraseña(hash);
        usuarioDao.registerUsuario(user);
        return;
    }

}
