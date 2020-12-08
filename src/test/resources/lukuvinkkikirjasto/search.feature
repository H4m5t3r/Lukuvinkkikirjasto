Feature: As a user I can search for reading tips.

    Scenario: The program asks for a search term
        When command "search" is selected
        Then message "Enter search term: " is shown

    # Scenario: Searches are performed with the entered search terms   
    #     When command "search" is selected
    #     And search term "test" is entered
    #     Then a search is performed with the search term "test"