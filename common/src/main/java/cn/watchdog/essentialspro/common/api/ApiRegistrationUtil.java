package cn.watchdog.essentialspro.common.api;

import cn.watchdog.essentialspro.api.*;

import java.lang.reflect.*;

public class ApiRegistrationUtil {
    private static final Method REGISTER;
    private static final Method UNREGISTER;

    static {
        try {
            REGISTER = EssentialsProProvider.class.getDeclaredMethod("register", EssentialsPro.class);
            REGISTER.setAccessible(true);

            UNREGISTER = EssentialsProProvider.class.getDeclaredMethod("unregister");
            UNREGISTER.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void registerProvider(EssentialsPro essentialsProApi) {
        try {
            REGISTER.invoke(null, essentialsProApi);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void unregisterProvider() {
        try {
            UNREGISTER.invoke(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
