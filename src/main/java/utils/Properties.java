package utils;

public class Properties {

    private static final PropertiesReader propertiesReader = new PropertiesReader();
    public static final String baseUri = propertiesReader.getBaseUri();
    public static final String createChannelEndPoint = propertiesReader.getCreateChannelEndPoint();
    public static final String renameChannelEndPoint = propertiesReader.getRenameChannelEndPoint();
    public static final String archiveChannelEndPoint = propertiesReader.getArchiveChannelEndPoint();
    public static final String channelListEndPoint = propertiesReader.getChannelListEndPoint();
    public static final String joinChannelEndPoint = propertiesReader.getJoinChannelEndPoint();
    public static final String oathToken = propertiesReader.getOathToken();


}
