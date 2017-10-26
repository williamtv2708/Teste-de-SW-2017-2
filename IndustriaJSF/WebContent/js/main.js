var i;

// validação do botão Login
// RF_13 - Digitação de dados Inconsistentes
function handleLoginRequest(xhr, status, args) {
    if(!args.validationFailed) {
        window.location.assign("cadastro.xhtml");
    }else{
    	passwordRed();
    	loginRed();
    }
}

// RF_15 – Tempo de Espera de Login
function verificaTempo() {
	if($('#bodyLogin .ui-growl.ui-widget .ui-growl-message').length > 0){
		setTimeout(reload(),10000);
	}
}

// RF_17 – Limpar Campos de Login
function limpaLogin(){
	document.getElementById('j_idt5:username').value="";
	document.getElementById('j_idt5:password').value="";
	// criar rf de iniciar seleção no campo login
	document.getElementById('j_idt5:username').focus();
}

function reload(){
	for(i=0 ; i<2 ; i++){
		if(i!=0){
			limpaLogin();
			window.location.assign("Login.xhtml");
		}
	}
}

// RF_11 - Digitação apenas de usuário
function passwordRed(){
	var mensagem = $('#bodyLogin .ui-growl-title').text();
	if(mensagem.indexOf('preencher o campo Senha') > 0) {
		var campoSenha = $('#bodyLogin .ui-password').css({
		    'border-color': '#FF0000 #FF0000'
		});
	}
}

//RF_12 - Digitação apenas de senha
function loginRed(){
	var mensagem = $('#bodyLogin .ui-growl-title').text();
	if(mensagem.indexOf('preencher o campo Usuário') > 0) {
		var campoSenha = $('#bodyLogin .loginRed').css({
		    'border-color': '#FF0000 #FF0000'
		});
	}
}

//criar rf pra evitar do usuário Colar
$(document).ready(function() {
    $(".bloqueiaColar").bind('paste', function(e) {
        e.preventDefault();
    });
});