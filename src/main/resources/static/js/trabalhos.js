$(document).ready(function () {
	moment.locale('pt-BR');
    var table = $('#table-trabalhos').DataTable({
    	language: {
            "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Portuguese-Brasil.json"
        },
    	searching: true,
    	order: [[ 1, "asc" ]],
    	lengthMenu: [5, 10],
        processing: true,
        serverSide: true,
        responsive: true,
        ajax: {
            url: '/trabalho/datatables/server',
            data: 'data'
        },
        columns: [
            {data: 'id'},
            {data: 'titulo'},
            {data : 'autores',
	            render : function(autores) {
					var aux = new Array();
					$.each(autores, function( index, value ){
						  var autor = " ";
						  autor = autor.concat(value.nome);
						  aux.push(autor);
					});
					return aux;
				}, orderable : false,
	        },
            {data: 'curso.nome'},
            {orderable: false, 
             data: 'id',
                "render": function(id) {
                    return '<a class="btn btn-success btn-sm btn-block" href="/trabalho/editar/'+ 
                    	id +'" role="button"><i class="fas fa-edit"></i></a>';
                }
            },
            {orderable: false,
             data: 'id',
                "render": function(id) {
                    return '<a class="btn btn-danger btn-sm btn-block" href="/trabalho/excluir/'+ 
                    	id +'" role="button" data-toggle="modal" data-target="#confirm-modal"><i class="fas fa-times-circle"></i></a>';
                }               
            }
        ]
    });
});    
