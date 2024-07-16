package steps;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import com.spotify.pojo.Playlist;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utility.SpecBuilder;

public class PlaylistSteps {
	RequestSpecification res;
	Response response ;
	static String playlistid;

	@Given("create playlist API payload")
	public void create_playlist_api_payload() throws IOException {
		Playlist reqPlayList = new Playlist();
		reqPlayList.setName(" Create 9 framework builder with rest assured23");
		reqPlayList.setDescription("Creating using spotify id222");
		reqPlayList.setPublic(false);
		res = given(SpecBuilder.reqSpec())
				.body(reqPlayList);
	}

	@When("user calls with  Post http request")
	public void user_calls_with_Post_http_request() {

		response = res.when()
				.post("users/31347cap2qzknrb7ecglwtie6i6e/playlists");
	}

	@Then("API call executed with status code {int}")
	public void api_call_executed_with_status_code(Integer int1) {

		Playlist playlist = response.as(Playlist.class);

		String playlistName = playlist.getName();
		System.out.println(playlistName);
		playlistid = playlist.getId();
		System.out.println("playlistid=" +playlistid);
		response.then()
		.spec(SpecBuilder.resSpec())
		.assertThat()
		.statusCode(int1);
	}

	@Given("Get a playlis payload")
	public void get_a_playlis_payload() throws IOException {

		res= given(SpecBuilder.reqSpec())
				.pathParam("pid", playlistid);


	}

	@When("user calls with get http request")
	public void user_calls_with_get_http_request() {

		System.out.println("playlistid in get="+playlistid);
		res.get("playlists/{pid}")
		.then()
		.spec(SpecBuilder.resSpec());


	}

	@Then("API call executed with the status code {int}")
	public void api_call_executed_with_the_status_code(Integer stsCode) {

		res.then()
		.spec(SpecBuilder.resSpec())
		.statusCode(stsCode);
	}

	@Given("update a playlis API payload")
	public void update_a_playlis_api_payload() throws IOException {

		Playlist reqPlayList = new Playlist();
		reqPlayList.setName("framework update with rest assured");
		reqPlayList.setDescription("Creating using spotify id222");
		reqPlayList.setPublic(false);
		res = given(SpecBuilder.reqSpec())
				.body(reqPlayList);
	}



	@When("user calls with put http request")
	public void user_calls_with_put_http_request() {

		response = res.when()
				.put("/playlists/" +playlistid);
	}

	@Then("API call executed should with status code {int}")
	public void api_call_executed_should_with_status_code(Integer expectedstatuscode) {

		response.then()

		.assertThat()

		.statusCode(expectedstatuscode);

	}




}