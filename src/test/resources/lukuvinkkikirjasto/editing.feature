Feature: As a user I can edit an existing reading tip.

    Scenario: Header can be modified if valid ID is given
        Given tip with header "Hello Ruby" and description "intro to coding" is added
        And command "edit header" is selected
        Then message "Give tip id:" is shown
        When id 1 and new header "Hello again Ruby" are given
        Then tip with id 1 has its header changed to "Hello again Ruby" 

    Scenario: Description can be modified if valid ID is given
        Given tip with header "Hello Ruby" and description "intro to coding" is added
        And command "edit description" is selected
        When id 1 and new description "intro to coding part 2" are given
        Then tip with id 1 has its description changed to "intro to coding part 2" 

    Scenario: Header cannot be modified if given ID number does not exist
        Given input "1" will be given
        And command "edit header" is selected
        Then message "Give tip id:" is shown
        And message "Could not find responding tip." is shown
        And header editor is not accessed

    Scenario: Header cannot be modified if given ID is not a number
        Given input "abc" will be given
        And command "edit header" is selected
        Then message "Give tip id:" is shown
        And message "Id must be a number." is shown
        And header editor is not accessed
