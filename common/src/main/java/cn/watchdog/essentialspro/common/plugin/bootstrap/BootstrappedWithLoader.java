package cn.watchdog.essentialspro.common.plugin.bootstrap;

/**
 * A {@link EssentialsProBootstrap} that was bootstrapped by a loader.
 */
public interface BootstrappedWithLoader {

    /**
     * Gets the loader object that did the bootstrapping.
     *
     * @return the loader
     */
    Object getLoader();

}
