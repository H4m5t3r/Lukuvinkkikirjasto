Feature: As a user I can search for reading tips.

    Scenario: The program asks for a search term
        When command "search" is selected
        Then message "Enter search term: " is shown

    Scenario: Searches are performed with the entered search terms   
        When command "search" is selected
        And search term "coding" is entered
        Then a search is performed with the search term "coding"

    Scenario: Messages that match the search term are listed
        Given the database contains 2 reading tips that match the search term: "Introduction to coding", "A beginner's guide" and "Effective coding", "Advanced coding guide"
        When command "search" is selected
        And search term "coding" is entered
        Then reading tip with header "Introduction to coding" and description "A beginner's guide" is included in the search results
        And reading tip with header "Effective coding" and description "Advanced coding guide" is included in the search results

    Scenario: No tips are shown when the search list is empty
        Given the database does not contain any reading tips that match the search term
        When command "search" is selected
        And search term "coding" is entered
        Then message "No tips" is shown