package pl.michzimny.goniec;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.SwingUtilities;

public class Goniec implements Printer {

	public static final String VERSION = "1.0";

	private static Goniec instance;

	public static Goniec getInstance() {
		return instance;
	}

	private final MainFrame mainFrame;

	private int port;

	private FtpSettings ftp;

	private Goniec() {
		instance = this;

		this.mainFrame = new MainFrame();

		try {
			Config config = new Config();
			this.port = config.getLocalPort();
			this.ftp = config.getFtpSettings();

			println("FTP settings: " + this.ftp);
			print("starting on port " + port + "... ");
			startListening();
		} catch (Exception e) {
			println(e.toString());
		}
	}

	public void startListening() throws IOException {
		try (ServerSocket socket = new ServerSocket(port)) {
			println("done");
			while (true) {
				Socket connectionSocket = socket.accept();
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(
						connectionSocket.getInputStream()));
				String line;
				final Session session = new Session();
				while ((line = inFromClient.readLine()) != null) {
					session.addLine(line);
				}
				if (!session.isEmpty()) {
					new Thread() {

						@Override
						public void run() {
							try {
								upload(session, this.getId(), Goniec.this);
							} catch (Exception e) {
								println(e.toString());
							}
						};
					}.start();
				}
			}
		}
	}

	public void upload(Session session, long threadId, Printer printer) throws IOException, FTPIllegalReplyException,
			FTPException, FileNotFoundException, FTPDataTransferException, FTPAbortedException {
		printer.println("starting thread #" + threadId);
		FTPClient client = new FTPClient();
		client.connect(ftp.getHost(), ftp.getPort());
		client.login(ftp.getUsername(), ftp.getPassword());
		client.changeDirectory(ftp.getDirectory());
		client.setType(FTPClient.TYPE_BINARY);
		for (File file : session.getFiles()) {
			printer.print("#" + threadId + ": sending: " + file + " ... ");
			client.upload(file);
			printer.println("done");
		}
		try {
			client.disconnect(true);
		} catch (Exception e) {
		}
		printer.println("finished thread #" + threadId);
	}

	@Override
	public void println(final String string) {
		print(string + "\n");
	}

	@Override
	public void print(final String string) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				mainFrame.print(string);
			}
		});
	}

	public static void main(String[] args) throws Exception {
		new Goniec();
	}

}
