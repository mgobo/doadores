function getEstadoProfileRest(){
    $.ajax({
        type: 'GET',
        url: 'http://localhost:'+window.location.port+'/doador/estado/profile',
        dataType: 'json',
        crossDomain: true
    }).done(function (data) {
        console.log(data);
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert(jqXHR.responseText);
    });
}

function openInfoComplementar(id) {
    $('#list-tab-complemento a').removeClass("active");
    $('#' + id).addClass("active");
    switch (id) {
        case "list-imc-list":
            openAuxFrame('IMC médio doadores');
            restAjaxJQueryIMC('doador/imc/faixa');
            break;
        case "list-genero-list":
            openAuxFrame('Obesos por gênero');
            restAjaxJQueryGenero('doador/obesos/genero');
            break;
        case "list-media-list":
            openAuxFrame('Idade média tipo sanguíneo');
            restAjaxJQueryTipoSangue('media/idade/sangue');
            break;
        default:
            break;
    }
}

function closeModal(){
    $('#modalComplemento').modal('hide');
    $('#modalComplemento').after('shown.bs.hide', function () {
        document.getElementById('body-content').innerHTML = "";
    });
}

function openAuxFrame(title) {
    document.getElementById('modalTitle').innerHTML = '';
    document.getElementById('modalTitle').innerHTML = title;
    $('#modalComplemento').modal('show');
}

function restAjaxJQueryIMC(path) {
    document.getElementById('body-content').innerHTML = "Aguarde...";
    $.ajax({
        type: 'GET',
        url: 'http://localhost:'+window.location.port+'/doador/imc/faixa',
        dataType: 'json',
        crossDomain: true
    }).done(function (data) {
        var tableIMC = '<table class="table table-bordered table-striped top">'+
                            '<tr>'+
                                '<td style="text-align: center">Faixa et&aacute;ria</td>'+
                                '<td style="text-align: center">M&eacute;dia</td>'+
                                '<td style="text-align: center">Total de doadores</td>'  
                            '</tr>'; 
        $(data.collectionData).each(function (i,value){
            tableIMC += '<tr>'+
                            '<td style="text-align: center">'+value.faixaIdade+'</td>'+
                            '<td style="text-align: center">'+value.imcMedioFaixaIdade+'</td>'+
                            '<td style="text-align: center">'+value.total+'</td>'+  
                        '</tr>';
        });
        tableIMC += '</table>';
        document.getElementById('body-content').innerHTML = tableIMC;
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert(jqXHR.responseText);
    });
}

function restAjaxJQueryGenero(path) {
    document.getElementById('body-content').innerHTML = "Aguarde...";
    $.ajax({
        type: 'GET',
        url: 'http://localhost:'+window.location.port+'/doador/obesos/genero',
        dataType: 'json',
        crossDomain: true
    }).done(function (data) {
        var tableObesos = '<table class="table table-bordered table-striped top">'+
                            '<tr>'+
                                '<td style="text-align: center">Total de homens</td>'+
                                '<td style="text-align: center">Percentual de obesos</td>'+
                            '</tr>'+ 
                            '<tr>'+
                                '<td style="text-align: center">'+data.data.totalMasculinosObesos+'</td>'+
                                '<td style="text-align: center">'+data.data.percentualMasculinosObesos+'</td>'+
                            '</tr>';
        tableObesos += '</table>';
        tableObesos += '<table class="table table-bordered table-striped top">'+
                            '<tr>'+
                                '<td style="text-align: center">Total de mulheres</td>'+
                                '<td style="text-align: center">Percentual de obesas</td>'+
                            '</tr>'+
                            '<tr>'+
                                '<td style="text-align: center">'+data.data.totalFemininosObesos+'</td>'+
                                '<td style="text-align: center">'+data.data.percentualFemininosObesos+'</td>'+
                            '</tr>';
        tableObesos += '</table>';
        tableObesos += '<div>Total de doadores: '+data.data.totalDoadores+'</div>';
        document.getElementById('body-content').innerHTML = tableObesos;
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert(jqXHR.responseText);
    });
}

function restAjaxJQueryTipoSangue() {
    document.getElementById('body-content').innerHTML = "Aguarde...";
    $.ajax({
        type: 'GET',
        url: 'http://localhost:'+window.location.port+'/doador/media/idade/sangue',
        dataType: 'json',
        crossDomain: true
    }).done(function (data) {
        var tableTipoSangue = '<table class="table table-bordered table-striped top">'+
                            '<tr>'+
                                '<td style="text-align: center">Tipo do Sangue</td>'+
                                '<td style="text-align: center">M&eacute;dia da idade</td>'+
                                '<td style="text-align: center">Total de doadores</td>'  
                            '</tr>'; 
        $(data.collectionData).each(function (i,value){
            tableTipoSangue += '<tr>'+
                            '<td style="text-align: center">'+value.tipoSangue+'</td>'+
                            '<td style="text-align: center">'+value.mediaIdade+'</td>'+
                            '<td style="text-align: center">'+value.totalDoadores+'</td>'  
                        '</tr>';
        });
        tableTipoSangue += '</table>';
        document.getElementById('body-content').innerHTML = tableTipoSangue;

    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert(jqXHR.responseText);
    });
}