FROM openjdk:15
WORKDIR /app/
COPY ./src/* /app/
RUN javac Token.java
RUN javac LexAnal.java
RUN javac GrammarAnal.java
RUN javac Test.java

