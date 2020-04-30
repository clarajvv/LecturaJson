package lecturaJson;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class UserManualPhysioStepsReader extends ObjetoReader {
	private static final String MANUAL_PHYSIO_TAGNAME = "userManualPhysioSteps";

	private static final String STEPTITLE_FIELD_TAGNAME = "stepTitle";
	private static final String STEPIMAGE_FIELD_TAGNAME = "stepImage";
	private static final String STEPTEXT_FIELD_TAGNAME = "stepText";
	private static final String PHYSIOREF_FIELD_TAGNAME = "physioRef";

	private static final String FIELD_SEPARATOR = "; ";

	public UserManualPhysioStepsReader(ObjetoReader o) {
		super(o);
		// TODO Auto-generated constructor stub
	}

	protected StringBuffer read(JsonReader reader, StringBuffer readData, String name) throws IOException {

		if (name.equals(MANUAL_PHYSIO_TAGNAME)) {
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
		StringBuffer mPhysioData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			mPhysioData.append(entryReader(reader)).append("\n");
			reader.endObject();
		}
		mPhysioData.append("\n");
		reader.endArray();
		return mPhysioData;
	}

	protected String entryReader(JsonReader reader) throws IOException {
		String stepTitle = null;
		String stepImage = null;
		String stepText = null;
		String physioRef = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(STEPTITLE_FIELD_TAGNAME)) {
				stepTitle = reader.nextString();
			} else if (name.equals(STEPIMAGE_FIELD_TAGNAME)) {
				stepImage = reader.nextString();
			} else if (name.equals(STEPTEXT_FIELD_TAGNAME)) {
				stepText = reader.nextString();
			} else if (name.equals(PHYSIOREF_FIELD_TAGNAME)) {
				physioRef = reader.nextString();
			} else {
				reader.skipValue();
			}

		}
		return stepTitle + FIELD_SEPARATOR + stepImage + FIELD_SEPARATOR + stepText + FIELD_SEPARATOR + physioRef;
	}

}
