window.addEventListener('load', function () {
    (function(){

      //con fetch invocamos a la API de odontólogos con el método GET
      //nos devolverá un JSON con una colección de odontólogos
      const url = '/odontologos';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
      //recorremos la colección de odontólogos del JSON
         for(dentist of data){
            //por cada odontólogo armaremos una fila de la tabla
            //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos el odontólogo
            var table = document.getElementById("dentistTable");
            var dentistRow =table.insertRow();
            let tr_id = 'tr_' + dentist.id;
            dentistRow.id = tr_id;

            //por cada odontologo creamos un boton delete que agregaremos en cada fila para poder eliminarla
            //dicho boton invocara a la funcion de java script deleteByKey que se encargará
            //de llamar a la API para eliminar un odontologo
            let deleteButton = '<button' +
                          ' id=' + '\"' + 'btn_delete_' + dentist.id + '\"' +
                          ' type="button" onclick="deleteBy('+dentist.id+')" class="btn btn-danger btn_delete">' +
                          '&times' +
                          '</button>';

            //por cada odontologo creamos un boton modificar que agregaremos en cada fila para poder eliminarla
            //dicho boton invocara a la funcion de java script modifyByKey que se encargará
            //de llamar a la API para modificar una odontologo
            //let modifyButton = '<button class="btn-form btn text-white border-0 btn_modify"><a href="odontologoActualizar.html?id=' + dentist.id + '>Editar</a></button>';
            let modifyButton = '<a href="odontologoActualizar.html?id=' + dentist.id + '" class="btn-form btn text-white border-0 btn_modify">Editar</a>';

            dentistRow.innerHTML =
                    '<td class=\"td_id\">' + dentist.id + '</td>' +
                    '<td class=\"td_nombre\">' + dentist.nombre.toUpperCase() + '</td>' +
                    '<td class=\"td_apellido\">' + dentist.apellido.toUpperCase() + '</td>' +
                    '<td class=\"td_matricula\">' + dentist.matricula + '</td>' +
                    '<td>' + deleteButton + '</td>' +
                    '<td>' + modifyButton + '</td>';
        };
    })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/odontologoLista.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


    })