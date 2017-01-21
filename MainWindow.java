import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *Class that throws the main window of the application
 *
 *@author Alba, Eva and Hector
 */
public class MainWindow {

	private JFrame frame;
	
	private JTextField tFResult;
	private JTextField tFNewOperationName;
	private JTextField tFNewOperationEstructure;
	
	private JComboBox cBOperatorA;
	private JComboBox cBOperatorB;
	private JComboBox cBOperation;
	private JComboBox cBNotA;
	private JComboBox cBNotB;
	
	private JButton btnOperate;
	private JButton btnCreate;
	
	private LogicCalculator calculator;
	
	private OperationCreator creator;
	

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
	 * Create the application
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the frame content
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
	 * Create and initialize a label
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
	 * Create and initialize all the program ComboBoxes
	 */
	private void initializeComboBoxes() {
		
		cBOperatorA = new JComboBox();
		cBOperatorA.setBounds(106, 41, 35, 20);
		cBOperatorA.addItem(0);
		cBOperatorA.addItem(1);
		frame.getContentPane().add(cBOperatorA);
		
		cBOperatorB = new JComboBox();
		cBOperatorB.setBounds(371, 41, 35, 20);
		cBOperatorB.addItem(0);
		cBOperatorB.addItem(1);
		frame.getContentPane().add(cBOperatorB);
		
		cBOperation = new JComboBox();
		cBOperation.setBounds(151, 41, 130, 20);
		cBOperation.addItem(" ");
		for(int i = 0; i < calculator.getOperations().size(); i++){
			cBOperation.addItem(calculator.getOperations().get(i));
		}
		frame.getContentPane().add(cBOperation);
		
		cBNotA = new JComboBox();
		cBNotA.setBounds(26, 41, 70, 20);
		cBNotA.addItem(" ");
		cBNotA.addItem("NOT");
		frame.getContentPane().add(cBNotA);
		
		cBNotB = new JComboBox();
		cBNotB.setBounds(291, 41, 70, 20);
		cBNotB.addItem(" ");
		cBNotB.addItem("NOT");
		frame.getContentPane().add(cBNotB);
	}

	/**
	 * Create and initialize all the program Frame
	 */
	private void initializeFrame() {
		frame = new JFrame();
		frame.setBounds(100, 100, 541, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}
	
	/**
	 * Create and initialize all the program Buttons
	 */
	private void initializeButtons() {
		btnOperate = new JButton("Operate");
		btnOperate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resultValue = calculator.operate(cBNotA.getSelectedItem(), cBOperatorA.getSelectedItem(), cBOperation.getSelectedItem(), cBNotB.getSelectedItem(), cBOperatorB.getSelectedItem());
				tFResult.setText(Integer.toString(resultValue));
			}
		});
		btnOperate.setBounds(416, 72, 89, 23);
		frame.getContentPane().add(btnOperate);
		
		btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					creator.create(tFNewOperationName.getText(), tFNewOperationEstructure.getText(), calculator);
				} catch (InvalidStructureException invalidEstructureError) {
					 javax.swing.JOptionPane.showMessageDialog(null, invalidEstructureError.getMessage(), "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
				} catch (InvalidNameException invalidNameError) {
					javax.swing.JOptionPane.showMessageDialog(null, invalidNameError.getMessage(), "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCreate.setBounds(413, 194, 89, 23);
		frame.getContentPane().add(btnCreate);
	}
	
	/**
	 * Create and initialize all the program TextFields
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
}
