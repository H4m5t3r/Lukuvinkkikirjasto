Feature: As a user I can list the existing reading tips.

    Scenario: an empty list gives a "No tips" -notification
        Given command "list" is selected and option "all" is given
        Then message "No tips" is shown

    Scenario: list command lists tip if a tip is added
        Given tip with header "Hello Ruby" and description "intro to coding" is added
        And command "list" is selected and option "all" is given
        Then tip with id, header "Hello Ruby" and description "intro to coding" is listed

    Scenario: unread tip is listed when the 'unread' option is selected
        Given tip with header "Hello Ruby" and description "intro to coding" is added
        And command "list" is selected and option "unread" is given
        Then tip with id, header "Hello Ruby" and description "intro to coding" is listed

    Scenario: unread tip is not listed when the 'read' option is selected
        Given tip with header "Hello Ruby" and description "intro to coding" is added
        And command "list" is selected and option "read" is given
        Then message "No tips" is shown

    Scenario: read tip is not listed when the 'unread' option is selected
        Given tip with header "Hello Ruby" and description "intro to coding" is added
        And reading tip with id 1 is marked as read
        And command "list" is selected and option "unread" is given
        Then message "No tips" is shown

    Scenario: read tip is listed when the 'read' option is selected
        Given tip with header "Hello Ruby" and description "intro to coding" is added
        And reading tip with id 1 is marked as read
        And command "list" is selected and option "read" is given
        Then tip with id, header "Hello Ruby" and description "intro to coding" is listed

    Scenario: tip previously marked as read but now unread is not listed when the 'read' option is selected
        Given tip with header "Hello Ruby" and description "intro to coding" is added
        And reading tip with id 1 is marked as read
        And reading tip with id 1 is marked as unread
        And command "list" is selected and option "read" is given
        Then message "No tips" is shown

    Scenario: tip previously marked as read but now unread is listed when the 'unread' option is selected
        Given tip with header "Hello Ruby" and description "intro to coding" is added
        And reading tip with id 1 is marked as read
        And reading tip with id 1 is marked as unread
        And command "list" is selected and option "unread" is given
        Then tip with id, header "Hello Ruby" and description "intro to coding" is listed