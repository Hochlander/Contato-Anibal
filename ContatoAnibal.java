import java.net.*;
import java.util.*;
import java.awt.*;
import java.applet.*;
import javax.swing.*;
import javax.swing.event.*;

public class ContatoAnibal extends JApplet {
	
	// a fun��o do HashMap ser� associar t�tulos a endere�os de busca
	private HashMap<String, URL> websiteInfo;
	
	private ArrayList<String> titulos;
	
	private JList listaPrincipal;
	
	// connstruindo o m�todo init()
	public void init() {
		websiteInfo = new HashMap<String, URL>();
		titulos = new ArrayList<String>();
		
		peguedohtml();
		// a linha abaixo adiciona o t�tulo ao applet
		add(new JLabel("quais sites voc� gostaria de visitar?"), BorderLayout.NORTH);
		// a lista abaixo adiciona a lista de t�tulos
		listaPrincipal = new JList(titulos.toArray());
		
	// adicionando event handlers
		listaPrincipal.addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						// getSelectedValue() � o m�todo que pega o t�tulo e armazena no objeto "balito"
						Object balito = listaPrincipal.getSelectedValue();
						
						// a linha abaixo pega a URL a que balito est� associado
						URL newDocument = websiteInfo.get(balito);
								
						// a linha abaixo quer identificar o navegador
						AppletContext browser = getAppletContext();
						
						// a linha abaixo d� o comando para o navegador te levar � URL associada ao t�tulo
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
					// o m�todo abaixo cria uma url a partir do objeto "endereco"
					// endereco n�o � mais que uma String de texto
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
