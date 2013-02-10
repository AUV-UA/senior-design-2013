package org.auvua.catfish;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.*;

public class CATfish {

	private JFrame frmCatfish;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Iterator<CommPortIdentifier> portsSet = getAvailableSerialPorts().iterator();
				
				while(portsSet.hasNext()) {
					System.out.println(portsSet.next().getName());
				}
				
				try {
					CATfish window = new CATfish();
					window.frmCatfish.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CATfish() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCatfish = new JFrame();
		frmCatfish.setResizable(false);
		frmCatfish.setTitle("CATfish Interface Panel");
		frmCatfish.setBounds(100, 100, 958, 354);
		frmCatfish.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		UIManager.put("nimbusBase", new Color(230, 230, 230));
		UIManager.put("nimbusBlueGrey", new Color(190, 190, 190));
		UIManager.put("control", new Color(240, 240, 240));
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{11, 323, 17, 203, 15, 165, 15, 0, 8, 0};
		gridBagLayout.rowHeights = new int[]{0, 166, 11, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frmCatfish.getContentPane().setLayout(gridBagLayout);
		
		JSeparator separator_3 = new JSeparator();
		GridBagConstraints gbc_separator_3 = new GridBagConstraints();
		gbc_separator_3.gridheight = 4;
		gbc_separator_3.insets = new Insets(0, 0, 0, 5);
		gbc_separator_3.gridx = 0;
		gbc_separator_3.gridy = 0;
		frmCatfish.getContentPane().add(separator_3, gbc_separator_3);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 4;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.anchor = GridBagConstraints.EAST;
		gbc_panel.fill = GridBagConstraints.VERTICAL;
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 0;
		frmCatfish.getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{43};
		gbl_panel.rowHeights = new int[]{14, 10, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblArduino = new JLabel("Arduino");
		lblArduino.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblArduino = new GridBagConstraints();
		gbc_lblArduino.insets = new Insets(0, 0, 5, 0);
		gbc_lblArduino.anchor = GridBagConstraints.NORTH;
		gbc_lblArduino.gridx = 0;
		gbc_lblArduino.gridy = 0;
		panel.add(lblArduino, gbc_lblArduino);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(SystemColor.desktop);
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 1;
		panel.add(separator_1, gbc_separator_1);
		
		JLabel lblDigitalOutputs = new JLabel("Digital Outputs");
		GridBagConstraints gbc_lblDigitalOutputs = new GridBagConstraints();
		gbc_lblDigitalOutputs.insets = new Insets(0, 0, 5, 0);
		gbc_lblDigitalOutputs.gridx = 0;
		gbc_lblDigitalOutputs.gridy = 2;
		panel.add(lblDigitalOutputs, gbc_lblDigitalOutputs);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 3;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{35, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JToggleButton toggleButton = new JToggleButton("0");
		GridBagConstraints gbc_toggleButton = new GridBagConstraints();
		gbc_toggleButton.insets = new Insets(0, 0, 5, 5);
		gbc_toggleButton.gridx = 0;
		gbc_toggleButton.gridy = 0;
		panel_1.add(toggleButton, gbc_toggleButton);
		
		JToggleButton toggleButton_1 = new JToggleButton("1");
		GridBagConstraints gbc_toggleButton_1 = new GridBagConstraints();
		gbc_toggleButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_toggleButton_1.gridx = 1;
		gbc_toggleButton_1.gridy = 0;
		panel_1.add(toggleButton_1, gbc_toggleButton_1);
		
		JToggleButton toggleButton_2 = new JToggleButton("2");
		GridBagConstraints gbc_toggleButton_2 = new GridBagConstraints();
		gbc_toggleButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_toggleButton_2.gridx = 2;
		gbc_toggleButton_2.gridy = 0;
		panel_1.add(toggleButton_2, gbc_toggleButton_2);
		
		JToggleButton toggleButton_3 = new JToggleButton("3");
		GridBagConstraints gbc_toggleButton_3 = new GridBagConstraints();
		gbc_toggleButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_toggleButton_3.gridx = 3;
		gbc_toggleButton_3.gridy = 0;
		panel_1.add(toggleButton_3, gbc_toggleButton_3);
		
		JToggleButton toggleButton_4 = new JToggleButton("4");
		GridBagConstraints gbc_toggleButton_4 = new GridBagConstraints();
		gbc_toggleButton_4.insets = new Insets(0, 0, 5, 5);
		gbc_toggleButton_4.gridx = 4;
		gbc_toggleButton_4.gridy = 0;
		panel_1.add(toggleButton_4, gbc_toggleButton_4);
		
		JToggleButton toggleButton_5 = new JToggleButton("5");
		GridBagConstraints gbc_toggleButton_5 = new GridBagConstraints();
		gbc_toggleButton_5.insets = new Insets(0, 0, 0, 5);
		gbc_toggleButton_5.gridx = 0;
		gbc_toggleButton_5.gridy = 1;
		panel_1.add(toggleButton_5, gbc_toggleButton_5);
		
		JToggleButton toggleButton_6 = new JToggleButton("6");
		GridBagConstraints gbc_toggleButton_6 = new GridBagConstraints();
		gbc_toggleButton_6.insets = new Insets(0, 0, 0, 5);
		gbc_toggleButton_6.gridx = 1;
		gbc_toggleButton_6.gridy = 1;
		panel_1.add(toggleButton_6, gbc_toggleButton_6);
		
		JToggleButton toggleButton_7 = new JToggleButton("7");
		GridBagConstraints gbc_toggleButton_7 = new GridBagConstraints();
		gbc_toggleButton_7.insets = new Insets(0, 0, 0, 5);
		gbc_toggleButton_7.gridx = 2;
		gbc_toggleButton_7.gridy = 1;
		panel_1.add(toggleButton_7, gbc_toggleButton_7);
		
		JToggleButton toggleButton_8 = new JToggleButton("8");
		GridBagConstraints gbc_toggleButton_8 = new GridBagConstraints();
		gbc_toggleButton_8.insets = new Insets(0, 0, 0, 5);
		gbc_toggleButton_8.gridx = 3;
		gbc_toggleButton_8.gridy = 1;
		panel_1.add(toggleButton_8, gbc_toggleButton_8);
		
		JToggleButton toggleButton_9 = new JToggleButton("9");
		GridBagConstraints gbc_toggleButton_9 = new GridBagConstraints();
		gbc_toggleButton_9.insets = new Insets(0, 0, 0, 5);
		gbc_toggleButton_9.gridx = 4;
		gbc_toggleButton_9.gridy = 1;
		panel_1.add(toggleButton_9, gbc_toggleButton_9);
		
		JLabel lblDigitalInputs = new JLabel("Digital Inputs");
		GridBagConstraints gbc_lblDigitalInputs = new GridBagConstraints();
		gbc_lblDigitalInputs.insets = new Insets(0, 0, 5, 0);
		gbc_lblDigitalInputs.gridx = 0;
		gbc_lblDigitalInputs.gridy = 4;
		panel.add(lblDigitalInputs, gbc_lblDigitalInputs);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 5;
		panel.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel label = new JLabel("10");
		label.setBackground(SystemColor.controlDkShadow);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panel_2.add(label, gbc_label);
		
		JLabel label_1 = new JLabel("11");
		label_1.setBackground(SystemColor.controlDkShadow);
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 0, 5);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 0;
		panel_2.add(label_1, gbc_label_1);
		
		JLabel label_2 = new JLabel("12");
		label_2.setBackground(SystemColor.controlDkShadow);
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.insets = new Insets(0, 0, 0, 5);
		gbc_label_2.gridx = 2;
		gbc_label_2.gridy = 0;
		panel_2.add(label_2, gbc_label_2);
		
		JLabel label_3 = new JLabel("13");
		label_3.setBackground(SystemColor.controlDkShadow);
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.gridx = 3;
		gbc_label_3.gridy = 0;
		panel_2.add(label_3, gbc_label_3);
		
		JLabel lblAnalogInputs = new JLabel("Analog Inputs");
		GridBagConstraints gbc_lblAnalogInputs = new GridBagConstraints();
		gbc_lblAnalogInputs.insets = new Insets(0, 0, 5, 0);
		gbc_lblAnalogInputs.gridx = 0;
		gbc_lblAnalogInputs.gridy = 6;
		panel.add(lblAnalogInputs, gbc_lblAnalogInputs);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 7;
		panel.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel lblA = new JLabel("A0:");
		GridBagConstraints gbc_lblA = new GridBagConstraints();
		gbc_lblA.insets = new Insets(0, 0, 5, 5);
		gbc_lblA.anchor = GridBagConstraints.EAST;
		gbc_lblA.gridx = 0;
		gbc_lblA.gridy = 0;
		panel_3.add(lblA, gbc_lblA);
		
		textField = new JTextField();
		textField.setEditable(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		panel_3.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblA_2 = new JLabel("A1:");
		GridBagConstraints gbc_lblA_2 = new GridBagConstraints();
		gbc_lblA_2.anchor = GridBagConstraints.EAST;
		gbc_lblA_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblA_2.gridx = 2;
		gbc_lblA_2.gridy = 0;
		panel_3.add(lblA_2, gbc_lblA_2);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 0;
		panel_3.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblA_1 = new JLabel("A2:");
		GridBagConstraints gbc_lblA_1 = new GridBagConstraints();
		gbc_lblA_1.anchor = GridBagConstraints.EAST;
		gbc_lblA_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblA_1.gridx = 0;
		gbc_lblA_1.gridy = 1;
		panel_3.add(lblA_1, gbc_lblA_1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 1;
		panel_3.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel lblA_3 = new JLabel("A3:");
		GridBagConstraints gbc_lblA_3 = new GridBagConstraints();
		gbc_lblA_3.anchor = GridBagConstraints.EAST;
		gbc_lblA_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblA_3.gridx = 2;
		gbc_lblA_3.gridy = 1;
		panel_3.add(lblA_3, gbc_lblA_3);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 3;
		gbc_textField_3.gridy = 1;
		panel_3.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		JLabel lblA_4 = new JLabel("A4:");
		GridBagConstraints gbc_lblA_4 = new GridBagConstraints();
		gbc_lblA_4.anchor = GridBagConstraints.EAST;
		gbc_lblA_4.insets = new Insets(0, 0, 0, 5);
		gbc_lblA_4.gridx = 0;
		gbc_lblA_4.gridy = 2;
		panel_3.add(lblA_4, gbc_lblA_4);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 0, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 2;
		panel_3.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		JLabel lblA_5 = new JLabel("A5:");
		GridBagConstraints gbc_lblA_5 = new GridBagConstraints();
		gbc_lblA_5.anchor = GridBagConstraints.EAST;
		gbc_lblA_5.insets = new Insets(0, 0, 0, 5);
		gbc_lblA_5.gridx = 2;
		gbc_lblA_5.gridy = 2;
		panel_3.add(lblA_5, gbc_lblA_5);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 3;
		gbc_textField_5.gridy = 2;
		panel_3.add(textField_5, gbc_textField_5);
		textField_5.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(SystemColor.desktop);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridheight = 4;
		gbc_separator.fill = GridBagConstraints.VERTICAL;
		gbc_separator.insets = new Insets(0, 0, 0, 5);
		gbc_separator.gridx = 4;
		gbc_separator.gridy = 0;
		frmCatfish.getContentPane().add(separator, gbc_separator);
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.gridheight = 4;
		gbc_panel_4.insets = new Insets(0, 0, 0, 5);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 5;
		gbc_panel_4.gridy = 0;
		frmCatfish.getContentPane().add(panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{44, 84, 0};
		gbl_panel_4.rowHeights = new int[]{14, 10, 0, 0};
		gbl_panel_4.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		JLabel lblCompass = new JLabel("Compass");
		lblCompass.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblCompass = new GridBagConstraints();
		gbc_lblCompass.gridwidth = 2;
		gbc_lblCompass.insets = new Insets(0, 0, 5, 0);
		gbc_lblCompass.gridx = 0;
		gbc_lblCompass.gridy = 0;
		panel_4.add(lblCompass, gbc_lblCompass);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(SystemColor.desktop);
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_2.gridwidth = 2;
		gbc_separator_2.insets = new Insets(0, 0, 5, 0);
		gbc_separator_2.gridx = 0;
		gbc_separator_2.gridy = 1;
		panel_4.add(separator_2, gbc_separator_2);
		
		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.gridwidth = 2;
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 2;
		panel_4.add(panel_5, gbc_panel_5);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[]{60, 0, 0};
		gbl_panel_5.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_5.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_5.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_5.setLayout(gbl_panel_5);
		
		JLabel lblHeading = new JLabel("Heading:");
		GridBagConstraints gbc_lblHeading = new GridBagConstraints();
		gbc_lblHeading.anchor = GridBagConstraints.EAST;
		gbc_lblHeading.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeading.gridx = 0;
		gbc_lblHeading.gridy = 0;
		panel_5.add(lblHeading, gbc_lblHeading);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.insets = new Insets(0, 0, 5, 0);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 1;
		gbc_textField_6.gridy = 0;
		panel_5.add(textField_6, gbc_textField_6);
		textField_6.setColumns(10);
		
		JLabel lblPitch = new JLabel("Pitch:");
		GridBagConstraints gbc_lblPitch = new GridBagConstraints();
		gbc_lblPitch.anchor = GridBagConstraints.EAST;
		gbc_lblPitch.insets = new Insets(0, 0, 5, 5);
		gbc_lblPitch.gridx = 0;
		gbc_lblPitch.gridy = 1;
		panel_5.add(lblPitch, gbc_lblPitch);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.insets = new Insets(0, 0, 5, 0);
		gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_7.gridx = 1;
		gbc_textField_7.gridy = 1;
		panel_5.add(textField_7, gbc_textField_7);
		textField_7.setColumns(10);
		
		JLabel lblRoll = new JLabel("Roll:");
		GridBagConstraints gbc_lblRoll = new GridBagConstraints();
		gbc_lblRoll.insets = new Insets(0, 0, 5, 5);
		gbc_lblRoll.anchor = GridBagConstraints.EAST;
		gbc_lblRoll.gridx = 0;
		gbc_lblRoll.gridy = 2;
		panel_5.add(lblRoll, gbc_lblRoll);
		
		textField_8 = new JTextField();
		textField_8.setEditable(false);
		GridBagConstraints gbc_textField_8 = new GridBagConstraints();
		gbc_textField_8.insets = new Insets(0, 0, 5, 0);
		gbc_textField_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_8.gridx = 1;
		gbc_textField_8.gridy = 2;
		panel_5.add(textField_8, gbc_textField_8);
		textField_8.setColumns(10);
		
		JLabel lblAccelX = new JLabel("Accel X:");
		GridBagConstraints gbc_lblAccelX = new GridBagConstraints();
		gbc_lblAccelX.anchor = GridBagConstraints.EAST;
		gbc_lblAccelX.insets = new Insets(0, 0, 5, 5);
		gbc_lblAccelX.gridx = 0;
		gbc_lblAccelX.gridy = 3;
		panel_5.add(lblAccelX, gbc_lblAccelX);
		
		textField_9 = new JTextField();
		textField_9.setEditable(false);
		GridBagConstraints gbc_textField_9 = new GridBagConstraints();
		gbc_textField_9.insets = new Insets(0, 0, 5, 0);
		gbc_textField_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_9.gridx = 1;
		gbc_textField_9.gridy = 3;
		panel_5.add(textField_9, gbc_textField_9);
		textField_9.setColumns(10);
		
		JLabel lblAccelY = new JLabel("Accel Y:");
		GridBagConstraints gbc_lblAccelY = new GridBagConstraints();
		gbc_lblAccelY.anchor = GridBagConstraints.EAST;
		gbc_lblAccelY.insets = new Insets(0, 0, 5, 5);
		gbc_lblAccelY.gridx = 0;
		gbc_lblAccelY.gridy = 4;
		panel_5.add(lblAccelY, gbc_lblAccelY);
		
		textField_10 = new JTextField();
		textField_10.setEditable(false);
		GridBagConstraints gbc_textField_10 = new GridBagConstraints();
		gbc_textField_10.insets = new Insets(0, 0, 5, 0);
		gbc_textField_10.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_10.gridx = 1;
		gbc_textField_10.gridy = 4;
		panel_5.add(textField_10, gbc_textField_10);
		textField_10.setColumns(10);
		
		JLabel lblAccelZ = new JLabel("Accel Z:");
		GridBagConstraints gbc_lblAccelZ = new GridBagConstraints();
		gbc_lblAccelZ.anchor = GridBagConstraints.EAST;
		gbc_lblAccelZ.insets = new Insets(0, 0, 0, 5);
		gbc_lblAccelZ.gridx = 0;
		gbc_lblAccelZ.gridy = 5;
		panel_5.add(lblAccelZ, gbc_lblAccelZ);
		
		textField_11 = new JTextField();
		textField_11.setEditable(false);
		GridBagConstraints gbc_textField_11 = new GridBagConstraints();
		gbc_textField_11.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_11.gridx = 1;
		gbc_textField_11.gridy = 5;
		panel_5.add(textField_11, gbc_textField_11);
		textField_11.setColumns(10);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(SystemColor.desktop);
		separator_4.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_separator_4 = new GridBagConstraints();
		gbc_separator_4.gridheight = 4;
		gbc_separator_4.insets = new Insets(0, 0, 0, 5);
		gbc_separator_4.fill = GridBagConstraints.VERTICAL;
		gbc_separator_4.gridx = 6;
		gbc_separator_4.gridy = 0;
		frmCatfish.getContentPane().add(separator_4, gbc_separator_4);
		
		JPanel panel_9 = new JPanel();
		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
		gbc_panel_9.gridheight = 2;
		gbc_panel_9.insets = new Insets(0, 0, 5, 5);
		gbc_panel_9.fill = GridBagConstraints.BOTH;
		gbc_panel_9.gridx = 1;
		gbc_panel_9.gridy = 0;
		frmCatfish.getContentPane().add(panel_9, gbc_panel_9);
		GridBagLayout gbl_panel_9 = new GridBagLayout();
		gbl_panel_9.columnWidths = new int[]{42, 74, 79, 0, 32, 0};
		gbl_panel_9.rowHeights = new int[]{0, 10, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_9.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_9.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_9.setLayout(gbl_panel_9);
		
		JLabel lblPortsAndConnections = new JLabel("Ports and Connections");
		lblPortsAndConnections.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblPortsAndConnections = new GridBagConstraints();
		gbc_lblPortsAndConnections.gridwidth = 5;
		gbc_lblPortsAndConnections.insets = new Insets(0, 0, 5, 0);
		gbc_lblPortsAndConnections.gridx = 0;
		gbc_lblPortsAndConnections.gridy = 0;
		panel_9.add(lblPortsAndConnections, gbc_lblPortsAndConnections);
		
		JSeparator separator_8 = new JSeparator();
		separator_8.setForeground(SystemColor.desktop);
		GridBagConstraints gbc_separator_8 = new GridBagConstraints();
		gbc_separator_8.gridwidth = 5;
		gbc_separator_8.insets = new Insets(0, 0, 5, 0);
		gbc_separator_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_8.gridx = 0;
		gbc_separator_8.gridy = 1;
		panel_9.add(separator_8, gbc_separator_8);
		
		JLabel lblDevice = new JLabel("Device");
		lblDevice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDevice.setFont(new Font("Tahoma", Font.ITALIC, 11));
		GridBagConstraints gbc_lblDevice = new GridBagConstraints();
		gbc_lblDevice.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblDevice.insets = new Insets(0, 0, 5, 5);
		gbc_lblDevice.gridx = 0;
		gbc_lblDevice.gridy = 2;
		panel_9.add(lblDevice, gbc_lblDevice);
		
		JLabel lblPort_3 = new JLabel("Port");
		lblPort_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblPort_3.setFont(new Font("Tahoma", Font.ITALIC, 11));
		GridBagConstraints gbc_lblPort_3 = new GridBagConstraints();
		gbc_lblPort_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPort_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblPort_3.gridx = 1;
		gbc_lblPort_3.gridy = 2;
		panel_9.add(lblPort_3, gbc_lblPort_3);
		
		JLabel lblBaud = new JLabel("Baud");
		lblBaud.setHorizontalAlignment(SwingConstants.CENTER);
		lblBaud.setFont(new Font("Tahoma", Font.ITALIC, 11));
		GridBagConstraints gbc_lblBaud = new GridBagConstraints();
		gbc_lblBaud.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBaud.insets = new Insets(0, 0, 5, 5);
		gbc_lblBaud.gridx = 2;
		gbc_lblBaud.gridy = 2;
		panel_9.add(lblBaud, gbc_lblBaud);
		
		JLabel lblConnect = new JLabel("Connect");
		lblConnect.setHorizontalAlignment(SwingConstants.CENTER);
		lblConnect.setFont(new Font("Tahoma", Font.ITALIC, 11));
		GridBagConstraints gbc_lblConnect = new GridBagConstraints();
		gbc_lblConnect.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblConnect.insets = new Insets(0, 0, 5, 5);
		gbc_lblConnect.gridx = 3;
		gbc_lblConnect.gridy = 2;
		panel_9.add(lblConnect, gbc_lblConnect);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setFont(new Font("Tahoma", Font.ITALIC, 11));
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblStatus.insets = new Insets(0, 0, 5, 0);
		gbc_lblStatus.gridx = 4;
		gbc_lblStatus.gridy = 2;
		panel_9.add(lblStatus, gbc_lblStatus);
		
		JLabel lblJoystick_1 = new JLabel("Joystick");
		GridBagConstraints gbc_lblJoystick_1 = new GridBagConstraints();
		gbc_lblJoystick_1.anchor = GridBagConstraints.EAST;
		gbc_lblJoystick_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblJoystick_1.gridx = 0;
		gbc_lblJoystick_1.gridy = 3;
		panel_9.add(lblJoystick_1, gbc_lblJoystick_1);
		
		JComboBox comboBox_2 = new JComboBox();
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_2.gridx = 1;
		gbc_comboBox_2.gridy = 3;
		panel_9.add(comboBox_2, gbc_comboBox_2);
		
		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setModel(new DefaultComboBoxModel(new String[] {"2400", "4800", "9600", "19200", "38400", "57600", "115200"}));
		comboBox_6.setSelectedIndex(2);
		GridBagConstraints gbc_comboBox_6 = new GridBagConstraints();
		gbc_comboBox_6.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_6.gridx = 2;
		gbc_comboBox_6.gridy = 3;
		panel_9.add(comboBox_6, gbc_comboBox_6);
		
		JButton btnConnect_3 = new JButton("Connect");
		GridBagConstraints gbc_btnConnect_3 = new GridBagConstraints();
		gbc_btnConnect_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnConnect_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnConnect_3.gridx = 3;
		gbc_btnConnect_3.gridy = 3;
		panel_9.add(btnConnect_3, gbc_btnConnect_3);
		
		JCheckBox checkBox = new JCheckBox("");
		checkBox.setEnabled(false);
		GridBagConstraints gbc_checkBox = new GridBagConstraints();
		gbc_checkBox.insets = new Insets(0, 0, 5, 0);
		gbc_checkBox.gridx = 4;
		gbc_checkBox.gridy = 3;
		panel_9.add(checkBox, gbc_checkBox);
		
		JLabel lblArduino_1 = new JLabel("Arduino");
		GridBagConstraints gbc_lblArduino_1 = new GridBagConstraints();
		gbc_lblArduino_1.anchor = GridBagConstraints.EAST;
		gbc_lblArduino_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblArduino_1.gridx = 0;
		gbc_lblArduino_1.gridy = 4;
		panel_9.add(lblArduino_1, gbc_lblArduino_1);
		
		JComboBox comboBox_3 = new JComboBox();
		GridBagConstraints gbc_comboBox_3 = new GridBagConstraints();
		gbc_comboBox_3.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_3.gridx = 1;
		gbc_comboBox_3.gridy = 4;
		panel_9.add(comboBox_3, gbc_comboBox_3);
		
		JComboBox comboBox_7 = new JComboBox();
		comboBox_7.setModel(new DefaultComboBoxModel(new String[] {"2400", "4800", "9600", "19200", "38400", "57600", "115200"}));
		comboBox_7.setSelectedIndex(2);
		GridBagConstraints gbc_comboBox_7 = new GridBagConstraints();
		gbc_comboBox_7.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_7.gridx = 2;
		gbc_comboBox_7.gridy = 4;
		panel_9.add(comboBox_7, gbc_comboBox_7);
		
		JButton btnConnect_4 = new JButton("Connect");
		GridBagConstraints gbc_btnConnect_4 = new GridBagConstraints();
		gbc_btnConnect_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnConnect_4.insets = new Insets(0, 0, 5, 5);
		gbc_btnConnect_4.gridx = 3;
		gbc_btnConnect_4.gridy = 4;
		panel_9.add(btnConnect_4, gbc_btnConnect_4);
		
		JCheckBox checkBox_1 = new JCheckBox("");
		checkBox_1.setEnabled(false);
		GridBagConstraints gbc_checkBox_1 = new GridBagConstraints();
		gbc_checkBox_1.insets = new Insets(0, 0, 5, 0);
		gbc_checkBox_1.gridx = 4;
		gbc_checkBox_1.gridy = 4;
		panel_9.add(checkBox_1, gbc_checkBox_1);
		
		JLabel lblCompass_1 = new JLabel("Compass");
		GridBagConstraints gbc_lblCompass_1 = new GridBagConstraints();
		gbc_lblCompass_1.anchor = GridBagConstraints.EAST;
		gbc_lblCompass_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblCompass_1.gridx = 0;
		gbc_lblCompass_1.gridy = 5;
		panel_9.add(lblCompass_1, gbc_lblCompass_1);
		
		JComboBox comboBox_4 = new JComboBox();
		GridBagConstraints gbc_comboBox_4 = new GridBagConstraints();
		gbc_comboBox_4.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_4.gridx = 1;
		gbc_comboBox_4.gridy = 5;
		panel_9.add(comboBox_4, gbc_comboBox_4);
		
		JComboBox comboBox_8 = new JComboBox();
		comboBox_8.setModel(new DefaultComboBoxModel(new String[] {"2400", "4800", "9600", "19200", "38400", "57600", "115200"}));
		comboBox_8.setSelectedIndex(4);
		GridBagConstraints gbc_comboBox_8 = new GridBagConstraints();
		gbc_comboBox_8.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_8.gridx = 2;
		gbc_comboBox_8.gridy = 5;
		panel_9.add(comboBox_8, gbc_comboBox_8);
		
		JButton btnConnect_5 = new JButton("Connect");
		GridBagConstraints gbc_btnConnect_5 = new GridBagConstraints();
		gbc_btnConnect_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnConnect_5.insets = new Insets(0, 0, 5, 5);
		gbc_btnConnect_5.gridx = 3;
		gbc_btnConnect_5.gridy = 5;
		panel_9.add(btnConnect_5, gbc_btnConnect_5);
		
		JCheckBox checkBox_2 = new JCheckBox("");
		checkBox_2.setEnabled(false);
		GridBagConstraints gbc_checkBox_2 = new GridBagConstraints();
		gbc_checkBox_2.insets = new Insets(0, 0, 5, 0);
		gbc_checkBox_2.gridx = 4;
		gbc_checkBox_2.gridy = 5;
		panel_9.add(checkBox_2, gbc_checkBox_2);
		
		JLabel lblMotors_1 = new JLabel("Motors");
		GridBagConstraints gbc_lblMotors_1 = new GridBagConstraints();
		gbc_lblMotors_1.anchor = GridBagConstraints.EAST;
		gbc_lblMotors_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotors_1.gridx = 0;
		gbc_lblMotors_1.gridy = 6;
		panel_9.add(lblMotors_1, gbc_lblMotors_1);
		
		JComboBox comboBox_5 = new JComboBox();
		GridBagConstraints gbc_comboBox_5 = new GridBagConstraints();
		gbc_comboBox_5.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_5.gridx = 1;
		gbc_comboBox_5.gridy = 6;
		panel_9.add(comboBox_5, gbc_comboBox_5);
		
		JComboBox comboBox_9 = new JComboBox();
		comboBox_9.setModel(new DefaultComboBoxModel(new String[] {"2400", "4800", "9600", "19200", "38400", "57600", "115200"}));
		comboBox_9.setSelectedIndex(2);
		GridBagConstraints gbc_comboBox_9 = new GridBagConstraints();
		gbc_comboBox_9.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_9.gridx = 2;
		gbc_comboBox_9.gridy = 6;
		panel_9.add(comboBox_9, gbc_comboBox_9);
		
		JButton btnConnect_6 = new JButton("Connect");
		GridBagConstraints gbc_btnConnect_6 = new GridBagConstraints();
		gbc_btnConnect_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnConnect_6.insets = new Insets(0, 0, 5, 5);
		gbc_btnConnect_6.gridx = 3;
		gbc_btnConnect_6.gridy = 6;
		panel_9.add(btnConnect_6, gbc_btnConnect_6);
		
		JCheckBox checkBox_3 = new JCheckBox("");
		checkBox_3.setEnabled(false);
		GridBagConstraints gbc_checkBox_3 = new GridBagConstraints();
		gbc_checkBox_3.insets = new Insets(0, 0, 5, 0);
		gbc_checkBox_3.gridx = 4;
		gbc_checkBox_3.gridy = 6;
		panel_9.add(checkBox_3, gbc_checkBox_3);
		
		JPanel panel_10 = new JPanel();
		GridBagConstraints gbc_panel_10 = new GridBagConstraints();
		gbc_panel_10.gridwidth = 5;
		gbc_panel_10.insets = new Insets(0, 0, 0, 5);
		gbc_panel_10.fill = GridBagConstraints.BOTH;
		gbc_panel_10.gridx = 0;
		gbc_panel_10.gridy = 7;
		panel_9.add(panel_10, gbc_panel_10);
		GridBagLayout gbl_panel_10 = new GridBagLayout();
		gbl_panel_10.columnWidths = new int[]{0, 94, 76, 0, 0};
		gbl_panel_10.rowHeights = new int[]{0, 0, 0};
		gbl_panel_10.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_10.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_10.setLayout(gbl_panel_10);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue = new GridBagConstraints();
		gbc_horizontalGlue.gridheight = 2;
		gbc_horizontalGlue.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalGlue.gridx = 0;
		gbc_horizontalGlue.gridy = 0;
		panel_10.add(horizontalGlue, gbc_horizontalGlue);
		
		JButton btnRefreshPorts = new JButton("Refresh Ports");
		GridBagConstraints gbc_btnRefreshPorts = new GridBagConstraints();
		gbc_btnRefreshPorts.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRefreshPorts.insets = new Insets(0, 0, 5, 5);
		gbc_btnRefreshPorts.gridx = 1;
		gbc_btnRefreshPorts.gridy = 0;
		panel_10.add(btnRefreshPorts, gbc_btnRefreshPorts);
		
		JButton btnConnectAll = new JButton("Connect All");
		GridBagConstraints gbc_btnConnectAll = new GridBagConstraints();
		gbc_btnConnectAll.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnConnectAll.insets = new Insets(0, 0, 5, 5);
		gbc_btnConnectAll.gridx = 2;
		gbc_btnConnectAll.gridy = 0;
		panel_10.add(btnConnectAll, gbc_btnConnectAll);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_1 = new GridBagConstraints();
		gbc_horizontalGlue_1.gridheight = 2;
		gbc_horizontalGlue_1.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalGlue_1.gridx = 3;
		gbc_horizontalGlue_1.gridy = 0;
		panel_10.add(horizontalGlue_1, gbc_horizontalGlue_1);
		
		JButton btnSavePresets = new JButton("Save Presets");
		GridBagConstraints gbc_btnSavePresets = new GridBagConstraints();
		gbc_btnSavePresets.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSavePresets.insets = new Insets(0, 0, 0, 5);
		gbc_btnSavePresets.gridx = 1;
		gbc_btnSavePresets.gridy = 1;
		panel_10.add(btnSavePresets, gbc_btnSavePresets);
		
		JButton btnLoadPresets = new JButton("Load Presets");
		GridBagConstraints gbc_btnLoadPresets = new GridBagConstraints();
		gbc_btnLoadPresets.insets = new Insets(0, 0, 0, 5);
		gbc_btnLoadPresets.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLoadPresets.gridx = 2;
		gbc_btnLoadPresets.gridy = 1;
		panel_10.add(btnLoadPresets, gbc_btnLoadPresets);
		
		JSeparator separator_9 = new JSeparator();
		separator_9.setForeground(SystemColor.desktop);
		separator_9.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_separator_9 = new GridBagConstraints();
		gbc_separator_9.fill = GridBagConstraints.VERTICAL;
		gbc_separator_9.gridheight = 4;
		gbc_separator_9.insets = new Insets(0, 0, 0, 5);
		gbc_separator_9.gridx = 2;
		gbc_separator_9.gridy = 0;
		frmCatfish.getContentPane().add(separator_9, gbc_separator_9);
		
		JSeparator separator_10 = new JSeparator();
		GridBagConstraints gbc_separator_10 = new GridBagConstraints();
		gbc_separator_10.gridheight = 4;
		gbc_separator_10.insets = new Insets(0, 0, 5, 0);
		gbc_separator_10.gridx = 8;
		gbc_separator_10.gridy = 0;
		frmCatfish.getContentPane().add(separator_10, gbc_separator_10);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setForeground(SystemColor.desktop);
		GridBagConstraints gbc_separator_6 = new GridBagConstraints();
		gbc_separator_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_6.insets = new Insets(0, 0, 5, 5);
		gbc_separator_6.gridx = 1;
		gbc_separator_6.gridy = 2;
		frmCatfish.getContentPane().add(separator_6, gbc_separator_6);
		
		JPanel panel_6 = new JPanel();
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.insets = new Insets(0, 0, 0, 5);
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 1;
		gbc_panel_6.gridy = 3;
		frmCatfish.getContentPane().add(panel_6, gbc_panel_6);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[]{43, 0};
		gbl_panel_6.rowHeights = new int[]{0, 10, 0, 0};
		gbl_panel_6.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_6.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_6.setLayout(gbl_panel_6);
		
		JLabel lblJoystick = new JLabel("Joystick");
		lblJoystick.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblJoystick = new GridBagConstraints();
		gbc_lblJoystick.insets = new Insets(0, 0, 5, 0);
		gbc_lblJoystick.gridx = 0;
		gbc_lblJoystick.gridy = 0;
		panel_6.add(lblJoystick, gbc_lblJoystick);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(SystemColor.desktop);
		GridBagConstraints gbc_separator_5 = new GridBagConstraints();
		gbc_separator_5.insets = new Insets(0, 0, 5, 0);
		gbc_separator_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_5.gridx = 0;
		gbc_separator_5.gridy = 1;
		panel_6.add(separator_5, gbc_separator_5);
		
		JPanel panel_7 = new JPanel();
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.gridx = 0;
		gbc_panel_7.gridy = 2;
		panel_6.add(panel_7, gbc_panel_7);
		GridBagLayout gbl_panel_7 = new GridBagLayout();
		gbl_panel_7.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_7.rowHeights = new int[]{0, 0};
		gbl_panel_7.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_7.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_7.setLayout(gbl_panel_7);
		
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_2 = new GridBagConstraints();
		gbc_horizontalGlue_2.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalGlue_2.gridx = 0;
		gbc_horizontalGlue_2.gridy = 0;
		panel_7.add(horizontalGlue_2, gbc_horizontalGlue_2);
		
		JButton btnViewInputs = new JButton("View Inputs");
		GridBagConstraints gbc_btnViewInputs = new GridBagConstraints();
		gbc_btnViewInputs.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnViewInputs.insets = new Insets(0, 0, 0, 5);
		gbc_btnViewInputs.gridx = 1;
		gbc_btnViewInputs.gridy = 0;
		panel_7.add(btnViewInputs, gbc_btnViewInputs);
		
		JButton btnConfigure = new JButton("Configure...");
		GridBagConstraints gbc_btnConfigure = new GridBagConstraints();
		gbc_btnConfigure.insets = new Insets(0, 0, 0, 5);
		gbc_btnConfigure.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnConfigure.gridx = 2;
		gbc_btnConfigure.gridy = 0;
		panel_7.add(btnConfigure, gbc_btnConfigure);
		
		Component horizontalGlue_3 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_3 = new GridBagConstraints();
		gbc_horizontalGlue_3.gridx = 3;
		gbc_horizontalGlue_3.gridy = 0;
		panel_7.add(horizontalGlue_3, gbc_horizontalGlue_3);
		
		JPanel panel_8 = new JPanel();
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.insets = new Insets(0, 0, 0, 5);
		gbc_panel_8.gridheight = 4;
		gbc_panel_8.fill = GridBagConstraints.BOTH;
		gbc_panel_8.gridx = 7;
		gbc_panel_8.gridy = 0;
		frmCatfish.getContentPane().add(panel_8, gbc_panel_8);
		GridBagLayout gbl_panel_8 = new GridBagLayout();
		gbl_panel_8.columnWidths = new int[]{0, 0, 0};
		gbl_panel_8.rowHeights = new int[]{0, 10, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_8.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_8.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_8.setLayout(gbl_panel_8);
		
		JLabel lblMotors = new JLabel("Motors");
		lblMotors.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblMotors = new GridBagConstraints();
		gbc_lblMotors.insets = new Insets(0, 0, 5, 0);
		gbc_lblMotors.gridwidth = 2;
		gbc_lblMotors.gridx = 0;
		gbc_lblMotors.gridy = 0;
		panel_8.add(lblMotors, gbc_lblMotors);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setForeground(SystemColor.desktop);
		GridBagConstraints gbc_separator_7 = new GridBagConstraints();
		gbc_separator_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_7.gridwidth = 2;
		gbc_separator_7.insets = new Insets(0, 0, 5, 0);
		gbc_separator_7.gridx = 0;
		gbc_separator_7.gridy = 1;
		panel_8.add(separator_7, gbc_separator_7);
		
		JLabel lblVerticalLeft = new JLabel("Vertical Left");
		GridBagConstraints gbc_lblVerticalLeft = new GridBagConstraints();
		gbc_lblVerticalLeft.anchor = GridBagConstraints.WEST;
		gbc_lblVerticalLeft.gridwidth = 2;
		gbc_lblVerticalLeft.insets = new Insets(0, 0, 5, 0);
		gbc_lblVerticalLeft.gridx = 0;
		gbc_lblVerticalLeft.gridy = 2;
		panel_8.add(lblVerticalLeft, gbc_lblVerticalLeft);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setValue(50);
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.gridwidth = 2;
		gbc_progressBar.insets = new Insets(0, 0, 5, 0);
		gbc_progressBar.gridx = 0;
		gbc_progressBar.gridy = 3;
		panel_8.add(progressBar, gbc_progressBar);
		
		JLabel lblVerticalRight = new JLabel("Vertical Right");
		GridBagConstraints gbc_lblVerticalRight = new GridBagConstraints();
		gbc_lblVerticalRight.anchor = GridBagConstraints.WEST;
		gbc_lblVerticalRight.insets = new Insets(0, 0, 5, 0);
		gbc_lblVerticalRight.gridwidth = 2;
		gbc_lblVerticalRight.gridx = 0;
		gbc_lblVerticalRight.gridy = 4;
		panel_8.add(lblVerticalRight, gbc_lblVerticalRight);
		
		JProgressBar progressBar_1 = new JProgressBar();
		progressBar_1.setValue(50);
		GridBagConstraints gbc_progressBar_1 = new GridBagConstraints();
		gbc_progressBar_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar_1.gridwidth = 2;
		gbc_progressBar_1.insets = new Insets(0, 0, 5, 0);
		gbc_progressBar_1.gridx = 0;
		gbc_progressBar_1.gridy = 5;
		panel_8.add(progressBar_1, gbc_progressBar_1);
		
		JLabel lblPortForward = new JLabel("Port Forward");
		GridBagConstraints gbc_lblPortForward = new GridBagConstraints();
		gbc_lblPortForward.anchor = GridBagConstraints.WEST;
		gbc_lblPortForward.gridwidth = 2;
		gbc_lblPortForward.insets = new Insets(0, 0, 5, 0);
		gbc_lblPortForward.gridx = 0;
		gbc_lblPortForward.gridy = 6;
		panel_8.add(lblPortForward, gbc_lblPortForward);
		
		JProgressBar progressBar_2 = new JProgressBar();
		progressBar_2.setValue(50);
		GridBagConstraints gbc_progressBar_2 = new GridBagConstraints();
		gbc_progressBar_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar_2.gridwidth = 2;
		gbc_progressBar_2.insets = new Insets(0, 0, 5, 0);
		gbc_progressBar_2.gridx = 0;
		gbc_progressBar_2.gridy = 7;
		panel_8.add(progressBar_2, gbc_progressBar_2);
		
		JLabel lblStarboardFront = new JLabel("Starboard Front");
		GridBagConstraints gbc_lblStarboardFront = new GridBagConstraints();
		gbc_lblStarboardFront.anchor = GridBagConstraints.WEST;
		gbc_lblStarboardFront.insets = new Insets(0, 0, 5, 0);
		gbc_lblStarboardFront.gridwidth = 2;
		gbc_lblStarboardFront.gridx = 0;
		gbc_lblStarboardFront.gridy = 8;
		panel_8.add(lblStarboardFront, gbc_lblStarboardFront);
		
		JProgressBar progressBar_3 = new JProgressBar();
		progressBar_3.setValue(50);
		GridBagConstraints gbc_progressBar_3 = new GridBagConstraints();
		gbc_progressBar_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar_3.gridwidth = 2;
		gbc_progressBar_3.insets = new Insets(0, 0, 5, 0);
		gbc_progressBar_3.gridx = 0;
		gbc_progressBar_3.gridy = 9;
		panel_8.add(progressBar_3, gbc_progressBar_3);
		
		JLabel lblPortAft = new JLabel("Port Aft");
		GridBagConstraints gbc_lblPortAft = new GridBagConstraints();
		gbc_lblPortAft.anchor = GridBagConstraints.WEST;
		gbc_lblPortAft.gridwidth = 2;
		gbc_lblPortAft.insets = new Insets(0, 0, 5, 0);
		gbc_lblPortAft.gridx = 0;
		gbc_lblPortAft.gridy = 10;
		panel_8.add(lblPortAft, gbc_lblPortAft);
		
		JProgressBar progressBar_4 = new JProgressBar();
		progressBar_4.setValue(50);
		GridBagConstraints gbc_progressBar_4 = new GridBagConstraints();
		gbc_progressBar_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar_4.gridwidth = 2;
		gbc_progressBar_4.insets = new Insets(0, 0, 5, 0);
		gbc_progressBar_4.gridx = 0;
		gbc_progressBar_4.gridy = 11;
		panel_8.add(progressBar_4, gbc_progressBar_4);
		
		JLabel lblStarboardAft = new JLabel("Starboard Aft");
		GridBagConstraints gbc_lblStarboardAft = new GridBagConstraints();
		gbc_lblStarboardAft.anchor = GridBagConstraints.WEST;
		gbc_lblStarboardAft.gridwidth = 2;
		gbc_lblStarboardAft.insets = new Insets(0, 0, 5, 0);
		gbc_lblStarboardAft.gridx = 0;
		gbc_lblStarboardAft.gridy = 12;
		panel_8.add(lblStarboardAft, gbc_lblStarboardAft);
		
		JProgressBar progressBar_5 = new JProgressBar();
		progressBar_5.setValue(50);
		GridBagConstraints gbc_progressBar_5 = new GridBagConstraints();
		gbc_progressBar_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar_5.gridwidth = 2;
		gbc_progressBar_5.gridx = 0;
		gbc_progressBar_5.gridy = 13;
		panel_8.add(progressBar_5, gbc_progressBar_5);
	}

	/**
     * @return    A HashSet containing the CommPortIdentifier for all serial ports that are not currently being used.
     */
    public static HashSet<CommPortIdentifier> getAvailableSerialPorts() {
        HashSet<CommPortIdentifier> h = new HashSet<CommPortIdentifier>();
        Enumeration thePorts = CommPortIdentifier.getPortIdentifiers();
        while (thePorts.hasMoreElements()) {
            CommPortIdentifier com = (CommPortIdentifier) thePorts.nextElement();
            switch (com.getPortType()) {
            case CommPortIdentifier.PORT_SERIAL:
                try {
                    CommPort thePort = com.open("CommUtil", 50);
                    thePort.close();
                    h.add(com);
                } catch (PortInUseException e) {
                    System.out.println("Port, "  + com.getName() + ", is in use.");
                } catch (Exception e) {
                    System.err.println("Failed to open port " +  com.getName());
                    e.printStackTrace();
                }
            }
        }
        return h;
    }
}
