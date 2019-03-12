E:
set projectLocation=E:\QfundTesting

cd %projectLocation%

set classpath=%projectLocation%\lib\*;%projectLocation%\bin

java org.testng.TestNG %projectLocation%\TLP_SmokeExecution.xml

pause