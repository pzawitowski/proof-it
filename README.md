## Home assignment task

* Application listens on port 8081 by default. The port can be changed in application.properties file.
* API endpoint is available under path: POST /api/v1/calculate
* to add new Risk type two Groovy scripts should be added to src/main/groovy directory for premium and sum insured rules
* each added Groovy class should implement interface com.proofit.insurance.calculator.Formula
* Groovy scripts directory can be changed in application.properties file. Property groovyScriptDirectory