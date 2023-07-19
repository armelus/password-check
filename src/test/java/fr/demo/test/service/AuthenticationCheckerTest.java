package fr.demo.test.service;

import fr.demo.test.utils.AuthResponse;
import fr.demo.test.utils.AuthStatus;
import fr.demo.test.utils.AuthUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthenticationCheckerTest {

    AuthenticationChecker authenticationChecker;


    @Test
    public void testValidPassword() {
        authenticationChecker = new AuthenticationCheckerImpl();
        assertPassword("armel", "demo123", AuthStatus.SUCCESS, AuthUtils.AUTH_SUCCESS_MESSAGE, 3);
    }

    @Test
    public void testInvalidPasswordWithTwoAttemptsLeft() {
        authenticationChecker = new AuthenticationCheckerImpl();
        assertPassword("armel", "itisnotcorrect", AuthStatus.FAILED, AuthUtils.AUTH_FAILED_TWO_ATTEMPTS_LEFT_MESSAGE, 2);
    }

    @Test
    public void testInvalidPasswordWithOneAttemptsLeft() {
        authenticationChecker = new AuthenticationCheckerImpl();
        String login = "armel";
        String[] passwords = {"password123", "incorrectPassword"};
        AuthStatus[] statuses = {AuthStatus.FAILED, AuthStatus.FAILED};
        String[] messages = {
                AuthUtils.AUTH_FAILED_TWO_ATTEMPTS_LEFT_MESSAGE,
                AuthUtils.AUTH_FAILED_ONE_ATTEMPT_LEFT_MESSAGE,
        };

        for (int i = 0; i < passwords.length; i++) {
            assertPassword(login, passwords[i], statuses[i], messages[i], (2 - i));
        }
    }

    @Test
    public void testInvalidPasswordWithBlockedAccount() {
        authenticationChecker = new AuthenticationCheckerImpl();
        String login = "armel";
        String[] passwords = {"password123", "secondPassword", "anotherPassword"};
        AuthStatus[] statuses = {AuthStatus.FAILED, AuthStatus.FAILED, AuthStatus.BLOCKED};
        String[] messages = {
                AuthUtils.AUTH_FAILED_TWO_ATTEMPTS_LEFT_MESSAGE,
                AuthUtils.AUTH_FAILED_ONE_ATTEMPT_LEFT_MESSAGE,
                AuthUtils.AUTH_FAILED_BLOCKED_MESSAGE
        };
        for (int i = 0; i < passwords.length; i++) {
            assertPassword(login, passwords[i], statuses[i], messages[i], (2 - i));
        }
    }

    /**
     * check password and launch commons assertions
     * @param login {@link String} user login
     * @param password {@link String} user password
     * @param expectedStatus {@link AuthStatus} expected status
     * @param expectedMessage {@link String} expected message
     * @param expectedAttemptsLeft {@link Integer} expected attempts left
     */
    private void assertPassword(String login, String password, AuthStatus expectedStatus, String expectedMessage, int expectedAttemptsLeft) {
        AuthResponse response = authenticationChecker.checkAuth(login, password);
        Assertions.assertNotNull(response);
        assertEquals(expectedStatus, response.status());
        assertEquals(expectedMessage, response.message());
        assertEquals(expectedAttemptsLeft, response.attemptsLeft());
    }

}
