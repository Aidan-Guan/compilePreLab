FROM openjdk:15-alpine
WORKDIR /app/
COPY ./src/* /app/
RUN javac Lab1_Token.java
RUN javac Lab1_LexicalAnalysisForGA.java
RUN javac Lab1_GrammarAnalysis.java
RUN javac Lab1_Test.java

