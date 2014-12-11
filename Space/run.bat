cd .\scripts
start MainContainer.bat
timeout /t 5 /nobreak
start Monitor.bat
timeout /t 3 /nobreak
start Mars.bat
timeout /t 2 /nobreak
start Jupiter.bat