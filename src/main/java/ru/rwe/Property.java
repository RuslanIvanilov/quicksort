package ru.rwe;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

class Property {
    private Property(){}
    static final boolean TEST_MODE;

    static{
        Logger log = Logger.getLogger(Property.class);

        try{
            Configuration config = new PropertiesConfiguration("app.properties");
            TEST_MODE = config.getBoolean("app-test-mode");

        }catch(Exception e){
            log.info(e);
            throw new RuntimeException(e);
        }

    }

}
