package com.test;

import com.league.solution2.PremierLeagueService;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class PremierLeagueServiceTest {

    private PremierLeagueService premierLeagueService = new PremierLeagueService();


    @Test
    public void calculateRank_test() throws Exception {

        List<String> input = Arrays.asList(
                "Lions 3, Snakes 3",
                "Tarantulas 1, FC Awesome 0",
                "Lions 1, FC Awesome 1",
                "Tarantulas 3, Snakes 1",
                "Lions 4, Grouches 0");

        List<String> expectedOutput = Arrays.asList(
                "1. Tarantulas, 6 pts",
                "2. Lions, 5 pts",
                "3. FC Awesome, 1 pt",
                "3. Snakes, 1 pt",
                "5. Grouches, 0 pts"
        );

        List<String> result = premierLeagueService.calculateRank(input);

        Assert.assertEquals(expectedOutput, result);

    }


    @Test
    public void calculateRank_test_only_draws() throws Exception {

        List<String> input = Arrays.asList(
                "Lions 3, Snakes 3",
                "Tarantulas 1, FC Awesome 1",
                "Lions 1, FC Awesome 1",
                "Tarantulas 1, Snakes 1",
                "Lions 0, Grouches 0");

        List<String> expectedOutput = Arrays.asList(
                "1. Lions, 3 pts",
                "2. FC Awesome, 2 pts",
                "2. Snakes, 2 pts",
                "2. Tarantulas, 2 pts",
                "5. Grouches, 1 pt"
        );


        List<String> result = premierLeagueService.calculateRank(input);

        Assert.assertEquals(expectedOutput, result);

    }
}