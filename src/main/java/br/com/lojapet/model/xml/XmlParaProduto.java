package br.com.lojapet.model.xml;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.com.lojapet.model.Compra;
import br.com.lojapet.model.Fornecedor;
import br.com.lojapet.model.Produto;

public class XmlParaProduto {

	private List<Produto> produtos = new ArrayList<>();
	private Document doc;

	public XmlParaProduto(Document doc) {
		this.doc = doc;
	}

	public List<Produto> constroiProduto() {
		extraiProdutos();
		return produtos;
	}

	public Fornecedor getNomeFornecedor() {
		return extraiFornecedor();
	}

	public Compra getAtributosBasicosCompra() {
		Compra compra = new Compra();
		extraiCompra(compra);
		extraiDataEmissao(compra);
		return compra;
	}

	private Compra extraiCompra(Compra compra) {

		NodeList listaDeTotal = doc.getElementsByTagName("total");

		// varredura na lista de total
		for (int i = 0; i < listaDeTotal.getLength(); i++) {

			// pega cada item (total) como um node
			Node noTotal = listaDeTotal.item(i);

			// verifica se o noTotal é do tipo element (e não do tipo texto etc)
			if (noTotal.getNodeType() == Node.ELEMENT_NODE) {

				// caso seja um element, converte o no noTotal em Element Total
				Element elementoTotal = (Element) noTotal;

				// recupero os nos filhos do elemento det
				NodeList listaDeFilhosDeTotal = elementoTotal.getChildNodes();

				// varredura na lista de filhos do elemento det
				for (int j = 0; j < listaDeFilhosDeTotal.getLength(); j++) {

					// pega cada item (total) como um node
					Node noICMSTot = listaDeFilhosDeTotal.item(j);

					if (noICMSTot.getNodeType() == Node.ELEMENT_NODE) {

						// caso seja um element, converte o no noTotal em Element Total
						Element elementoICMSTot = (Element) noICMSTot;

						// recupero os nos filhos do elemento det
						NodeList listaDeFilhosICMSTot = elementoICMSTot.getChildNodes();

						for (int x = 0; x < listaDeFilhosICMSTot.getLength(); x++) {
							// pega cada item (total) como um node
							Node noFilho = listaDeFilhosICMSTot.item(x);

							// verifico se são tipo element
							if (noFilho.getNodeType() == Node.ELEMENT_NODE) {

								// converto o no filho em element filho
								Element elementoFilho = (Element) noFilho;

								extraiValorCompra(elementoFilho, compra);

							}

						}

					}

				}
			}

		}

		return compra;
	}

	private Compra extraiDataEmissao(Compra compra) {

		NodeList listaDeTotal = doc.getElementsByTagName("protNFe");

		// varredura na lista de total
		for (int i = 0; i < listaDeTotal.getLength(); i++) {

			// pega cada item (total) como um node
			Node noTotal = listaDeTotal.item(i);

			// verifica se o noTotal é do tipo element (e não do tipo texto etc)
			if (noTotal.getNodeType() == Node.ELEMENT_NODE) {

				// caso seja um element, converte o no noTotal em Element Total
				Element elementoTotal = (Element) noTotal;

				// recupero os nos filhos do elemento det
				NodeList listaDeFilhosDeTotal = elementoTotal.getChildNodes();

				// varredura na lista de filhos do elemento det
				for (int j = 0; j < listaDeFilhosDeTotal.getLength(); j++) {

					// pega cada item (total) como um node
					Node noICMSTot = listaDeFilhosDeTotal.item(j);

					if (noICMSTot.getNodeType() == Node.ELEMENT_NODE) {

						// caso seja um element, converte o no noTotal em Element Total
						Element elementoICMSTot = (Element) noICMSTot;

						// recupero os nos filhos do elemento det
						NodeList listaDeFilhosICMSTot = elementoICMSTot.getChildNodes();

						for (int x = 0; x < listaDeFilhosICMSTot.getLength(); x++) {
							// pega cada item (total) como um node
							Node noFilho = listaDeFilhosICMSTot.item(x);

							// verifico se são tipo element
							if (noFilho.getNodeType() == Node.ELEMENT_NODE) {

								// converto o no filho em element filho
								Element elementoFilho = (Element) noFilho;

								extraiDataEmissaoCompra(elementoFilho, compra);

							}

						}

					}

				}
			}

		}

		return compra;
	}

