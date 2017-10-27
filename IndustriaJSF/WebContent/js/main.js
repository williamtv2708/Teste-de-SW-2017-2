// validação do botão Login
// RF_13 - Digitação de dados Inconsistentes
function handleLoginRequest(xhr, status, args) {

	if (!args.validationFailed) {
		window.location.assign("cadastro.xhtml");
	} else {
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

// RNF/SEG-01 - Requisito de Segurança
function bloqueiaURL() {
	var url = window.location.href.toString();
	if(url == "http://localhost:8080/IndustriaJSF/faces/cadastro.xhtml"){
		alert("Erro! Você não tem permissão de acesso!");
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
}

$(document).ready(init());