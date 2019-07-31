
Feature: Api Automation putRequest
  I want to use this feature to update the existing entry in Json file

@APITest
  Scenario: update the json body and verify the status code
    Given a base URI using put request
    When I update the existing value in json file using json object
    Then I validate the status code is equals to 200

  
