package com.gree.q.servlet.jaspers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class TestPdf {
	public static void main(String[] args) {
		
		
		String path = TestPdf.class.getResource(".").getPath();

		// File jasperFileName = new File(path, "YBJ.jasper");
		String jasper = path +"YBJ.jasper" ;
		// �ڶ��������ò���ֵ


		Map reportParameters = new HashMap();

		reportParameters.put("varable1", "ASD"); // ���ò���ֵ
		reportParameters.put("varable2", "SDFSDFS");
		reportParameters.put("djno", "000121232");

		String print;
		try {
			print = JasperFillManager.fillReportToFile(jasper,
					reportParameters, new JREmptyDataSource());
			
			File sourceFile = new File(print);

			JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(sourceFile); // ��ɱ�����

			JasperViewer.viewReport(jasperPrint);
			
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}
}
