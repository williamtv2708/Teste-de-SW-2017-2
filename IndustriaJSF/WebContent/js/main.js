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

function readonlyTrue() {
	// adicionando readonly nos calendários pra aparecer a mensagem de erro
	$('.ui-calendar.bloqueiaColar.mesAnoRed input').prop('readonly', true);
}

function readonlyFalse(){
	// remove os readonly dos calendários pra bloquear novamente a escrita
	$('.ui-calendar.bloqueiaColar.mesAnoRed input').prop('readonly', false);
}

//RF_13 - Digitação de dados Inconsistentes
// login validator
$('#bodyLogin .login').click(function(){
	var user = $('#bodyLogin .inicializarLimpoUsuario').val();
	var key = $('#bodyLogin .inicializarLimpoSenha').val();
	
	// RN_03 – Usuário Cadastrado previamente
	if(user == "usuario"){
		if(key == "william20"){
//			RF_16 - Tempo de Espera de Dados
			// deslogando o usuário em 10 minutos
			createCookie('cookieLogin', 'logado', null, null, 10, null);
			window.location.assign("cadastro.xhtml");
		}else{
			if(user != "" && key != ""  && key.length > 5){
				// erro de senha
				alert("Erro ao fazer tentar Login, Senha Inválida");
			}
		}
	}else{
		if(user != "" && key != "" && user.length > 5){
			// erro de usuário
			alert("Erro ao fazer tentar Login, Usuário Inválido");
		}
	}
});


//RNF/SEG-01 - Requisito de Segurança
function validaCookie(){
	if(getCookie('cookieLogin') == ""){
		alert("Erro! Você não tem permissão de acesso!");
		redirectLogin();
	}
}

// criar rf - ao enviar cadastro é pra limpar todos os campos
function limpaCamposCadastro(){
	
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

function init() {
	// marcando readonly
	readonlyTrue();
	
	// logout
	$('.logout').click(function(){
		deleteCookie('cookieLogin');
		redirectLogin();
	});
	
	// iniciando os actionListener dos botões
	$('#bodyCadastro .buttonEnviar span').prop('actionListener', '#{cadastro.enviar}');
	
	// criar rf pra evitar do usuário Colar
	$('.bloqueiaColar').bind('paste', function(e) {
		e.preventDefault();
	});
	
	// inicializar o Login com o Campo Usuário já selecionado - criar rf
	// inicializar o Cadastro com o Campo Nome já selecionado - criar rf
	$('.inicializaSelecionado').focus();

	// RF_02 - Seletor de férias - iniciar desabilitado
	if('#bodyCadastro'){
		$('.opaco input').prop('disabled', true );
	}
	
	// iniciar os campos de data opacos
	$('.cinza').css({
		'border-color' : '#CCCCCC #CCCCCC',
		'color' : '#CCCCCC'
	});
	
//	RF_10 - Enviar Informações
	// validações do botão Enviar
	$('.click').click(function(){
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
			// testar aqui pra ele não avançar se estiver vazio
		}else{
			bordaNormal(b);
		}
		
//		RF_01 - Informações Corretas
		// validação total pra mostrar dados na tela de confirmação
		// limpar os campos após concluído já criada function, tem q fazer-------------------------------------------------------------------------------------------
		var finalName = $('.bloqueiaColar.pitura.inicializaSelecionado').val();
		var finalmes = $('.ui-inputfield.ui-widget.ui-state-default.ui-corner-all.hasDatepicker').val();
		var finalFeriasFalse = $('.ui-chkbox-icon.ui-icon.ui-c.ui-icon-blank').val();
		var finalFeriasTrue = $('.ui-chkbox-icon.ui-icon.ui-c.ui-icon-check').val();
		var finalQuantidade = $('.ui-helper-hidden-accessible select').val();
		if(finalName != ""){
			if(finalmes != ""){
				// seletor desmarcado - RF_02 - Seletor de férias
				if(finalFeriasFalse != undefined){
					// valida se foi selecionado quantidade de folgas
					if(finalQuantidade == ""){
						alert("Erro! Por favor selecione uma quantidade de folgas!")
					}else{
//						RF_09 - Validação de Folgas
						if(finalQuantidade == "0 Dia" || finalQuantidade == "1 Dia" || finalQuantidade == "2 Dias" || finalQuantidade == "3 Dias"){
							
							// mensagem de confirmação, se sim, finalizar
							var r = confirm("Tem certeza que deseja selecionar " + finalQuantidade + " para folga?")
							
							if (r==true){
								alert("Funcionário " + finalName + " cadastrado com sucesso com os seguintes dados:\n - Data de Trabalho: " + finalmes + "\n - Funcionário sem férias no Mês\n - Funcionário com " + finalQuantidade + " de folga.")
								// limpar todos os campos - criar rf
								limpaCamposCadastro();
							}
							
						}else{
							// apenas finalizar
							alert("Funcionário " + finalName + " cadastrado com sucesso com os seguintes dados:\n - Data de Trabalho: " + finalmes + "\n - Funcionário sem férias no Mês\n - Funcionário com " + finalQuantidade + " de folga.")
							// limpar todos os campos - criar rf
							limpaCamposCadastro();
						}
					}
				// seletor marcado - RF_02 - Seletor de férias
				}else if(finalFeriasTrue != undefined){
					// valida se foi selecionado quantidade de folgas
					if(finalQuantidade == ""){
						alert("Erro! Por favor selecione uma quantidade de folgas!")
					}else{
//						RF_09 - Validação de Folgas
						if(finalQuantidade == "0 Dia" || finalQuantidade == "1 Dia" || finalQuantidade == "2 Dias" || finalQuantidade == "3 Dias"){
							
							// mensagem de confirmação, se sim, finalizar
							var r = confirm("Tem certeza que deseja selecionar " + finalQuantidade + " para folga?")
							
							if (r==true){
								alert("Funcionário " + finalName + " cadastrado com sucesso com os seguintes dados:\n - Data de Trabalho: " + finalmes + "\n - Funcionário com férias no Mês\n - Funcionário com " + finalQuantidade + " de folga.")
								// limpar todos os campos - criar rf
								limpaCamposCadastro();
							}
							
						}else{
							// apenas finalizar
							alert("Funcionário " + finalName + " cadastrado com sucesso com os seguintes dados:\n - Data de Trabalho: " + finalmes + "\n - Funcionário com férias no Mês\n - Funcionário com " + finalQuantidade + " de folga.")
							// limpar todos os campos - criar rf
							limpaCamposCadastro();
						}
					}
				}
			}
		}
		setTimeout(readonlyTrue(), 3000);
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