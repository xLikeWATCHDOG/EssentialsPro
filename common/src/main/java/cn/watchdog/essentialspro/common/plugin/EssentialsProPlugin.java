package cn.watchdog.essentialspro.common.plugin;

import cn.watchdog.essentialspro.common.plugin.bootstrap.*;
import cn.watchdog.essentialspro.common.plugin.logging.*;

/**
 * Main internal interface for EssentialsPro plugins, providing the base for
 * abstraction throughout the project.
 * <p>
 * All plugin platforms implement this interface.
 */
public interface EssentialsProPlugin {

    /**
     * Gets the bootstrap plugin instance
     *
     * @return the bootstrap plugin
     */
    EssentialsProBootstrap getBootstrap();

    /**
     * Gets a wrapped logger instance for the platform.
     *
     * @return the plugin's logger
     */
    PluginLogger getLogger();

}
