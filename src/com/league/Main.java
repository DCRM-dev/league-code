package com.league;

import com.league.solution1.LeagueService;
import com.league.solution2.PremierLeagueService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        LeagueService leagueService = new LeagueService();
        PremierLeagueService premierLeagueService = new PremierLeagueService();

        if(validateInputArguments(args)) {

            try {
                List<String> input = readInputFile(args[0]);

//            List<String> result = leagueService.calculateRank(input);        // use a TreeMap<String, Integer>
                List<String> result = premierLeagueService.calculateRank(input);   // Use a List<Team>

                for (int i = 0; i < result.size(); i++) {
                    System.out.println(result.get(i));
                }

            } catch (IOException e) {
                System.out.println("Error reading input file. " + e.getMessage());
                e.printStackTrace();
            }
        }
    }


    public static boolean validateInputArguments(String[] args) {
        boolean isValid = true;
        //First validate the input args and length.
        if (args != null && args.length >= 1) {
            //Here validate the args[0] = input File
            File inputFile = new File(args[0]);
            if (!inputFile.exists()) {
                isValid = false;
                System.out.println("input file does not exist: " + inputFile);
            }
        } else {
            isValid = false;
            System.out.println("One argument is required as input file name");
        }

        return isValid;
    }

    public static List<String> readInputFile(String filePath) throws IOException {

        List<String> allLines = Files.readAllLines(Paths.get(filePath));
        return allLines;

    }


}
