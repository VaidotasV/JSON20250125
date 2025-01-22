import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Person person = new Person("Vaidotas", 30, "vaidotas@gmail.com");

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(person);
            System.out.println("JSON string: " + jsonString);

            List<Person> personList = new ArrayList<>();
            personList.add(person);
            personList.add(new Person("Marius", 40, "marius@gmail.com"));
            personList.add(new Person("Edmundas", 40, "edmundas@gmail.com"));

            File jsonFile = new File("persons.json");
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, personList);
            System.out.println("JSON sąrašas išsaugotas faile 'persons.json'.");

            if (jsonFile.exists()) {
                System.out.println("Failas 'persons.json' sėkmingai sukurtas.");
            }

            List<Person> deserializedList = objectMapper.readValue(jsonFile, new TypeReference<List<Person>>() {});
            System.out.println("Deserializuotas sąrašas: " + deserializedList);

            if (personList.equals(deserializedList)) {
                System.out.println("Deserializuoti elementai SUTAMPA su originaliais.");
            } else {
                System.out.println("Deserializuoti elementai NESUTAMPA su originaliais.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
