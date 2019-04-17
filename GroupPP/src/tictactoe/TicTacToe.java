package tictactoe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TicTacToe 
{

	private GridButtonListener buttonListener;
	
	// private popOutButtonListener popOutButtonListener;
	private JButton gridButtons[] = new JButton[9];
	private JButton popOutButtons[] = new JButton[2];
	private Font buttonFont = new Font("Arial", Font.ITALIC, 55);
	private JFrame popOutFrame;
	public boolean win = false;
	private int count = 0;

	public TicTacToe()
	{

		// Create JFrames
		JFrame frame = new JFrame("Tic Tac Toe");
		popOutFrame = new JFrame("You Won!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		popOutFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		popOutFrame.setAlwaysOnTop(true);
		popOutFrame.setPreferredSize(new Dimension(300, 100));

		// Create JPanel & set preferences
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 3));
		panel.setPreferredSize(new Dimension(300, 300));
		panel.setBackground(Color.DARK_GRAY);
		buttonListener = new GridButtonListener();

		// Initialize popOutPanel & set preferences
		JPanel popOutPanel = new JPanel();
		GridLayout layout = new GridLayout(1, 2, 20, 0);
		popOutPanel.setLayout(layout);
		popOutPanel.setPreferredSize(new Dimension(100, 100));

		// Create gridButtons array & add to panel
		for (int x = 0; x < 9; x++) 
		{
			gridButtons[x] = new JButton();
			panel.add(gridButtons[x]);
			gridButtons[x].addActionListener(buttonListener);
		}

		// popOutButtons array added to panel
		for (int x = 0; x < 2; x++) 
		{
			popOutButtons[x] = new JButton();
			popOutPanel.add(popOutButtons[x]);

			// actionListener to popOutButtons to play a new game or play again
			popOutButtons[x].addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent f) {
					if (((JButton) f.getSource()).getText().equals("Another game?")) 
					{
						frame.dispose();
						popOutFrame.setVisible(false);
						new TicTacToe();
					}
				}
			});

			// actionListener to popOutButtons for exiting application
			popOutButtons[x].addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent f) 
				{
					if (((JButton) f.getSource()).getText().equals("Exit"))
					{
						System.exit(0);
					}
				}
			});
		}

		popOutButtons[0].setText("Play Again!");
		popOutButtons[1].setText("Exit");

		frame.getContentPane().add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		popOutFrame.getContentPane().add(popOutPanel);
		popOutFrame.pack();
		popOutFrame.setLocationRelativeTo(frame);
	}

	//Win condition detections
	private boolean detectWin() 
	{
		// top row detection
		if (!gridButtons[0].getText().equals("") && gridButtons[0].getText().equals(gridButtons[1].getText())
				&& gridButtons[0].getText().equals(gridButtons[2].getText())) {
			System.out.printf("'%s' wins horizontally!%n", gridButtons[0].getText());
			win = true;
		}
		// middle row dtection
		else if (!gridButtons[3].getText().equals("") && gridButtons[3].getText().equals(gridButtons[4].getText())
				&& gridButtons[3].getText().equals(gridButtons[5].getText())) {
			System.out.printf("'%s' wins horizontally!%n", gridButtons[3].getText());
			win = true;
		}
		
		// bottom row detection
		else if (!gridButtons[6].getText().equals("") && gridButtons[6].getText().equals(gridButtons[7].getText())
				&& gridButtons[6].getText().equals(gridButtons[8].getText())) {
			System.out.printf("'%s' wins horizontally!%n", gridButtons[6].getText());
			win = true;
		}
		
		// left column detection
		else if (!gridButtons[0].getText().equals("") && gridButtons[0].getText().equals(gridButtons[3].getText())
				&& gridButtons[0].getText().equals(gridButtons[6].getText())) {
			System.out.printf("'%s' wins vertically!%n", gridButtons[0].getText());
			win = true;
		}
		
		// middle column detection
		else if (!gridButtons[1].getText().equals("") && gridButtons[1].getText().equals(gridButtons[4].getText())
				&& gridButtons[1].getText().equals(gridButtons[7].getText())) {
			System.out.printf("'%s' wins vertically!%n", gridButtons[1].getText());
			win = true;
		}
		
		// right colum detection
		else if (!gridButtons[2].getText().equals("") && gridButtons[2].getText().equals(gridButtons[5].getText())
				&& gridButtons[2].getText().equals(gridButtons[8].getText())) {
			System.out.printf("'%s' wins vertically!%n", gridButtons[2].getText());
			win = true;
		}
		
		// top left to bottom right detection
		else if (!gridButtons[0].getText().equals("") && gridButtons[0].getText().equals(gridButtons[4].getText())
				&& gridButtons[0].getText().equals(gridButtons[8].getText())) {
			System.out.printf("'%s' wins diagonally!%n", gridButtons[0].getText());
			win = true;
		}
		
		//top right to bottom left detection
		else if (!gridButtons[6].getText().equals("") && gridButtons[6].getText().equals(gridButtons[2].getText())
				&& gridButtons[6].getText().equals(gridButtons[2].getText())) {
			System.out.printf("'%s' wins diagonally!%n", gridButtons[6].getText());
			win = true;
		}
		
		if (win) 
		{
			popOutFrame.setVisible(true);
		}
		
		return win;
	}

	private class GridButtonListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent event) 
		{
			if (count % 2 == 0 && ((JButton) event.getSource()).getText().equals("")) 
			{
				((JButton) event.getSource()).setText("X");
				((JButton) event.getSource()).setFont(buttonFont);
				((JButton) event.getSource()).setForeground(Color.black);
				if (!win) 
				{
					detectWin();
				}
				count++;
			} 
			
			else if (count % 2 != 0 && ((JButton) event.getSource()).getText().equals(""))
			{
				((JButton) event.getSource()).setText("O");
				((JButton) event.getSource()).setFont(buttonFont);
				((JButton) event.getSource()).setForeground(Color.red);

				if (!win) 
				{
					detectWin();
				}
				count++;
			}
		}
	}
}