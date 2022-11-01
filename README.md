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
  * First thing I needed to do was separate the business logic from the POJOs, so I split them into the standard
  entity/repo/service classes.
  * I needed to deal with data storage. Initially I thought about using a H2 database but decided against
  it due to time restraints. It would be much easier to debug in memory lists than a H2 database.
  * Next was dealing with the discounts. I needed a way to organize the discounts on the same layer. First I had to 
remove all of the discounts on separate - that meant removing the discounts in the Movie entity and the discounts in reservation.
  * After some thinking, I designed a discount system based on a discount interface. That way I could have
 all of the discounts I needed based on the interface they implemented. It would be easy to add new ones or replace old ones.
  * That required adding Spring boot, so I messed around with the pom and project structure a bit.
At this point, I added H2 with an application-test.yml file and the spring-boot dependency but
promptly removed it as hibernate was going to take too much time to clean up.
  * Since I was working on the business code at this point, I needed to write a series of tests
to know when it started working. Since the project came with some nice business-flavor text, it
was probably best to use Cucumber.
  * Unfortunately Cucumber deprecated its own annotations and started using the Junit5 Suite annotations,
so that took more time than expected.
  * It doesn't look like there's an external interface for using the services like a REST API, so it's an
application with no starting point. I'm tempted to add one, but it doesn't look like
I can make assumptions about the interface.
  * 