#LEAGUE RANK
### com.league.Main 
contains the main method.

#JDK
This application is coded and run in project-jdk-name="11" project-jdk-type="JavaSDK"

#Application arguments to run in commandline
args[0] = path and input file name
Message error will print if
input file does not exist, argument is not present.


#Output
print in standard output the league rank, one line per team.


#Solution
This application has two approach to solve the problem.

##Solution 1
It is contained in solution1 package.
LeagueService class contains the List<String> calculateRank(List<Sting> input) method.
This solution use a TreeMap to store the <teamName,teamPoints> and use a customComparator.
Here the result map is sorted by teamPoints

##Solution 2
It is contained in solution2 package.
LeaguePremierService class contains the List<String> calculateRank(List<Sting> input) method.
This solution use a List<Team> to store the teams.
The result list is sorted by teamPoints and then by teamName using stream.
As here I define a Team class. More information about the team can be added in future if is required.


#Junit
LeagueServiceTest and PremierLeagueServiceTest contains junit test for each implementation.