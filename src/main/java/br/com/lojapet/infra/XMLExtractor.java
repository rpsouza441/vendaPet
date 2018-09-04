package br.com.lojapet.infra;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import br.com.lojapet.model.Produto;
import br.com.lojapet.model.xml.XmlParaProduto;

@Component
public class XMLExtractor {

	@Autowired
	private HttpServletRequest request;

	public List<Produto> read(MultipartFile file) {
		try {
			
			// objetos para construir e fazer a leitura do documento
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = null;
			try {
				builder = factory.newDocumentBuilder();
			} catch (ParserConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// abre e faz o parser de um documento xml de acordo com o nome passado no
			// parametro
			Document doc = null;
			try {
				doc = builder.parse(file.getInputStream());
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			XmlParaProduto xmlParaProduto = new XmlParaProduto(doc);
			List<Produto> produtos = xmlParaProduto.constroiProduto();
			
			return  produtos;

		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	

}
