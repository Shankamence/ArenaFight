package com.arenafight;

import java.util.Arrays;
import java.util.Random;

/**
 * A class that is used to represent the rolls of die in the game for specific purposes.
 * Contains methods to roll die for obtaining stat values, setting names and damage calc.
 */
public class Roll {

  /**
   * A method that is used to roll a 6sided die 4 times to calculate a stat value.
   *
   * @param seed int The seed to use in the random number generator
   * @return int a valid stat value
   */
  public int rollForStats(int seed) {
    Random randSeeder = new Random(seed);
    Random random = new Random(randSeeder.nextInt());
    int validRoll = 4;
    int[] statArray = new int[4];
    while (validRoll > 0) {
      int randomRoll = random.nextInt(7 - 1) + 1;
      if (randomRoll != 1) {
        statArray[validRoll - 1] = randomRoll;
        validRoll--;
      }
    }
    Arrays.sort(statArray);
    return statArray[3] + statArray[2] + statArray[1];

  }

  /**
   * Returns the damage for a weapon when given the upper and lower bound of damage.
   *
   * @param lowerBound int lower bound damage range
   * @param upperBound int upper bound damage range
   * @param seed       int random seed
   * @return the damage roll int
   */
  public int rollInRange(int lowerBound, int upperBound, int seed) throws IllegalArgumentException {
    if (lowerBound > upperBound) {
      throw new IllegalArgumentException("The lowerbound must be lesser than the upperbound");
    }
    Random randSeeder = new Random(seed);
    Random random = new Random(randSeeder.nextInt());
    upperBound = upperBound + 1;
    return random.nextInt(upperBound - lowerBound) + lowerBound;
  }

  /**
   * Contains a string array filled with names and
   * picks a random name from that array and returns it.
   *
   * @param seed the integer to determine the random value
   * @return the selected name String
   */
  public String rollForCharacterName(int seed) {
    Random randSeeder = new Random(seed);
    Random random = new Random(randSeeder.nextInt());
    String[] names = {
      "Liam",
      "Olivia",
      "Noah",
      "Emma",
      "Oliver",
      "Ava",
      "William",
      "Sophia",
      "Elijah",
      "Isabella",
      "James",
      "Charlotte",
      "Benjamin",
      "Amelia",
      "Lucas",
      "Mia",
      "Mason",
      "Harper",
      "Ethan",
      "Evelyn"
    };
    return names[random.nextInt((names.length))];
  }

  /**
   * Contains a string array filled with gear classes and
   * picks a random gear class from that array and returns it.
   *
   * @param seed the integer to determine the random value
   * @return the selected name String
   */
  public String rollForGearClass(int seed) {
    Random randSeeder = new Random(seed);
    Random random = new Random(randSeeder.nextInt());
    String[] names = {
      "Copper",
      "Steel",
      "Iron",
      "Titanite",
      "Orichalchum",
      "Bronze",
      "Silver",
      "Glass",
      "Gold",
      "Diamond",
      "Adamantite",
      "Unbreakable",
      "Holy",
      "Dark"
    };
    return names[random.nextInt((names.length))];
  }

  /**
   * Contains a string array filled with titles and
   * picks a random title from that array and returns it.
   *
   * @param seed the integer to determine the random value
   * @return the selected title String
   */
  public String rollForTitle(int seed) {
    Random randSeeder = new Random(seed);
    Random random = new Random(randSeeder.nextInt());
    String[] names = {
      "Brave",
      "Mean",
      "Rebellious",
      "Generous",
      "Dangerous",
      "Criminal",
      "Holy",
      "Loyal",
      "Corrupted",
      "Kind",
      "Evil",
      "Rude",
      "Coward",
      "Honest",
      "Arrogant",
      "Naive",
      "Lord",
      "Steely",
      "Thieving",
      "Strong"
    };
    return names[random.nextInt((names.length))];
  }


}
