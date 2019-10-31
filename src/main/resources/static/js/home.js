
$(document).ready(function() {
    moment.locale('pt-BR');
    var table = $('#table-trabalhos').DataTable({
    	language: {
            "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Portuguese-Brasil.json"
        },
        searching : true,
        lengthMenu : [ 5, 10, 50 ],
        processing : true,
        serverSide : true,
        responsive : true,
        order: [1, 'desc'],
        ajax : {
            url : '/trabalho/home/datatables/server',
            data : 'data'
        },
        columns : [
        	{data: 'titulo'},
            {data : 'autores',
	            render : function(autores) {
	            	var autor = "";
    				$.each(autores, function( index, value ){
    					  autor = autor.concat(value.nome);
    					  autor = autor.concat("<br>");
    				});
    				return autor;
				}, orderable : false,
	        },
            {data: 'curso.nome'},
            {orderable: false, 
                data: 'id',
                   "render": function(id) {
                       return '<a href="/trabalho/detalhes/'+id+'"><i class="fas fa-file-pdf fa-2x"></i></a>';
                   }
             },
        ]
    });
});
