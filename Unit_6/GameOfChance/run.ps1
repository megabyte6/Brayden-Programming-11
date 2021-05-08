$pathToJava8 = 'C:\Program Files\Java\jdk-1.8.0_281\bin\java.exe'

# Check if the user wants to run the standalone applicaton (jar file) or not
if (($args[0]) -and ($args[0] -eq 'app')) {
    if (Test-Path -Path $pathToJava8) {
         & $pathToJava8 -jar .\bin\standalone-application\BingoWithJavaFX.jar
    } else {
        Write-Host "Error: the file '$pathToJava8' doesn't seem to exist"
    }
} else {
    java --module-path ..\javafx-sdk-16\lib\ --add-modules javafx.controls,javafx.fxml -cp .\bin\compiled\ Main
}