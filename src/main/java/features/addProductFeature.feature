Feature: Cart Page

Scenario:  Verify that user is able to add product to Cart
Given user is on HomePage
When user clicks on Product from HomePage
Then user should be able to see PDP
And user is able to add the product to Cart
 
 
Scenario:  Verify that user is able to launch browser and navigate to Google
Given Launching the Chrome Browser
When user opens a browser
And enters "http://www.google.com"
Then user should be navigated to google search Page
 