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