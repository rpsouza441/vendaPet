function moedaParaNumero(valor) {
	return isNaN(valor) == false ? parseFloat(valor) : parseFloat(valor
			.replace("R$", "").replace(".", "").replace(",", "."));
}
function numeroParaMoeda(n, c, d, t) {
	c = isNaN(c = Math.abs(c)) ? 2 : c, d = d == undefined ? "," : d,
			t = t == undefined ? "." : t, s = n < 0 ? "-" : "",
			i = parseInt(n = Math.abs(+n || 0).toFixed(c)) + "",
			j = (j = i.length) > 3 ? j % 3 : 0;
	return s + (j ? i.substr(0, j) + t : "")
			+ i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t)
			+ (c ? d + Math.abs(n - i).toFixed(c).slice(2) : "");
}


$(document).on("change", "#desconto", function() {

	var venda = moedaParaNumero($('#valorVenda').val());
	var desconto = moedaParaNumero($('#desconto').val());

	if (venda > 0 && desconto > 1) {
		var total = venda - desconto;
		$('#valorFinal').val(numeroParaMoeda(total.toFixed(2)));
	}

});

$(document).on("change", "#valorFinal", function() {

	var venda = moedaParaNumero($('#valorVenda').val());
	var valorFinal = moedaParaNumero($('#valorFinal').val());

	if (venda > 0 && valorFinal > 0) {
		var total = venda - valorFinal;
		$('#desconto').val(numeroParaMoeda(total.toFixed(2)));
	}

});


$(function() {
	$('.money').maskMoney({
		prefix : 'R$ ',
		allowNegative : false,
		thousands : '.',
		decimal : ',',
		affixesStay : false
	});
});

$(document)

.ready(
		function() {
			var date_input = $('.dataPicker'); // our date input has the name
												// "dataPicker"
			var container = $('.bootstrap-iso form').length > 0 ? $(
					'.bootstrap-iso form').parent() : "body";
			var options = {
				format : 'dd/mm/yyyy',
				language : "pt-BR",
				container : container,
				todayHighlight : true,
				autoclose : true,
				orientation : 'bottom',

			};
			date_input.datepicker(options);
		});