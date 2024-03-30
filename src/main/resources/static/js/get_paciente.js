window.addEventListener('load', function () {
    (function(){

      //con fetch invocamos a la API de pacientes con el método GET
      //nos devolverá un JSON con una colección de pacientes
      const url = '/pacientes';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
      //recorremos la colección de pacientes del JSON
         for(patient of data){
            //por cada paciente armaremos una fila de la tabla
            //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos el paciente
            var table = document.getElementById("patientTable");
            var patientRow =table.insertRow();
            let tr_id = 'tr_' + patient.id;
            patientRow.id = tr_id;

            //por cada paciente creamos un boton delete que agregaremos en cada fila para poder eliminarla
            //dicho boton invocara a la funcion de java script deleteByKey que se encargará
            //de llamar a la API para eliminar un paciente
            let deleteButton = '<button' +
                          ' id=' + '\"' + 'btn_delete_' + patient.id + '\"' +
                          ' type="button" onclick="deleteBy('+patient.id+')" class="btn btn-danger btn_delete">' +
                          '&times' +
                          '</button>';

            //por cada paciente creamos un boton modificar que agregaremos en cada fila para poder eliminarla
            //dicho boton invocara a la funcion de java script modifyByKey que se encargará
            //de llamar a la API para modificar una paciente
            let modifyButton = '<a href="pacienteActualizar.html?id=' + patient.id + '><button class="btn-form btn text-white border-0 btn_modify">Editar</button></a>';


            patientRow.innerHTML =
                    '<td class=\"td_id\">' + patient.id + '</td>' +
                    '<td class=\"td_nombre\">' + patient.nombre.toUpperCase() + '</td>' +
                    '<td class=\"td_apellido\">' + patient.apellido.toUpperCase() + '</td>' +
                    '<td class=\"td_dni\">' + patient.dni + '</td>' +
                    '<td class=\"td_fecha_ingreso\">' + patient.fecha_ingreso + '</td>' +
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