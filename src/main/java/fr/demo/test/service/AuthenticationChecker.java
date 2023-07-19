package fr.demo.test.service;

import fr.demo.test.utils.AuthResponse;

public interface AuthenticationChecker {
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
    AuthResponse checkAuth(String login, String password);
}
