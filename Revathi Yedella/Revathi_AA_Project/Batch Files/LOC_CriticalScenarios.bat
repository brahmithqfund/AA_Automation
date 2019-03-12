E:
set projectLocation=E:\QfundTesting

cd %projectLocation%

set classpath=%projectLocation%\lib\*;%projectLocation%\bin

java org.testng.TestNG %projectLocation%\LOC_CriticalScenarios.xml

pause
