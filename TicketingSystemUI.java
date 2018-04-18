import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TicketingSystemUI extends JFrame{
	
	private JPanel mainPanel;
	private JPanel infoPanel;
	private JPanel transcriptPanel;
	private JPanel buttonPanel;
	
	private JLabel selectTicketLabel;
	private JLabel customerNameLabel;
	private JLabel customerAddressLabel;
	private JLabel customerPhoneLabel;
	private JLabel serviceIdLabel;
	private JLabel serviceStartDateLabel;
	private JLabel serviceEndDateLabel;
	private JLabel transcriptTextLabel;
	private JLabel departmentIdLabel;
	
	private JTextField customerNameTextField;
	private JTextField customerAddressTextField;
	private JTextField customerPhoneTextField;
	private JTextField serviceIdTextField;
	private JTextField serviceStartYearTextField;
	private JTextField serviceStartMonthTextField;
	private JTextField serviceStartDayTextField;
	private JTextField serviceEndYearTextField;
	private JTextField serviceEndMonthTextField;
	private JTextField serviceEndDayTextField;
	private JTextArea transcriptTextTextField;
	private JTextField departmentIdTextField;
	
	private JCheckBox isResolvedCheckBox;
	
	private JComboBox ticketListComboBox;
	
	private JButton updateButton;
	private JButton addButton;
	private JButton exitButton;
	private JButton loadButton;
	private JButton saveButton;
	private JButton analysisButton;
	
	private final int frameWidth = 630;
	private final int frameHeight = 370;
	
	private Component currentReference;
	
	private FlowMonitor flowMonitorObject;
	
	private boolean isAdmin;
	
	public TicketingSystemUI(boolean isAdmin){
		flowMonitorObject = new FlowMonitor();
		
		this.isAdmin = isAdmin;
		currentReference = this;
		frameSettings();
		panelFactory();
		labelFactory();
		textFieldFactory();
		checkBoxFactory();
		comboBoxFactory();
		buttonFactory();
		
		componentSettings();
		
		
	}
	
	private void componentSettings(){
		infoPanel.add(selectTicketLabel, new AbsoluteConstraints(10,10,100,20));
		infoPanel.add(ticketListComboBox, new AbsoluteConstraints(120,10,150,20));
		
		infoPanel.add(customerNameLabel, new AbsoluteConstraints(10,40,100,20));
		infoPanel.add(customerNameTextField, new AbsoluteConstraints(120,40,150,20));
		
		infoPanel.add(customerAddressLabel, new AbsoluteConstraints(10,70,100,20));
		infoPanel.add(customerAddressTextField, new AbsoluteConstraints(120,70,150,20));
		
		infoPanel.add(customerPhoneLabel, new AbsoluteConstraints(10,100,100,20));
		infoPanel.add(customerPhoneTextField, new AbsoluteConstraints(120,100,150,20));
		
		infoPanel.add(serviceIdLabel, new AbsoluteConstraints(10,130,100,20));
		infoPanel.add(serviceIdTextField, new AbsoluteConstraints(120,130,50,20));
		
		infoPanel.add(serviceStartDateLabel, new AbsoluteConstraints(10,160,100,20));
		infoPanel.add(serviceStartYearTextField, new AbsoluteConstraints(120,160,50,20));
		infoPanel.add(serviceStartMonthTextField, new AbsoluteConstraints(180,160,50,20));
		infoPanel.add(serviceStartDayTextField, new AbsoluteConstraints(240,160,50,20));
		
		infoPanel.add(serviceEndDateLabel, new AbsoluteConstraints(10,190,100,20));
		infoPanel.add(serviceEndYearTextField, new AbsoluteConstraints(120,190,50,20));
		infoPanel.add(serviceEndMonthTextField, new AbsoluteConstraints(180,190,50,20));
		infoPanel.add(serviceEndDayTextField, new AbsoluteConstraints(240,190,50,20));
		
		infoPanel.add(departmentIdLabel, new AbsoluteConstraints(10,220,100,20));
		infoPanel.add(departmentIdTextField, new AbsoluteConstraints(120,220,50,20));
		
		infoPanel.add(isResolvedCheckBox, new AbsoluteConstraints(10,240,100,20));
		
		transcriptPanel.add(transcriptTextTextField, new AbsoluteConstraints(10, 20, 280, 240));
		
		buttonPanel.add(updateButton, new AbsoluteConstraints(10, 10, 80, 20));
		buttonPanel.add(addButton, new AbsoluteConstraints(100, 10, 80, 20));
		buttonPanel.add(loadButton, new AbsoluteConstraints(190, 10, 80, 20));
		buttonPanel.add(saveButton, new AbsoluteConstraints(280, 10, 80, 20));
		buttonPanel.add(analysisButton, new AbsoluteConstraints(370, 10, 80, 20));
		buttonPanel.add(exitButton, new AbsoluteConstraints(500, 10, 80, 20));
		
		mainPanel.add(infoPanel, new AbsoluteConstraints(10,10,300,270));
		mainPanel.add(transcriptPanel, new AbsoluteConstraints(320,10,300,270));
		mainPanel.add(buttonPanel, new AbsoluteConstraints(10,290,610,40));
		
		this.add(mainPanel, new AbsoluteConstraints(0,0,frameWidth,frameHeight));
	}
	
	private void frameSettings(){
        this.pack();
        this.setTitle("Ticket Management System");
        this.setSize(frameWidth,frameHeight);
        this.setLayout(new AbsoluteLayout());
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.validate();
        this.setVisible(true);           
    }
	
	private void panelFactory(){
		mainPanel = new JPanel();
		mainPanel.setEnabled(true);
		mainPanel.setVisible(true);
		mainPanel.setLayout(new AbsoluteLayout());
		mainPanel.setBorder(BorderFactory.createEtchedBorder());
		
		infoPanel = new JPanel();
		infoPanel.setEnabled(true);
		infoPanel.setVisible(true);
		infoPanel.setLayout(new AbsoluteLayout());
		infoPanel.setBorder(BorderFactory.createEtchedBorder());
		
		transcriptPanel = new JPanel();
		transcriptPanel.setEnabled(true);
		transcriptPanel.setVisible(true);
		transcriptPanel.setLayout(new AbsoluteLayout());
		transcriptPanel.setBorder(BorderFactory.createTitledBorder("Transcript Text"));
		
		buttonPanel = new JPanel();
		buttonPanel.setEnabled(true);
		buttonPanel.setVisible(true);
		buttonPanel.setLayout(new AbsoluteLayout());
		buttonPanel.setBorder(BorderFactory.createEtchedBorder());
	}
	
	private void comboBoxFactory(){
        ticketListComboBox = new JComboBox();
        for(int counter=0; counter<flowMonitorObject.getTicketList().size(); counter++){
        	ticketListComboBox.insertItemAt(flowMonitorObject.getTicketList().get(counter).ticketID, counter);
        }
                
        ticketListComboBox.setEnabled(true);
        ticketListComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ticket ticket = null;
				String ticketId = "";
				if(ticketListComboBox.getSelectedIndex() != -1){
					ticketId = ticketListComboBox.getSelectedItem().toString();
				}
				
				for(int counter=0; counter<flowMonitorObject.getTicketList().size(); counter++){
					ticket = flowMonitorObject.getTicketList().get(counter);
					
					if(ticket.ticketID == ticketId){
						String startDate[] = ticket.serviceInformationObject.getServiceStartDate().split("/");
						String endDate[] = ticket.serviceInformationObject.getServiceEndDate().split("/");
						
						customerNameTextField.setText(ticket.customerInformationObject.getCustomerName());
						customerAddressTextField.setText(ticket.customerInformationObject.getCustomerAddress());
						customerPhoneTextField.setText(String.valueOf(ticket.customerInformationObject.getCustomerPhoneNumber()));
						serviceIdTextField.setText(ticket.serviceInformationObject.getServiceID());
						if(startDate.length > 0){
							serviceStartYearTextField.setText(startDate[0]);
						}
						if(startDate.length > 1){
							serviceStartMonthTextField.setText(startDate[1]);
						}
						if(startDate.length > 2){
							serviceStartDayTextField.setText(startDate[2]);
						}
						
						if(endDate.length > 0){
							serviceEndYearTextField.setText(endDate[1]);
						}
						if(endDate.length > 1){
							serviceEndMonthTextField.setText(endDate[2]);
						}
						if(endDate.length > 2){
							serviceEndDayTextField.setText(endDate[3]);
						}
						
						departmentIdTextField.setText(String.valueOf(ticket.departmentID));
						transcriptTextTextField.setText(ticket.transcriptObject.getTranscript());
					}
				}				
				
			}
		});
    }
	
	private void labelFactory(){
		selectTicketLabel = new JLabel("Select Ticket");
		customerNameLabel = new JLabel("Customer Name");
		customerAddressLabel = new JLabel("Customer Address");
		customerPhoneLabel = new JLabel("Customer Phone #");
		serviceIdLabel = new JLabel("Service Id");
		serviceStartDateLabel = new JLabel("Service Start Date");
		serviceEndDateLabel = new JLabel("Service End Date");
		transcriptTextLabel = new JLabel("Transcript Text");
		departmentIdLabel = new JLabel("DepartmentId");
	}
	
	private void textFieldFactory(){
		customerNameTextField = new JTextField();
		customerAddressTextField = new JTextField();
		customerPhoneTextField= new JTextField();
		serviceIdTextField= new JTextField();
		serviceStartYearTextField= new JTextField();
		serviceStartMonthTextField= new JTextField();
		serviceStartDayTextField= new JTextField();
		serviceEndYearTextField= new JTextField();
		serviceEndMonthTextField= new JTextField();
		serviceEndDayTextField= new JTextField();
		transcriptTextTextField= new JTextArea();
		departmentIdTextField= new JTextField();
		departmentIdTextField.setEnabled(false);
	}
	
	private void checkBoxFactory(){
		isResolvedCheckBox = new JCheckBox("Is Resolved");
		isResolvedCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isResolvedCheckBox.isSelected()){
					departmentIdTextField.setEnabled(true);
				}
				else{
					departmentIdTextField.setEnabled(false);
				}
			}
		});
	}
	
	private void update(){
		String ticketId = "";
		if(ticketListComboBox.getSelectedIndex() > -1 ){
			ticketId = ticketListComboBox.getSelectedItem().toString();
		}
		
		String customerName = customerNameTextField.getText();
		String customerAddress = customerAddressTextField.getText();
		String customerPhone =customerPhoneTextField.getText();
		String serviceId = serviceIdTextField.getText();
		String serviceStartDate = serviceStartYearTextField.getText() + "/" + serviceStartMonthTextField.getText() + "/" + serviceStartDayTextField.getText();
		String serviceEndDate = serviceEndYearTextField.getText() + "/" + serviceEndMonthTextField.getText() + "/" + serviceEndDayTextField.getText();
		String departmentId = departmentIdTextField.getText();
		String transcript = transcriptTextTextField.getText();
				
		for(int counter=0; counter<flowMonitorObject.getTicketList().size(); counter++){
			if(flowMonitorObject.getTicketList().get(counter).ticketID == ticketId){
				if(customerName != ""){
					flowMonitorObject.getTicketList().get(counter).customerInformationObject.setCustomerName(customerName);
				}
				
				if(customerAddress != ""){
					flowMonitorObject.getTicketList().get(counter).customerInformationObject.setCustomerAddress(customerAddress);
				}
				
				if(customerPhone != ""){
					try{
						flowMonitorObject.getTicketList().get(counter).customerInformationObject.setCustomerPhoneNumber(Integer.valueOf(customerPhone));
					}
					catch(NumberFormatException ex){
						JOptionPane.showMessageDialog(currentReference, "Invalid Phone Number", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				if(serviceId != ""){
					flowMonitorObject.getTicketList().get(counter).serviceInformationObject.setServiceID(serviceId);
				}
				
				if(serviceStartDate != ""){
					flowMonitorObject.getTicketList().get(counter).serviceInformationObject.setServiceStartDate(serviceStartDate);
				}
				
				if(serviceEndDate != ""){
					flowMonitorObject.getTicketList().get(counter).serviceInformationObject.setServiceEndDate(serviceEndDate);
				}
				
				if(departmentId != ""){
					try{
						flowMonitorObject.getTicketList().get(counter).departmentID = Integer.valueOf(departmentId);
					}
					catch(NumberFormatException ex){
						JOptionPane.showMessageDialog(currentReference, "Invalid Department ID", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				if(transcript != ""){
					flowMonitorObject.getTicketList().get(counter).transcriptObject.setTranscript(transcript);
				}
			}
		}
		
		reset();
		
	}
	
	private Ticket add(){
		Ticket ticket = new Ticket();
		
		//String ticketId = ticketListComboBox.getSelectedItem().toString();
		String customerName = customerNameTextField.getText();
		String customerAddress = customerAddressTextField.getText();
		String customerPhone =customerPhoneTextField.getText();
		String serviceId = serviceIdTextField.getText();
		String serviceStartDate = serviceStartYearTextField.getText() + "/" + serviceStartMonthTextField.getText() + "/" + serviceStartDayTextField.getText();
		String serviceEndDate = serviceEndYearTextField.getText() + "/" + serviceEndMonthTextField.getText() + "/" + serviceEndDayTextField.getText();
		String departmentId = departmentIdTextField.getText();
		String transcript = transcriptTextTextField.getText();
				
		if(customerName != ""){
			ticket.customerInformationObject.setCustomerName(customerName);
		}
				
		if(customerAddress != ""){
			ticket.customerInformationObject.setCustomerAddress(customerAddress);
		}
				
		if(customerPhone != ""){
			try{
				ticket.customerInformationObject.setCustomerPhoneNumber(Integer.valueOf(customerPhone));
			}
			catch(NumberFormatException ex){
				ticket.customerInformationObject.setCustomerPhoneNumber(-1);
			}
		}	
		
		if(serviceId != ""){
			ticket.serviceInformationObject.setServiceID(serviceId);
		}
				
		if(serviceStartDate != ""){
			ticket.serviceInformationObject.setServiceStartDate(serviceStartDate);
		}
				
		if(serviceEndDate != ""){
			ticket.serviceInformationObject.setServiceEndDate(serviceEndDate);
		}
				
		if(departmentId != ""){
			try{
				ticket.departmentID = Integer.valueOf(departmentId);
			}
			catch(NumberFormatException ex){
				ticket.departmentID = -1;
			}
		}
				
		if(transcript != ""){
			ticket.transcriptObject.setTranscript(transcript);
		}
		
		return ticket;
	}
	
	private void reset(){
		for(int i=ticketListComboBox.getItemCount()-1;i>=0;i--){
			ticketListComboBox.removeItemAt(i);
		}
		
		for(int counter=0; counter<flowMonitorObject.getTicketList().size(); counter++){
			ticketListComboBox.insertItemAt(flowMonitorObject.getTicketList().get(counter).ticketID, counter);
		}
		
		customerNameTextField.setText("");
		customerAddressTextField.setText("");
		customerPhoneTextField.setText("");
		serviceIdTextField.setText("");
		serviceStartYearTextField.setText(""); 
		serviceStartMonthTextField.setText("");
		serviceStartDayTextField.setText("");
		serviceEndYearTextField.setText("");
		serviceEndMonthTextField.setText("");
		serviceEndDayTextField.setText("");
		departmentIdTextField.setText("");
		transcriptTextTextField.setText("");
	}
	
	private void buttonFactory(){
		addButton = new JButton("Add");
		addButton.setToolTipText("Add Ticket");		
		
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ticket ticket = add();
				flowMonitorObject.getTicketList().add(ticket);
				JOptionPane.showMessageDialog(currentReference, "Ticket has been added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
				reset();
			}
		});
		
		updateButton = new JButton("Update");
		updateButton.setToolTipText("Update Ticket");
		
		
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
				JOptionPane.showMessageDialog(currentReference, "Ticket has been updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		loadButton = new JButton("Load");
		loadButton.setToolTipText("Load Tickets");
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    String filePath = "";
			        
			    FileNameExtensionFilter ticketFileFilter = new FileNameExtensionFilter("Text Files (*.txt, *.doc, *.docx)", "txt", "doc", "docx");
			        
			    JFileChooser chooser = new JFileChooser("Browse Ticketing Data");
			    chooser.setFileFilter(ticketFileFilter);
			    chooser.setAcceptAllFileFilterUsed(false);
			    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			    chooser.setMultiSelectionEnabled(false);
			    chooser.showOpenDialog(currentReference);
			    
			    if(chooser.getSelectedFile() != null){
			    	filePath = chooser.getSelectedFile().getAbsolutePath();
			    	
			    	BufferedReader br = null;
			    	try {
			    		new BufferedReader(new FileReader(filePath));
			    	    StringBuilder sb = new StringBuilder();
			    	    String line = br.readLine();

			    	    while (line != null) {
			    	    	Ticket ticket = null;
			    	        StringTokenizer tokenizer = new StringTokenizer(line, ":");
			    	        while(tokenizer.hasMoreTokens()){
			    	        	String token = tokenizer.nextToken();
			    	        	token.replaceAll("[", "");
			    	        	token.replace("]", "");
			    	        	
			    	        	if(token.equalsIgnoreCase("ticketID")){
			    	        		ticket = new Ticket();
			    	        		ticket.ticketID=token;
			    	        	}
			    	        	else if(token.equalsIgnoreCase("customerRepID")){
			    	        		ticket.customerRepID = token;
			    	        	}
			    	        	else if(token.equalsIgnoreCase("dateCreated")){
			    	        		ticket.dateCreated = token;
			    	        	}
			    	        	else if(token.equalsIgnoreCase("customerServiceRepID")){
			    	        		ticket.customerServiceRepID = token;
			    	        	}
			    	        	else if(token.equalsIgnoreCase("cusomerID")){
			    	        		ticket.customerInformationObject.setCustomerID(token);
			    	        	}
			    	        	else if(token.equalsIgnoreCase("customerName")){
			    	        		ticket.customerInformationObject.setCustomerName(token);
			    	        	}
			    	        	else if(token.equalsIgnoreCase("customerPhoneNumber")){
			    	        		//try{
			    	        		ticket.customerInformationObject.setCustomerPhoneNumber(Integer.valueOf(token));
			    	        		//}
			    	        					    	        	}
			    	        	else if(token.equalsIgnoreCase("customerAddress")){
			    	        		ticket.customerInformationObject.setCustomerAddress(token);
			    	        	}
			    	        	else if(token.equalsIgnoreCase("serviceID")){
			    	        		ticket.serviceInformationObject.setServiceID(token);
			    	        	}
			    	        	else if(token.equalsIgnoreCase("transcript")){
			    	        		ticket.transcriptObject.setTranscript(token);
			    	        		
			    	        		flowMonitorObject.getTicketList().add(ticket);
			    	        	}
			    	        }
			    	    	
			    	    	sb.append(line);
			    	        sb.append(System.lineSeparator());
			    	        line = br.readLine();
			    	    }
			    	    String everything = sb.toString();
			    	}
			    	catch(Exception ex){
			    		JOptionPane.showMessageDialog(currentReference, "Error reading Tickets from file", "Error", JOptionPane.ERROR_MESSAGE);
			    	}
			    	finally {
			    		try{
			    			br.close();
			    		}
			    		catch(IOException ex){
				    		JOptionPane.showMessageDialog(currentReference, "Error closing buffer reader", "Error", JOptionPane.ERROR_MESSAGE);
			    		}
			    	}
			    }
			}	
		});
		
		saveButton = new JButton("Save");
		saveButton.setToolTipText("Save Tickets");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String fullPath = "";
		        JFileChooser fc = new JFileChooser("Title");
		        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		        fc.showSaveDialog(currentReference);
		        
		        if(fc.getSelectedFile() != null){
		            fullPath = fc.getSelectedFile().getPath();
		            
		            File fchoosen = new File(fullPath);
		            PrintWriter out = null;
		            
		            try{
			            out = new PrintWriter("filename.txt");
			            
			            for(int counter=0; counter<flowMonitorObject.getTicketList().size(); counter++){
			            	out.println("[ticketID]:[" + flowMonitorObject.getTicketList().get(counter).ticketID + "]");
			            	out.println("[CustomerRepID]:[" + flowMonitorObject.getTicketList().get(counter).customerRepID + "]");
			            	out.println("[dateCreated]:[" + flowMonitorObject.getTicketList().get(counter).dateCreated + "]");
			            	out.println("[customerServiceRepID]:[" + flowMonitorObject.getTicketList().get(counter).customerServiceRepID + "]");
			            	out.println("[cusomerID]:[" + flowMonitorObject.getTicketList().get(counter).customerInformationObject.getCustomerID() + "]");
			            	out.println("[customerName]:[" + flowMonitorObject.getTicketList().get(counter).customerInformationObject.getCustomerName() + "]");
			            	out.println("[customerPhoneNumber]:[" + flowMonitorObject.getTicketList().get(counter).customerInformationObject.getCustomerPhoneNumber() + "]");
			            	out.println("[customerAddress]:[" + flowMonitorObject.getTicketList().get(counter).customerInformationObject.getCustomerAddress() + "]");
			            	out.println("[serviceID]:[" + flowMonitorObject.getTicketList().get(counter).serviceInformationObject.getServiceID() + "]");
			            	out.println("[transcript]:[" + flowMonitorObject.getTicketList().get(counter).transcriptObject.getTranscript() + "]");
			            }
		            }
		            catch(Exception ex){
		            	JOptionPane.showMessageDialog(currentReference, "Error writing Tickets to file", "Error", JOptionPane.ERROR_MESSAGE);
		            }
		            finally{
		            	out.close();
		            }
		        }		        
			}
		});
		
		analysisButton = new JButton("Analysis");
		analysisButton.setToolTipText("Display Sentiment Analysis");
		analysisButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog("Please Enter Analsis Type\n\n [1] Total Sentiment Score \n [2] Average CSR Sentiment Score \n [3] Average Service Sentiment Score");
				int analysisType = 0;
				try{
					analysisType = Integer.valueOf(input);
					ArrayList<Object> sentimentResponse = flowMonitorObject.sentimentAnalysis();
					switch(analysisType){
						case 1:
							int sentimentAnalysis = (Integer)sentimentResponse.get(0);
							JOptionPane.showMessageDialog(currentReference, "Total Sentiment = " + sentimentAnalysis, "Sentiment Analysis", JOptionPane.INFORMATION_MESSAGE);
							break;
						case 2:
							String output = "";
							HashMap<String, Double> csrSentiment = (HashMap<String, Double>)sentimentResponse.get(1);
							Iterator it = csrSentiment.entrySet().iterator();
							
							while (it.hasNext()) {
						        Map.Entry pair = (Map.Entry)it.next();
						        output += pair.getKey().toString() + "\t\t\t" + pair.getValue().toString() + "\n";		        
						    }
						    JOptionPane.showMessageDialog(currentReference, output, "Average CSR Sentiment Analysis", JOptionPane.INFORMATION_MESSAGE);
							break;
						case 3:
							String output1 = "";
							HashMap<String, Double> serviceSentiment = (HashMap<String, Double>)sentimentResponse.get(2);
							Iterator it1 = serviceSentiment.entrySet().iterator();
							
							while (it1.hasNext()) {
						        Map.Entry pair = (Map.Entry)it1.next();
						        output1 += pair.getKey().toString() + "\t\t\t" + pair.getValue().toString() + "\n";		        
						    }	
							JOptionPane.showMessageDialog(currentReference, output1, "Average Service Sentiment Analysis", JOptionPane.INFORMATION_MESSAGE);
							break;
						default:
							JOptionPane.showMessageDialog(currentReference, "Invalid Analysis Option Provided. \nPlease provide a valid input with value between 1 and 3", "Error", JOptionPane.ERROR_MESSAGE);
							break;
					}
				}
				catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(currentReference, "Invalid Analysis Option Provided. \nPlease provide a valid input with value between 1 and 3", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		exitButton = new JButton("Exit");
		exitButton.setToolTipText("Exit"); 
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmation = JOptionPane.showConfirmDialog(currentReference, "You are about to exit Ticketing System.\n"
						+ "\n Are you sure you want to quit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
				
				if(confirmation == JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
		});
		
		if(isAdmin == false){
			addButton.setVisible(false);
			loadButton.setVisible(false);
			saveButton.setVisible(false);
			analysisButton.setVisible(false);
		}
		else{
			addButton.setVisible(true);
			loadButton.setVisible(true);
			saveButton.setVisible(true);
			analysisButton.setVisible(true);
		}
			
	}
}
