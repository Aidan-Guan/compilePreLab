FROM openjdk:15
WORKDIR /app/
COPY ./src/* /app/
RUN javac ExpValue.java
RUN javac GrammarAnalysis.java
RUN javac Ident.java
RUN javac LexAnal.java
RUN javac Token.java
RUN javac Test.java

