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

runtest:
	javac -cp lib/junit-platform-console-standalone-1.11.0.jar Testa.java
