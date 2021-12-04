
#About/Overview 
This project is a small text based battle simulator for Jumptastic games that allows users to create and pit characters against each other 
in turn based battle. The players can be equipped with items and a weapon. The player is assigned random ability values for strength, dexterity,
charisma and constitution which can be boosted by potions or other equippable gear like foot wear and head gear. These values are summed and 
a damage calculation formula is applied to get the damage when attacking an opponent. The game goes on until one of the players loses all their health and goes to 0 or negative.
The player with health higher than zero is declared the winner. The match can also end in a draw if a significant amount of turns has passed and no player is determined as the 
victor.

#List of features. 

1) Any number of characters with random values and fun names can be created. There are over a million combinations of unique characters that can be generated.
2) An equipment bag or loot bag can be created from which a character can equip themselves with gear and potions. These bags are randomly generated and will be different each game.
3) The character is assigned twenty random items from the loot bag and may equip the items as defined by the game (1 helmet, 1 footwear, belts within the given carry limit)
4) There is a rematch feature where a player can ask for a rematch with the same characters. The players will change their equipment and weapons in order to make it interesting!
5) Certain weapons have special effects or conditions. For example: Katanas are held in pairs and can strike twice, Flails require a stat to be above a certain level, etc
6) The gear that can be held by the character is randomly generated and have interesting names and abilities. Each type of gear affects a certain stat with some gear affecting upto 2.
7) Users also have the option to have their character fight in the arena barehanded with no equipment.

#Design/Model Changes. 
Overall the implementation and the inheritance hierarchy of the project has not changed. There are a few attributes and methods moved from one class to another.

In the updated design factory classes and builder classes have been added for Abilities and Items respectively.
Earlier Weapon was an interface, but now it is an abstract class that has some methods that are common to all weapons.

Ability values were previously stored as integers in the Character class but now a reference to an ability object is stored.
This helps in code re-usability.

#How to Run the Program.
1) Open cmd
2) Navigate to the directory with the jar
3) Type in java -jar ArenaFight.jar
4) No arguments are necessary, the driver will run the code.

The first run will be a barehanded fight run with no equipment. On giving No to rematch
the fight will restart with the same characters equipped with gear and a weapon.
The program will end when the user decides they do not want a rematch after this.

#Example Run Description
1) The Run1.txt contains a run that has both the player and the opponent fully equipped and once the match is over, no rematch is selected
2) Run2.txt contains a run that has the player and opponent fully equipped and asks for a rematch twice with slightly different games.
3) Run3.txt contains a run where the player and opponent have no equipment and are barehanded. It ends without a rematch.
4) Run4.txt contains a run where both characters are equipped. The player character loses the first time but on rematch, the player character wins.

#Assumptions. 
It has been assumed that the damage ranges for weapons are fixed in the constructor and may be subject to change.
If it were the case that these values are always fixed then they would have been made final variables (or static final depending on the use case)

#Limitations. 
The weapon damage ranges cannot be altered without opening the constructor for the specific weapon.
As an alternative if required the constructor could take in the upper bound and lower bound values.

#Citations. 
[1] Baeldung, Generating Random numbers in a range, https://www.baeldung.com/java-generating-random-numbers-in-range

[2] StackOverflow, Random number between one and ten, https://stackoverflow.com/questions/20389890/generating-a-random-number-between-1-and-10-java

[3] C4code, How to print the results to console in a tabular format using java?, https://c4code.wordpress.com/2018/03/17/how-to-print-the-results-to-console-in-a-tabular-format-using-java/, 10/01/21