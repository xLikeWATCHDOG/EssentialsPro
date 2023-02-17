package cn.watchdog.essentialspro.common.plugin.bootstrap;

import org.checkerframework.checker.nullness.qual.*;

/**
 * Bootstrap plugin interface
 *
 * <p>Instances of this interface are responsible for loading the
 * "EssentialsPro plugin" on their respective platforms.</p>
 */
public interface EssentialsProBootstrap {
    /**
     * Attempts to identify the plugin behind the given classloader.
     *
     * <p>Used for giving more helpful log messages when things break.</p>
     *
     * @param classLoader the classloader to identify
     * @return the name of the classloader source
     * @throws Exception anything
     */
    default @Nullable String identifyClassLoader(ClassLoader classLoader) throws Exception {
        return null;
    }
}
