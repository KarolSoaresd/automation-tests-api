package br.testing.config;

import org.aeonbits.owner.ConfigCache;

public final class ConfigurationManager {

    public static Configuration getConfiguration() {
        return ConfigCache.getOrCreate(Configuration.class);
    }
}
