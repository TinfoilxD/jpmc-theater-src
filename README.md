# Introduction

This is a poorly written application, and we're expecting the candidate to greatly improve this code base.

## Instructions
* **Consider this to be your project! Feel free to make any changes**
* There are several deliberate design, code quality and test issues in the current code, they should be identified and resolved
* Implement the "New Requirements" below
* Keep it mind that code quality is very important
* Focus on testing, and feel free to bring in any testing strategies/frameworks you'd like to implement
* You're welcome to spend as much time as you like, however, we're expecting that this should take no more than 2 hours

## `movie-theater`

### Current Features
* Customer can make a reservation for the movie
  * And, system can calculate the ticket fee for customer's reservation
* Theater have a following discount rules
  * 20% discount for the special movie
  * $3 discount for the movie showing 1st of the day
  * $2 discount for the movie showing 2nd of the day
* System can display movie schedule with simple text format

## New Requirements
* New discount rules; In addition to current rules
  * Any movies showing starting between 11AM ~ 4pm, you'll get 25% discount
  * Any movies showing on 7th, you'll get 1$ discount
  * The discount amount applied only one if met multiple rules; biggest amount one
* We want to print the movie schedule with simple text & json format

## Developer Log
  * Separated business logic from data entities
  * Replaced initial data storage with in memory lists. Due to time constraints, in memory lists for test purposes
were chosen over actual databases like H2 or embedded postgres.
  * Removed discount logic in entities and services and separated it into the Discount interface and DiscountService.
  * Setup springboot to help inject Discount interface implementations
  * Setup cucumber to test business scenarios
  * Replaced Junit4 with Junit5
  * Moved print logic to separate service and added Jackson for json stringify abilities
  * Replaced String codes with enums for Movie Codes
  * Added discount codes to store discount related information
  * Added new discount requirements
  * Added cucumber tests for multiple discount qualifications
  * Added unit tests for individual discount logic
  * Added unit tests for filtering logic in various repositories and services