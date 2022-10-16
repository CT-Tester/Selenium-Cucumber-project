@Module_Login
Feature: Search_Wikipedia

@UI @Mobile @Automated @Regression @Priority_High @Login_Error
  Scenario: TC01_Search_in_Google
  #Check First Automation year
    Given I start a "chrome" sesion and debug environment is set to "Yes"
    When I wait for "Home_Google" screen to load
    Then I tap on "Desktop_search_bar"
    Then I write the item name "automatización"
    Then I look for the wikipedia entry
    Then I wait for "Wikipedia" screen to load
    Then I look for the year of the first automation process
    Then I take one screenshot
