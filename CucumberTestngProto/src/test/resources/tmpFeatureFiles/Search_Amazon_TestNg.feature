@Module_Login
Feature: Login to CWB app

  @UI @Mobile @Automated @Regression @Priority_High
  Scenario Outline: TC01_Login
  #Login Across LOBs
    Given I start a "chrome" sesion and debug environment is set to "Yes"
    When I wait for "Home" screen to load
    Then I handle Cookies popup 
    Then I tap on "Desktop_my_account"
    Then I wait for "Username" screen to load
    Then I enter "username"
    Then I wait for "Password" screen to load
    Then I enter "password"
    Then I wait for "Home" screen to load

    Examples:
      |Username| Password |
      


  @UI @Mobile @Automated @Regression @Priority_High @Login_Error
  Scenario Outline: TC02_Search
  #Login Across LOBs
    Given I start a "chrome" sesion and debug environment is set to "Yes"
    When I wait for "Home" screen to load
    Then I tap on "Dektop_search_bar"
    Then I write the item name "<Item searched>"
    Then I look for the item label "<Item label>" and click on it
    Then I wait for "Item_selected" screen to load
    Then I try to include the item into the basket

    Examples:
      | Item searched                  | Item label |
      | Batería Samsung i9060i         | I9060I     |
      | Bateria externa solar 50000mah | KRISDONIA  |
      
 
 