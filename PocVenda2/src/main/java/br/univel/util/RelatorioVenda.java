package br.univel.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import br.univel.model.Cliente;
import br.univel.model.Produto;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioVenda {


		private String path; // Caminho base
		
		private String pathToReportPackage; // Caminho do package onde estão armazenados os relatórios Jasper.

		public RelatorioVenda() {
			this.path = this.getClass().getClassLoader().getResource("").getPath();
			this.pathToReportPackage = this.path + "br/univel/util/";
			System.out.println(path);
		}
		
		// Método chamado na sua tela de relatório

		public void imprimir(List<Produto> produtos) throws IOException {
			
			try {
				// Projeto do Relatorio feito no iReport ou outro programa
				// OBS: Para evitar erros, colocar o projeto do Relatorio no mesmo diretorio desta classe, ou seja, ambos devem estar juntos.			

				InputStream is = getClass().getResourceAsStream("RelVenda.jrxml");
				System.out.println(is);
				
				JasperReport report = JasperCompileManager.compileReport(is);

				// passar a lista recebida no método

				JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(produtos, false));
				
				// Diretorio que deseja salvar o relatorio

				JasperExportManager.exportReportToPdfFile(print, "c:/RelatorioVendas.pdf");

				// Para abrir o relatorio na pasta que vc salvou, utilizar o comando abaixo

				Runtime.getRuntime().exec("cmd /c start C:/RelatorioVendas.pdf");
				
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


