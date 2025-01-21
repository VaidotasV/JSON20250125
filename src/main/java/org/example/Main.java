import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        Person person = new Person("Vaidotas", 45, "vaidotas@gmail.com");

        ObjectMapper objectMapper = new ObjectMapper();

        String fileName = "rezultatas.json";

        try {
            objectMapper.writeValue(new File(fileName), person);
            System.out.println("JSON objektas sėkmingai įrašytas į failą: " + fileName);

            File jsonFile = new File(fileName);
            if (jsonFile.exists()) {
                System.out.println("Failas '" + fileName + "' sukurtas.");
            }

            Person deserializedPerson = objectMapper.readValue(jsonFile, Person.class);
            System.out.println("Deserializuotas objektas: " + deserializedPerson);

            if (person.equals(deserializedPerson)) {
                System.out.println("Deserializuotas objektas sutampa su originaliu objektu.");
            } else {
                System.out.println("Deserializuotas objektas NESUTAMPA su originaliu objektu.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
