package pl.michzimny.goniec;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FtpSettings {

	private final String host;

	private final int port;

	private final String username;

	private final String password;

	private final String directory;

	@Override
	public String toString() {
		return "[" + username + "] @ [" + host + ":" + port + "] : [" + directory + "]";
	}

}
