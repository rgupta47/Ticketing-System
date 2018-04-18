import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Project2_14 extends JFrame{
	private JPanel mainPanel;
	private JLabel userRoleLabel;
	private JRadioButton userRadioButton;
	private JRadioButton adminRadioButton;
	private ButtonGroup radioButtonGroup;
	private JButton proceedButton;
	private JButton exitButton;
	
	private final int frameWidth = 340;
	private final int frameHeight = 100;
	
	private Component currentReference;
	
	public Project2_14() {
		frameSettings();
		
		panelFactory();
		labelFactory();
		radioButtonFactory();
		buttonGroupFactory();
		buttonFactory();
		
		componentSettings();
		
		currentReference = this;
	}
	
	public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (Exception e){
            e.printStackTrace();
        }
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Project2_14();               
            }
        });
    }
	
	private void componentSettings(){
		mainPanel.add(userRoleLabel, new AbsoluteConstraints(10, 10, 100, 20));
		mainPanel.add(adminRadioButton, new AbsoluteConstraints(120, 10, 100, 20));
		mainPanel.add(userRadioButton, new AbsoluteConstraints(230, 10, 100, 20));
		
		mainPanel.add(proceedButton, new AbsoluteConstraints(85, 40, 80, 20));
		mainPanel.add(exitButton, new AbsoluteConstraints(175, 40, 80, 20));
		
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
	}
	
	private void labelFactory(){
		userRoleLabel = new JLabel("Select User Role");
	}
	
	private void radioButtonFactory(){
		userRadioButton = new JRadioButton("User");
		userRadioButton.setSelected(false);
				
		adminRadioButton = new JRadioButton("Administrator");
		adminRadioButton.setSelected(true);		
	}
	
	private void buttonFactory(){
		proceedButton = new JButton("Proceed");
		proceedButton.setToolTipText("Proceed");
		proceedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isAdmin = false;
				if(adminRadioButton.isSelected()){
					isAdmin = true;
				}
				else if(userRadioButton.isSelected()){
					isAdmin = false;
				}
					
				TicketingSystemUI ticketingUIObj = new TicketingSystemUI(isAdmin);
				currentReference.setVisible(false);
				ticketingUIObj.setVisible(true);
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
	}
	
	private void buttonGroupFactory(){
		radioButtonGroup = new ButtonGroup();
		
		radioButtonGroup.add(userRadioButton);
		radioButtonGroup.add(adminRadioButton);
	}
}
