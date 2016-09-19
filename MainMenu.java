import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.Scanner;
import java.awt.*;
//testing -Allen
public class MainMenu extends JFrame implements ActionListener {

	private JPanel contentPane;						// Main Window
	private String path;							// file path
	private File file = new File("./quiz.txt");		// file object
	private JPanel center;							// center panel (CENTER)
	private JPanel bottom;							// bottom panel (BOTTOM)
	private int enteredQuestions;					// -User supplied- number of questions to use
	private int numQuestions;						// The length of the list or number of actual questions
	
	// button panel items 
	private JPanel buttonPanel;						// Button panel
	private JButton btnStartQuiz;					// Start Quiz button
	private JButton btnCreateQuiz;					// Create Quiz button
	private JButton btnModifyQuiz;					// Modify Quiz button
	private JTextField txtQuestions;				// Number of questions
	
	// word panel items
	private JPanel wordPanel;						// Word panel
	private JTextField word;						// Field to hold word
	private JTextField def;							// Field to hold definition
	private JButton	btnWordAdd;						// Add Word button
	private JButton btnReturn;						// Return to button panel
	private FileOutputStream ios;					//
	private OutputStreamWriter osw;  				//
    private Writer w;								//
	
	// file panel items
	private JPanel filePanel;						// File panel
	private JButton btnFilePath;					// File path button
	private JFileChooser fc = new JFileChooser();	// File chooser object
	private JTextField txtfilepath;					// file path of quiz
	
	// quiz panel items --
	private JPanel quizPanel;						// quiz interface panel
	private JLabel lblquestion;
	private JRadioButton radio1;					
	private JRadioButton radio2;
	private JRadioButton radio4;
	private JRadioButton radio3;
	private ButtonGroup rg;							// radio button group
	private JButton btnNext;						// next button
	
	// info panel items --
	private JPanel infoPanel;						// info panel
	private JLabel infoLabel;						// information space
	
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
		setBounds(450, 450, 450, 300);
		contentPane = new JPanel();
		setContentPane(contentPane);
		BorderLayout borders = new BorderLayout(5, 5);
		contentPane.setLayout(borders);
		
		// Title Panel (TOP)
		
		JPanel titlePanel = new JPanel();
		JLabel titleLabel = new JLabel("Vocabulary Quiz");
		titleLabel.setFont(new Font("Ravie", Font.PLAIN, 22));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titlePanel.add(titleLabel);
		contentPane.add(titlePanel,BorderLayout.NORTH);
		
		// Center panel (CENTER)
		
		center = new JPanel();
		center.setLayout(new CardLayout());
		contentPane.add(center, BorderLayout.CENTER);
		
		// Button Panel (CENTER)
		
		buttonPanel = new JPanel();
		center.add(buttonPanel);
		buttonPanel.setLayout(null);
		
		btnStartQuiz = new JButton("Start Quiz");
		btnStartQuiz.setBounds(159, 27, 117, 29);
		buttonPanel.add(btnStartQuiz);
		btnStartQuiz.addActionListener(this);
		
		btnCreateQuiz = new JButton("Create Quiz");
		btnCreateQuiz.setBounds(159, 67, 117, 29);
		buttonPanel.add(btnCreateQuiz);
		btnCreateQuiz.addActionListener(this);
		
		btnModifyQuiz = new JButton("Modify Quiz");
		btnModifyQuiz.setBounds(159, 107, 117, 29);
		buttonPanel.add(btnModifyQuiz);
		btnModifyQuiz.addActionListener(this);
		
		JLabel lblQuestions = new JLabel("Number of Questions:");
        lblQuestions.setBounds(125, 158, 151, 22);
        buttonPanel.add(lblQuestions);
		
		txtQuestions = new JTextField("all");
		txtQuestions.setBounds(254, 154, 30, 30);
		buttonPanel.add(txtQuestions);
		txtQuestions.addActionListener(this);
		
		
		// Quiz words create / modify (CENTER)
	
		wordPanel = new JPanel();
		wordPanel.setVisible(false);
		wordPanel.setBounds(0, 0, 446, 195);
		center.add(wordPanel);
		wordPanel.setLayout(null);
		
		JLabel lblword = new JLabel("Word: ");
		lblword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblword.setBounds(48, 33, 52, 14);
		wordPanel.add(lblword);
		
		word = new JTextField();
		word.setBounds(110, 30, 208, 20);
		wordPanel.add(word);
		word.setColumns(18);
		
		JLabel lblDefinition = new JLabel("Definition: ");
		lblDefinition.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDefinition.setBounds(28, 58, 72, 14);
		wordPanel.add(lblDefinition);
		
		def = new JTextField();
		def.setBounds(110, 61, 208, 64);
		wordPanel.add(def);
		def.setColumns(18);
		
		btnWordAdd = new JButton("Add word");
		btnWordAdd.setBounds(110, 136, 101, 31);
		wordPanel.add(btnWordAdd);
		btnWordAdd.addActionListener(this);
		
		btnReturn = new JButton("Return");
		btnReturn.setBounds(221, 136, 97, 31);
		wordPanel.add(btnReturn);
		btnReturn.addActionListener(this);
		
		// Bottom panel
		
		bottom = new JPanel();
		bottom.setLayout(new CardLayout());
		contentPane.add(bottom, BorderLayout.SOUTH);
		
		// File selection menu (BOTTOM)
		
		filePanel = new JPanel();
		bottom.add(filePanel);
		
			// File selection button
		
		btnFilePath = new JButton("File path");
		filePanel.add(btnFilePath);
		btnFilePath.addActionListener(this);
		
