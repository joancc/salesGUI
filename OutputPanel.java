package salesGUI;

import java.text.DecimalFormat;

import javax.swing.*;

public class OutputPanel extends JPanel{
	JLabel jlSalesOutput;
	JPanel leftPanel, rightPanel;
	JLabel jlSalesBar;
	JTextField jtfSalesBar;
	JButton done;
	SalesApp app;
	int salesBar;
	int[] sales;
	
	public OutputPanel(SalesApp container){
		app = container;
		sales = app.getSales();
		leftPanel = new JPanel();
		rightPanel = new JPanel();
		add("West", leftPanel);
		add("East", rightPanel);
		jlSalesOutput = new JLabel();
		rightPanel.add(jlSalesOutput);
		jlSalesOutput.setText("");
	}
	
	public void refreshOutput(){
		jlSalesOutput.setText("");
	}

	protected void writeOutput(){
		app.calculateMinMax();
		DecimalFormat df1 = new DecimalFormat("####.##");
		
		String txtOutput = "<html>Sales Figures<br>_________________<br>";
		for (int i = 0; i < sales.length; i++) {
			txtOutput += "Sales Person " + (i+1) + ": $" + sales[i] + "<br>";
		}
		txtOutput += "<br>The lowest sales belongs to sales person " + 
	            (app.getMin() + 1) + " with $" + sales[app.getMin()] + "<br>";
        txtOutput += "The highest sales belongs to sales person " + 
            (app.getMax() + 1) + " with $" + sales[app.getMax()] + "<br>";
        txtOutput += "<br>The total sales were: $ " + 
            app.getTotalSales() + "<br>";
        txtOutput += "The average sales was: $ " + df1.format(app.getAverage()) + 
            "<br><br>";
        txtOutput += createSalesBarInfo();
        txtOutput += "</html>";
        
        jlSalesOutput.setText(txtOutput);
        validate();
        repaint();
	}

	protected String createSalesBarInfo() {
		String salesBarOutput = "";
		int overSalesBar = 0;
		int[] performance = app.determineTopSalesPeople();
		int[] sales = app.getSales();
		
		for (int i = 0; i < sales.length; i++) {
			if (performance[i]==1) {
				overSalesBar++;
				salesBarOutput += "Sales person " + (i+1) + " sold more than the " +
						"sales goal of " + sales[i] + "<br>";
			}else if(performance[i] == 0){
				salesBarOutput += "Sales person " + (i+1) + " reached " +
						"sales goal " + sales[i] + " exactly<br>";
			}
		}
		
		if (overSalesBar  == 1) {
			salesBarOutput += "Only " + overSalesBar + " sales person sold more " +
					"than the sales goal of " + app.getBar() + "<br><br>";					
		}else{
			salesBarOutput += overSalesBar + 
	                " sales people sold more than the sales goal of " + app.getBar() + 
	                "<br><br>";
		}
		return salesBarOutput;
	}
}
