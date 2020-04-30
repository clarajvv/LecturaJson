package lecturaJson;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GsonDatabaseClient {

	public static void main(String[] args) {
		try {
			DatabaseJSonReader dbjp = new DatabaseJSonReader();

			UserManualStepsReader umsa = new UserManualStepsReader(null);
			UserManualPhysioStepsReader umpa = new UserManualPhysioStepsReader(umsa);
			RescueMedicinePresentationsReader rmpa = new RescueMedicinePresentationsReader(umpa);
			MedicinePresentationsReader mpa = new MedicinePresentationsReader(rmpa);
			PosologiesReader pa = new PosologiesReader(mpa);
			InhalersReader ia = new InhalersReader(pa);
			PhysiotherapiesReader pya = new PhysiotherapiesReader(ia);
			ActiveIngredientsReader aia = new ActiveIngredientsReader(pya);
			MedicineReader mr = new MedicineReader(aia);

			try {
				System.out.println(dbjp.parse("./src/main/resources/datos.json", mr));
			} finally {
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
