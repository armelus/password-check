package fr.demo.test.utils;

/**
 * Auth Response Record
 * @param status {@link AuthStatus} Status of authentication
 * @param message {@link String} message to be printed
 * @param attemptsLeft {@link Integer} attempts left
 */
public record AuthResponse(AuthStatus status, String message, Integer attemptsLeft) {
}
