@rem !!!!!!! Please read important information. !!!!!!
@rem If "java" is not in your path, please set the path 
@rem for Java 2 Runtime Environment in the path variable below
@rem for example :
@rem @SET PATH=D:\jdk1.6.0_18;%PATH%

@echo off

rem Set Quartz to the base directory of the Quartz Distribution
SET WD=%~dp0
SET QUARTZ=%WD%engine\schedule
rem a configuration file for log4j logging
SET LOG4J_PROPS="-Dlog4j.configuration=file:%QUARTZ%\log4j.xml"
rem Set the location and name of the quartz.properties file
SET QUARTZ_PROPS="-Dorg.quartz.properties=%QUARTZ%\quartz.properties"

@FOR /F "delims=" %%i IN ('dir /b /a-d /s "%WD%*.jar"') DO @echo %%i >> JavaPath.tmp

SetLocal EnableDelayedExpansion
SET TMP_CP=.
@FOR /F %%I IN (JavaPath.tmp) DO SET TMP_CP=!TMP_CP!;%%I

SET QUARTZ_CP=%TMP_CP%
DEL JavaPath.tmp

"java" -cp %QUARTZ_CP% %QUARTZ_PROPS% %LOG4J_PROPS% PluginLoader

@rem @echo on
@rem @echo Work Directory:%WD%
@rem @echo QUARTZ Directory:%QUARTZ%
@rem @echo CP:%QUARTZ_CP%
@rem @echo LOG4J_PROPS:%LOG4J_PROPS%
@rem @echo QUARTZ_PROPS:%QUARTZ_PROPS%
