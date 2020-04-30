package lecturaJson;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class RescueMedicinePresentationsReader extends ObjetoReader {
	private static final String RESCUEMEDPRES_TAGNAME = "rescueMedicinePresentations";

	private static final String MEDREF_FIELD_TAGNAME = "medicineRef";
	private static final String ACTINGREF_FIELD_TAGNAME = "activeIngRef";
	private static final String INHREF_FIELD_TAGNAME = "inhalerRef";
	private static final String DOSE_FIELD_TAGNAME = "dose";

	private static final String FIELD_SEPARATOR = "; ";

	public RescueMedicinePresentationsReader(ObjetoReader o) {
		super(o);
		// TODO Auto-generated constructor stub
	}

	protected StringBuffer read(JsonReader reader, StringBuffer readData, String name) throws IOException {

		if (name.equals(RESCUEMEDPRES_TAGNAME)) {
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
		StringBuffer rescueMedicinePresentationData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			rescueMedicinePresentationData.append(entryReader(reader)).append("\n");
			reader.endObject();
		}
		rescueMedicinePresentationData.append("\n");
		reader.endArray();
		return rescueMedicinePresentationData;
	}

	// Parses the contents of a rescue medicine presentation entry
	protected String entryReader(JsonReader reader) throws IOException {
		String medRef = null;
		String aiRef = null;
		String inhRef = null;
		String dose = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(MEDREF_FIELD_TAGNAME)) {
				medRef = reader.nextString();
			} else if (name.equals(ACTINGREF_FIELD_TAGNAME)) {
				aiRef = reader.nextString();
			} else if (name.equals(INHREF_FIELD_TAGNAME)) {
				StringBuffer res = new StringBuffer();
				reader.beginArray();
				while (reader.hasNext()) {
					res.append(reader.nextString()).append(",");
				}
				reader.endArray();
				res.deleteCharAt(res.length() - 1);
				inhRef = new String(res);
			} else if (name.equals(DOSE_FIELD_TAGNAME)) {
				StringBuffer res = new StringBuffer();
				reader.beginArray();
				while (reader.hasNext()) {
					res.append(reader.nextString()).append(",");
				}
				reader.endArray();
				res.deleteCharAt(res.length() - 1);
				dose = new String(res);
			} else {
				reader.skipValue();
			}

		}
		return medRef + FIELD_SEPARATOR + aiRef + FIELD_SEPARATOR + inhRef + FIELD_SEPARATOR + dose;
	}

}
