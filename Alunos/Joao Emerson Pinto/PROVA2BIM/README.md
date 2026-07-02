Para executar o programa rode o seguinte comando no powershell dentro do diretorio raiz do projeto 

New-Item -ItemType Directory -Force -Path out | Out-Null; $fontes = Get-ChildItem src -Recurse -Filter *.java; javac -encoding UTF-8 -cp "lib\gson-2.11.0.jar" -d out $fontes.FullName; java -cp "out;lib\gson-2.11.0.jar" Main
java -cp "out;lib\gson-2.11.0.jar" Main