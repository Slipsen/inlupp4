plzwork:
	javac -d classes org/ioopm/Driver.java


all:
	javac -d classes org/ioopm/calculator/Calculator.java
compile:
	javac org/ioopm/parser/*.java

run:
	java -cp classes org/ioopm/calculator/Calculator

parser:
	java -cp classes org/ioopm/parser/CalculatorParser

test:
	javac -d classes Test.java
	java -cp classes Test
try:
	javac -sourcepath org org/ioopm/calculator/Calculator.java

clean:
	rm -rf classes

runtest:
	javac -cp lib/junit-platform-console-standalone-1.11.0.jar Testa.java
