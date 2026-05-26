package util;

public class GenerarHashes {
    public static void main(String[] args) {
        System.out.println("Hash admin123: " + PasswordUtil.hashPassword("admin123"));
        System.out.println("Hash emp123: " + PasswordUtil.hashPassword("emp123"));
    }
}