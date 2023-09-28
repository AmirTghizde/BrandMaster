package util;

import java.util.regex.Pattern;

public class Validation {
    public static boolean isValidEmail(String email) {
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(emailPattern);
        return email.matches(pattern.pattern());
    }

    public static boolean isValidWebsite(String website) {
        String websitePattern = "(https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9]+\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]+\\.[^\\s]{2,})";
        Pattern pattern = Pattern.compile(websitePattern);
        return website.matches(pattern.pattern());
    }

    public static boolean isValidPassword(String password) {
        String passwordPatternPattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
        Pattern pattern = Pattern.compile(passwordPatternPattern);
        return password.matches(pattern.pattern());
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        String phoneNumberPattern = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$";
        //### ### ####
        Pattern pattern = Pattern.compile(phoneNumberPattern);
        return phoneNumber.matches(pattern.pattern());
    }

    public static boolean isValidNationalCode(String nationalCode) {
        String nationalCodePattern = "^[0-9]{10}$";
        Pattern pattern = Pattern.compile(nationalCodePattern);
        return nationalCode.matches(pattern.pattern());
    }
}
