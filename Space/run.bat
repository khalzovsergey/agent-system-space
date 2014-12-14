cd .\scripts
start MainContainer.bat
timeout /t 5 /nobreak
start Monitor.bat
timeout /t 3 /nobreak
start Mars.bat
start Earth.bat
start ShipFactory_1.bat
start ShipFactory_2.bat
start ShipFactory_3.bat