Feature:

  Background:
    Given I am on the home page

  Scenario:
    Given That I search for an artist
    When I click the add link
    And I fill out the modal
    Then I have successfully created an artist

    Given That I search for the artists name
    Then They appear in the search results

    Given That I can see the artist I want to change
    When I click the pencil icon
    And I fill out the change modal
    Then The artist has been update

    Given That I can see the artist I want to delete
    When I click the cross icon
    Then The artist has been deleted
