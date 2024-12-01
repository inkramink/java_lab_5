package org.example;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Injector class to inject dependencies into fields annotated with {@link AutoInjectable}.
 */
public class Injector {

    private Properties properties;

    /**
     * Default constructor that loads properties from the default config file.
     */
    public Injector() {
        this("config.properties");
    }

    /**
     * Constructor that loads properties from the specified file.
     * @param propertiesFilePath the path to the properties file
     */
    public Injector(String propertiesFilePath) {
        this.properties = loadPropertiesFromFile(propertiesFilePath);
    }

    private Properties loadPropertiesFromFile(String filePath) {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + filePath);
                return properties;
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }

    /**
     * Injects dependencies into the fields of the given object that are annotated with {@link AutoInjectable}.
     * @param object the object to inject dependencies into
     * @param <T> the type of the object
     * @return the object with injected dependencies
     */
    public <T> T inject(T object) {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                Class<?> fieldType = field.getType();
                String implClassName = properties.getProperty(fieldType.getName());

                if (implClassName != null) {
                    try {
                        Class<?> implClass = Class.forName(implClassName);
                        Object implInstance = implClass.getDeclaredConstructor().newInstance();
                        field.setAccessible(true);
                        field.set(object, implInstance);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return object;
    }
}