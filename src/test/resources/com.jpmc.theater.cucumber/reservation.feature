Feature: Customer makes a reservation

  Scenario: Special Movie
    Given I am a random customer
    And the current date is "10-30-2022"
    And I want to see a special movie
    And the movie is doing its 3rd showing at 7
    When I make a reservation of 1 person
    Then the movie will be discounted at 20 percent off
    And the applied discount is "SPECIAL"

  Scenario: Special Movie - Multiple People
    Given I am a random customer
    And the current date is "10-30-2022"
    And I want to see a special movie
    And the movie is doing its 3rd showing at 7
    When I make a reservation of 2 person
    Then the movie will be discounted at 20 percent off
    And the applied discount is "SPECIAL"

  Scenario: 1st Showing
    Given I am a random customer
    And the current date is "10-30-2022"
    And I want to see a random normal movie
    And the movie is doing its 1st showing at 7
    When I make a reservation of 1 person
    Then the movie will be discounted at flat rate of $3 off
    And the applied discount is "FIRSTSHOWING"

  Scenario: 2nd Showing
    Given I am a random customer
    And I want to see a random normal movie
    And the current date is "10-30-2022"
    And the movie is doing its 2nd showing at 7
    When I make a reservation of 1 person
    Then the movie will be discounted at flat rate of $2 off
    And the applied discount is "SECONDSHOWING"

  Scenario: 11am showing
    Given I am a random customer
    And the current date is "10-30-2022"
    And I want to see a random normal movie
    And the movie is doing its 3rd showing at 11
    When I make a reservation of 1 person
    Then the movie will be discounted at 25 percent off
    And the applied discount is "PEAKTIME"

  Scenario: 4pm showing
    Given I am a random customer
    And the current date is "10-30-2022"
    And I want to see a random normal movie
    And the movie is doing its 3rd showing at 15
    When I make a reservation of 1 person
    Then the movie will be discounted at 25 percent off
    And the applied discount is "PEAKTIME"

  Scenario: 7th of month
    Given I am a random customer
    And the current date is "10-07-2022"
    And I want to see a random normal movie
    And the movie is doing its 3rd showing at 7
    When I make a reservation of 1 person
    Then the movie will be discounted at flat rate of $1 off
    And the applied discount is "SEVENTHDAY"

  Scenario: Pick highest of multiple discounts
    Given I am a random customer
    And the current date is "10-07-2022"
    And I want to see a random normal movie
    And the movie is doing its 3rd showing at 11
    When I make a reservation of 1 person
    Then the movie will be discounted at 25 percent off
    And the applied discount is "PEAKTIME"
