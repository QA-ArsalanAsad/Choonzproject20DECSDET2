Feature:

  Background:
    Given I am on the home page

  Scenario:
    Given That I search for an playlist
    When I click the add link
    And I fill out the modal
    Then I have successfully created a playlist

    Given That I search for the playlist name
    Then They appear in the search results

    Given That I can see the playlist I want to change
    When I click the pencil icon
    And I fill out the change modal
    Then The playlist has been update

    Given That I can see the playlist I want to delete
    When I click the cross icon
    Then The playlist has been deleted
