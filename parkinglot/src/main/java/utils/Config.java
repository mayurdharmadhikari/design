package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Properties;

public class Config {
    private static final String configFileName = "Config.Properties";
    private static Config configInstance = new Config(configFileName);
    private Properties properties = new Properties();

    public static Config getInstance() {
        synchronized (Config.class) {
            if (configInstance == null) {
                configInstance = new Config(configFileName);
            }
            return configInstance;
        }
    }

    private Config(final String fileName) {
        readConfig(fileName);
    }

    public String getSetting(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        return properties.getProperty(key);
    }

    private Properties readConfig(String fileName) {
        FileReader fileReader = null;
        try {
            final URL fileUrl = ClassLoader.getSystemClassLoader().getResource(fileName);
            final String file = URLDecoder.decode(fileUrl.getPath(), "UTF-8");
            fileReader = new FileReader(file);
            properties.load(fileReader);
        } catch (FileNotFoundException e) {

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fileReader != null) {
            try {
                fileReader.close();
            } catch (IOException e) {
            }
        }
        return properties;
    }
}
