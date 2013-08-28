package pl.michzimny.goniec;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Session {

	private File directory;

	private final ArrayList<File> files = new ArrayList<>();

	public void addLine(String line) {
		if (line.equals("TeStW") || line.equals("bye")) {
			return;
		}
		if (line.contains("\\")) {
			directory = new File(line);
		} else {
			files.add(new File(directory, line));
		}
	}

	public List<File> getFiles() {
		@SuppressWarnings("unchecked")
		List<File> list = (List<File>) files.clone();
		Collections.reverse(list);
		return list;
	}

	public boolean isEmpty() {
		return files.size() == 0;
	}

}
