package commons;

import com.github.javafaker.Faker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DataHelper {
    private final Faker  faker  = new Faker();
    private final Locale locale = new Locale("en");

    public static DataHelper getData() {
        return new DataHelper();
    }

    public String getFullname() {
        return faker.name().fullName();
    }

    public String getEmail() {
        return faker.internet().emailAddress();
    }
}
