package lecturaJson;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class PosologiesReader extends ObjetoReader {
	private static final String POSOLOGIES_TAGNAME = "posologies";

	private static final String DESCRIPTION_FIELD_TAGNAME = "description";

	public PosologiesReader(ObjetoReader o) {
		super(o);
		// TODO Auto-generated constructor stub
	}

	protected StringBuffer read(JsonReader reader, StringBuffer readData, String name) throws IOException {

		if (name.equals(POSOLOGIES_TAGNAME)) {
			readData.append(reader(reader)).append("\n");
		} else if (sig != null) {
			readData = super.read(reader, readData, name);
		} else {
			reader.skipValue();
			System.err.println("Category " + name + " not processed.");
		}
		return readData;
	}

	protected StringBuffer reader(JsonReader reader) throws IOException {
		StringBuffer posolData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			posolData.append(entryReader(reader)).append("\n");
			reader.endObject();
		}
		posolData.append("\n");
		reader.endArray();
		return posolData;
	}

	protected String entryReader(JsonReader reader) throws IOException {
		String posolDesc = null;
		while (reader.hasNext()) {
			String desc = reader.nextName();
			if (desc.equals(DESCRIPTION_FIELD_TAGNAME)) {
				posolDesc = reader.nextString();
			} else {
				reader.skipValue();
			}

		}
		return posolDesc;

	}

}
