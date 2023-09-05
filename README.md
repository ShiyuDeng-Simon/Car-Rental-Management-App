# Car Rental Application

## Product Features

This application focuses on providing convenience for car rental businesses to
manage their listings. 
Businesses can add car to a rental and add as many rentals as they want.
Businesses can also remove or edit existing rentals and browse 
all the rentals listed and view the details such as car make, type, year, 
fuel type, daily rate, etc. 

## Intended Audience
The application will be made for the interest of car rental businesses and 
customers. Anyone who are interested in car rental service and would like to 
check the availability and fees without making a phone call would benefit from it. 

Categories of customers
- Car rental businesses
- travellers
- business person

## Motivation
If I need to rent a car in Vancouver and get the best deal with the most
suitable car, I will have to call many car rental services in order to 
check for availability and compare prices. This process can be time-consuming 
and inconvenient. This application can make the process of car rental fast 
and easy.

## User Stories

- As a business user, I want to be able to create and add as many rentals as I want.
- As a business user, I want to be able to remove a rental.
- As a business user, I want to be able to update the price of a rental.
- As a business user, I want to be able to view all the rentals.
- As a business user, I want to be able to save my rental listings to file.
- As a business user, I want to be able to load my rental listings from file.
- As a business user, I want to be able to filter the existing rental listings by car type.

## Instructions for Grader


- You can generate the first required action related to adding Xs to a Y by clicking the "View My Listings"
  button on the main menu. in that panel you can choose to filter listings by car type.
- You can generate the second required action related to adding Xs to a Y by clicking the "View My Listings"
  button on the main menu. in that panel you can choose to view details of each listing and choose to remove 
  any listing. 
- You can locate my visual component by clicking the "Save Rental Listings" or the "Load Listings From Files" 
  on the main menu. Once task completed, there will be a check mark animation on the right bottom corner. There
  is also a logo of a car rental business.
- You can save the state of my application by clicking the "Save Rental Listings" button on the main menu.
- You can reload the state of my application by clicking the "Load Listings From Files" button on the main menu.

## Phase 4: Task 2
Tue Apr 11 13:23:41 PDT 2023
A new listing has been added

Tue Apr 11 13:23:41 PDT 2023
A new listing has been added

Tue Apr 11 13:23:41 PDT 2023
A new listing has been added

Tue Apr 11 13:23:41 PDT 2023
A new listing has been added

Tue Apr 11 13:23:41 PDT 2023
A new listing has been added

Tue Apr 11 13:23:41 PDT 2023
A new listing has been added

Tue Apr 11 13:23:41 PDT 2023
A new listing has been added

Tue Apr 11 13:25:11 PDT 2023
A new listing has been added

Tue Apr 11 13:25:17 PDT 2023
Filtering listings by type -- Sports Car

Tue Apr 11 13:25:20 PDT 2023
The selected listing has been removed

Tue Apr 11 13:25:20 PDT 2023
Filtering listings by type -- Sports Car

Tue Apr 11 13:25:26 PDT 2023
Filtering listings by type -- Sedan

Tue Apr 11 13:25:27 PDT 2023
Filtering listings by type -- Sedan

Tue Apr 11 13:25:29 PDT 2023
Filtering listings by type -- SUV

## Phase 4: Task 3:
One of the refactoring that could be done is the saveBusiness and the loadBusiness
methods in CarRentalApp and MainMenu Class are basically doing the same thing. To avoid 
the duplications, another class could be added as a helper class and the save and load 
methods can be added in this class as a class method so other classes can easily access and 
benefit from its functionalities.