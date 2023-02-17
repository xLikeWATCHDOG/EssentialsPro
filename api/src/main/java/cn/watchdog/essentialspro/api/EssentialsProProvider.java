package cn.watchdog.essentialspro.api;

import org.checkerframework.checker.nullness.qual.*;

import static org.jetbrains.annotations.ApiStatus.*;

/**
 * Provides static access to the {@link EssentialsPro} API.
 *
 * <p>Ideally, the ServiceManager for the platform should be used to obtain an
 * instance, however, this provider can be used if this is not viable.</p>
 */
public final class EssentialsProProvider {
    private static EssentialsPro instance = null;

    @Internal
    private EssentialsProProvider() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * Gets an instance of the {@link EssentialsPro} API,
     * throwing {@link IllegalStateException} if the API is not loaded yet.
     *
     * <p>This method will never return null.</p>
     *
     * @return an instance of the EssentialsPro API
     * @throws IllegalStateException if the API is not loaded yet
     */
    public static @NonNull EssentialsPro get() {
        EssentialsPro instance = EssentialsProProvider.instance;
        if (instance == null) {
            throw new NotLoadedException();
        }
        return instance;
    }

    @Internal
    static void register(EssentialsPro instance) {
        EssentialsProProvider.instance = instance;
    }

    @Internal
    static void unregister() {
        EssentialsProProvider.instance = null;
    }

    /**
     * Exception thrown when the API is requested before it has been loaded.
     */
    private static final class NotLoadedException extends IllegalStateException {
        private static final String MESSAGE = "The EssentialsPro API isn't loaded yet!\n" + "This could be because:\n" + "  a) the EssentialsPro plugin is not installed or it failed to enable\n" + "  b) the plugin in the stacktrace does not declare a dependency on LuckPerms\n" + "  c) the plugin in the stacktrace is retrieving the API before the plugin 'enable' phase\n" + "     (call the #get method in onEnable, not the constructor!)\n";

        NotLoadedException() {
            super(MESSAGE);
        }
    }

}
