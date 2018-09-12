
 function moedaParaNumero(valor)
 {
     return isNaN(valor) == false ? parseFloat(valor) :   parseFloat(valor.replace("R$","").replace(".","").replace(",","."));
 }
 function numeroParaMoeda(n, c, d, t)
 {
     c = isNaN(c = Math.abs(c)) ? 2 : c, d = d == undefined ? "," : d, t = t == undefined ? "." : t, s = n < 0 ? "-" : "", i = parseInt(n = Math.abs(+n || 0).toFixed(c)) + "", j = (j = i.length) > 3 ? j % 3 : 0;
     return s + (j ? i.substr(0, j) + t : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t) + (c ? d + Math.abs(n - i).toFixed(c).slice(2) : "");
 }
 
 
   $(document).on("change", ".margemDeLucro", function () {

	   var compra = moedaParaNumero(  $('.valorDeCompra').val());
	   var lucro = moedaParaNumero(  $('.margemDeLucro').val());

       if ( lucro > 0 && compra > 0) {
           var total = (compra+((compra*lucro)/100));
           $('.valorDeVenda').val(numeroParaMoeda(total.toFixed(2)));
       } 

   });
   
   $(document).on("change", ".valorDeCompra", function () {

	   var compra = moedaParaNumero(  $('.valorDeCompra').val());
	   var lucro = moedaParaNumero(  $('.margemDeLucro').val());
	   var venda = moedaParaNumero( $('.valorDeVenda').val());

       if ( lucro > 0 && compra > 0) {
           var total = (compra+((compra*lucro)/100));
           $('.valorDeVenda').val(numeroParaMoeda(total.toFixed(2)));
       } 
       
       if ( venda > 0 && compra > 0) {
           var lucro  = venda - compra;
           var  lucroComCompra = lucro / compra * 100;
           $('.margemDeLucro').val(lucroComCompra.toFixed(2).replace('.',','));
       } 

   });
 
   
   $(document).on("change", ".valorDeVenda", function () {
	   var venda = moedaParaNumero( $('.valorDeVenda').val());
	   var compra = moedaParaNumero(  $('.valorDeCompra').val());
	   var lucro = moedaParaNumero(  $('.margemDeLucro').val());

	   if (venda > 0 && compra > 0 ) {
           var lucro  = venda - compra;
           var  lucroComCompra = lucro / compra * 100;
           $('.margemDeLucro').val(lucroComCompra.toFixed(2).replace('.',','));
           console.log(' venda e compra mudança venda');

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
   
   <!-- previne que ENTER submeta o formulario -->
   $(document).ready(function() {

       $(window).keydown(function(event){

           if((event.keyCode == 13) && ($(event.target)[0]!=$("textarea")[0])) {

               event.preventDefault();

               return false;

           }

       });

   });

   
//   $( document ).ready(function() {
//		
//		var listProdutoEstoque = [];
//		
//		// Customer-Form submit
//		$('#modalProdutoEstoque').on("click",function(){
//			console.log('fui chamado');
//			// get data from submit form
//			
//			var produtoEstoque = {
//					id:"",
//					produto:{},
//					estoque : $( "#estoqueSelecionado" ).val(),
//	    			quantidade : $("#quantidade").val(),
//	    			
//
//	    	}
//			listProdutoEstoque.push(produtoEstoque);
//			console.log('saida do modal ');
//			console.log(produtoEstoque);
//			
//	    	resetData();
//		});
//	    
//		// Submit List of Customer to Back-End server
//		$('#modalProdutoEstoque').click(function(){
//			ajaxPost();
//		});
//		
//	    function ajaxPost(){
//	    	
//	    	// DO POST
//	    	$.ajax({
//				type : "POST",
//				contentType : "application/json",
//				accept: 'text/plain',
//				url : window.location + "/adicionarEstoque",
//				data : JSON.stringify(listProdutoEstoque),
//				dataType: 'text',
//				success : function(result) {
//					// clear customer body
////					$('#customerTable tbody').empty();
////					$('#customerTable').hide();
//					
//					// re-set customer table list
//					
//					// fill successfully message on view
////					$("#postResultDiv").html("<p style='background-color:#7FA7B0; color:white; padding:20px 20px 20px 20px'>" + 
////												result +
////											  "</p>");
//				},
//				error : function(e) {
////					alert("Error!")
//					console.log("ERROR: ", e);
//				}
//			});
//	    }
//	    
//	    function resetData(){
//	    	$("#name").val("");
//	    	$("#age").val("");
//	    	$("#street").val("");
//	    	$("#postcode").val("");
//	    }
//	});
   