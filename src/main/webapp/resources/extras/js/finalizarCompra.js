

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

$(document).on("change", "#parcela", function() {

	var venda = moedaParaNumero($('#valorFinal').val());
	var parcelas = moedaParaNumero($('#parcela').val());
	if (venda > 0 && parcelas > 0) {
		var total = venda / parcelas;
		$("#valorParcelamento").html(numeroParaMoeda(total.toFixed(2)));

	}
	
	if (venda < 0 || parcelas < 0) {
		$("#valorParcelamento").html(0);

	}
	

});

$(document).on("change", "#desconto", function() {

	var venda = moedaParaNumero($('#subtotal').val());
	var desconto = moedaParaNumero($('#desconto').val());
	var parcela = $('#parcelas').val();
	
	console.log("desconto" + desconto);
	
	if (venda > 0 && desconto > 0) {
		var total = venda - desconto;
		var totalParcelado = total / parcela;
		
		$('#total').val(numeroParaMoeda(total.toFixed(2)));
		
		$(".totalParcelado").each(function(){
			$(this).val(numeroParaMoeda(totalParcelado.toFixed(2)));
       });
	}
	console.log("desconto" + desconto);
	if (isNaN(desconto)) {
		console.log("desconto" + desconto);
		var total = venda;
		
		$('#total').val(numeroParaMoeda(total.toFixed(2)));
		
		var totalParcelado = total / parcela;
		$(".totalParcelado").each(function(){
			$(this).val(numeroParaMoeda(totalParcelado.toFixed(2)));
		});
	}

});


$(document).on("change", "#total", function() {

	var venda = moedaParaNumero($('#subtotal').val());
	var valorFinal = moedaParaNumero($('#total').val());
	var parcela = $('#parcelas').val();

	if (venda > 0 && valorFinal > 0) {
		var total = venda - valorFinal;
		var totalParcelado = valorFinal / parcela;
		$('#desconto').val(numeroParaMoeda(total.toFixed(2)));

		$(".totalParcelado").each(function(){
			$(this).val(numeroParaMoeda(totalParcelado.toFixed(2)));
		});
	}

});

$(document).on("change", ".valorCusto", function() {
	
	var valorCusto = moedaParaNumero($('.valorCusto').val());
	var quantidade = $('.quantidadeProduto').val();
	var parcela = $('#parcelas').val();

	if (valorCusto > 0 && quantidade > 0) {
		var saida = valorCusto * quantidade;
		$('#subtotal').val(numeroParaMoeda(saida.toFixed(2)));
		$('#desconto').val(0);
		$('#total').val(numeroParaMoeda(saida.toFixed(2)));
		var totalParcelado = saida / parcela;
		$(".totalParcelado").each(function(){
			$(this).val(numeroParaMoeda(totalParcelado.toFixed(2)));
		});
		
	}
	
});
$(document).on("change", ".quantidadeProduto", function() {
	
	var valorCusto = moedaParaNumero($('.valorCusto').val());
	var quantidade = $('.quantidadeProduto').val();
	var parcela = $('#parcelas').val();

	if (valorCusto > 0 && quantidade > 0) {
		var saida = valorCusto * quantidade;
		$('#subtotal').val(numeroParaMoeda(saida.toFixed(2)));
		$('#desconto').val(0);
		$('#total').val(numeroParaMoeda(saida.toFixed(2)));
		var totalParcelado = saida / parcela;
		$(".totalParcelado").each(function(){
			$(this).val(numeroParaMoeda(totalParcelado.toFixed(2)));
		});
		
	}
	
});




$(document).on("change", "#input-search", function() {
	if(!$(this).val()){
		$("#idCliente").val("");
		
	} 
	
	
});

$("input[name='parcela']").TouchSpin({
	initval : 1,
	min : 1,
	max : 12,

});
$(".quantidadeProduto").TouchSpin({
	initval : 1,
	min : 1,
	
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

$("input[name='parcelas']").TouchSpin({
    min: 1,
    max:12
    
});



$(document)

.ready(
		function() {
			
			   $('.dateTimePicker').datetimepicker({
				   locale: 'pt-br',
				   
				   
			    });
			   var date_input = $('.datePicker'); //our date input has the name "dataPicker"
               var container = $('.bootstrap-iso form').length > 0 ? $(
                       '.bootstrap-iso form').parent() :
                   "body";
               var options = {
                   format: 'dd/mm/yyyy',
                   language: "pt-BR",
                   container: container,
                   todayHighlight: true,
                   autoclose: true,
                   orientation: 'bottom',
                   
               };
               date_input.datepicker(options);
			   
			  
		});
$(document)

.ready(
		function() { 
			var parcelas= $('#parcelas').val();
			if(parcelas>1){
				$('#input-search').attr("required", true);
				
			}
			
			
			
		});




//
//$(document).ready(function() {
//	$('#input-search').autocomplete({
//		source : '${pageContext.request.contextPath }/carrinho/search'
//	});
//});
