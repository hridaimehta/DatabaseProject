build: ProjectPart2.class

ProjectPart2.class: ProjectPart2.java
	javac ProjectPart2.java

run: ProjectPart2.class
	java -cp .:mssql-jdbc-11.2.0.jre18.jar ProjectPart2

clean:
	rm ProjectPart2.class
	rm MyDatabase.class
