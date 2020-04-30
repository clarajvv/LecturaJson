package lecturaJson;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.stream.JsonReader;

/**
 * Created by jmalvarez on 11/5/16.
 * http://developer.android.com/intl/es/training/basics/network-ops/xml.html
 */
public class DatabaseJSonReader {

	private static final String MEDICINES_TAGNAME = "medicines";
	private static final String RESCUEMEDPRES_TAGNAME = "rescueMedicinePresentations";
	private static final String MANUAL_PHYSIO_TAGNAME = "userManualPhysioSteps";
	private static final String ACTIVE_ING_TAGNAME = "activeIngredients";
	private static final String INHALERS_TAGNAME = "inhalers";
	private static final String POSOLOGIES_TAGNAME = "posologies";
	private static final String MEDICINE_PRESENT_TAGNAME = "medicinePresentations";
	private static final String MANUAL_STEPS_TAGNAME = "userManualSteps";
	private static final String PHYSIOTHERAPIES_TAGNAME = "physiotherapies";

	private static final String NAME_FIELD_TAGNAME = "name";
	private static final String MEDREF_FIELD_TAGNAME = "medicineRef";
	private static final String ACTINGREF_FIELD_TAGNAME = "activeIngRef";
	private static final String INHREF_FIELD_TAGNAME = "inhalerRef";
	private static final String DOSE_FIELD_TAGNAME = "dose";
	private static final String IMAGE_FIELD_TAGNAME = "image";
	private static final String DESCRIPTION_FIELD_TAGNAME = "description";
	private static final String POSREF_FIELD_TAGNAME = "posologyRef";
	private static final String STEPTITLE_FIELD_TAGNAME = "stepTitle";
	private static final String STEPIMAGE_FIELD_TAGNAME = "stepImage";
	private static final String STEPTEXT_FIELD_TAGNAME = "stepText";
	private static final String PHYSIOREF_FIELD_TAGNAME = "physioRef";

	private static final String FIELD_SEPARATOR = "; ";

	public DatabaseJSonReader() {
	}

	public String parse(String jsonFileName/*, ObjetoReader oRead*/) throws IOException {

		InputStream usersIS = new FileInputStream(new File(jsonFileName));
		JsonReader reader = new JsonReader(new InputStreamReader(usersIS, "UTF-8"));

		reader.beginObject();
		StringBuffer readData = new StringBuffer();

		while (reader.hasNext()) {
			// usamos el reader como argumento en las creaciones de los readers

			

			String name = reader.nextName();

			if (name.equals(MEDICINES_TAGNAME)) {
				readData.append(readMedicines(reader)).append("\n");
			} else if (name.equals(RESCUEMEDPRES_TAGNAME)) {
				readData.append(readRescueMedicinePresentations(reader)).append("\n");
			} else if (name.equals(ACTIVE_ING_TAGNAME)) {
				readData.append(readActiveIng(reader)).append("\n");
			} else if (name.equals(PHYSIOTHERAPIES_TAGNAME)) {
				readData.append(readPhysiotherapy(reader)).append("\n");
			} else if (name.equals(INHALERS_TAGNAME)) {
				readData.append(readInhaler(reader)).append("\n");
			} else if (name.equals(POSOLOGIES_TAGNAME)) {
				readData.append(readPosology(reader)).append("\n");
			} else if (name.equals(MEDICINE_PRESENT_TAGNAME)) {
				readData.append(readMedicinePresentation(reader)).append("\n");
			} else if (name.equals(MANUAL_PHYSIO_TAGNAME)) {
				readData.append(readManualPhysio(reader)).append("\n");
			} else if (name.equals(MANUAL_STEPS_TAGNAME)) {
				readData.append(readManualSteps(reader)).append("\n");
			} else {
				reader.skipValue();
				System.err.println("Category " + name + " not processed.");
			}

		}

		reader.endObject();
		reader.close();
		usersIS.close();

		return new String(readData);

	}

