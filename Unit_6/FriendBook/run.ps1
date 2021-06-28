$pathToJava8 = 'C:\Program Files\Java\jdk1.8.0_291\bin\java.exe'

# Check if the user wants to run the standalone application (jar file) or not
if (($args[0]) -and ($args[0] -eq 'jar')) {
    if (Test-Path -Path $pathToJava8) {
        & $pathToJava8 -jar .\bin\jar\FriendBook.jar
    } else {
        Write-Host "Error: the file '$pathToJava8' doesn't seem to exist"
    }
} else {
    & 'java' --module-path ..\resources\win\javafx-sdk-16\lib\ --add-modules javafx.controls,javafx.fxml -cp .\bin\compiled\ App
}
