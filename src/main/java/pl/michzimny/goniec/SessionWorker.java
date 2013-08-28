package pl.michzimny.goniec;

import java.util.List;

import javax.swing.SwingWorker;

public class SessionWorker extends SwingWorker<Void, String> implements Printer {

	private final Session session;

	public SessionWorker(Session session) {
		this.session = session;
	}

	@Override
	protected Void doInBackground() throws Exception {
		Goniec.getInstance().upload(session, -1, this);
		return null;
	}

	@Override
	public void print(String string) {
		publish(string);
	}

	@Override
	public void println(String string) {
		print(string + "\n");
	}

	@Override
	protected void process(List<String> chunks) {
		for (String chunk : chunks) {
			MainFrame.getInstance().print(chunk);
		}
	}

}