	private StringBuffer readManualSteps(JsonReader reader) throws IOException {
		StringBuffer mStepsData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			mStepsData.append(readManualStepsEntry(reader)).append("\n");
			reader.endObject();
		}
		mStepsData.append("\n");
		reader.endArray();
		return mStepsData;
	}

	private String readManualStepsEntry(JsonReader reader) throws IOException {
		String stepTitle = null;
		String stepImage = null;
		String stepText = null;
		String inhalerRef = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(STEPTITLE_FIELD_TAGNAME)) {
				stepTitle = reader.nextString();
			} else if (name.equals(STEPIMAGE_FIELD_TAGNAME)) {
				stepImage = reader.nextString();
			} else if (name.equals(STEPTEXT_FIELD_TAGNAME)) {
				stepText = reader.nextString();
			} else if (name.equals(INHREF_FIELD_TAGNAME)) {
				inhalerRef = reader.nextString();
			} else {
				reader.skipValue();
			}

		}
		return stepTitle + FIELD_SEPARATOR + stepImage + FIELD_SEPARATOR + stepText + FIELD_SEPARATOR + inhalerRef;

	}

	private StringBuffer readManualPhysio(JsonReader reader) throws IOException {
		StringBuffer mPhysioData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			mPhysioData.append(readManualPhysioEntry(reader)).append("\n");
			reader.endObject();
		}
		mPhysioData.append("\n");
		reader.endArray();
		return mPhysioData;
	}

	private String readManualPhysioEntry(JsonReader reader) throws IOException {
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
		return stepTitle + FIELD_SEPARATOR + stepImage + FIELD_SEPARATOR
				+ stepText + FIELD_SEPARATOR + physioRef;
	}

	private StringBuffer readMedicinePresentation(JsonReader reader) throws IOException {
		StringBuffer presenData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			presenData.append(readMedPresentEntry(reader)).append("\n");
			reader.endObject();
		}
		presenData.append("\n");
		reader.endArray();
		return presenData;
	}

	private String readMedPresentEntry(JsonReader reader) throws IOException {
		String medRef = null;
		String aiRef = null;
		String inhRef = null;
		String dose = null;
		String posRef = null;
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
				//res.deleteCharAt(res.length() - 1);
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
			} else if (name.contentEquals(POSREF_FIELD_TAGNAME)) {
				StringBuffer res = new StringBuffer();
				reader.beginArray();
				while (reader.hasNext()) {
					res.append(reader.nextString()).append(",");
				}
				reader.endArray();
				res.deleteCharAt(res.length() - 1);
				posRef = new String(res);
			} else {
				reader.skipValue();
			}

		}
		return medRef + FIELD_SEPARATOR + aiRef + FIELD_SEPARATOR + inhRef + FIELD_SEPARATOR + dose + FIELD_SEPARATOR
				+ posRef;

	}

	private StringBuffer readPosology(JsonReader reader) throws IOException {
		StringBuffer posolData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			posolData.append(readPosolEntry(reader)).append("\n");
			reader.endObject();
		}
		posolData.append("\n");
		reader.endArray();
		return posolData;
	}

	private String readPosolEntry(JsonReader reader) throws IOException {
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

	private StringBuffer readInhaler(JsonReader reader) throws IOException {
		StringBuffer inhalerData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			inhalerData.append(readInhalerEntry(reader)).append("\n");
			reader.endObject();
		}
		inhalerData.append("\n");
		reader.endArray();
		return inhalerData;
	}

	private String readInhalerEntry(JsonReader reader) throws IOException {
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

	private StringBuffer readPhysiotherapy(JsonReader reader) throws IOException {
		StringBuffer physioData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			physioData.append(readPhysioEntry(reader)).append("\n");
			reader.endObject();
		}
		physioData.append("\n");
		reader.endArray();
		return physioData;
	}

	private String readPhysioEntry(JsonReader reader) throws IOException {
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

	private StringBuffer readActiveIng(JsonReader reader) throws IOException {
		StringBuffer ingData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			ingData.append(readMedicineEntry(reader)).append("\n");
			reader.endObject();
		}
		ingData.append("\n");
		reader.endArray();
		return ingData;
	}

	// Parses the contents of a medicine list.
	private StringBuffer readMedicines(JsonReader reader) throws IOException {
		StringBuffer medicineData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			medicineData.append(readMedicineEntry(reader)).append("\n");
			reader.endObject();
		}
		medicineData.append("\n");
		reader.endArray();
		return medicineData;
	}

	// Parses the contents of a medicine or active ingredient
	private String readMedicineEntry(JsonReader reader) throws IOException {
		// reader.require(XmlPullParser.START_TAG, ns, SINGLE_ELEMENT_TAGNAME);
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

	// Parses the contents of a medicine list.
	private StringBuffer readRescueMedicinePresentations(JsonReader reader) throws IOException {
		StringBuffer rescueMedicinePresentationData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			rescueMedicinePresentationData.append(readRescueMedicinePresentationEntry(reader)).append("\n");
			reader.endObject();
		}
		rescueMedicinePresentationData.append("\n");
		reader.endArray();
		return rescueMedicinePresentationData;
	}

	// Parses the contents of a rescue medicine presentation entry
	private String readRescueMedicinePresentationEntry(JsonReader reader) throws IOException {
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
