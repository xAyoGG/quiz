import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.awt.*;

public class MainMenu extends JFrame implements ActionListener {

	private JPanel contentPane;						// Main Window
	
	// main menu items 
	private JPanel buttonPanel;						// Button panel
	private JButton btnStartQuiz;					// Start Quiz button
	private JButton btnCreateQuiz;					// Create Quiz button
	private JButton btnModifyQuiz;					// Modify Quiz button
	private JButton btnFilePath;					// File path button
	private JFileChooser fc = new JFileChooser();	// File chooser object
	private JTextField filepath;					// file path of quiz
	
	//sub menu items
	private JPanel subMenu;							// sub-menu panel
	private JTextField wordField;					// Field to hold word
	private JTextField definitionField;				// Field to hold definition
	private JButton	btnWordAdd;						// Add Word button
	private JButton btnReturn;						// Finish button to hide sub-menu

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		
		// Main Menu Window
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		setContentPane(contentPane);
		BorderLayout borders = new BorderLayout(5, 5);
		contentPane.setLayout(borders);
		
		// Title Panel (NORTH)
		
		JPanel titlePanel = new JPanel();
		JLabel titleLabel = new JLabel("Vocabulary Quiz");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titlePanel.add(titleLabel);
		contentPane.add(titlePanel,BorderLayout.NORTH);
		
		// Button Panel (CENTER)
		
		buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.CENTER);
		buttonPanel.setLayout(null);
		
		btnStartQuiz = new JButton("Start Quiz");
		btnStartQuiz.setBounds(159, 37, 117, 29);
		buttonPanel.add(btnStartQuiz);
		btnStartQuiz.addActionListener(this);
		
		btnCreateQuiz = new JButton("Create Quiz");
		btnCreateQuiz.setBounds(159, 77, 117, 29);
		buttonPanel.add(btnCreateQuiz);
		btnCreateQuiz.addActionListener(this);
		
		btnModifyQuiz = new JButton("Modify Quiz");
		btnModifyQuiz.setBounds(159, 117, 117, 29);
		buttonPanel.add(btnModifyQuiz);
		btnModifyQuiz.addActionListener(this);
		
		// (HIDDEN) Sub-menu for create / modify quiz
		
		subMenu = new JPanel();
		subMenu.setVisible(false);			// Visible = false
		subMenu.setBounds(0, 0, 446, 195);
		buttonPanel.add(subMenu);
		subMenu.setLayout(null);
		
		JLabel word = new JLabel("Word: ");
		word.setHorizontalAlignment(SwingConstants.RIGHT);
		word.setBounds(48, 33, 52, 14);
		subMenu.add(word);
		
		wordField = new JTextField();
		wordField.setBounds(110, 30, 208, 20);
		subMenu.add(wordField);
		wordField.setColumns(18);
		
		JLabel lblDefinition = new JLabel("Definition: ");
		lblDefinition.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDefinition.setBounds(28, 58, 72, 14);
		subMenu.add(lblDefinition);
		
		definitionField = new JTextField();
		definitionField.setBounds(110, 61, 208, 64);
		subMenu.add(definitionField);
		definitionField.setColumns(18);
		
		btnWordAdd = new JButton("Add word");
		btnWordAdd.setBounds(110, 136, 101, 31);
		subMenu.add(btnWordAdd);
		btnWordAdd.addActionListener(this);
		
		btnReturn = new JButton("Return");
		btnReturn.setBounds(221, 136, 97, 31);
		subMenu.add(btnReturn);
		btnReturn.addActionListener(this);
		
		// Create file selection menu
		
		JPanel filePanel = new JPanel();
		contentPane.add(filePanel, BorderLayout.SOUTH);
		
			// File selection button
		
		btnFilePath = new JButton("File path");
		filePanel.add(btnFilePath);
		String path = ".\\quiz.txt";
		btnFilePath.addActionListener(this);
		
			// File path display
		
		filepath = new JTextField();
		filepath.setColumns(25);
		filePanel.add(filepath);
		filepath.setText(".\\quiz.txt");
	}
	
	// Action for File path button
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnStartQuiz) {					// Start Quiz
			System.out.println("Start Quiz");
		}
		if(e.getSource() == btnCreateQuiz || e.getSource() == btnModifyQuiz) {
			subMenu.setVisible(true);						// sub-menu 'opened'
			btnFilePath.setEnabled(false);
			btnStartQuiz.setVisible(false);
			btnCreateQuiz.setVisible(false);
			btnModifyQuiz.setVisible(false);
			if(e.getSource() == btnModifyQuiz) {			// Modify Quiz 
				
			}
			else {											// Create new Quiz
				
			}
		}
		if(e.getSource() == btnFilePath) {					// Select File [works]
			int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				String path = file.getAbsolutePath();
				filepath.setText(path);
			}
		}
		if(e.getSource() == btnWordAdd) {					// Add word
			System.out.println("Add word " + wordField.getText() + ", definition " + definitionField.getText());
		}
		if(e.getSource() == btnReturn) {					// Return to main menu [works]
			subMenu.setVisible(false);
			btnStartQuiz.setVisible(true);
			btnCreateQuiz.setVisible(true);
			btnModifyQuiz.setVisible(true);
			btnFilePath.setEnabled(true);
		}
	}
}
