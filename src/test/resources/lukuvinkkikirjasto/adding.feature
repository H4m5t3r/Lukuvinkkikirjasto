Feature: A reading tip can be added.

    Scenario: Adding is successful with valid name and header
        Given command "new" is selected
	Then message "Header: " is shown
        When header "Hello Ruby" and description "intro to coding" are given
        Then the tip "Hello Ruby" is added.

    Scenario: Adding a book is successful with valid parameters
        Given command "add book" is selected
        Then message "Writer: " is shown
        When writer "Ohtu", name "Hello Ruby", isbn "123-456-789012-3-4", year "2020" and description "intro to coding" are given
        Then the book "Hello Ruby" is added.

    Scenario: Adding a podcast is successful with valid parameters
        Given command "add podcast" is selected
        Then message "Host: " is shown
        When host "Ohtu", name "Hello Ruby", link "https://ohjelmistotuotanto-hy.github.io/" and description "intro to coding" are given
        Then the podcast "Hello Ruby" is added.

    Scenario: Adding a blog is successful with valid parameters
        Given command "add blog" is selected
        Then message "Writer: " is shown
        When writer "Ohtu", name "Hello Ruby", link "https://ohjelmistotuotanto-hy.github.io/" and description "intro to coding" are given
        Then the blog "Hello Ruby" is added.

    Scenario: Adding a video is successful with valid parameters
        Given command "add video" is selected
        Then message "Name: " is shown
        When name "Hello Ruby", link "https://ohjelmistotuotanto-hy.github.io/", published "2020" and description "intro to coding" are given
        Then the video "Hello Ruby" is added.

