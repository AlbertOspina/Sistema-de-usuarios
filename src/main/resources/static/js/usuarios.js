// Call the dataTables jQuery plugin
$(document).ready(function() {

  cargarUsuarios();
  $('#usuarios').DataTable();
  insertarEmailUsuario();
});

function insertarEmailUsuario(){
    document.getElementById('txt_email_usuario').outerHTML = localStorage.email;
}

function getHeaders() {
    return {
            'Accept' : 'application/json',
            'Content-Type' : 'application/json',
            'Authentication' : localStorage.token
    };
}

async function cargarUsuarios() {

    const request = await fetch('api/usuarios', {
        method : 'GET',
        headers : getHeaders()
    });

    const usuarios = await request.json();

    let listadoHTML = '';

    for (let usuario of usuarios){
        let telefonoTexto = usuario.telefono == null ? '-' : usuario.telefono;
        let botonEliminar = '<a href="#" onclick="eliminarUsuario('+usuario.id+')"  class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
        let usuarioHTML = '<tr><td>'+ usuario.id +'</td><td>'+ usuario.nombre + ' '
                        + usuario.apellido +'</td><td>'+ usuario.email +'</td><td>'+ usuario.edad +'</td><td>'
                        + telefonoTexto +'</td><td>'+ botonEliminar +'</td></tr>';
        listadoHTML += usuarioHTML;
        }

    document.querySelector('#usuarios tbody').outerHTML = listadoHTML;
    console.log(usuarios);
}

async function eliminarUsuario(id) {

    if (!confirm('¿Desea eliminar este usuario ('+id+') ?')){
        return;
    }

    const request = await fetch('api/usuario/'+id, {
        method : 'DELETE',
        headers : getHeaders()
    });

    location.reload();
}