import java.net.*;
import java.util.*;
import java.awt.*;
import java.applet.*;
import javax.swing.*;
import javax.swing.event.*;

public class ContatoAnibal extends JApplet {
	
	// a função do HashMap será associar títulos a endereços de busca
	private HashMap<String, URL> websiteInfo;
	
	private ArrayList<String> titulos;
	
	private JList listaPrincipal;
	
	// connstruindo o método init()
	public void init() {
		websiteInfo = new HashMap<String, URL>();
		titulos = new ArrayList<String>();
		
		peguedohtml();
		// a linha abaixo adiciona o título ao applet
		add(new JLabel("quais sites você gostaria de visitar?"), BorderLayout.NORTH);
		// a lista abaixo adiciona a lista de títulos
		listaPrincipal = new JList(titulos.toArray());
		
	// adicionando event handlers
		listaPrincipal.addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						// getSelectedValue() é o método que pega o título e armazena no objeto "balito"
						Object balito = listaPrincipal.getSelectedValue();
						
						// a linha abaixo pega a URL a que balito está associado
						URL newDocument = websiteInfo.get(balito);
								
						// a linha abaixo quer identificar o navegador
						AppletContext browser = getAppletContext();
						
						// a linha abaixo dá o comando para o navegador te levar à URL associada ao título
						browser.showDocument(newDocument);
					}	
				}		
		);
		add(new JScrollPane(listaPrincipal), BorderLayout.CENTER);
		
		// construindo pegueDoHTML()
		private void peguedohtml(){
			String titulo;
			String endereco;
			URL url;
			int contador = 0;
			titulo = getParameter("titulo"+contador);
			
			while(titulo!=null) {
				endereco = getParameter("endereco"+ contador);
				try {
					// o método abaixo cria uma url a partir do objeto "endereco"
					// endereco não é mais que uma String de texto
					url = new URL(endereco);
					websiteInfo.put(titulo,url);
					titulos.add(titulo);
				}catch(MalformedURLException urlException) {
					urlException.printStackTrace();
				}
				++contador;
				titulo = getParameter("titulo"+contador);
			}
		
		}				
			
	}
}
