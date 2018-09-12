<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
            <%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
                <%@taglib prefix="customTags" tagdir="/WEB-INF/tags"%>
                    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
                        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
                            <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

                                <fmt:message key="cliente.cadastro.title" var="title" />
                                <customTags:page title="${title}" cadastroCliente="active">

                                    <jsp:attribute name="extraScripts">
										
										<!-- Focus no input -->
										<script src="/resources/extras/js/focusPrimeiroInput.js"></script>
                                        </script>
										
                                        <!-- toggle -->
                                        <link href="/resources/plugins/bootstrap-toggle/bootstrap-toggle.min.css" rel="stylesheet">
                                        <script src="/resources/plugins/bootstrap-toggle/bootstrap-toggle.min.js"></script>

                                        <!-- troca cpf cnpj -->
                                        <script>
                                            $(document).ready(function() {
                                                cpfCnpj();
                                            });

                                            $('#toggle-demo').bind('change', function() {
                                                cpfCnpj();

                                            });

                                            function cpfCnpj() {
                                                if ($('#toggle-demo').is(':checked')) {
                                                    $("#cpf").show();
                                                    $("#cnpj").hide();
                                                } else {
                                                    $("#cnpj").show();
                                                    $("#cpf").hide();
                                                }
                                            }

                                        </script>




                                          <!-- Date Picker -->
  <link rel="stylesheet" href="/resources/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
  <!-- Daterange picker -->
  <link rel="stylesheet" href="/resources/bower_components/bootstrap-daterangepicker/daterangepicker.css">
  
