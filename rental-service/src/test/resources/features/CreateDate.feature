Feature: Create a new date in the database

  Background:
    Given the endpoint date is available
    And A publication is already stored

    Scenario Outline: a date is created for the first time
      Given date information is entered
      | startDate | months | phoneNumber | email                | description       | status | publicationId | tenantId |
      | 2022-10-21| 5      | 98589026    | alejandro@outlook.es | nueva descripcion | 0      | 1             | 1        |
      And there is no date created for the post with id "<id>"
      Then response is "<statusCode>"
      Examples:
        | id | statusCode |
        | 1  | 200        |
