
Feature: Api Automation deleteRequest
  I want to use this template for deleting the existing record in the json body

@APITest
  Scenario: Delete existing record and verify the status code
    Given set base URI
    When I perform delete request
    Then I validate the status code is equals 200

  
