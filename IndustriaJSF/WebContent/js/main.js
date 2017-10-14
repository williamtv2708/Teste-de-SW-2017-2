function handleLoginRequest(xhr, status, args) {
    if(!args.validationFailed) {
        window.location.assign("cadastro.xhtml");
    }
}

// RF_15 – Tempo de Espera de Login
function reload() {
	if($('#bodyLogin .ui-growl.ui-widget .ui-growl-message').length > 0){
		//adicionar o import la na login.xhtml e testar, se funcionar, ajustar o tempo pra 30000
		setTimeout(window.location.assign("Login.xhtml"),5000);
		limpaLogin();
	}
}

// RF_17 – Limpar Campos de Login
function limpaLogin(){
	document.getElementById('username').reset();
	document.getElementById('password').reset();
}