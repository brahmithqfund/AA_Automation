E:
set projectLocation=E:\AA

cd %projectLocation%

set classpath=%projectLocation%\lib\*;%projectLocation%\bin

java org.testng.TestNG %projectLocation%\PDL_Regression.xml

pause
