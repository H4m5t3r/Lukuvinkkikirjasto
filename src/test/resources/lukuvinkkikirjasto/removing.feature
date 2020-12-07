Feature: As a user I can remove an existing reading tip.

    Scenario: The program asks for an id when "delete" is selected
        When command "delete" is selected
        Then message "Give tip id:" is shown
      
    Scenario: The delete function only accepts numbers as id
        Given tip with header "Hello Ruby" and description "intro to coding" is added
        When command "delete" is selected
        When delete id "a" is given
        Then message "Id must be a number." is shown

    Scenario: Removing an existing reading tip works
        Given tip with header "Hello Ruby" and description "intro to coding" is added
        When command "delete" is selected
        And delete id 1 and confirmation "y" are given
        Then the tip with id 1 is removed.

    Scenario: Reading tips are not removed without confirmation
        Given tip with header "Hello Ruby" and description "intro to coding" is added
        When command "delete" is selected
        And delete id 1 and confirmation "no" are given
        Then the tip with id 1 is not removed.
