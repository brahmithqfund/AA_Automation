E:
set projectLocation=E:\AA\SmokeTesting

cd %projectLocation%

set classpath=%projectLocation%\lib\*;%projectLocation%\bin

java org.testng.TestNG %projectLocation%\TLP_GenericTests.xml

pause
