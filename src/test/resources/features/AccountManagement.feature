Feature:

  Scenario: Register-Login-Logout

    Given I am on the home page
    When I click the register button
    And I enter my desired details
    And I hit the submit button
    Then I have successfully registered

    Given I am on the login page
    When I enter my details
    And I hit the submit button
    Then I have successfully logged in

    Given I am on the home page
    When I hit the logout button
    Then I have successfully logged out
