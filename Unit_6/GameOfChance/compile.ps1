# Compile all classes using java 8 and put them in the bin folder
# This first step is to make sure that the jar file can run as JavaFX jar files no longer have
# support from later versions of java
& 'C:\Program Files\Java\jdk-1.8.0_281\bin\javac.exe' .\src\DataStore.java .\src\Easy_Controller.java .\src\Hard_Controller.java .\src\Instructions_Controller.java .\src\Main.java .\src\Medium_Controller.java .\src\Start_Controller.java
Move-Item -Path '.\src\*.class' -Destination '.\bin\compiled\' -Force
Copy-Item -Path '.\src\*.fxml' -Destination '.\bin\compiled\' -Force

# Create a jar file with the classes compiled with java 8
jar -c -f .\bin\standalone-application\BingoWithJavaFX.jar -m .\MANIFEST.MF -C .\bin\compiled\ .

# Recompile classes using the latest version of java and put them in the bin folder
javac --module-path ..\javafx-sdk-16\lib\ --add-modules javafx.controls,javafx.fxml .\src\DataStore.java .\src\Easy_Controller.java .\src\Hard_Controller.java .\src\Instructions_Controller.java .\src\Main.java .\src\Medium_Controller.java .\src\Start_Controller.java
Move-Item -Path '.\src\*.class' -Destination '.\bin\compiled\' -Force