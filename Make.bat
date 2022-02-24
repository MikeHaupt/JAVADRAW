javac module-info.java .\com\mhauptart\drawstuff\*.java --module-path "C:\javafx-sdk-17.0.2\lib" --add-modules javafx.controls
jar cmvf drawstuff.mf com.mhauptart.drawstuff.jar *.class .\com\mhauptart\drawstuff\*.class

::execute next commented line to create executable windows installer
::jpackage --input ./ --module-path "C:\javafx-jmods-17.0.2" --add-modules javafx.controls --type exe --main-jar ./com.mhauptart.drawstuff.jar --name ClickMe2
