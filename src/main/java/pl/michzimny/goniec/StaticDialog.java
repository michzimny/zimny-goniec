package pl.michzimny.goniec;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class StaticDialog extends JDialog {

	private final JTextField path = new JTextField();

	private final JTextField shortname = new JTextField();

	public StaticDialog(final MainFrame parent) {
		super(parent, "Sending Pary static pages");
		setModal(true);
		setLayout(new FlowLayout());
		setSize(600, 100);
		setResizable(false);

		add(new JLabel("Enter HTML directory path and tournament shortname:"));
		add(path);
		path.setColumns(30);
		add(shortname);
		shortname.setColumns(5);

		JButton button = new JButton("Find & send!");
		add(button);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				StaticDialog.this.setVisible(false);
				new Thread() {

					@Override
					public void run() {
						try {
							Session session = prepareSession(path.getText(), shortname.getText());
							if (!session.isEmpty()) {
								new SessionWorker(session).execute();
							}
						} catch (Exception e) {
							parent.print(e.toString() + "\n");
						}
					};
				}.run();
			}
		});
	}

	protected Session prepareSession(String path, final String shortname) {
		File dir = new File(path);
		String[] files = dir.list(new FilenameFilter() {

			@Override
			public boolean accept(File file, String filename) {
				int i = filename.lastIndexOf('.');
				String name = filename;
				if (i > 0) {
					name = filename.substring(0, i);
				}
				return name.toLowerCase().contains(shortname.toLowerCase());
			}
		});

		Session session = new Session();
		if (files != null) {
			session.addLine(dir.getAbsolutePath());
			for (String filename : files) {
				session.addLine(filename);
			}
		}
		return session;
	}
}
