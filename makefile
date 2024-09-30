all:
	javac -d classes org/ioopm/calculator/Calculator.java
run:
	java -cp classes org/ioopm/calculator/Calculator

test1:
	javac -d classes Test1.java
	java -cp classes Test1

test:
	javac -d classes Test.java
	java -cp classes Test
try:
	javac -sourcepath org org/ioopm/calculator/Calculator.java

clean:
	rm -rf classes

miscTests:
	javac -d VariousTests  VariousTests/MainTest.java
	java -cp VariousTests VariousTests/MainTest
	rm -rf VariousTests/VariousTests


runtest:
	javac -cp lib/junit-platform-console-standalone-1.11.0.jar Testa.java
