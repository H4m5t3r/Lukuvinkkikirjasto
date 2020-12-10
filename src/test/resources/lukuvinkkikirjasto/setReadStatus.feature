Feature: As a user I can change the 'read' status of a reading tip.

    Scenario: The program asks for an id when "mark as read" is selected.
        When command "mark as read" is selected
        Then message "Give tip id:" is shown

    Scenario: The program asks for an id when "mark as unread" is selected.
        When command "mark as unread" is selected
        Then message "Give tip id:" is shown

    Scenario: A reading tip can be marked as 'read' after it has been created.
        Given tip with header "Hello Ruby" and description "intro to coding" is added
        When command "mark as read" is selected
        And id "1" is given to be marked as read
        Then tip with id "1" is marked as read

    Scenario: A reading tip can be marked as unread after it has been marked as read.
        Given tip with header "Hello Ruby" and description "intro to coding" is added
        And reading tip with id 1 is marked as read
        When command "mark as unread" is selected
        And id "1" is given to be marked as unread
        Then tip with id "1" is marked as unread