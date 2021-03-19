# QA Choonz Final Project 

## Introductions

This project aims to be a simple CRUD music application that allows for editing and 
managing a local music library, it could also be turned into an online/cloud application
as it has registration/login services built into it. This application is a server based
application, the back-end is based in the spring-boot framework, and the front-end uses
a mixture of HTML, CSS and JS.

## Getting Started
### Requirements

* Java (version 11 or above)
* Maven (optional, for compiling from source files)
* IntelliJ or Spring Tool Suite (optional, for running the files without building)  
* A browser with JS enabled (Firefox or Chrome recommended)

### Initiating the server

There are 3 main ways to run this project, the recommended (and easiest) way is to 
navigate to target/ folder, there should either be a Choonz-*.jar or a Choonz-*.war
file. Open a terminal window here (bash, CMD, PowerShell etc) and run the following 
command `java -jar Choonz-*.jar` or `java -jar Choonz-*.war`, where * is the current 
build number. This should initialise the server, once the terminal window stops 
outputting text (this should take between 5-30 seconds depending on your computer).
You can now move on to accessing the webpage.

The second option to run this project is to compile the source files locally and 
produce your own executable. To do this navigate to the root directory and open 
a terminal window, run `maven clean package`, this may take a few minutes to execute 
and build, and will also run all tests. After this is complete a .jar or .war executable 
will be generated in target/ which can be run.

The last option (not recommended) is to run the source files for this you will need
to clone the project from GitHub to your local machine, open these file in your code 
editor of choice (IntelliJ or Spring Tool Suite recommended), update maven to pull down all 
the dependencies. Then press the respective run button in your editor, this should start the 
server.

### Accessing the webpage

Once the server has been initialised, you are read to access the webpage (the front-end),
this is done by navigating to http://localhost:8082 it should look something
like [this](https://photos.app.goo.gl/1dbZvvCkGLueMHm29). From here are now ready to fully
explore the current version of the build, have fun!

## Operational Instructions
Once you have opened the webpage, it is recommended you make an account, this is done
by hitting the account settings button on the right-hand corner of the site. You should 
then you will be prompted to enter a username and password (twice). After the registration
you will be prompted to log in. Now you have successfully authenticated yourself with the server.
To access the full functionality of the server search for an entity of your choice (Artist/Album/Track/Genre/Playlist).
You can then create one if you desire, once an entity has been created you will be given an option
to delete or update the entity.

## Tests
#### Current Coverage : > 90%
To see the coverage of this project make sure you have an IDE that has JaCoCo installed (such as
IntelliJ or Eclipse, and then run the tests with coverage), or install JaCoCo on your IDE. I have
user 4 types of testing for this project. Unit tests, Integration tests, Acceptance tests and System test.

### Running The Tests

#### Unit Tests
Unit tests allow us to investigate how a class performs on its own
without relying on any other classes, other classes are "mocked"
using mockito, and the methods are tested using JUnit5. These tests
are labelled with *UnitTest and can be run within your IDE as long
as you have JUnit5 and mockito installed. Coverage can be generated
using the JaCoCo plugin (IntelliJ and Eclipse have JaCoCo built in).

#### Integration Tests
Integration tests allow us to investigate how classes interact with
other object, in this case I have used integration testing to see how
the controller class behaves. This test can also be run in an IDE as
long as the dependencies have been met, they are labelled as *IntegrationTest.

#### Acceptance Test
Acceptance tests show us whether our program behaves as it should
and that the business logic that is wanted has actually been implemented.
This usually revolves around user stories. These tests require the Chrome
browser to be installed. The test scripts can be modified to use another browser,
simple add the driver for the browser into the driver section, change the
driver in the given script to be the representative driver for that browser (
e.g. change ChromeDriver() to be FirefoxDriver()). You can then run the
tests, a browser will pop-up, and the tests' logic will be simulated.

#### System Tests
System tests are usually used to judged how a system can handle with different
types of stresses, whether they can recover and how quickly. We used Jmeter to run
a variety of tests such as spike, load, soak and stress. These all test how the system
reacts to the intense changes in user load or increases in user load. These tests are
saved as a .jmx files in the test/ folder, and can be run with Jmeter.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors
* Team Leader and Manager - [Arsalan Asad](https://github.com/QA-ArsalanAsad)
* Back-end Developer and Non-functional tester - [Vaughan-Williams, Peter](https://github.com/petervw-qa)
* Test Lead - [Mezals, Raimonds](https://github.com/RaimondsMezalsQA)
* Front-end Lead - [Henry Oliver-Edwards](https://github.com/QAHenryOliverEdwards) 

### Training Team

- **Client** - [**Angelica Charry**](https://github.com/acharry) - **Software Delivery Manager**
- **Product Owner** - [**Nick Johnson**](https://github.com/nickrstewarttds) - **Initial work (backend & frontend development, specification)**
- **Product Owner** - [**Edward Reynolds**](https://github.com/Edrz-96) - **Initial work (testing, specification)**
- [**Jordan Harrison**](https://github.com/JHarry444) - **General Java wizardry**
- [**Alan Davies**](https://github.com/MorickClive)
- [**Savannah Vaithilingham**](https://github.com/savannahvaith)
- [**Vinesh Ghela**](https://github.com/vineshghela)
- [**Piers Barber**](https://github.com/PCMBarber)

### Development Team

- Team names and roles here, e.g. **Scrum Master**

## Acknowledgements

