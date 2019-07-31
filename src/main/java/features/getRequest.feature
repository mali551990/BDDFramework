
Feature: Api Automation getRequest
  I want to use this feature to retrieve the data response

@APITest
  Scenario: To Perform Get Request using Rest Assured Framework
    Given a base URI
    When i enter the city
    Then I get the body response
    And the status code is equals 200


