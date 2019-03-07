set projectLocation=E:\QC_Workspace\AA_Automation
cd %projectLocation%
set classpath=%projectLocation%\lib\*;%projectLocation%\bin
java org.testng.TestNG %projectLocation%\PDL.xml
pause