package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class PropertiesReader {
    private Properties prop = new Properties();

    PropertiesReader() {
        String propertiesFilePath = "config.properties";
        InputStream inputStream;
        inputStream = getInputStream(propertiesFilePath);

        try {
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private InputStream getInputStream(String propertiesFilePath) {
        return this.getClass().getClassLoader().getResourceAsStream(propertiesFilePath);
    }

    String getBaseUri() {
        return prop.getProperty("BaseURi");
    }


    String getOathToken() {
        return prop.getProperty("OathToken");
    }


    String getCreateChannelEndPoint() {
        return prop.getProperty("createChannelEndPoint");
    }

    String getArchiveChannelEndPoint() {
        return prop.getProperty("archiveChannelEndPoint");
    }

    String getJoinChannelEndPoint() {
        return prop.getProperty("joinChannelEndPoint");
    }

    String getRenameChannelEndPoint() {
        return prop.getProperty("renameChannelEndPoint");
    }

    String getChannelListEndPoint() {
        return prop.getProperty("channelListEndPoint");
    }

}
