package lecturaJson;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class InhalersReader extends ObjetoReader {
	private static final String INHALERS_TAGNAME = "inhalers";

	private static final String NAME_FIELD_TAGNAME = "name";
	private static final String IMAGE_FIELD_TAGNAME = "image";

	private static final String FIELD_SEPARATOR = "; ";

	public InhalersReader(ObjetoReader o) {
		super(o);
		// TODO Auto-generated constructor stub
	}

	protected StringBuffer read(JsonReader reader, StringBuffer readData, String name) throws IOException {

		if (name.equals(INHALERS_TAGNAME)) {
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
		StringBuffer inhalerData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			inhalerData.append(entryReader(reader)).append("\n");
			reader.endObject();
		}
		inhalerData.append("\n");
		reader.endArray();
		return inhalerData;
	}

	protected String entryReader(JsonReader reader) throws IOException {
		String inhalerName = null;
		String inhalerImage = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(NAME_FIELD_TAGNAME)) {
				inhalerName = reader.nextString();
			} else if (name.equals(IMAGE_FIELD_TAGNAME)) {
				inhalerImage = reader.nextString();
			} else {
				reader.skipValue();
			}

		}
		return inhalerName + FIELD_SEPARATOR + inhalerImage;
	}

}
