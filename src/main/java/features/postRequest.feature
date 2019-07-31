
Feature: Api Automation postRequest
  I want to use this feature to add the new entry in Json file

@APITest
  Scenario: Add a new Response in the Response Body
    Given a Base URI
    When I add the new entry in the json body using json object
    Then I validate the status code equals 201

  
