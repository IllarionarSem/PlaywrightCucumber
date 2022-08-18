Feature: Simple feature file

  Scenario: Handle Table with ModelHandler
    Given User open challenging dom page
    Then Print table objects

  Scenario: Handle Elements list example
    Given User open EBay page
    When User navigates to popular category 'Born Pretty'
    Then Print section items

  Scenario: Handel DataTable with ModelHandler
    Given get book as list
      | Author | Title        | Year |
      | sem    | flowers      | 2012 |
      | artem  | near the sea | 2022 |
      | polina | sky          | 2007 |