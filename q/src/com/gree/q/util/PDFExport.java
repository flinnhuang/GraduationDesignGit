package com.gree.q.util;

/** 
 * @copyRight Beijing Tsing-Tech Reachway Software Co.,Ltd. 
 * @author Jimmy.Shine 2007-5-12 
 */  
  
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
  
/** 
 *  
 */  
public class PDFExport {  
	
	public static void main(String[] args) {
		
		try {
			JasperCompileManager.compileReportToFile("D:\\workspace\\contract\\src\\com\\gree\\ht\\util\\report\\template\\YBJ.jasper");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(123);
	}
	
	
	/*
    *//** 
     * �������� 
     *  
     * @param request 
     * @param response 
     * @param reportFilePath 
     * @param params 
     * @param con 
     * @param fileName 
     * @throws JasperReportException 
     *//*  
    public void export(HttpServletRequest request, HttpServletResponse response, String reportFilePath, Map params,  
            HashMap maps, String fileName) {  
    		JasperPrintWithConnect
//        JasperPrint jasperPrint = new JasperPrintWithConnection(reportFilePath, params, con).getJasperPrint();  
        // ��������japserPrint����session�С�  
        request.getSession().setAttribute(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);  
        // �õ�japserPrintList  
        List jasperPrintList = BaseHttpServlet.getJasperPrintList(request);  
        // ��û��JasperPrintList�����׳��쳣  
//        if (jasperPrintList == null) {  
//            throw new JasperReportException("��Http Session��û���ҵ�JasperPrint List");  
//        }  
        try {  
            OutputStream ouputStream = response.getOutputStream();  
            try {  
                response.setContentType("application/pdf");  
                response.setCharacterEncoding("UTF-8");  
                if (fileName == null || fileName.equals(""))  
                    response.setHeader("Content-Disposition", "inline; filename=\"noTitle.pdf\"");  
                else  
                    response.setHeader("Content-Disposition", "inline; filename=\""  
                            + URLEncoder.encode(fileName, "UTF-8") + ".pdf\"");  
                // ʹ��JRPdfExproter����������pdf  
                JRPdfExporter exporter = new JRPdfExporter();  
                // ����JasperPrintList  
                exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);  
  
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);  
                exporter.exportReport();  
            } catch (JRException e) {  
                e.printStackTrace();  
//                throw new JasperReportException("�ڵ���pdf��ʽ����ʱ�������");  
            } finally {  
                if (ouputStream != null) {  
                    try {  
                        ouputStream.close();  
                    } catch (IOException ex) {  
                    }  
                }  
            }  
        } catch (IOException ioe) {  
            ioe.printStackTrace();  
//            throw new JasperReportException("��Response��ȡ��OutputStreamʱ�������!");  
        }  
    }  */
  
   
  
}  
