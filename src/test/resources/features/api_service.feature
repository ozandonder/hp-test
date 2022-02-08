@api
Feature: API SERVICE

  @test
  Scenario: Create valid user via api
    Given API Operator should create user
      | Key        | Value |
      | id         | VALID |
      | username   | VALID |
      | firstName  | VALID |
      | lastName   | VALID |
      | email      | VALID |
      | password   | VALID |
      | phone      | VALID |
      | userStatus | VALID |
    Then Check api response
      | code | message |
      | 200  | VALID   |

  @test
  Scenario: Edit valid user via api
    Given API Operator should create user
      | Key      | Value |
      | id       | VALID |
      | username | VALID |
    And API Operator set user
    And API Operator should edit user with username as "VALID"
      | Key      | Value |
      | username | VALID |
    And API Operator set user
    Then Check api response
      | code | message |
      | 200  | VALID   |

  @test
  Scenario: Delete valid user via api
    Given API Operator should create user
      | Key      | Value |
      | id       | VALID |
      | username | VALID |
    And API Operator set user
    And API Operator should delete user with username as "VALID"
    Then Check delete api response
      | code | message |
      | 200  | VALID   |

  @test
  Scenario Outline: User Api - Create request id validation
    Given API Operator should create user
      | Key      | Value   |
      | id       | <value> |
      | username | VALID   |
    Then Check api response
      | code           | message           |
      | <responseCode> | <responseMessage> |
    Examples:
      | value     | responseCode | responseMessage        |
      | abc       | 500          | something bad happened |
      | .,!'^&?=* | 500          | something bad happened |
      | VALID     | 200          | VALID                  |
      |           | 200          | 9223372036854775807    |

  @test
  Scenario Outline: User Api - Create request username validation
    Given API Operator should create user
      | Key      | Value   |
      | id       | VALID   |
      | username | <value> |
    Then Check api response
      | code           | message           |
      | <responseCode> | <responseMessage> |
    Examples:
      | value     | responseCode | responseMessage |
      | 123213    | 200          | VALID           |
      | abc       | 200          | VALID           |
      | .,!'^&?=* | 200          | VALID           |
      |           | 200          | VALID           |
      | VALID     | 200          | VALID           |