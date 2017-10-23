var i;

// validação do botão Login
function handleLoginRequest(xhr, status, args) {
    if(!args.validationFailed) {
        window.location.assign("cadastro.xhtml");
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