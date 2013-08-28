package pl.michzimny.goniec;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class Config {

	private static final String FILENAME = "goniec.xml";

	private static final String LOCAL_PORT = "local.port";

	private static final String FTP_HOST = "ftp.host";

	private static final String FTP_PORT = "ftp.port";

	private static final String FTP_USERNAME = "ftp.username";

	private static final String FTP_PASSWORD = "ftp.password";

	private static final String FTP_DIRECTORY = "ftp.directory";

	private final Properties properties;

	public Config() throws InvalidPropertiesFormatException, FileNotFoundException, IOException {
		this.properties = new Properties();
		properties.loadFromXML(new FileInputStream(FILENAME));
	}

	public int getLocalPort() {
		return Integer.parseInt(properties.getProperty(LOCAL_PORT, "8090"));
	}

	public FtpSettings getFtpSettings() {
		String host = properties.getProperty(FTP_HOST);
		int port = Integer.parseInt(properties.getProperty(FTP_PORT, "21"));
		String username = properties.getProperty(FTP_USERNAME);
		String password = properties.getProperty(FTP_PASSWORD);
		String directory = properties.getProperty(FTP_DIRECTORY);
		return new FtpSettings(host, port, username, password, directory);
	}

}
