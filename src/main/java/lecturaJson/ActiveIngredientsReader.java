package lecturaJson;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class ActiveIngredientsReader extends ObjetoReader {

	private static final String ACTIVE_ING_TAGNAME = "activeIngredients";
	
	private static final String NAME_FIELD_TAGNAME = "name";

	public ActiveIngredientsReader(ObjetoReader o) {
		super(o);
		// TODO Auto-generated constructor stub
	}

	protected StringBuffer read(JsonReader reader, StringBuffer readData, String name) throws IOException {
		
		if (name.equals(ACTIVE_ING_TAGNAME)) {
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
		StringBuffer ingData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			ingData.append(entryReader(reader)).append("\n");
			reader.endObject();
		}
		ingData.append("\n");
		reader.endArray();
		return ingData;
	}

	protected String entryReader(JsonReader reader) throws IOException {
		// reader.require(XmlPullParser.START_TAG, ns, SINGLE_ELEMENT_TAGNAME);
		String ingName = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(NAME_FIELD_TAGNAME)) {
				ingName = reader.nextString();
			} else {
				reader.skipValue();
			}
		}

		return ingName;
	}

}
