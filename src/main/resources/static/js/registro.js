$(document).ready(function() {
    // on ready
});

async function registrarUsuario(){

    let datos = {};
    datos.nombre = document.getElementById("txtNombre").value;
    datos.apellido = document.getElementById("txtApellido").value;
    datos.telefono = document.getElementById("txtTelef").value;
    datos.email = document.getElementById("txtEmail").value;
    datos.edad = document.getElementById("txtEdad").value;
    datos.contraseña = document.getElementById("txtPassword").value;

    let repetirContraseña = document.getElementById("RepeatPassword").value;

    if (datos.contraseña != repetirContraseña){
        alert("Contraseña diferente");
        return;
    }

    const request = await fetch('api/usuario', {
        method : 'POST',
        headers : {
            'Accept' : 'application/json',
            'Content-Type' : 'application/json'
        },
        body : JSON.stringify(datos)

    });
    alert('Cuenta creada');
    window.location.href = 'login.html'

}