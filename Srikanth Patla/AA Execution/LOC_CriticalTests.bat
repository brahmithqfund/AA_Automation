E:
set projectLocation=E:\AA\SmokeTesting

cd %projectLocation%

set classpath=%projectLocation%\lib\*;%projectLocation%\bin

java org.testng.TestNG %projectLocation%\LOC_CriticalTests.xml

pause