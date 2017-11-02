// validação do botão Login
// RF_13 - Digitação de dados Inconsistentes
function handleLoginRequest(xhr, status, args) {

	if (args.validationFailed) {
//		window.location.assign("cadastro.xhtml");
		passwordRed();
		loginRed();
	}
	
//	var user = $('#bodyLogin .inicializarLimpoUsuario').text();
//	var password = $('#bodyLogin .inicializarLimpoSenha').text();
//	
//	if (!args.validationFailed && user.indexOf('usuario') > 0 && password.indexOf('william20') > 0){
//		window.location.assign("cadastro.xhtml");
//	} else {
//		passwordRed();
//		loginRed();
//	}
}

// RF_15 – Tempo de Espera de Login
function verificaTempo() {
	if ($('#bodyLogin .ui-growl.ui-widget .ui-growl-message').length > 0) {
		setTimeout(reload(), 30000);
	}
}

// RF_17 – Limpar Campos de Login
function limpaLogin() {
	$('#bodyLogin .inicializarLimpoUsuario').value="";
	$('#bodyLogin .inicializarLimpoSenha').value="";
}

function reload() {
	for (var i = 0; i < 2; i++) {
		if (i != 0) {
			limpaLogin();
			window.location.assign("Login.xhtml");
		}
	}
}

// RF_11 - Digitação apenas de usuário
function passwordRed() {
	var mensagem = $('#bodyLogin .ui-growl-title').text();
	if (mensagem.indexOf('preencher o campo Senha') > 0) {
		var campoSenha = $('#bodyLogin .ui-password').css({
			'border-color' : '#FF0000 #FF0000'
		});
	}
}

// RF_12 - Digitação apenas de senha
function loginRed() {
	var mensagem = $('#bodyLogin .ui-growl-title').text();
	if (mensagem.indexOf('preencher o campo Usuário') > 0) {
		var campoSenha = $('#bodyLogin .loginRed').css({
			'border-color' : '#FF0000 #FF0000'
		});
	}
}

// fazer rf para os campos em vermelho
function bordaRed(i) {
	var pinta = i.css({
		'border-color' : '#FF0000 #FF0000'
	});
}

//fazer rf para os campos em vermelho
function bordaNormal(i) {
	var pinta = i.css({
		'border-color' : '#bed6f8 #bed6f8'
	});
}

// RNF/SEG-01 - Requisito de Segurança
function bloqueiaURL() {
	var url = window.location.href.toString();
	if(url == "http://localhost:8080/IndustriaJSF/faces/cadastro.xhtml"){
//		alert("Erro! Você não tem permissão de acesso!");
//		window.location.assign("Login.xhtml");
	}
}

// RF_02 - Seletor de férias
function checkFerias() { 
	var check = $('.testeOpaco div.ui-chkbox-box');
	if (check.hasClass('ui-state-active')){
		$('.opaco input').prop('disabled', true );
	}else{
		$('.opaco input').prop('disabled', false );
	}
}

function init() {
	// criar rf pra evitar do usuário Colar
	$('.bloqueiaColar').bind('paste', function(e) {
		e.preventDefault();
	});
	
	// inicializar o Login com o Campo Usuário já selecionado - criar rf
	$('.inicializaSelecionado').focus();

	// chamada - RNF/SEG-01 - Requisito de Segurança
	bloqueiaURL();
	
	// RF_02 - Seletor de férias - iniciar desabilitado
	if('#bodyCadastro'){
		$('.opaco input').prop('disabled', true );
	}
	
	$('.click').click(function(){
		// pitando campo nome
		var a = $('.pitura');
		if(a.val() == ""){
			bordaRed(a);
		}else{
			bordaNormal(a);
		}
		
		// pintando campo Mes de Trabalho
		var b = $('.ui-inputfield.ui-widget.ui-state-default.ui-corner-all.hasDatepicker');
		if(b.val() == ""){
			bordaRed(b);
			alert("Por favor, preencher o campo Mês de Trabalho!");
			// testar aqui pra ele não avançar se estiver vazio
		}else{
			bordaNormal(b);
		}
	})
}

//	Formatação do Calendário para Português
PrimeFaces.locales['pt'] = {
    closeText: 'Fechar',
    prevText: 'Anterior',
    nextText: 'Próximo',
    currentText: 'Começo',
    monthNames: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
    monthNamesShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
    dayNames: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado'],
    dayNamesShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb'],
    dayNamesMin: ['D', 'S', 'T', 'Q', 'Q', 'S', 'S'],
    weekHeader: 'Semana',
    firstDay: 0,
    isRTL: false,
    showMonthAfterYear: false,
    yearSuffix: '',
    timeOnlyTitle: 'Só Horas',
    timeText: 'Tempo',
    hourText: 'Hora',
    minuteText: 'Minuto',
    secondText: 'Segundo',
    ampm: false,
    month: 'Mês',
    week: 'Semana',
    day: 'Dia',
    allDayText: 'Todo o Dia'
};

$(document).ready(init());