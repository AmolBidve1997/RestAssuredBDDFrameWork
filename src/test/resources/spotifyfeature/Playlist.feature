Feature: Validate Playlist API
Scenario: Verify if create playlist functionality is working 
Given create playlist API payload
When  user calls with  Post http request
Then API call executed with status code 201

Scenario: Verify if fetch playlist functionality is working
Given Get a playlis payload
When user calls with get http request
Then API call executed with the status code 200

Scenario: Verify if update playlist functionality is working
Given update a playlis API payload
When user calls with put http request
Then API call executed should with status code 400