			// File path display
		
		txtfilepath = new JTextField();
		txtfilepath.setColumns(25);
		filePanel.add(txtfilepath);
		txtfilepath.setText(".\\quiz.txt");
		
		// Quiz panel (CENTER)
		
		quizPanel = new JPanel();
		quizPanel.setVisible(false);
		center.add(quizPanel);
		quizPanel.setLayout(new GridLayout(6, 1, 0, 0));
		
		lblquestion = new JLabel("Question");
		lblquestion.setHorizontalAlignment(SwingConstants.CENTER);
		quizPanel.add(lblquestion);
		
		radio1 = new JRadioButton("radio1");
		quizPanel.add(radio1);
		radio1.addActionListener(this);
		
		radio2 = new JRadioButton("radio2");
		quizPanel.add(radio2);
		radio2.addActionListener(this);
		
		radio3 = new JRadioButton("radio3");
		quizPanel.add(radio3);
		radio3.addActionListener(this);
		
		radio4 = new JRadioButton("radio4");
		quizPanel.add(radio4);
		radio4.addActionListener(this);
		
		rg = new ButtonGroup();					// radio button group
		rg.add(radio1);
		rg.add(radio2);
		rg.add(radio3);
		rg.add(radio4);
		
		btnNext = new JButton("Next");
		btnNext.setEnabled(false);
		quizPanel.add(btnNext);
		btnNext.addActionListener(this);
		
		// Info panel (SOUTH)
		
		infoPanel = new JPanel();
		infoPanel.setVisible(false);
		bottom.add(infoPanel);
		
		infoLabel = new JLabel(txtfilepath.getText() + ":: Question: X/Y");
		infoPanel.add(infoLabel);
		
		// add panels to center panel
		
		center.add(buttonPanel);
		center.add(wordPanel);
        center.add(quizPanel);
        
        
	}
	
	// Action for File path button
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnStartQuiz) {					// Start Quiz
			buttonPanel.setVisible(false);
			wordPanel.setVisible(false);
			quizPanel.setVisible(true);
			filePanel.setVisible(false);
			infoPanel.setVisible(true);
			//...
		}
		if(e.getSource() == btnCreateQuiz || e.getSource() == btnModifyQuiz) {
			buttonPanel.setVisible(false);
			wordPanel.setVisible(true);					// word panel 'opened'
			btnFilePath.setEnabled(false);
			if(e.getSource() == btnModifyQuiz) {		// Modify Quiz 
				if(file.exists() && file.canWrite()) {
					try {
						ios = new FileOutputStream(file);
						osw = new OutputStreamWriter(ios);  
						w = new BufferedWriter(osw); 
					} catch (FileNotFoundException fnfe) {
						System.out.println("modify quiz 404 error");
					}
				}
				//...
			}
			else {										// Create new Quiz
				if(file.exists()) {
					System.out.println("delete");
					file.delete();
				}
				try {
					System.out.println("create");
					file.createNewFile();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
				System.out.println(file.toString());
				if(file.exists() && file.canRead() && file.canWrite()) {
					try {
						ios = new FileOutputStream(file);
						osw = new OutputStreamWriter(ios);  
						w = new BufferedWriter(osw); 
					} catch (FileNotFoundException fnfe) {
						System.out.println("create quiz 404 error");
					}
				}
				//...
			}
		}
		if(e.getSource() == btnFilePath) {					// Select File [works]
			String[] ft = {"txt"};		// String array of acceptable filetypes
			FileNameExtensionFilter filter = new FileNameExtensionFilter("text document", ft);
			fc.setFileFilter(filter);
			int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				file = fc.getSelectedFile();
				path = file.getAbsolutePath();
				txtfilepath.setText(path);
			}
		}
		if(e.getSource() == btnWordAdd) {					// Add word
			System.out.println("Add word " + word.getText() + ", definition " + def.getText());
			try {
	            w.append(word.getText() + " :" + def.getText());
			} catch (IOException wae) {
				System.out.println("write append error");
			}
			//...
		}
		if(e.getSource() == btnReturn) {					// Return to main menu [works]
			try {
				w.write(w.toString());
			} catch (IOException ioe) {
				System.out.println("writer io error");
			}
			wordPanel.setVisible(false);
			btnFilePath.setEnabled(true);
			buttonPanel.setVisible(true);
		}
		if(e.getSource() == radio1 || e.getSource() == radio2 || e.getSource() == radio3 || e.getSource() == radio4) {
			btnNext.setEnabled(true);
		}
		if(e.getSource() == btnNext) {						// Quiz Next button
			String s = "";
			if(radio1.isSelected()) {
				s = radio1.getText();
			}
			else if(radio2.isSelected()) {
				s = radio2.getText();
			}
			else if(radio3.isSelected()) {
				s = radio3.getText();
			}
			else if(radio4.isSelected()) {
				s = radio4.getText();
			}
			rg.clearSelection();
			btnNext.setEnabled(false);
			System.out.println("You chose " + s);
			//...
		}
		if(e.getSource() == txtQuestions) {				// Quiz questions selection 
			String s = txtQuestions.getText();
			try {
				enteredQuestions = Integer.valueOf(s);
			}
			catch(NumberFormatException nfe) {			// disables btnStartQuiz if it can't parse the text to integer
				btnStartQuiz.setEnabled(false);
				return;
			}
			/*
			if(enteredQuestions > numQuestions) {		// disable btnStartQuiz if enteredQuestions > numQuestions
				btnStartQuiz.setEnabled(false);
				return;
			}
			*/
			btnStartQuiz.setEnabled(true);
		}
	}
}
