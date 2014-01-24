package control;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.PDF;
import view.Dashboard;

public class IgnoredPdfFilter implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		filter();
	}

	public void filter() {
		final List<PDF> allPdfFiles = FileSystem.getAllPdfFiles();
		final List<PDF> ignoredPdf = new ArrayList<PDF>();
		for (PDF pdf : allPdfFiles) {
			if (pdf.isIgnored()) {
				ignoredPdf.add(pdf);
			}
		}
		if (ignoredPdf.isEmpty()) {

		} else {
			Dashboard.clearResultList();
			for (PDF pdf : ignoredPdf) {
				Dashboard.addToResultList(pdf);
			}
		}
	}
}
