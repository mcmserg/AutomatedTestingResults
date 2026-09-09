package ru.netology.TestingResuils.data;


import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateName(Faker faker) {
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String generatePhone(Faker faker) {
        return faker.phoneNumber().phoneNumber();
    }

    public static String generateEmail(Faker faker) {
        return faker.internet().emailAddress();
    }

    public static class Registration {
        private static Faker faker;

        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            faker = new Faker(new Locale(locale));
            return new UserInfo(generateName(faker), generatePhone(faker), generateEmail(faker));
        }
    }

    @Value
    public static class UserInfo {
        String name;
        String phone;
        String email;
    }
}