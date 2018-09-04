package br.com.lojapet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import br.com.lojapet.model.Produto;
import br.com.lojapet.model.xml.XmlParaProduto;

public class Teste {

	public static void main(String[] args) {

		
		//extrair produtos da NFe 
		//puxar funcionalidade se necessario
		
		
		
		try {
			// objetos para construir e fazer a leitura do documento
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			// abre e faz o parser de um documento xml de acordo com o nome passado no
			// parametro
			Document doc = builder.parse ("C:\\file.xml");

			// cria uma lista de det(Serviços da NFe). Busca no documento todas as tag det
			
			
			XmlParaProduto xmlParaProduto = new XmlParaProduto(doc);
			List<Produto> produtos = xmlParaProduto.constroiProduto();
			
			System.out.println("saida do produto");
			
			for (Produto produto2 : produtos) {
				System.out.println(produto2.getValorCusto());
				System.out.println(produto2);
				BigDecimal cem = new BigDecimal(100);
				produto2.setValorCusto(produto2.getValorCusto().add(cem));
				System.out.println(produto2.getValorCusto());
			}

		} catch (ParserConfigurationException ex) {
			Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SAXException ex) {
			Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	

}
