package ApiTestCases;

import annotation.Author;
import annotation.TestCaseNotes;
import entities.response.CreateChannel;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.Categories;
import utils.Properties;
import utils.ResourceHelper;

import java.util.List;

import static annotation.TesterName.AKASH;

public class SlackApi {



    public static String newChannelName;
    public CreateChannel createChannel;

    @BeforeTest
    public void setUp() {
        RestAssured.baseURI = Properties.baseUri;
        newChannelName = RandomStringUtils.randomAlphabetic(5).toLowerCase();
        createChannel = new CreateChannel();

    }

    @Author(name = AKASH)
    @TestCaseNotes(Steps = "create a channel||set the channel name", expecatedResult = "channel should be created")
    @Test(groups = Categories.SMOKE)
    public void createChannel() {
        JSONObject payload = new JSONObject();
        payload.put("name", newChannelName);
        payload.put("validate", "true");
        ResponseBody response = ResourceHelper.
                post(Properties.oathToken, Properties.createChannelEndPoint, payload.toString());
        response.prettyPrint();
        String channelName = response.jsonPath().get("channel.name");
       String channelId = response.jsonPath().get("channel.id");
        createChannel.setChannelId(channelId);
        Assert.assertEquals(newChannelName, channelName, "channel is not created");

    }

    @Author(name = AKASH)
    @TestCaseNotes(Steps = "create a channel||set new channel name", expecatedResult = "channel name should be changed with new name")
    @Test(groups = Categories.SMOKE, dependsOnMethods = "createChannel")
    public void renameChannel() {
        String renameChannelName = RandomStringUtils.randomAlphabetic(5).toLowerCase();
        JSONObject credential = new JSONObject();
        String oldChannelName = createChannel.getChannelId();
        credential.put("channel", oldChannelName);
        credential.put("name", renameChannelName);
        credential.put("validate", "true");
        ResponseBody response = ResourceHelper.
                post(Properties.oathToken, Properties.renameChannelEndPoint, credential.toString());
        response.prettyPrint();
        String channelName = response.jsonPath().get("channel.name");
        Assert.assertEquals(renameChannelName, channelName, "unable to rename the channel");

    }

    @Author(name = AKASH)
    @TestCaseNotes(Steps = "set the channel id with channel id which we want to archived||", expecatedResult = "channel should be archived")
    @Test(groups = Categories.SMOKE, dependsOnMethods = "createChannel")
    public void archiveChannel() {
        JSONObject credential = new JSONObject();
        credential.put("channel", createChannel.getChannelId());
        ResponseBody response = ResourceHelper.
                post(Properties.oathToken, Properties.archiveChannelEndPoint, credential.toString());

        Boolean isArchived = response.jsonPath().get("ok");
        Assert.assertTrue(isArchived, "channel is not archived");


    }


    @Author(name = AKASH)
    @TestCaseNotes(Steps = "", expecatedResult = " list of channel name name should be display")
    @Test
    public void listAllChannel() {
        Response response = ResourceHelper.get(Properties.oathToken, Properties.channelListEndPoint);
        response.prettyPrint();
        List<String> listOfChannel = response.getBody().jsonPath().getList("channels.name");
        listOfChannel.forEach(System.out::println);


    }


}




