var validator = 0;

// validação do botão Login
// RF_13 - Digitação de dados Inconsistentes
//function handleLoginRequest(xhr, status, args) {
//
//	if (args.validationFailed) {
//		window.location.assign("cadastro.xhtml");
//		passwordRed();
//		loginRed();
//	}
	
//	var user = $('#bodyLogin .inicializarLimpoUsuario').text();
//	var password = $('#bodyLogin .inicializarLimpoSenha').text();
//	
//	if (!args.validationFailed && user.indexOf('usuario') > 0 && password.indexOf('william20') > 0){
//		window.location.assign("cadastro.xhtml");
//	} else {
//		passwordRed();
//		loginRed();
//	}
//}

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
//		$('.buttonEnviar span').prop('textContent', 'Enviar');
//		$('#bodyCadastro .buttonEnviar span').prop('actionListener', '#{cadastro.enviar}');
//		$('.opaco input').prop('disabled', true);
//		// ajustar pra limpar os campos
//		$('.opaco input').value="";
//		// deixar cinza as letras e campo
//		$('.cinza').css({
//			'border-color' : '#CCCCCC #CCCCCC',
//			'color' : '#CCCCCC'
//		});
//		$('.cinza input').css({
//			'border-color' : '#CCCCCC #CCCCCC',
//			'color' : '#CCCCCC'
//		});
//		
//		// remove o required dos campos de data
//		$('.initFerias.cinza input').prop('aria-required', false);
//		$('.endFerias.cinza input').prop('aria-required', false);
//	}else{
//		$('.buttonEnviar span').prop('textContent', 'Avançar');
//		$('#bodyCadastro .buttonEnviar span').prop('actionListener', '#{cadastro.avancar}');
//		$('.opaco input').prop('disabled', false);
//		bordaNormal($('.cinza'));
//		$('.cinza').css({
//			'color' : '#000000'
//		});
//		$('.cinza input').css({
//			'color' : '#000000'
//		});
//		
//		// deixar os campos de data required
//		$('.initFerias.cinza input').prop('aria-required', true);
//		$('.endFerias.cinza input').prop('aria-required', true);
	}
}

function readonlyTrue() {
	// adicionando readonly nos calendários pra aparecer a mensagem de erro
	$('.ui-calendar.bloqueiaColar.mesAnoRed input').prop('readonly', true);
//	$('.initFerias.cinza input').prop('readonly', true);
//	$('.endFerias.cinza input').prop('readonly', true);
}

// login validator
$('#bodyLogin .login').click(function(){
	var user = $('#bodyLogin .inicializarLimpoUsuario').val();
	var key = $('#bodyLogin .inicializarLimpoSenha').val();
	
	if(user == "usuario"){
		if(key == "william20"){
			$('.nameLabel').data('loginvalidator', 1);
			validator = validator + 1;
			verificaLogin(validator);
		}else{
			if(user != "" && key != ""  && key.length > 5){
				//erro de senha
				alert("Erro ao fazer tentar Login, Senha Inválida");
			}
		}
	}else{
		if(user != "" && key != "" && user.length > 5){
			//erro de usuário
			alert("Erro ao fazer tentar Login, Usuário Inválido");
		}
	}
});

function verificaLogin(validator){
	if(validator == 1){
		window.location.assign("cadastro.xhtml");
	}
}

function limpaCamposCadastro(){
	
}

function init() {
	// marcando readonly
	readonlyTrue();
	
	// iniciando os actionListener dos botões
	$('#bodyCadastro .buttonEnviar span').prop('actionListener', '#{cadastro.enviar}');
//	$('#bodyFerias .buttonEnviar span').prop('actionListener', '#{cadastro.enviar}');
	
	// criar rf pra evitar do usuário Colar
	$('.bloqueiaColar').bind('paste', function(e) {
		e.preventDefault();
	});
	
	// inicializar o Login com o Campo Usuário já selecionado - criar rf
	// inicializar o Cadastro com o Campo Nome já selecionado - criar rf
	$('.inicializaSelecionado').focus();

	// chamada - RNF/SEG-01 - Requisito de Segurança
	bloqueiaURL();
	
	// RF_02 - Seletor de férias - iniciar desabilitado
	if('#bodyCadastro'){
		$('.opaco input').prop('disabled', true );
	}
	
	// iniciar os campos de data opacos
	$('.cinza').css({
		'border-color' : '#CCCCCC #CCCCCC',
		'color' : '#CCCCCC'
	});
	
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
		
		// verifica o checkbox pra pintar os campos de férias
		var check = $('.testeOpaco div.ui-chkbox-box');
		if (check.hasClass('ui-state-active')){
			// pintando campo Início de Férias
//			var c = $('.initFerias input');
//			if(c.val() == ""){
//				bordaRed(c);
//				// testar aqui pra ele não avançar se estiver vazio
//			}else{
//				bordaNormal(c);
//			}
//			
//			// pintando campo Fim de Férias
//			var c = $('.endFerias input');
//			if(c.val() == ""){
//				bordaRed(c);
//				// testar aqui pra ele não avançar se estiver vazio
//			}else{
//				bordaNormal(c);
//			}
			
			//testar se todos os campos foram preenchidos
			//apresentar mensagem de finalização
//			window.location.assign("ferias.xhtml");
		}
		
		// trocar para a página de férias
//		if($('.buttonEnviar span').prop('actionListener', '#{cadastro.avancar}')){
//			window.location.assign("ferias.xhtml");
//		}
		
		// validação total pra mostrar dados na tela de confirmação
		// limpar os campos após concluído
		var finalName = $('.bloqueiaColar.pitura.inicializaSelecionado').val();
		var finalmes = $('.ui-inputfield.ui-widget.ui-state-default.ui-corner-all.hasDatepicker').val();
		var finalFeriasFalse = $('.ui-chkbox-icon.ui-icon.ui-c.ui-icon-blank').val();
		var finalFeriasTrue = $('.ui-chkbox-icon.ui-icon.ui-c.ui-icon-check').val();
		var finalQuantidade = $('.ui-helper-hidden-accessible select').val();
		if(finalName != ""){
			if(finalmes != ""){
				// seletor desmarcado
				if(finalFeriasFalse != undefined){
					// valida se foi selecionado quantidade de folgas
					if(finalQuantidade == ""){
						alert("Erro! Por favor selecione uma quantidade de folgas!")
					}else{
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
				// seletor marcado
				}else if(finalFeriasTrue != undefined){
					// valida se foi selecionado quantidade de folgas
					if(finalQuantidade == ""){
						alert("Erro! Por favor selecione uma quantidade de folgas!")
					}else{
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
		
		// removendo readonly dos calendários pra aparecer a mensagem de erro
		$('.ui-calendar.bloqueiaColar.mesAnoRed input').prop('readonly', false);
//		$('.initFerias.cinza input').prop('readonly', false);
//		$('.endFerias.cinza input').prop('readonly', false);
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