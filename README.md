# bank-demo
Sample Bank Application Based on following requirement.

Create a spring boot java api that does the following operations
- Creates a new savings bank account
- Gets the savings bank account
Request Body needs to be validated
Account data model:
Account number (Autogenerated by the api)
Id (Autogenerated by the api)
Customer name (input : Mandatory)
Account nick name (input : optional)
Account Nick name should be between 5 to 30 characters.
A customer cannot create more than 5 accounts.
To ensure that the nick name is not offensive, the nick name should be checked against a list of offensive nick names.
The bank account details needs to be saved in a postgres database, in a single table.
Optional:
Handle error scenarios where the database may be unavailable.
If you are familiar with a caching framework such as Redis, cache the GET calls to the postgres database.
Write any tests that you think would be useful
The application should be built using Gradle or Maven.
Please feel free to leave TODOs in the source where impractical or time consuming to code features that you would otherwise have coded in a
real world api.
Where requirements appear to be insufficient, assume the requirements based on what you think might be best.
