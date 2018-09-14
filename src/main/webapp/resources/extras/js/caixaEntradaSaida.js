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
			
			   $('.dataPicker').datetimepicker({locale: 'pt-br'});
		});