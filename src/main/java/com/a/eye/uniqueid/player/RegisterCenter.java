package com.a.eye.uniqueid.player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * {@link UniqueIDPlayer}'s RegisterCenter
 * Use SPI({@link ServiceLoader}) to get all IDGenerator implementations.
 * Also, provide {@link RegisterCenter#register(IDGenerator)} to register any implementations as your wish.
 * <p>
 * All implementations will stay and be hold by {@link RegisterCenter}.
 * <p>
 * Created by wusheng on 2016/12/29.
 */
public enum RegisterCenter {
    /**
     * stay in singleton.
     */
    INSTANCE;

    /**
     * all registered implementations.
     */
    private Map<String, IDGenerator> registeredGenerator = new HashMap<String, IDGenerator>();

    /**
     * In default mechanism,
     * {@link RegisterCenter} use SPI({@link ServiceLoader}) to get all IDGenerator implementations.
     */
    RegisterCenter() {
        Iterator<IDGenerator> generatorIterator = ServiceLoader.load(IDGenerator.class).iterator();
        while (generatorIterator.hasNext()) {
            IDGenerator next = generatorIterator.next();
            registeredGenerator.put(next.name().trim(), next);
        }
    }

    /**
     * register {@link IDGenerator} implementation manually.
     * This method should not be called concurrency, or in high-throughput processes.
     *
     * @param instance {@link IDGenerator} implementation.
     * @return {@link RegisterCenter#INSTANCE}
     */
    public synchronized RegisterCenter register(IDGenerator instance) {
        registeredGenerator.put(instance.name(), instance);
        return this;
    }

    /**
     * find a {@link UniqueIDPlayer} by name
     *
     * @param name {@link IDGenerator}'s register name.
     * @return {@link IDGenerator} instance.
     * @throws UnregisteredGeneratorException when register name not found.
     */
    public UniqueIDPlayer find(String name) throws UnregisteredGeneratorException {
        IDGenerator generator = registeredGenerator.get(name);
        if (generator != null) {
            return new UniqueIDPlayer(generator);
        }

        throw new UnregisteredGeneratorException("Register name:" + name + " not found");
    }

    /**
     * find a {@link UniqueIDPlayer} by named("uniqueid.player.config") config file.
     *
     * File contains the only {@link IDGenerator}'s register name.
     * If many names exist, use the first and ignore others.
     * Line comments start with '#' are supported in config file.
     *
     * @return
     * @throws UnregisteredGeneratorException
     */
    public UniqueIDPlayer find() throws UnregisteredGeneratorException {
        InputStream configResource = RegisterCenter.class.getResourceAsStream("/uniqueid.player.config");
        String generatorName = "default";
        if (configResource != null) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(configResource));
            String line = null;
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    String trim = line.trim();
                    if (trim.length() > 0 && !trim.startsWith("#")) {
                        generatorName = trim;
                    }
                }
            } catch (IOException e) {
                throw new UnregisteredGeneratorException("uniqueid.player.config can't provide the name", e);
            }
        }

        return find(generatorName);
    }
}
