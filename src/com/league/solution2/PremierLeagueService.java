package com.league.solution2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PremierLeagueService {

    private List<Team> resultBoard;

    public PremierLeagueService() {
        this.resultBoard = new ArrayList<>();
    }


    public List<String> calculateRank(List<String> input) {

        input.stream().forEach(
                match -> {
                    String[] teams = match.split(", ");
                    String homeTeamName = getTeamName(teams[0]);
                    int homeScore = getTeamScore(teams[0]);

                    String visitTeamName = getTeamName(teams[1]);
                    int visitScore = getTeamScore(teams[1]);

                    addTeamToBoard(resultBoard, homeTeamName);
                    addTeamToBoard(resultBoard, visitTeamName);

                    if (homeScore > visitScore) {
                        addScore(resultBoard, homeTeamName, 3);
                    } else if (homeScore < visitScore) {
                        addScore(resultBoard, visitTeamName, 3);
                    } else {
                        addScore(resultBoard, homeTeamName, 1);
                        addScore(resultBoard, visitTeamName, 1);
                    }

                }
        );

        resultBoard = resultBoard
                .stream()
                .sorted(Comparator
                        .comparingInt(Team::getPoints)
                        .reversed()
                        .thenComparing((t1, t2) -> t1.getName().compareToIgnoreCase(t2.getName())))    // ignore upper/down case-sensitive alphabetic order
                .collect(Collectors.toList());

        List<String> outputList = new ArrayList<>();

        int i = 1;
        int previousPoints = -1;
        int rank = 1;
        for (Team team : resultBoard) {

            if (previousPoints != team.getPoints()) {
                rank = i;
            }
            i++;
            String line = String.format("%s. %s, %s %s", rank, team.getName(), team.getPoints(), getSuffixPoints(team.getPoints()));
            previousPoints = team.getPoints();
            outputList.add(line);
        }

        return outputList;
    }

    private void addTeamToBoard(List<Team> resultBoard, String teamName) {

        resultBoard
                .stream()
                .filter(t -> teamName.equalsIgnoreCase(t.getName()))
                .findFirst()
                .ifPresentOrElse(
                        team -> {
                        },
                        () -> {
                            Team team = new Team(teamName, 0);
                            resultBoard.add(team);
                        }
                );

    }

    private void addScore(List<Team> resultBoard, String teamName, int pointsToAdd) {

        resultBoard
                .stream()
                .filter(t -> teamName.equalsIgnoreCase(t.getName()))
                .findFirst()
                .ifPresent(
                        team1 -> {
                            team1.setPoints(team1.getPoints() + pointsToAdd);
                        }
                );
    }

    private String getTeamName(String input) {
        return input.substring(0, input.lastIndexOf(" "));
    }

    private int getTeamScore(String input) {
        return Integer.parseInt(input.substring(input.lastIndexOf(" ") + 1));
    }

    private String getSuffixPoints(Integer value) {
        if (value == 1)
            return "pt";
        else
            return "pts";
    }

}
