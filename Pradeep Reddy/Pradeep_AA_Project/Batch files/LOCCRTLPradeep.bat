E:
set projectLocation=E:\AA

cd %projectLocation%

set classpath=%projectLocation%\lib\*;%projectLocation%\bin

java org.testng.TestNG %projectLocation%\LOCCRTLPradeep.xml

pause
