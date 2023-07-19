package fr.demo.test.service;

import fr.demo.test.utils.AuthResponse;
import fr.demo.test.utils.AuthStatus;
import fr.demo.test.utils.AuthUtils;

public class AuthenticationCheckerImpl implements AuthenticationChecker {

    private Integer lastInputPasswordCount;

    public AuthenticationCheckerImpl() {
        this.lastInputPasswordCount = 3;
    }

    /**
     * Check User password and get status, message and attempts left.<br/><br/>
     * If provided password is correct, return {@link AuthResponse} with status SUCCESS, attemptsLeft 3 and message: "le mot de passe est correct renvoie que vous êtes connecté".<br/><br/>
     * If provided password is incorrect:
     * <ul>
     *     <li>if is incorrect 1x: return {@link AuthResponse} with status FAILED, attemptsLeft 2 and message: "le mot de passe est incorrect, il reste 2 tentatives"</li>
     *     <li>If is incorrect 2x: return {@link AuthResponse} with status FAILED, attemptsLeft 1 and message: "le mot de passe est incorrect, essayez 1"</li>
     *     <li>if is incorrect 3x: return {@link AuthResponse} with status BLOCKED, attemptsLeft 0 and message: "mot de passe erroné, utilisateur bloqué".</li>
     * </ul>
     *
     * @param login    {@link String} login of user
     * @param password {@link String} Password of use to verify
     * @return {@link AuthResponse} Auth response Record with status, message and attempts left
     */
    @Override
    public AuthResponse checkAuth(String login, String password) {
        // check if password is into the password list
        if (AuthUtils.passwords.contains(password)) {
            // return success auth response object {@see AuthResponse(AuthStatus.SUCCESS, AuthUtils.AUTH_SUCCESS_MESSAGE, lastInputPasswordCount)}
            return new AuthResponse(AuthStatus.SUCCESS, AuthUtils.AUTH_SUCCESS_MESSAGE, lastInputPasswordCount);
        }

        // decrement attempts because on this step, password is incorrect
        Integer attemptsLeft = decrement(password);

        // get message based on attempts left
        String message = switch (attemptsLeft) {
            case 2 -> AuthUtils.AUTH_FAILED_TWO_ATTEMPTS_LEFT_MESSAGE;
            case 1 -> AuthUtils.AUTH_FAILED_ONE_ATTEMPT_LEFT_MESSAGE;
            default -> AuthUtils.AUTH_FAILED_BLOCKED_MESSAGE;
        };

        // get status based on attempts left
        AuthStatus status = switch (attemptsLeft) {
            case 2, 1 -> AuthStatus.FAILED;
            default -> AuthStatus.BLOCKED;
        };

        return new AuthResponse(status, message, attemptsLeft);
    }

    /**
     * Decrement counter of password verification.
     * if provided password is not into the passwords list, we decrement counter
     *
     * @param password password to Verify
     * @return attempts left
     */
    private Integer decrement(String password) {
        if (!AuthUtils.passwords.contains(password)) {
            lastInputPasswordCount--;
        }
        return lastInputPasswordCount;
    }
}
