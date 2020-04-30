package lecturaJson;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class MedicineReader extends ObjetoReader {
	private static final String MEDICINES_TAGNAME = "medicines";

	private static final String NAME_FIELD_TAGNAME = "name";

	public MedicineReader(ObjetoReader o) {
		super(o); // aqui se crea sig
		// TODO Auto-generated constructor stub
	}

	protected StringBuffer read(JsonReader reader, StringBuffer readData, String name) throws IOException {

		if (name.equals(MEDICINES_TAGNAME)) {
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
		String medName = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(NAME_FIELD_TAGNAME)) {
				medName = reader.nextString();
			} else {
				reader.skipValue();
			}
		}

		return medName;
	}

}
