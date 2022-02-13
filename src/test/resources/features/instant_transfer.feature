@mf @payment @instantTransfer
Feature: INSTANT TRANSFER

  Background:
    Given Buyer try to login
      | username | email | password |
      | VALID    | VALID | VALID    |

  @test
  Scenario Outline: User should correctly see instant payment bank at basket summary page
    When Buyer should add to product basket
    And Buyer should go to Payment Page
    And Buyer select <bankName> instant transfer
    Then Buyer should see instant transfer bank name as <bankName> at Basket Summary Page
    Examples:
      | bankName      |
      | Akbank        |
      | İş Bankası    |
      | Kuveyt Türk   |
      | Fibabanka     |
      | Vakıfbank     |
      | AlBaraka Türk |