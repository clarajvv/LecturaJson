package lecturaJson;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class PhysiotherapiesReader extends ObjetoReader {
	private static final String PHYSIOTHERAPIES_TAGNAME = "physiotherapies";

	private static final String NAME_FIELD_TAGNAME = "name";
	private static final String IMAGE_FIELD_TAGNAME = "image";

	private static final String FIELD_SEPARATOR = "; ";

	public PhysiotherapiesReader(ObjetoReader o) {
		super(o);
		// TODO Auto-generated constructor stub
	}

	protected StringBuffer read(JsonReader reader, StringBuffer readData, String name) throws IOException {

		if (name.equals(PHYSIOTHERAPIES_TAGNAME)) {
			readData.append(reader(reader)).append("\n");
		} else if (sig != null) {
			readData = super.read(reader, readData, name);
		} else {
			reader.skipValue();
			System.err.println("Category " + name + " not processed.");
		}
		return readData;
	}

	protected String entryReader(JsonReader reader) throws IOException {
		String physioName = null;
		String physioImage = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(NAME_FIELD_TAGNAME)) {
				physioName = reader.nextString();
			} else if (name.equals(IMAGE_FIELD_TAGNAME)) {
				physioImage = reader.nextString();
			} else {
				reader.skipValue();
			}

		}
		return physioName + FIELD_SEPARATOR + physioImage;
	}

}
