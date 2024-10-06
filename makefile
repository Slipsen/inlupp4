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
testPrint: 
	make all
	make run < parse_files/input1.txt > parse_files/output1.txt
	diff parse_files/output1.txt parse_files/output1expected.txt > parse_files/result1.txt

runtest:
	javac -cp lib/junit-platform-console-standalone-1.11.0.jar Testa.java
