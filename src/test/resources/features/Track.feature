Feature:

  Background:
    Given I am on the home page

  Scenario:
    Given That I search for an track
    When I click the add link
    And I fill out the modal
    Then I have successfully created an track

    Given That I search for the track name
    Then They appear in the search results

    Given That I can see the track I want to change
    When I click the pencil icon
    And I fill out the change modal
    Then The track has been update

    Given That I can see the track I want to delete
    When I click the cross icon
    Then The track has been deleted
