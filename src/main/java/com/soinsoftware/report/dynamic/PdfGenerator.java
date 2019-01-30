package com.soinsoftware.report.dynamic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.exception.DRException;

public class PdfGenerator {
	
	private final File compiledReport;
	private final String pdfFileName;
	
	public PdfGenerator(final File compiledReport, final String pdfFileName) {
		this.compiledReport = compiledReport;
		this.pdfFileName = pdfFileName;
	}
	
	public String generate(Map<String, Object> parameters, Collection<?> dataSource) throws GeneratorException {
		JasperReportBuilder report = DynamicReports.report();
		try {
			report.setTemplateDesign(compiledReport);
			report.setParameters(parameters).setDataSource(dataSource);
			File file = File.createTempFile(pdfFileName, ".pdf");
			report.toPdf(new FileOutputStream(file));
			return file.getAbsolutePath();
		} catch (DRException | IOException ex) {
			throw new GeneratorException(String.format("El reporte '%s' no pudo ser generado.", pdfFileName), ex);
		}
	}
}