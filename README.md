# README #

- This framework is designed with assumptions and stubs. Replacing the stubs with actual data will resolve the errors.

- This is a BDD framework built using cucumber and Rest-Assured. All the relevant tests related to the provided API are written in the feature file and are parameterized using Examples table.

- All the test cases can run for different set of test data passed through the examples table in the feature file and no test data is hard-coded.

- The payload is also parameterized and is sent in the form of a map object.

- Reporting is implemented using Extent Reports.

- Path params are sent using the configuration.properties file.



### How to run the tests? ###

- Tests can be executed by right clicking on the runner file and running it as junit test.

- Alternatively, the tests can be executed via command line by passing the relevant cucumber tags with mvn test command.


### What can be improved? ###

- Common methods can be created and then be reused within the tests. This will increase the code reusability.

- Enums related to the platform can be created and a logic can be written to use them to make the framework platform independent.
