#!/bin/sh
# Build the classpath from the dependencies in the target/lib directory
CLASSPATH="target/BlueStory-1.0.0.jar:$(echo target/lib/*.jar | tr ' ' ':')"
# Run the Java application with the specified arguments
java -server -cp "$CLASSPATH" server.Start -Dnet.sf.odinms.wzpath=wz/
# Pause (wait for user input) - Unix equivalent of 'pause' in Windows
echo "Press any key to continue..."
read -p ""
