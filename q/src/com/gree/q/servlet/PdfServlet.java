package com.gree.q.servlet;

import java.io.File;  
import java.io.IOException;  
import java.util.HashMap;  
import java.util.Map;  
  
import javax.servlet.ServletContext;  
import javax.servlet.ServletException;  
import javax.servlet.ServletOutputStream;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;  
import net.sf.jasperreports.engine.JRExporterParameter;  
import net.sf.jasperreports.engine.JasperFillManager;  
import net.sf.jasperreports.engine.JasperPrint;  
import net.sf.jasperreports.engine.JasperReport;  
import net.sf.jasperreports.engine.export.JRPdfExporter;  
import net.sf.jasperreports.engine.util.FileBufferedOutputStream;  
import net.sf.jasperreports.engine.util.JRLoader;  
  
  
public class PdfServlet extends HttpServlet {  
  
    /**
	 * 
	 */
	private static final long serialVersionUID = -5927715825131881198L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        this.doPost(request, response);  
    }  
  
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
  
        ServletContext servletContext = this.getServletConfig()  
                .getServletContext();
        
//        System.out.println("1:"+servletContext.getResource("/"));
//        System.out.println("2:"+servletContext.getResource("."));
    	//System.out.println(servletContext.getRealPath(".")+"jaspers");
        
        File jasperFile = new File(servletContext.getRealPath("/")+"jaspers", request.getParameter("jasper"));
        
        Map parameters = (HashMap)request.getSession().getAttribute("params");
        request.getSession().removeAttribute("params");
        
        System.out.println(parameters);
        
        JasperPrint jasperPrint = null;
        try {
            jasperPrint = JasperFillManager.fillReport(jasperFile.getAbsolutePath(),
                    parameters, new JREmptyDataSource());
            System.out.println("111111111111111");
        } catch (JRException e) {
            e.printStackTrace();
        }
        if (null != jasperPrint) {
        	System.out.println("222222222222222");
//            FileBufferedOutputStream fbos = new FileBufferedOutputStream();
//            JRPdfExporter exporter = new JRPdfExporter();
//            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fbos);
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//            try {
//                exporter.exportReport();
//                fbos.close();
//                if (fbos.size() > 0) {
//                    response.setContentType("application/pdf");
//                    response.setContentLength(fbos.size());
//                    ServletOutputStream ouputStream = response.getOutputStream();
//                    try {
//                        fbos.writeData(ouputStream);
//                        fbos.dispose();
//                        ouputStream.flush();
//                    } finally {
//                        if (null != ouputStream) {
//                            ouputStream.close();
//                        }
//                    }
//                }
//            } catch (JRException e1) {
//                e1.printStackTrace();
//            }finally{
//                if(null !=fbos){
//                    fbos.close();
//                    fbos.dispose();
//                }
//            }
        }
    }
}
