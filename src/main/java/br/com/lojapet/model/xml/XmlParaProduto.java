package br.com.lojapet.model.xml;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
	private void extraiProdutos() {

		NodeList listaDeDet = doc.getElementsByTagName("det");

		// varredura na lista de det
		for (int i = 0; i < listaDeDet.getLength(); i++) {

			// pego cada item (det) como um node
			Node noDet = listaDeDet.item(i);

			// verifica se o noDet é do tipo element (e não do tipo texto etc)
			if (noDet.getNodeType() == Node.ELEMENT_NODE) {

				// caso seja um element, converte o no Det em Element det
				Element elementoDet = (Element) noDet;

				// pegar o atributo do element
				String id = elementoDet.getAttribute("nItem");

				// imprimindo o nItem
//				System.out.println();
//				System.out.println("N ITEM = " + id);

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

	private void extraiCadaElementoProd(Element elementoFilho, Produto produto) {
		
		// verifico em qual filho estamos pela tag
		switch (elementoFilho.getTagName()) {
		case "xProd":
			// imprimo o nome
//			System.out.println("NOME=" + elementoFilho.getTextContent());
			produto.setNome(elementoFilho.getTextContent());
			break;

		case "uCom":
			// imprimo a idade
//			System.out.println("Unidade=" + elementoFilho.getTextContent());
			produto.setUnidade(elementoFilho.getTextContent());
			break;

		case "qCom":
			// imprimo o peso
//			System.out.println("Quantidade=" + elementoFilho.getTextContent());
			produto.setQuantidade( (long) Double.parseDouble(elementoFilho.getTextContent()));
			break;

		case "vUnCom":
			// imprimo o peso
//			System.out.println("Valor Unitario=" + elementoFilho.getTextContent());
			// Create a DecimalFormat that fits your requirements
			DecimalFormatSymbols symbols = new DecimalFormatSymbols();
			symbols.setGroupingSeparator(',');
			symbols.setDecimalSeparator('.');
			String pattern = "#,##0.0#";
			DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
			decimalFormat.setParseBigDecimal(true);

			// parse the string
			BigDecimal bigDecimal = BigDecimal.ZERO;
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
		case "vProd":
			// imprimo o peso
//			System.out.println("Valor total=" + elementoFilho.getTextContent());
			break;
		case "cEAN":
			// imprimo o peso
//			System.out.println("Codigo de barras=" + elementoFilho.getTextContent());
			if (!elementoFilho.getTextContent().isEmpty()) {
				produto.setCodBarras(Long.parseLong(elementoFilho.getTextContent()));
				
			}
			break;
		}
		
	}

}
