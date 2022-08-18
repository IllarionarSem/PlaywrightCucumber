Feature: Api test example

  Scenario: Post booking
    Given Set token
    When User have Booking and it stored by key '1029_BKN'
      | First Name | Last Name  | Total Price | Deposit Paid | Additional Needs |
      | Simon      | Ilaryionau | 1500        | true         | Dinner           |
    And Booking check in and check out dates, stored by key '1029_BKN'
      | Check In   | Check Out  |
      | 2018-01-01 | 2019-01-01 |
    And Post booking stored by key '1029_BKN' and store booking info by key '1029_BKI'
    Then Update Booking with id stored by key '1029_BKI' first and last name
      | First Name | Last Name |
      | Caroline   | Smith     |
    And Delete Booking with id stored by key '1029_BKI'
