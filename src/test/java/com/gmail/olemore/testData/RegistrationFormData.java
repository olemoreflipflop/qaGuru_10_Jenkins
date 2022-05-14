package com.gmail.olemore.testData;

import com.github.javafaker.Faker;

import java.time.Year;

public class RegistrationFormData {
    static Faker faker = new Faker();

    static String[]
            genderArray = {"Male", "Female", "Other"},
            monthArray = {"January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November", "December"},
            hobbiesArray = {"Sports", "Reading", "Music"},
            subjectArray = {"Computer Science", "History", "Arts", "Chemistry", "Maths"};

    static int currentYear = Year.now().getValue();

    public static String
            firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            emailAddress = faker.internet().emailAddress(),
            gender = genderArray[faker.number().numberBetween(0, 2)],
            mobileNumber = faker.phoneNumber().subscriberNumber(10),
            day = String.valueOf(faker.number().numberBetween(10, 29)), // стоит переделать под месяц
            month = monthArray[faker.number().numberBetween(0, 11)],
            year = String.valueOf(faker.number().numberBetween(1900, currentYear)),
            subject = subjectArray[faker.number().numberBetween(0, 4)],
            hobby = hobbiesArray[faker.number().numberBetween(0, 2)],
            address = faker.address().streetAddress(),
    // не рандомные данные
            filePath = "file.png",
            state = "NCR",
            city = "Delhi";
}
