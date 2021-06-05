$javaFiles = @(
    '.\lib\com\github\megabyte6\utils\Database.java'
    '.\src\App.java'
    '.\src\EditFriend.java'
    '.\src\Friend.java'
    '.\src\FriendBook.java'
    '.\src\FriendDatabase.java'
)

# Compile all classes using java 8 and put them in the bin folder
# This is to make sure that the jar file can run as a JavaFX application
# because JavaFX jar files are not supported by later versions of java
& 'C:\Program Files\Java\jdk1.8.0_291\bin\javac.exe' -d .\bin\compiled\ $javaFiles
Copy-Item -Path '.\src\layout\' -Destination '.\bin\compiled\' -Recurse -Force

& 'jar' -c -f .\bin\jar\FriendBook.jar -m MANIFEST.MF -C .\bin\compiled\ .

# Recompile classes using the latest version of java
& 'javac' --module-path ..\resources\win\javafx-sdk-16\lib\ --add-modules javafx.controls,javafx.fxml -d .\bin\compiled\ $javaFiles
