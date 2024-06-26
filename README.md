# FXThemes
Utility classes for advanced Theme development for JavaFX (Java)

- Set a background blur effect on a JavaFX Window  

![Acrylic Backdrop](Acrylic.jpg)

- Set dark or light window decorations  

![True dark mode](DarkMode.jpg)

- Fully customize a Window frame color, text color and border  

![Changing Window Frame colors](WindowFrameColors.jpg)

## Documentation
Documentation of FXThemes can be found in this link: [FXThemes documentation](https://pixelduke.com/fxthemes/).
You should check it if you want to know more about this library.

## How to get it
You can get the library through Maven Central.

Here are examples for Gradle and Maven (replace version number with the version you want):

#### Gradle
```groovy
implementation 'com.pixelduke:fxthemes:1.5.1'
```

#### Maven
```xml
<dependency>
    <groupId>com.pixelduke</groupId>
    <artifactId>fxthemes</artifactId>
    <version>1.5.1</version>
</dependency>
```

## Source code
As of the time of writing of this documentation the code is being compiled on Java 21 and JavaFX 21. These Java and JavaFX
versions may be updated in the future.

The FXThemes-samples subproject has samples that you can run and check out how to use FXThemes.

## Running the sample demos
To run the demos,  enter the following command in the Command Prompt / Terminal, inside the project directory:
```
mvn javafx:run -f fxthemes-samples/pom.xml
```
To choose which demo to run, change the "pom.xml" script file inside "FXThemes-samples" folder and replace the 
contents of the `<mainClass>` xml tag with the name of the Application derived class you'd like to execute.

## Pull Requests (PR)
We welcome contributions via PR.  
Before submitting a PR please file an issue for prior discussion. This will avoid you wasting time with a PR that
might not be approved because, for instance, might be outside the intended scope of the project.

### Filing bugs
When filing bugs it's most often good practice to attach a small sample app (as small, simple and with the fewest lines of
code as possible). This app when executed, should show the bug happening.  
The reason for this is the limited amount of resources and time I have and also because in the process of filing a bug,
developers sometimes discover that the bug isn't in the library but somewhere else.   
Without a small example app the issue might be closed prematurely.

## Feedback request
Please send pictures of your application that is using this library, or a site that shows your application. Or share it
through Twitter (you can reference
me through my twitter handle @P_Duke if you'd like).   
This is very important for me to know how users are effectively using it and make adjustments accordingly to make this
library better.
Also, and if you allow it, to showcase example uses.    
Seeing this library get used also always motivates me to keep working on it.

## License
FXThemes uses the ['GNU General Public License, version 2, with the Classpath Exception'](https://openjdk.java.net/legal/gplv2+ce.html)



