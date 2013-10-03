package net.canang.cfi.biz.integration.birt;

import org.eclipse.birt.report.engine.api.*;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * BirtView is used to run and render BIRT reports.
 * This class expects the request to contain a ReportName and ReportFormat
 * parameter. In addition Report parameters are automatically searched for in the
 * the request object.
 * http://spring.io/blog/2012/01/30/spring-framework-birt
 *
 * @author rafizan.baharum
 * @since 10/3/13
 */
public class BirtView extends AbstractView {


    public static final String PARAM_ISNULL = "__isnull";
    public static final String UTF_8_ENCODE = "UTF-8";

    private IReportEngine birtEngine;
    private String reportNameRequestParameter = "ReportName";
    private String reportFormatRequestParameter = "ReportFormat";
    private IRenderOption renderOptions;

    public void setRenderOptions(IRenderOption ro) {
        this.renderOptions = ro;
    }

    public void setReportFormatRequestParameter(String rf) {
        Assert.hasText(rf, "the report format parameter must not be null");
        this.reportFormatRequestParameter = rf;
    }

    public void setReportNameRequestParameter(String rn) {
        Assert.hasText(rn, "the reportNameRequestParameter must not be null");
        this.reportNameRequestParameter = rn;
    }

    protected void renderMergedOutputModel(
            Map map, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        String reportName = request.getParameter(this.reportNameRequestParameter);
        String format = request.getParameter(this.reportFormatRequestParameter);
        ServletContext sc = request.getSession().getServletContext();
        if (format == null) {
            format = "html";
        }
        IReportRunnable runnable = null;
        runnable = birtEngine.openReportDesign(sc.getRealPath("/Reports") + "/" + reportName);
        IRunAndRenderTask runAndRenderTask = birtEngine.createRunAndRenderTask(runnable);
        runAndRenderTask.setParameterValues(discoverAndSetParameters(runnable, request));


        response.setContentType(birtEngine.getMIMEType(format));
        IRenderOption options = null == this.renderOptions ? new RenderOption() : this.renderOptions;
        if (format.equalsIgnoreCase("html")) {
            HTMLRenderOption htmlOptions = new HTMLRenderOption(options);
            htmlOptions.setOutputFormat("html");
            htmlOptions.setOutputStream(response.getOutputStream());
            htmlOptions.setImageHandler(new HTMLServerImageHandler());
            htmlOptions.setBaseImageURL(request.getContextPath() + "/images");
            htmlOptions.setImageDirectory(sc.getRealPath("/images"));
            runAndRenderTask.setRenderOption(htmlOptions);

        } else if (format.equalsIgnoreCase("pdf")) {
            PDFRenderOption pdfOptions = new PDFRenderOption(options);
            pdfOptions.setOutputFormat("pdf");
            pdfOptions.setOption(IPDFRenderOption.PAGE_OVERFLOW, IPDFRenderOption.FIT_TO_PAGE_SIZE);
            pdfOptions.setOutputStream(response.getOutputStream());
            runAndRenderTask.setRenderOption(pdfOptions);
        } else {

            String att = "download." + format;
            String uReportName = reportName.toUpperCase();
            if (uReportName.endsWith(".RPTDESIGN")) {
                att = uReportName.replace(".RPTDESIGN", "." + format);
            }
            response.setHeader("Content-Disposition", "attachment; filename=\"" + att + "\"");
            options.setOutputStream(response.getOutputStream());
            options.setOutputFormat(format);
            runAndRenderTask.setRenderOption(options);
        }
        runAndRenderTask.getAppContext().put(EngineConstants.APPCONTEXT_BIRT_VIEWER_HTTPSERVET_REQUEST, request);
        runAndRenderTask.run();
        runAndRenderTask.close();

    }

    private Map discoverAndSetParameters(IReportRunnable runnable, HttpServletRequest request) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    public void setBirtEngine(IReportEngine birtEngine) {
        this.birtEngine = birtEngine;
    }
}

