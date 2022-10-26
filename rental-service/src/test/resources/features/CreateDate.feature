Feature: Create a new date in the database

  Background:
    Given the endpoint date is available
    And A publication is already stored

    Scenario Outline: a date is created for the first time
      Given there is no date created for the post with id "<id>"
      And date information is entered
      | startDate | months | phoneNumber | email                | description       | status | publicationId | tenantId |
      | 2022-10-21| 5      | 98589026    | alejandro@outlook.es | nueva descripcion | 0      | 1             | 1        |
      Then response is "<statusCode>"
      Examples:
        | id | statusCode |
        | 1  | 200        |

      Scenario Outline: a date is created from an already used publicationId
        Given exists a date created for the post with id "<id>"
        And date information is entered
          | startDate | months | phoneNumber | email                | description       | status | publicationId | tenantId |
          | 2022-10-21| 5      | 98589026    | alejandro@outlook.es | nueva descripcion | 0      | 1             | 1        |
        Then response is "<statusCode>"
        Examples:
          | id | statusCode |
          | 1  | 422        |