
all:;	mvn clean package
	mvn compile exec:java -Dexec.mainClass="com.ardublock.Main"

clean:;	mvn clean package

build:;	mvn compile exec:java -Dexec.mainClass="com.ardublock.Main"
