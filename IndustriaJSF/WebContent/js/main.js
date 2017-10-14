// validação do botão Login
function handleLoginRequest(xhr, status, args) {
    if(!args.validationFailed) {
        window.location.assign("cadastro.xhtml");
    }
}

// RF_15 – Tempo de Espera de Login
function reload() {
	if($('#bodyLogin .ui-growl.ui-widget .ui-growl-message').length > 0){
		setTimeout(window.location.assign("Login.xhtml"),30000);
		limpaLogin();
	}
}

// RF_17 – Limpar Campos de Login
function limpaLogin(){
	document.getElementById('j_idt5:username').value="";
	document.getElementById('j_idt5:password').value="";
	// criar rf de iniciar seleção no campo login
	document.getElementById('j_idt5:username').focus();
}