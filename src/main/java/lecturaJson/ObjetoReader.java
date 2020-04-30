package lecturaJson;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public abstract class ObjetoReader {
	protected ObjetoReader sig;

	public ObjetoReader(ObjetoReader o) {
		sig = o;
	}

	protected StringBuffer read(JsonReader reader, StringBuffer readData, String name) throws IOException {
		return sig.read(reader, readData, name);
	}

	protected abstract StringBuffer reader(JsonReader reader) throws IOException;

	protected abstract String entryReader(JsonReader reader) throws IOException;

}



