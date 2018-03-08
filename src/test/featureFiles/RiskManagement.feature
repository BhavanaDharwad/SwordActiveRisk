Feature: Risk Management, Leverage, Compatibility search feature
  
  @tag1
  Scenario: Search for Risk Management
  Given Go to website"http://www.sword-activerisk.com/"
  When Enter search text "Risk management"
  And Click on search button
  Then Get result page title
  And Get header text
  And Validate the search content
  And Validate search result
  And Validate search links
  
  @tag2
  Scenario: Leverage search result
  When Enter the search text as "Leverage"
  And Click on searchbutton
  Then Get page title
  And get Header text
  And Validate Leverage search result
  And Validate Leverage search links 
  
  @tag3
  Scenario: Search for Compatibility scenario
  When Search for text "Compatibility"
  And Click on Search button
  Then Collect result page title
  And Validate search content 
