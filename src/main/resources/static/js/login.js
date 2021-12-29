$(document).ready(function() {
    // on ready
});

async function ingresarUsuario() {

    let datos = {};
    datos.email = document.getElementById("InputEmail").value;
    datos.contraseña = document.getElementById("InputPassword").value;

    const request = await fetch('api/login', {
        method : 'POST',
        headers : {
            'Accept' : 'application/json',
            'Content-Type' : 'application/json'
        },
        body : JSON.stringify(datos)
    });

    const respuesta = await request.text();

    if (respuesta != 'Fail'){
        localStorage.token = respuesta;
        localStorage.email = datos.email;
        window.location.href = 'usuarios.html'
    } else {
        alert('Las credenciales son incorrectas!')
    }

}