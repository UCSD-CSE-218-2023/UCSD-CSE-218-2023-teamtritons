package edu.ucsd.flappycow.util;

public class Contract {
    public static boolean enabled = true;

    public static void require(boolean expression, String property) throws ViolationException {
        if (enabled && !expression) throw new ViolationException("Precondition violated: " + property);
    }

    public static void ensure(boolean expression, String property) throws ViolationException {
        if (enabled && !expression) throw new ViolationException("Postcondition violated: " + property);
    }

    public static void invariant(boolean expression, String property) throws ViolationException {
        if (enabled && !expression) throw new ViolationException("Class invariant violated: " + property);
    }

    public static class ViolationException extends RuntimeException {
        ViolationException(String message) { super(message); };
    }
}