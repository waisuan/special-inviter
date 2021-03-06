# Context
This is a sample Kotlin standalone app that returns a list of invitees/customers that are within the allowed distance between
an invitee and a given source coordinate.

_Psst...```src/main/kotlin/org/esia/specialinviter/App.kt``` is a good starting point to see how the app operates internally_

# Prerequisites
- Java >= 1.8.0
- Gradle (Optional)

# Build
Under the project's root directory, run the following commands:
1. ```./gradlew clean build```
2. ```./gradlew shadowJar```

You should see an executable JAR file called **special-inviter-1.0-all.jar** under the ```build/libs``` directory.

# Execute
Under the project's root directory, run the following command:
- ```java -jar build/libs/special-inviter-1.0-all.jar```

For help with usage, run the following command:
 - ```java -jar build/libs/special-inviter-1.0-all.jar -h```

Usage should look as follows:
```
» java -jar build/libs/special-inviter-1.0-all.jar -h
usage: [-h] [-D MAX_DISTANCE] [--kilometres] [-C COORDINATES] [CUSTOMERS_FILE]

optional arguments:
  -h, --help                    show this help message and exit

  -D MAX_DISTANCE,              A numerical value denoting the max allowed
  --max-distance MAX_DISTANCE   distance a customer needs to be in to be
                                invited

  --kilometres, --miles,        The unit of the given max distance
  --nautical-miles

  -C COORDINATES,               The coordinates of the source/home office in
  --coordinates COORDINATES     the form of <latitude,longitude> (e.g.
                                53.339428,-6.257664)


positional arguments:
  CUSTOMERS_FILE                Path to file containing a list of customers to
                                potentially invite
```

By default, if you were to execute without any input arguments, the app will fill in the gaps with the following defaults:-
- ```--max-distance 100.0```
- ```--kilometres```
- ```--coordinates 53.339428,-6.257664```
- ```src/main/resources/customers.txt```

It's also a good starting point for seeing what the app ultimately produces.

# Tests
Under the project's root directory, run the following command:
- ```./gradlew clean test```

You should see something along the lines of:-
```
--------------------------------------------------------------------------------------------
|  Results: SUCCESS (13 tests, 13 successes, 0 failures, 0 skipped) Gradle Test Run :test  |
--------------------------------------------------------------------------------------------
```

You can also find a HTML/prettified overview of the test results under the ```build/reports/tests``` directory. It should be labeled under **index.html**.