// RF_17 – Limpar Campos de Login
function limpaLogin() {
	var validator = false;
	$('#bodyLogin .inicializarLimpoUsuario').value="";
	$('#bodyLogin .inicializarLimpoSenha').value="";
}

function reload() {
	for (var i = 0; i < 2; i++) {
		if (i != 0) {
			limpaLogin();
			redirectLogin();
		}
	}
}

function redirectLogin(){
	window.location.assign("Login.xhtml");
}

function redirectCadastro(){
	window.location.assign("cadastro.xhtml");
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

// RF_14 - Campos obrigatórios
// pintando em vermelho
function bordaRed(i) {
	var pinta = i.css({
		'border-color' : '#FF0000 #FF0000'
	});
}

//RF_14 - Campos obrigatórios
// pintando em cinza
function bordaNormal(i) {
	var pinta = i.css({
		'border-color' : '#bed6f8 #bed6f8'
	});
}

// RF_18 - Bloqueio de Data
function readonlyTrue() {
	// adicionando readonly nos calendários pra aparecer a mensagem de erro
	$('.ui-calendar.bloqueiaColar.mesAnoRed input').prop('readonly', true);
}

//RF_18 - Bloqueio de Data
function readonlyFalse(){
	// remove os readonly dos calendários pra bloquear novamente a escrita
	$('.ui-calendar.bloqueiaColar.mesAnoRed input').prop('readonly', false);
}

//RF_13 - Digitação de dados Inconsistentes
// login validator
$('#bodyLogin .login').click(function(){
	validaCookieLogado();
	var user = $('#bodyLogin .inicializarLimpoUsuario').val();
	var key = $('#bodyLogin .inicializarLimpoSenha').val();
	
	// RN_03 – Usuário Cadastrado previamente
	if(user == "usuario"){
		bordaNormal($('.loginRed'));
		if(key == "william20"){
			bordaNormal($('.passRed'));
//			RF_16 - Tempo de Espera de Dados
			// deslogando o usuário em 10 minutos
			createCookie('cookieLogin', 'logado', null, null, 10, null);
			redirectCadastro();
		}else{
			bordaRed($('.passRed'));
			if(user != "" && key != ""  && key.length > 5){
				// erro de senha
				alert("Erro ao fazer tentar Login, Senha Inválida");
				bordaRed($('.passRed'));
			}
		}
	}else{
		if(user == ""){
			bordaRed($('.loginRed'));
		}
		if(user != "" && key != "" && user.length > 5){
			// erro de usuário
			alert("Erro ao fazer tentar Login, Usuário Inválido");
			bordaRed($('.loginRed'));
		}
		if(key == ""){
			bordaRed($('.passRed'));
			bordaRed($('.loginRed'));
		}else{
			bordaNormal($('.passRed'));
		}
	}
	
	
});

// valida se o usuário passou dos 10 minutos de login e expirou
//RNF/SEG-01 - Requisito de Segurança
function validaCookieAtivo(){
	if(getCookie('cookieLogin') == ""){
		alert("Erro! Sua sessão expirou!");
		redirectLogin();
	}
}

// valida se o usuário já está logado pra não logar novamente
//RNF/SEG-01 - Requisito de Segurança
function validaCookieLogado(){
	if(getCookie('cookieLogin') == "logado"){
		alert("Erro! Você já está logado!");
		window.location.assign("cadastro.xhtml");
		redirectLogin();
	}
}

// valida se o usuário entrou direto pela url ou algo parecido sem logar
//RNF/SEG-01 - Requisito de Segurança
function validaCookie(){
	if(getCookie('cookieLogin') == ""){
		alert("Erro! Você não tem permissão de acesso!");
		redirectLogin();
	}
}

// RF_19 – Mensagem de Confirmação de Envio
// ao enviar cadastro é pra limpar todos os campos
function limpaCamposCadastro(){
	redirectCadastro();
}

// criador do cookie
function createCookie(key, value, expireDays, expireHours, expireMinutes, expireSeconds) {
    var expireDate = new Date();
    if (expireDays) {
        expireDate.setDate(expireDate.getDate() + expireDays);
    }
    if (expireHours) {
        expireDate.setHours(expireDate.getHours() + expireHours);
    }
    if (expireMinutes) {
        expireDate.setMinutes(expireDate.getMinutes() + expireMinutes);
    }
    if (expireSeconds) {
        expireDate.setSeconds(expireDate.getSeconds() + expireSeconds);
    }
    document.cookie = key +"="+ escape(value) +
        ";domain="+ window.location.hostname +
        ";path=/"+
        ";expires="+expireDate.toUTCString();
}

// deslogando ao fechar o navegador
function removeSession(){
	deleteCookie('cookieLogin');
}

// remover o cookie
function deleteCookie(name) {
	createCookie(name, "", null , null , null, 1);
}

// pegar o cookie
function getCookie(c_name) {
    if (document.cookie.length > 0) {
        c_start = document.cookie.indexOf(c_name + "=");
        if (c_start != -1) {
            c_start = c_start + c_name.length + 1;
            c_end = document.cookie.indexOf(";", c_start);
            if (c_end == -1) {
                c_end = document.cookie.length;
            }
            return unescape(document.cookie.substring(c_start, c_end));
        }
    }
    return "";
}

function validaTotalCadastro(){
	// teste se está ativo ainda o login
	validaCookieAtivo();
	
	// pitando campo nome
	var a = $('.pitura');
	if(a.val() == ""){
		bordaRed(a);
	}else{
		bordaNormal(a);
	}
	
	// pintando campo Mes de Trabalho
	var b = $('.mesAnoRed input');
	if(b.val() == ""){
		bordaRed(b);
	}else{
		bordaNormal(b);
	}
	
	// valida se foi selecionado quantidade de folgas
	var finalQuantidade = $('.ui-helper-hidden-accessible select').val();
	if(finalQuantidade == ""){
		bordaRed($('.ui-selectonemenu'));
	}else{
		bordaNormal($('.ui-selectonemenu'));
//		RF_09 - Validação de Folgas
		if(finalQuantidade == "0 Dia" || finalQuantidade == "1 Dia" || finalQuantidade == "2 Dias" || finalQuantidade == "3 Dias"){
			// mensagem de confirmação, se sim, finalizar
			var r = confirm("Tem certeza que deseja selecionar " + finalQuantidade + " para folga?")
			
			if (r==true){
				alert("Funcionário " + finalName + " cadastrado com sucesso com os seguintes dados:\n - Data de Trabalho: " + finalmes + "\n - Funcionário sem férias no Mês\n - Funcionário com " + finalQuantidade + " de folga.")
				limpaCamposCadastro();
			}
			
		}else{
			// apenas finalizar
			alert("Funcionário " + finalName + " cadastrado com sucesso com os seguintes dados:\n - Data de Trabalho: " + finalmes + "\n - Funcionário sem férias no Mês\n - Funcionário com " + finalQuantidade + " de folga.")
			limpaCamposCadastro();
		}
	}
	
//	RF_01 - Informações Corretas
	// validação total pra mostrar dados na tela de confirmação
	var finalName = $('.bloqueiaColar.pitura.inicializaSelecionado').val();
	var finalmes = $('.ui-inputfield.ui-widget.ui-state-default.ui-corner-all.hasDatepicker').val();
	var finalFeriasFalse = $('.ui-chkbox-icon.ui-icon.ui-c.ui-icon-blank').val();
	var finalFeriasTrue = $('.ui-chkbox-icon.ui-icon.ui-c.ui-icon-check').val();
	if(finalName != ""){
		if(finalmes != ""){
			// seletor desmarcado - RF_02 - Seletor de férias
			if(finalFeriasFalse != undefined){
			// seletor marcado - RF_02 - Seletor de férias
			}else if(finalFeriasTrue != undefined){
				// valida se foi selecionado quantidade de folgas
				if(finalQuantidade == ""){
					alert("Erro! Por favor selecione uma quantidade de folgas!")
				}else{
//					RF_09 - Validação de Folgas
					if(finalQuantidade == "0 Dia" || finalQuantidade == "1 Dia" || finalQuantidade == "2 Dias" || finalQuantidade == "3 Dias"){
						
						// mensagem de confirmação, se sim, finalizar
						var r = confirm("Tem certeza que deseja selecionar " + finalQuantidade + " para folga?")
						
						if (r==true){
							alert("Funcionário " + finalName + " cadastrado com sucesso com os seguintes dados:\n - Data de Trabalho: " + finalmes + "\n - Funcionário com férias no Mês\n - Funcionário com " + finalQuantidade + " de folga.")
							limpaCamposCadastro();
						}
						
					}else{
						// apenas finalizar
						alert("Funcionário " + finalName + " cadastrado com sucesso com os seguintes dados:\n - Data de Trabalho: " + finalmes + "\n - Funcionário com férias no Mês\n - Funcionário com " + finalQuantidade + " de folga.")
						limpaCamposCadastro();
					}
				}
			}
		}
	}
	//adicionando readonly pra bloquear novamente o CTRL+V
	setTimeout(readonlyTrue(), 3000);
}

// RF_20 - Validação Botão Sair
// logout
$('.logout').click(function(){
	var logout = confirm("Tem certeza que deseja sair?")
	if (logout==true){
		deleteCookie('cookieLogin');
		redirectLogin();
	}
});

function init() {
	// RNF/SEG-01 rodando no chrome
	if (navigator.userAgent.indexOf("Chrome") == -1) {
//		alert('Por favor, utilize o navegador Chrome para esta aplicação!');
	}
	
	// marcando readonly
	readonlyTrue();
	
	// iniciando os actionListener do botão
	$('#bodyCadastro .buttonEnviar span').prop('actionListener', '#{cadastro.enviar}');
	
	// RF_21 – Retirar o Evento Colar
	$('.bloqueiaColar').bind('paste', function(e) {
		e.preventDefault();
	});
	
	// RF_22 - Inicializar Selecionado
	// inicializar o Login com o Campo Usuário já selecionado
	// inicializar o Cadastro com o Campo Nome já selecionado
	$('.inicializaSelecionado').focus();

	// RF_02 - Seletor de férias - iniciar desabilitado
	if('#bodyCadastro'){
		$('.opaco input').prop('disabled', true );
	}
	
	// RF_10 - Enviar Informações
	// validações do botão Enviar
	$('.click').click(function(){
		validaTotalCadastro();
	});
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