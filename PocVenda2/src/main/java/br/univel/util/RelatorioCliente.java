package br.univel.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import br.univel.model.Cliente;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioCliente {


		private String path; // Caminho base
		
		private String pathToReportPackage; // Caminho do package onde estão armazenados os relatórios Jasper.

		public RelatorioCliente() {
			this.path = this.getClass().getClassLoader().getResource("").getPath();
			this.pathToReportPackage = this.path + "br/univel/util/";
			System.out.println(path);
		}
		
		// Método chamado na sua tela de relatório

		public void imprimir(List<Cliente> clientes) throws IOException {
			
			try {
				// Projeto do Relatorio feito no iReport ou outro programa
				// OBS: Para evitar erros, colocar o projeto do Relatorio no mesmo diretorio desta classe, ou seja, ambos devem estar juntos.			

				InputStream is = getClass().getResourceAsStream("RelClientes.jrxml");
				System.out.println(is);
				
				JasperReport report = JasperCompileManager.compileReport(is);

				// passar a lista recebida no método

				JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(clientes, false));
				
				// Diretorio que deseja salvar o relatorio

				JasperExportManager.exportReportToPdfFile(print, "c:/relatorio/RelatorioClientes.pdf"); // exportar o pdf gera

				// Para abrir o relatorio na pasta salva, utilizar o comando abaixo

				Runtime.getRuntime().exec("cmd /c start C:/relatorio/RelatorioClientes.pdf"); // abrir o pdf
				
			} catch (JRException e) {
				e.printStackTrace();
			}
			
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public String getPathToReportPackage() {
			return pathToReportPackage;
		}

		public void setPathToReportPackage(String pathToReportPackage) {
			this.pathToReportPackage = pathToReportPackage;
		}
		
	}


