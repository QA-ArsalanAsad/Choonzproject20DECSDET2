Feature:

  Background:
    Given I am on the home page

  Scenario:
    Given That I search for an album
    When I click the add link
    And I fill out the modal
    Then I have successfully created an album

    Given That I search for the album name
    Then They appear in the search results

    Given That I can see the album I want to change
    When I click the pencil icon
    And I fill out the change modal
    Then The album has been update

    Given That I can see the album I want to delete
    When I click the cross icon
    Then The album has been deleted
