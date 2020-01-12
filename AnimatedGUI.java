
//Name : Siska Kristanti Lim
//Student number : 170281939
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics.*;

public class AnimatedGUI {
	private JFrame frame;
	private int xSquare;
	private int xCircle;
	private int diameter;
	private int xvelSquare;
	private int xvelCircle;
	private boolean animateSquare;
	private boolean animateCircle;
	private JButton startStopSquareAnimationButton;
	private JButton startStopCircleAnimationButton;
	private DrawPanel drawPanel;

	public AnimatedGUI() {
		xSquare = 1;
		xCircle = 1;
		xvelSquare = 1;
		xvelCircle = 1;
		diameter = 50;
		animateSquare = true;
		animateCircle = true;
		startStopSquareAnimationButton = new JButton("click me to stop the square animation");
		startStopCircleAnimationButton = new JButton("click me to stop the circle animation");
		drawPanel = new DrawPanel();
	}

	public static void main(String[] args) {
		AnimatedGUI gui = new AnimatedGUI();
		gui.go();
	}

	public void go() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().add(drawPanel, BorderLayout.CENTER);
		frame.getContentPane().add(startStopSquareAnimationButton, BorderLayout.PAGE_START);
		startStopSquareAnimationButton.addActionListener(new SquareListener());
		frame.getContentPane().add(startStopCircleAnimationButton, BorderLayout.PAGE_END);
		startStopCircleAnimationButton.addActionListener(new CircleListener());

		frame.setSize(500, 500);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
	}

	class DrawPanel extends JPanel {
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.yellow);
			g2.fillRect(0, 0, this.getWidth(), this.getHeight());
			g2.setColor(Color.red);
			g2.fillRect(xSquare, 0, 50, 50);
			g2.setColor(Color.blue);
			g2.fillOval((this.getWidth() - (diameter + xCircle)) / 2, (this.getHeight() - (diameter + xCircle)) / 2,
					diameter + xCircle, diameter + xCircle);
			if (animateSquare)
				animateSquare();
			if (animateCircle)
				animateCircle();
		}

		public void animateSquare() {
			xSquare = xSquare + xvelSquare;
			try {
				Thread.sleep(5);
			} catch (Exception ex) {
			}
			if (xSquare + 50 >= this.getWidth()) {
				xvelSquare *= -1;
				xSquare = this.getWidth() - 50;
			} else if (xSquare <= 0) {
				xvelSquare *= -1;
				xSquare = -xSquare;
			}
			repaint();
		}

		public void animateCircle() {
			xCircle = xCircle + xvelCircle;
			double diagonal = (this.getWidth() * this.getWidth()) + (this.getHeight() * this.getHeight());
			try {
				Thread.sleep(5);
			} catch (Exception ex) {
			}
			if (xCircle + diameter > Math.sqrt(diagonal) + 10) {
				xvelCircle *= -1;
				xCircle = (int) (Math.sqrt(diagonal)) - diameter;
			} else if (xCircle <= 0) {
				xvelCircle *= -1;
				xCircle = -xCircle;
			}
			repaint();
		}

	}

	class SquareListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			animateSquare = !animateSquare;
			drawPanel.repaint();
		}
	}

	class CircleListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			animateCircle = !animateCircle;
			drawPanel.repaint();
		}
	}
}