package pl.michzimny.goniec;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MainFrame extends JFrame {

	private static MainFrame instance;

	public static MainFrame getInstance() {
		return instance;
	}

	private final JTextArea textArea = new JTextArea();

	private final JScrollPane scroll;

	public MainFrame() {
		super("Zimny Goniec " + Goniec.VERSION);

		instance = this;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 600);

		getContentPane().setLayout(new BorderLayout());
		scroll = new JScrollPane(textArea);
		getContentPane().add(scroll, BorderLayout.CENTER);
		textArea.setEditable(false);

		buildMenu();

		setVisible(true);
	}

	private void buildMenu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("Goniec");
		menuBar.add(menu);

		JMenuItem itemClear = new JMenuItem("clear");
		menu.add(itemClear);
		itemClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				clear();
			}
		});

		JMenuItem itemStatic = new JMenuItem("static...");
		menu.add(itemStatic);
		itemStatic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new StaticDialog(MainFrame.this).setVisible(true);
			}
		});
	}

	protected void clear() {
		textArea.setText("");
	}

	public synchronized void print(final String string) {
		textArea.setText(textArea.getText() + string);
		JScrollBar v = scroll.getVerticalScrollBar();
		v.setValue(v.getMaximum());
	}

}
