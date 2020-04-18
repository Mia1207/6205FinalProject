# INFO6205 Program Structure & Algorithms Spring 2020 - Final Project
## Project Description

This project is to create a ranking system for English Premier League (EPL). The input to our system is a set of prior encounters with a result. These results are scores of each game. The output is the probability of win-draw-lose in coming games in season 2019-2020 according to the schedule. In this project, we foucs on the season 2019-2020, especially on all the matches that were abandoned due to COVID-19.

Our system solves the following problems:
  - Calculate the win, draw and loss probability that team A will beat team B if they meet with each other in a head to head matchup.
 - Draw the probability density function for goal difference in each game.
- Predict those coming games. “Complete the season” and give a final table with 38 matches “played” by each team.
-    Predict the final rank of those twenty teams with the prediction of 92 coming games.

## Team Information
name | Email Address
-|-|
Huarui Lu | lu.hua@northeastern.edu
Zhiwei Li | li.zhiw@northeastern.edu

## Prerequisites
 - An IDE to run the project either like IntelliJ.
 - Java SDK 11 installed.

## How to Run
1. Open 6205FinalProject in IDE.
2. SDK version may be asked to setup when first run. Java version 11 is prefereed.
3. In case of JAR files not imported successfully, all the JAR files needed were uploaded. You can find them in folder "JAR".
4. Run the main function of RankingSystem.java.

## Expected Output
1. Current rank result calculated by ELO Rating Principle and EPL Rating Principle, for all the matches finished. These rank tables are stored in main/resources/ Rank result of EPL Principle and main/resources/ Rank result of ELO Rating Principle, Separately.
2. The win, draw and loss probability that team A will beat team B if they meet with each other in a head to head matchup. At the same time, probability density function graphs will be generated in other windows. All of the graphs will be saved automatically in path main/resources/Distribution Graph.
3. Final rank result calculated by ELO Rating Principle and EPL Rating Principle, for all the matches, including those finished and postponed. At the same time these final rank tables are stored in “main/resources/Rank result of EPL Principle” and “main/resources/ Rank result of ELO Rating Principle”, Separately

## Data Sources
https://www.football-data.co.uk/englandm.php




