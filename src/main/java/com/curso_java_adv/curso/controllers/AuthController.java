package com.curso_java_adv.curso.controllers;

import com.curso_java_adv.curso.dao.UsuarioDao;
import com.curso_java_adv.curso.dao.UsuarioDaoImpl;
import com.curso_java_adv.curso.models.Usuario;
import com.curso_java_adv.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwt;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario user){

        Usuario usuarioLogueado = usuarioDao.obtenerUsuarioPorCredenciales(user);

        if (usuarioLogueado != null){

            String jwtToken = jwt.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getEmail());
            return jwtToken;

        } else { return "Fail";}
    }

}
