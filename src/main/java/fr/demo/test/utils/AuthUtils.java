package fr.demo.test.utils;

import java.util.Arrays;
import java.util.List;

public final class AuthUtils {
    public static final String AUTH_FAILED_TWO_ATTEMPTS_LEFT_MESSAGE = "le mot de passe est incorrect, il reste 2 tentatives";
    public static final String AUTH_FAILED_ONE_ATTEMPT_LEFT_MESSAGE = "le mot de passe est incorrect, essayez 1";
    public static final String AUTH_FAILED_BLOCKED_MESSAGE = "mot de passe erroné, utilisateur bloqué";
    public static final String AUTH_SUCCESS_MESSAGE = "le mot de passe est correct renvoie que vous êtes connecté";
    public static final List<String> passwords = Arrays.asList("armel123", "test123", "demo123");
}
