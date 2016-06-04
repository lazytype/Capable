package example.factorial;

public final class Factorial {

    /**
     * Java doesn't allow "abstract final" classes or any notion of classes with only static members
     */
    private Factorial() {}

    /**
     * An instance of Capability is required in order to call factorialHelper
     * but it's not possible to get one outside of this package, so it doesn't
     * matter that it's public*!
     *
     * * besides polluting the API ;)
     */
    public static long factorialHelper(final int n, final long product, final Capability C) {
        if (n == 0) {
            return product;
        }

        return factorialHelper(n - 1, n * product, C);
    }

    public static long factorial(final int n) {
        // Writing in tail-call form to give an excuse to have a "private" helper :)
        return factorialHelper(n, 1, Capability.access());
    }
}

// LOL Design Patterns
class Capability {
    private static final Capability SINGLETON = new Capability();

    private Capability() {}

    static Capability access() {
        return SINGLETON;
    }
}