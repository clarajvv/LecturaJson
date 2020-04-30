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

	protected StringBuffer reader(JsonReader reader) throws IOException {
		StringBuffer objetoData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			objetoData.append(entryReader(reader)).append("\n");
			reader.endObject();
		}
		objetoData.append("\n");
		reader.endArray();
		return objetoData;
	}

	protected abstract String entryReader(JsonReader reader) throws IOException;

}