	private void extraiDataEmissaoCompra(Element elementoFilho, Compra compra) {
		switch (elementoFilho.getTagName()) {
		case "dhRecbto":

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = sdf.parse((elementoFilho.getTextContent()));
			} catch (DOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);

			compra.setDataEmissao(cal);
			break;
		}

	}

	private void extraiValorCompra(Element elementoFilho, Compra compra) {
		switch (elementoFilho.getTagName()) {
		case "vNF":
			// imprimo o nome
			// System.out.println("NOME=" + elementoFilho.getTextContent());
			BigDecimal total = new BigDecimal(elementoFilho.getTextContent());
			compra.setTotal(total);
			break;
		}

	}

	private Fornecedor extraiFornecedor() {
		Fornecedor fornecedor = new Fornecedor();
		NodeList listaDeEmit = doc.getElementsByTagName("emit");
		// varredura na lista de emit
		for (int i = 0; i < listaDeEmit.getLength(); i++) {

			// pega cada item (emit) como um node
			Node noEmit = listaDeEmit.item(i);

			// verifica se o noEmit é do tipo element (e não do tipo texto etc)
			if (noEmit.getNodeType() == Node.ELEMENT_NODE) {

				// caso seja um element, converte o no Det em Element det
				Element elementoEmit = (Element) noEmit;

				// recupero os nos filhos do elemento det
				NodeList listaDeFilhosDeEmit = elementoEmit.getChildNodes();
				for (int j = 0; j < listaDeFilhosDeEmit.getLength(); j++) {

					Node noFilho = listaDeFilhosDeEmit.item(j);

					// verifico se são tipo element
					if (noFilho.getNodeType() == Node.ELEMENT_NODE) {

						// converto o no filho em element filho
						Element elementoFilho = (Element) noFilho;

						extraiCadaElementoEmit(elementoFilho, fornecedor);

					}

				}

			}

		}

		return fornecedor;
	}

	private void extraiProdutos() {

		NodeList listaDeDet = doc.getElementsByTagName("det");

		// varredura na lista de det
		for (int i = 0; i < listaDeDet.getLength(); i++) {

			// pega cada item (det) como um node
			Node noDet = listaDeDet.item(i);

			// verifica se o noDet é do tipo element (e não do tipo texto etc)
			if (noDet.getNodeType() == Node.ELEMENT_NODE) {

				// caso seja um element, converte o no Det em Element det
				Element elementoDet = (Element) noDet;

				// pegar o atributo do element
				String id = elementoDet.getAttribute("nItem");

				// imprimindo o nItem
				// System.out.println();
				// System.out.println("N ITEM = " + id);

				// recupero os nos filhos do elemento det
				NodeList listaDeFilhosDeDet = elementoDet.getChildNodes();

				// varredura na lista de filhos do elemento det
				for (int j = 0; j < listaDeFilhosDeDet.getLength(); j++) {

					// pego cada item (prod) como um node
					Node noProd = listaDeFilhosDeDet.item(j);

					// verifica se o noProd é do tipo element (e não do tipo texto etc)
					if (noProd.getNodeType() == Node.ELEMENT_NODE) {

						// caso seja um element, converte o no prod em Element prod
						Element elementoProd = (Element) noProd;

						// recupero os nos filhos do elemento prod
						NodeList listaDeFilhosDeProd = elementoProd.getChildNodes();

						Produto produto = new Produto();
						// varredura na lista de filhos do elemento prod
						for (int x = 0; x < listaDeFilhosDeProd.getLength(); x++) {
							// crio um no com o cada tag filho dentro do no prod
							Node noFilho = listaDeFilhosDeProd.item(x);

							// verifico se são tipo element
							if (noFilho.getNodeType() == Node.ELEMENT_NODE) {

								// converto o no filho em element filho
								Element elementoFilho = (Element) noFilho;

								extraiCadaElementoProd(elementoFilho, produto);

							}

						}
						Produto p = new Produto();
						if (!produto.equals(p)) {
							produtos.add(produto);

						}

					}

				}
			}
		}
	}

	private void extraiCadaElementoEmit(Element elementoFilho, Fornecedor fornecedor) {
		switch (elementoFilho.getTagName()) {
		case "xNome":
			// imprimo o nome
			// System.out.println("NOME=" + elementoFilho.getTextContent());
			fornecedor.setNome(elementoFilho.getTextContent());
			break;
		}

	}

	private void extraiCadaElementoProd(Element elementoFilho, Produto produto) {
		// Create a DecimalFormat that fits your requirements
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator(',');
		symbols.setDecimalSeparator('.');
		String pattern = "#,##0.0#";
		DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
		decimalFormat.setParseBigDecimal(true);
		BigDecimal bigDecimal = BigDecimal.ZERO;

		// verifico em qual filho estamos pela tag
		switch (elementoFilho.getTagName()) {
		case "xProd":
			// set o nome
			produto.setNome(elementoFilho.getTextContent());
			break;

		case "uCom":
			// set a Unidade
			produto.setUnidade(elementoFilho.getTextContent());
			break;

		case "qCom":
			// set quantidade
			produto.setQuantidade((long) Double.parseDouble(elementoFilho.getTextContent()));
			break;

		case "vUnCom":
			// set o valor custo

			// parse the string
			try {
				bigDecimal = (BigDecimal) decimalFormat.parse(elementoFilho.getTextContent());
			} catch (DOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			produto.setValorCusto(bigDecimal);
			break;
		case "vUnTrib":
			// set o valor total

			// parse the string
			try {
				bigDecimal = (BigDecimal) decimalFormat.parse(elementoFilho.getTextContent());
			} catch (DOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			produto.setValorVenda(bigDecimal);
			break;
		case "vProd":
			// set o valor multiplicado com quantidade
			// System.out.println("Valor total=" + elementoFilho.getTextContent());
			break;
		case "cEAN":
			// set codigo de barras
			if (!elementoFilho.getTextContent().isEmpty()) {
				produto.setCodBarras(Long.parseLong(elementoFilho.getTextContent()));

			}
			break;
		}

	}

}
