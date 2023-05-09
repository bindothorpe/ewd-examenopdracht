package util;

public class Text {

    public static String refactorRoleName(String input) {
        input = input.toLowerCase().replaceAll("role_", "");
        return input.substring(0, 1).toUpperCase() +
                input.substring(1).toLowerCase();
    }

}
