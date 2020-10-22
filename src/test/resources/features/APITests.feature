@api-test
Feature: Address Book API
	Documentation: https://restful-booker.herokuapp.com/apidoc/index.html
	
  Background: Prerequisites
		Given url "https://restful-booker.herokuapp.com"
		
  Scenario: POST - Auth - CreateToken
  	And path "/auth"
  	And header Content-Type = application/json
  	And body
  	"""
			{
			    "username" : "admin",
			    "password" : "password123"
			}
  	"""
    When method POST
    Then status 200
    And extract "token"
	
  Scenario: POST - Booking - CreateBooking
  	And path "/booking"
  	And header Content-Type = application/json
  	And header Accept = application/json
  	And body
  	"""
	  	{
		    "firstname" : "Marcos",
		    "lastname" : "Lima",
		    "totalprice" : 111,
		    "depositpaid" : true,
		    "bookingdates" : {
		    	"checkin" : "2020-10-10",
		    	"checkout" : "2019-10-11"
		    },
		    "additionalneeds" : "Breakfast"
			}
  	"""
    When method POST
    Then status 200

  Scenario: GET - Booking - GetBookingIds
  	And path "/booking"
  	And query firstname = "Marcos"
  	And query lastname = "Lima"
    When method GET
    Then status 200
    And match response contains "bookingid"
    And extract "bookingid"
	
  Scenario: GET - Booking - GetBooking
  	And path "/booking/{bookingid}"
  	And header Accept = application/json
    When method GET
    Then status 200
    And match response "firstname" = "Marcos"
    And match response "bookingdates.checkin" = "2020-10-10"
    
  Scenario: PUT - Booking - UpdateBooking
  	And path "/booking/{bookingid}"
  	And header Content-Type = application/json
  	And header Accept = application/json
  	And header Cookie = token={token}
  	And body
  	"""
	  	{
		    "firstname" : "Marcos Updated",
		    "lastname" : "Lima Updated",
		    "totalprice" : 222,
		    "depositpaid" : false,
		    "bookingdates" : {
		    	"checkin" : "1999-10-10",
		    	"checkout" : "1999-10-11"
		    },
		    "additionalneeds" : "Breakfast Updated"
			}
  	"""
    When method PUT
    Then status 200
    And match response "firstname" = "Marcos Updated"
    And match response "totalprice" = 222
    And match response "bookingdates.checkin" = "1999-10-10"
    And match response "additionalneeds" = "Breakfast Updated" 
    
  Scenario: PATCH - Booking - PartialUpdateBooking
  	And path "/booking/{bookingid}"
  	And header Content-Type = application/json
  	And header Accept = application/json
  	And header Cookie = token={token}
  	And body
  	"""
			{
			    "firstname" : "Joao",
			    "lastname" : "Silva"
			}
  	"""
    When method PATCH
    Then status 200
    And match response "firstname" = "Joao"
    And match response "lastname" = "Silva"
    
  Scenario: DELETE - Booking - DeleteBooking
  	And path "/booking/{bookingid}"
  	And header Cookie = token={token}
    When method DELETE
    Then status 201
    