package view;

/*
 * Adapted from code by Ariana Fairbanks
 * 
 */

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import adapter.Controller;

public class Frame extends JFrame {
	private static final long serialVersionUID;
	public static Dimension DIMENSIONS;

	static {
		serialVersionUID = -2248105492340561524L;
		DIMENSIONS = new Dimension(970, 600);
	}

	/*
	 * INSTANCE FIELDS
	 */
	private Controller controller;
	private JPanel panel;
//	private Default setting;
	private Dimension size;

	/*
	 * CONSTRUCTORS
	 */
	public Frame(Controller controller) {
		this.controller = controller;

		//
		panel = new Default(controller);

		//
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		// setUndecorated(true);
		setMinimumSize(DIMENSIONS);
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);

	}

	public void updateState() {
		// FIXME - need list of various views/states
		
		setContentPane(panel);
		panel.revalidate();
		panel.repaint();
	}
}
