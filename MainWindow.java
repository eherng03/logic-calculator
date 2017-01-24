package INCO;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

/**
 *The main window of the application. It shows the user interface.
 *
 *@author Alba, Eva and Hector.
 */
public class MainWindow {

	private JFrame frame;
	
	private JTextField tFResult;
	private JTextField tFNewOperationName;
	private JTextField tFNewOperationEstructure;
	
	private JComboBox<Integer> cBOperatorA;
	private JComboBox<Integer> cBOperatorB;
	private JComboBox<String> cBOperation;
	
	private JButton btnOperate;
	private JButton btnCreate;
	
	private LogicCalculator calculator;
	
	private OperationCreator creator;
	private JMenuBar menuBar;
	private JMenuItem mntmHelp;
	private JMenuItem mntmHelp_1;
	

	/**
	 * Launch the application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the frame content.
	 */
	private void initialize() {
		
		calculator = new LogicCalculator();
		
		creator = new OperationCreator();
		
		initializeFrame();
		initializeComboBoxes();
		initializeButtons();
		initializeTextFields();
		initializeLabel("A", 41, 22, 46, 14);
		initializeLabel("B", 210, 22, 46, 14);
		initializeLabel("Operation", 86, 22, 114, 14);
		initializeLabel("Result", 266, 22, 61, 14);
		initializeLabel("If the operation does not exist you can create it", 41, 86, 239, 29);

	}
	




//---------------------------------------------------------------------------------------------------
//					Initializations
//---------------------------------------------------------------------------------------------------
	/**
	 * Create and initialize a label.
	 * @param string
	 * @param i
	 * @param j
	 * @param k
	 * @param l
	 */
	private void initializeLabel(String string, int i, int j, int k, int l) {
		JLabel lbl = new JLabel(string);
		lbl.setBounds(126, 23, k, l);
		frame.getContentPane().add(lbl);
		
	}

	/**
	 * Create and initialize all the program ComboBoxes.
	 */
	private void initializeComboBoxes() {
		
		cBOperatorA = new JComboBox<Integer>();
		cBOperatorA.setBounds(106, 41, 35, 20);
		cBOperatorA.addItem(0);
		cBOperatorA.addItem(1);
		frame.getContentPane().add(cBOperatorA);
		
		cBOperatorB = new JComboBox<Integer>();
		cBOperatorB.setBounds(328, 41, 35, 20);
		cBOperatorB.addItem(0);
		cBOperatorB.addItem(1);
		frame.getContentPane().add(cBOperatorB);
		
		cBOperation = new JComboBox<String>();
		cBOperation.setBounds(172, 41, 130, 20);
		cBOperation.addItem(" ");
		for(int i = 0; i < calculator.getOperations().size(); i++){
			cBOperation.addItem(calculator.getOperations().get(i).getName());
		}
		frame.getContentPane().add(cBOperation);
	}

	/**
	 * Create and initialize all the program Frame.
	 */
	private void initializeFrame() {
		frame = new JFrame();
		frame.setBounds(100, 100, 541, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mntmHelp_1 = new JMenuItem("Help");
		mntmHelp_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				frame.setVisible(true);
				frame.setBounds(100, 100, 541, 250);
				frame. addWindowListener(new WindowAdapter(){
		            @Override
		            public void windowClosing(WindowEvent e)
		            {
		                e.getWindow().dispose();
		            }
		        });
				frame.getContentPane().setLayout(null);
				
				JTextArea textArea = new JTextArea();
				textArea.setBounds(0, 0, 541, 300);
				frame.getContentPane().add(textArea);
				textArea.setEditable(false);
				textArea.setText("\n      Hello, welcome to the Logic Calculator help.\n"+
								"      Here we show you different operation examples that you could create, but you could let\n"
								+ "      your imagination and create as many operations as you want.\n"
								+ "      We hope you enjoy.\n\n"
								+ "      AND = NOT ( NOT A OR NOT B)\n"
								+ "      NOR = NOT ( A OR B )\n"
								+ "      NAND = NOT ( A AND B )\n"
								+ "      XOR = ( NOT A AND B ) OR ( NOT B AND A )\n"
								+ "      NXOR = ( A AND B ) OR ( NOT B AND NOT A )\n");
			}
		});
		menuBar.add(mntmHelp_1);
		
	}
	
	/**
	 * Create and initialize all the program Buttons
	 */
	private void initializeButtons() {
		btnOperate = new JButton("Operate");
		btnOperate.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 Operand result = calculator.operate(cBOperatorA.getSelectedItem(), cBOperation.getSelectedItem(), cBOperatorB.getSelectedItem());
				 if(result != null){
					 int resultValue = result.getValue();
					 tFResult.setText(Integer.toString(resultValue));
				 }else{
					 javax.swing.JOptionPane.showMessageDialog(null, "Choose an operation", "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
				 }
			 }
		});
		btnOperate.setBounds(416, 72, 89, 23);
		frame.getContentPane().add(btnOperate);
		
		btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				create();
			}
		});
		btnCreate.setBounds(413, 194, 89, 23);
		frame.getContentPane().add(btnCreate);
	}
	
	
	private void create(){
		try {
			
			creator.createOperation(tFNewOperationName.getText(), tFNewOperationEstructure.getText(), calculator, this);
			
		}catch (InvalidStructureException invalidEstructureError) {
			javax.swing.JOptionPane.showMessageDialog(null, invalidEstructureError.getMessage(), "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
		} catch (InvalidNameException invalidNameError) {
			javax.swing.JOptionPane.showMessageDialog(null, invalidNameError.getMessage(), "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
		} catch (ClassNotFoundException e) {
			javax.swing.JOptionPane.showMessageDialog(null, "The new operation couldn't be created", "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
		} catch (InstantiationException e) {
			javax.swing.JOptionPane.showMessageDialog(null, "The new operation couldn't be created", "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
		} catch (IllegalAccessException e) {
			javax.swing.JOptionPane.showMessageDialog(null, "The new operation couldn't be created", "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			javax.swing.JOptionPane.showMessageDialog(null, "The new operation couldn't be created", "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
		}
	}
	/**
	 * Create and initialize all the program TextFields.
	 */
	private void initializeTextFields() {
		tFResult = new JTextField();
		tFResult.setBounds(416, 41, 86, 20);
		frame.getContentPane().add(tFResult);
		tFResult.setColumns(10);
		
		tFNewOperationName = new JTextField();
		tFNewOperationName.setColumns(10);
		tFNewOperationName.setBounds(26, 163, 115, 20);
		frame.getContentPane().add(tFNewOperationName);
		
		tFNewOperationEstructure = new JTextField();
		tFNewOperationEstructure.setColumns(10);
		tFNewOperationEstructure.setBounds(151, 163, 341, 20);
		frame.getContentPane().add(tFNewOperationEstructure);
	}
	

	public void repaint() {
		cBOperation.removeAllItems();
		cBOperation.addItem(" ");
		for(int i = 0; i < calculator.getOperations().size(); i++){
			cBOperation.addItem(calculator.getOperations().get(i).getName());
		}
		
	}
}