<script src="/resources/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
  <script src="/resources/bower_components/bootstrap-datepicker/js/locales/bootstrap-datepicker.pt-BR.js"></script>
 			<!-- 		datepicker -->
                                        <script>
                                        
                                            $(document)
                                            
                                                .ready(
                                                    function() {
                                                        var date_input = $('.dataPicker'); //our date input has the name "dataPicker"
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
                                                    })

                                        </script>




                                        <!-- InputMask -->
                                        <script src="/resources/plugins/jquerymask/jquery.mask.js"></script>
                                        <!-- input mask -->
                                        <script>
                                            var SPMaskBehavior = function(val) {
                                                    return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' :
                                                        '(00) 0000-00009';
                                                },
                                                spOptions = {
                                                    onKeyPress: function(val, e, field, options) {
                                                        field.mask(SPMaskBehavior.apply({}, arguments), options);
                                                    }
                                                };
                                            $('.numero').mask('0#');
                                            $('.sp_celphones').mask(SPMaskBehavior, spOptions);
                                            $('.cpf').mask('000.000.000-00', {
                                                reverse: true
                                            });
                                            $('.cnpj').mask('00.000.000/0000-00', {
                                                reverse: true
                                            });
                                            $('.cep').mask('00000-000');
                                            $('.cep_with_callback').mask(
                                                '00000-000', {
                                                    onComplete: function(cep) {
                                                        console.log('Mask is done!:', cep);
                                                    },
                                                    onKeyPress: function(cep, event, currentField, options) {
                                                        console.log('An key was pressed!:', cep, ' event: ', event,
                                                            'currentField: ', currentField.attr('class'),
                                                            ' options: ', options);
                                                    },
                                                    onInvalid: function(val, e, field, invalid, options) {
                                                        var error = invalid[0];
                                                        console.log("Digit: ", error.v,
                                                            " is invalid for the position: ", error.p,
                                                            ". We expect something like: ", error.e);
                                                    }
                                                });

                                        </script>


                                        <!-- Auto Complete endereço -->
                                        <script type="text/javascript">
                                            $(document).ready(function() {

                                                function limpa_formulário_cep() {
                                                    // Limpa valores do formulário de cep.
                                                    $("#rua").val("");
                                                    $("#bairro").val("");
                                                    $("#cidade").val("");
                                                    $("#uf").val("");
                                                    $("#ibge").val("");
                                                }

                                                //Quando o campo cep perde o foco.
                                                $("#cep").blur(function() {

                                                    //Nova variável "cep" somente com dígitos.
                                                    var cep = $(this).val().replace(/\D/g, '');

                                                    //Verifica se campo cep possui valor informado.
                                                    if (cep != "") {

                                                        //Expressão regular para validar o CEP.
                                                        var validacep = /^[0-9]{8}$/;

                                                        //Valida o formato do CEP.
                                                        if (validacep.test(cep)) {

                                                            //Preenche os campos com "..." enquanto consulta webservice.
                                                            $("#rua").val("...");
                                                            $("#bairro").val("...");
                                                            $("#cidade").val("...");
                                                            $("#uf").val("...");
                                                            $("#ibge").val("...");

                                                            //Consulta o webservice viacep.com.br/
                                                            $.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?", function(dados) {

                                                                if (!("erro" in dados)) {
                                                                    //Atualiza os campos com os valores da consulta.
                                                                    $("#rua").val(dados.logradouro);
                                                                    $("#bairro").val(dados.bairro);
                                                                    $("#cidade").val(dados.localidade);
                                                                    $("#uf").val(dados.uf);
                                                                    $("#ibge").val(dados.ibge);
                                                                } //end if.
                                                                else {
                                                                    //CEP pesquisado não foi encontrado.
                                                                    limpa_formulário_cep();
                                                                    alert("CEP não encontrado.");
                                                                }
                                                            });
                                                        } //end if.
                                                        else {
                                                            //cep é inválido.
                                                            limpa_formulário_cep();
                                                            alert("Formato de CEP inválido.");
                                                        }
                                                    } //end if.
                                                    else {
                                                        //cep sem valor, limpa formulário.
                                                        limpa_formulário_cep();
                                                    }
                                                });
                                            });

                                        </script>



                                    </jsp:attribute>
                                    <jsp:body>
                                        <!-- Content Header (Page header) -->
                                        <section class="content-header">
                                            <h1>
                                                <fmt:message key="cliente.cadastro.h3" />
                                            </h1>
                                            <ol class="breadcrumb">
                                                 <li><a href="/"><i class="fa fa-dashboard"></i><fmt:message key="navegacao.home" /></a></li>
											        <li class="active"><a href="#"><fmt:message key="navegacao.atendimento" /></a></li>
											        <li class="active"><a href="#"><fmt:message key="navegacao.cadastro" /></a></li>
											        <li class="active"><a href="/cliente"><fmt:message key="navegacao.cliente" /></a></li>
                                            </ol>
                                        </section>

                                        <!-- Main content -->
                                        <section class="content">
                                            <!-- Small boxes (Stat box) -->

                                            <div class="row">

                                                <!-- right column -->
                                                <div class="col-lg-12 col-md-12 col-sm-12">
                                                    <!-- Horizontal Form -->
                                                    <div class="box box-primary">
                                                        <div class="box-header with-border">
                                                            <!--               <h3 class="box-title"> -->
                                                            <%--               <fmt:message key="cliente.cadastro.h3" /> --%>
                                                                <!-- 			  </h3> -->
                                                        </div>
                                                        <!-- /.box-header -->
                                                        <!-- form start -->
                                                        <form:form action="${s:mvcUrl('CC#gravarCliente').build() }" method="post" modelAttribute="cliente" autocomplete="off">

                                                            <div class="box-body">

                                                                    <div class="form-group col-lg-4 col-md-6 col-sm-12">
													                  <label ><fmt:message key="cliente.cadastro.nomeCompleto" /></label>
													                 <form:errors path="nomeCompleto" class="text-danger" element="div" />
													                  <fmt:message key="cliente.cadastro.nomeCompletoPlaceHolder" var="nomeCompletoPlaceHolder"/>
													                  <form:input path="nomeCompleto" cssClass="form-control"  placeholder="${nomeCompletoPlaceHolder}"  required="true" />
													                 
													                </div>
													                
													    <!-- radio -->
										                <div class="form-group col-lg-4 col-md-6 col-sm-12 ">
										               	<label class="control-label" for="input-type">
										               			<fmt:message key="cliente.cadastro.sexo" />
										               		</label>
										               	    <div id="input-type" class="row">
          													  <div class="col-sm-6">
												                     <label class="radio-inline">
														                    <form:radiobutton path="sexo" value="Masculino"/> 
														                     <fmt:message key="cliente.cadastro.masculino" />
														              </label>
													              </div>
           														 <div class="col-sm-6">
												                    <label class="radio-inline">
														              <form:radiobutton path="sexo" value="Feminino"/> 
														               <fmt:message key="cliente.cadastro.feminino" />
														             </label>
													             </div>
													        </div>
                                                                 <form:errors class="text-danger" path="sexo" />
										                </div>


                                                                    <div class="form-group col-lg-4 col-md-12 col-sm-12 ">
                                                                        <label><fmt:message key="cliente.cadastro.cpfCnpj" /></label>
                                                                        <div class="input-group">

                                                                            <span class="input-group-btn">
		                  		                  	
																                  	<form:checkbox data-toggle="toggle" data-on="CPF"
																								data-off="CNPJ" data-onstyle="success" id="toggle-demo"
																								data-offstyle="primary" path="tipoPessoa"  class="btn btn-default" />
															                 
															                   </span>



                                                                            <fmt:message key="cliente.cadastro.cpfPlaceHolder" var="cpfPlaceHolder" />
                                                                            <form:input path="cpf" cssClass="form-control cpf" placeholder="${cpfPlaceHolder}" maxlength="11" />




                                                                            <fmt:message key="cliente.cadastro.cnpjPlaceHolder" var="cnpjPlaceHolder" />
                                                                            <form:input path="cnpj" cssClass="form-control cnpj" placeholder="${cnpjPlaceHolder}" maxlength="14" />
                                                                        </div>

                                                                            <form:errors class="text-danger" path="cpf" />
                                                                            <form:errors class="text-danger" path="cnpj" />

                                                                    </div>
                                                                    
                                                                    
											                      <div class="form-group col-lg-2 col-md-6 col-sm-6">
													                 <div id="console-event">   
														                  <label >
														                	 <fmt:message key="cliente.cadastro.clienteSemNome" />
														                 </label>
													                 </div>
                                                                            <span class="input-group-btn">
												                  
												                  
												                  <form:checkbox data-toggle="toggle" 
												                 	 	 data-on="Ativo" data-off="Desativado"
												                  		 data-onstyle="success" id="toggle-demo"
																		 data-offstyle="danger" path="clienteSemNome"  class="btn btn-default" />
												                  
															                   </span>
												               
												                  
												                <form:errors class="text-danger" path="clienteSemNome" />
												                </div>
                                                                    
                                                                    
                                                                    <div class="form-group col-lg-2 col-md-6 col-sm-12 ">

                                                                        <label><fmt:message key="cliente.cadastro.dataNascimento" /></label>
                                                                        <form:input type="text" path="dataNascimento" cssClass="form-control dataPicker" id="date" name="date" placeholder="${data} DD/MM/AAAA" />
                                                                            <form:errors class="text-danger" path="dataNascimento" />
                                                                    </div>





                                                                    <div class="form-group col-lg-4 col-md-6 col-sm-12 ">
                                                                        <label>
														                  	<fmt:message	key="cliente.cadastro.telefone" />
														                  </label>
                                                                        <div class="input-group">
                                                                            <div class="input-group-addon">
                                                                                <i class="fa fa-phone"></i>
                                                                            </div>
                                                                            <form:input path="telefone" type="text" class="form-control sp_celphones" id="sp_celphones" />
                                                                        </div>
                                                                            <form:errors class="text-danger" path="telefone" />
                                                                    </div>


                                                                    <div class="form-group col-lg-4 col-md-6 col-sm-12 ">
                                                                        <label><fmt:message key="cliente.cadastro.celular" /></label>
                                                                        <div class="input-group">
                                                                            <div class="input-group-addon">
                                                                                <i class="fa fa-mobile"></i>
                                                                            </div>
                                                                            <form:input path="celular" type="text" class="form-control sp_celphones" id="sp_celphones" />
                                                                        </div>
                                                                            <form:errors class="text-danger" path="celular" />
                                                                    </div>




                                                                    <div class="form-group col-lg-4 col-md-6 col-sm-12 ">
                                                                        <label><fmt:message key="cliente.cadastro.email" /></label>
                                                                        <div class="input-group">
                                                                            <div class="input-group-addon">
                                                                                <i class="fa  fa-at"></i>
                                                                            </div>
                                                                            <fmt:message key="cliente.cadastro.emailPlaceHolder" var="emailPlaceholder" />
                                                                            <form:input path="email" type="text" cssClass="form-control" placeholder="${emailPlaceholder}" />
                                                                        </div>
                                                                            <form:errors class="text-danger" path="email" />
                                                                    </div>




                                                                    <div class="form-group col-lg-3 col-md-6 col-sm-12 ">
                                                                        <label><fmt:message key="cliente.cadastro.cep" /></label>
                                                                        <fmt:message key="cliente.cadastro.cepPlaceHolder" var="placeholder" />
                                                                        <form:input path="cep" cssClass="form-control cep" placeholder="${placeholder}" id="cep" onblur="pesquisacep(this.value);" />
                                                                            <form:errors class="text-danger" path="cep" />
                                                                    </div>








                                                                    <div class="form-group col-lg-3 col-md-6 col-sm-12">
                                                                        <label><fmt:message
											key="cliente.cadastro.rua" /></label>
                                                                        <fmt:message key="cliente.cadastro.ruaPlaceHolder" var="placeholder" />
                                                                        <form:input path="rua" cssClass="form-control" placeholder="${placeholder}" id="rua" size="60" />
                                                                            <form:errors class="text-danger" path="rua" />
                                                                    </div>






                                                                    <div class="form-group col-lg-2 col-md-6 col-sm-12">
                                                                        <label><fmt:message
											key="cliente.cadastro.numero" /></label>
                                                                        <fmt:message key="cliente.cadastro.numeroPlaceHolder" var="placeholder" />
                                                                        <form:input path="numero" cssClass="form-control numero" placeholder="${placeholder}" id="numero" />
                                                                            <form:errors class="text-danger" path="numero" />
                                                                    </div>


                                                                    <div class="form-group col-lg-2 col-md-6 col-sm-12">
                                                                        <label><fmt:message
											key="cliente.cadastro.complemento" /></label>
                                                                        <fmt:message key="cliente.cadastro.complementoPlaceHolder" var="placeholder" />
                                                                        <form:input path="complemento" cssClass="form-control" placeholder="${placeholder}" id="complemento" />
                                                                            <form:errors class="text-danger" path="complemento" />
                                                                    </div>






                                                                    <div class="form-group col-lg-4 col-md-5 col-sm-12">
                                                                        <label><fmt:message
											key="cliente.cadastro.bairro" /></label>
                                                                        <fmt:message key="cliente.cadastro.bairroPlaceHolder" var="placeholder" />
                                                                        <form:input path="bairro" cssClass="form-control" placeholder="${placeholder}" id="bairro" size="40" />
                                                                            <form:errors class="text-danger" path="bairro" />
                                                                    </div>








                                                                    <div class="form-group col-lg-4 col-md-5 col-sm-12">
                                                                        <label><fmt:message
											key="cliente.cadastro.cidade" /></label>
                                                                        <fmt:message key="cliente.cadastro.cidadePlaceHolder" var="placeholder" />
                                                                        <form:input path="cidade" cssClass="form-control" placeholder="${placeholder}" id="cidade" size="40" />
                                                                            <form:errors class="text-danger" path="cidade" />
                                                                    </div>









                                                                    <div class="form-group col-lg-2 col-md-2 col-sm-12">
                                                                        <label><fmt:message key="cliente.cadastro.uf" /></label>
                                                                        <fmt:message key="cliente.cadastro.ufPlaceHolder" var="placeholder" />
                                                                        <form:input path="uf" cssClass="form-control" placeholder="${placeholder}" id="uf" size="2" />
                                                                            <form:errors class="text-danger" path="uf" />
                                                                    </div>



                                                                    <div class="form-group col-lg-12 col-md-12 col-sm-12">
                                                                        <label><fmt:message
											key="cliente.cadastro.observacoes" /></label>
                                                                        <fmt:message key="cliente.cadastro.observacoesPlaceHolder" var="observacoesPlaceholder" />
                                                                        <form:textarea path="observacoes" cssClass="form-control" placeholder="${observacoesPlaceholder}" id="observacoes" rows="3" />
                                                                    </div>

                                                                </div>




                                                            <!-- /.box-body -->
                                                            <div class="box-footer">
                                                                <!--                 <button type="submit" class="btn btn-default">Cancel</button> -->
                                                                <button type="submit" class="btn btn-info pull-right btn-lg btn-block">
						<fmt:message key="cliente.cadastro.salvar" />
					</button>
                                                                <div class="clearfix"></div>
                                                            </div>
                                                            <input type="hidden" name="id" value="${cliente.id}" />

                                                            <!-- /.box-footer -->
                                                        </form:form>
                                                    </div>
                                                    <!-- /.box -->

                                                </div>
                                                <!--/.col (right) -->
                                            </div>
                                            <!-- /.row -->


                                        </section>
                                        <!-- /.content -->
                                    </jsp:body>

                                </customTags:page>


                                <!-- bootstrap datepicker -->
                                <link rel="stylesheet" href="../../bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
