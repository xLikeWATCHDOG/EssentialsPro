package cn.watchdog.essentialspro.common.api;

import cn.watchdog.essentialspro.api.*;
import cn.watchdog.essentialspro.common.plugin.*;
import cn.watchdog.essentialspro.common.plugin.bootstrap.*;
import cn.watchdog.essentialspro.common.plugin.logging.*;

/**
 * Implements the EssentialsPro API using the plugin instance
 */
public class EssentialsProApiProvider implements EssentialsPro {

    private final EssentialsProPlugin plugin;

    public EssentialsProApiProvider(EssentialsProPlugin plugin) {
        this.plugin = plugin;
    }

    public void ensureApiWasLoadedByPlugin() {
        EssentialsProBootstrap bootstrap = this.plugin.getBootstrap();
        ClassLoader pluginClassLoader;
        if (bootstrap instanceof BootstrappedWithLoader) {
            pluginClassLoader = ((BootstrappedWithLoader) bootstrap).getLoader().getClass().getClassLoader();
        } else {
            pluginClassLoader = bootstrap.getClass().getClassLoader();
        }

        for (Class<?> apiClass : new Class[]{EssentialsPro.class, EssentialsProProvider.class}) {
            ClassLoader apiClassLoader = apiClass.getClassLoader();

            if (!apiClassLoader.equals(pluginClassLoader)) {
                String guilty = "unknown";
                try {
                    guilty = bootstrap.identifyClassLoader(apiClassLoader);
                } catch (Exception e) {
                    // ignore
                }

                PluginLogger logger = this.plugin.getLogger();
                logger.warn("It seems that the EssentialPro API has been (class)loaded by a plugin other than EssentialPro!");
                logger.warn("The API was loaded by " + apiClassLoader + " (" + guilty + ") and the " + "EssentialPro plugin was loaded by " + pluginClassLoader.toString() + ".");
                logger.warn("This indicates that the other plugin has incorrectly \"shaded\" the " + "EssentialPro API into its jar file. This can cause errors at runtime and should be fixed.");
                return;
            }
        }
    }
}
