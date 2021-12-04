import com.arenafight.Arena;
import com.arenafight.Character;
import com.arenafight.Item;
import com.arenafight.gear.Belt;
import com.arenafight.gear.BeltSize;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * A test class used to test the Character class. It has methods that tests the range
 * of the base stats of the character and the modified stats of the character.
 * It checks the resetCharacter method which reverts the
 * character to their default state and removes all their items
 * and weapons. It also checks the damage calculation and the
 * health calculation.
 */
public class CharacterTest {

  @Test
  public void testCharacterBaseStats() {
    int masterseed = 69;
    Character character = new Character(masterseed);
    assertTrue(character.getBaseStats().getStrength() >= 4
        && character.getBaseStats().getStrength() <= 18);
    assertTrue(character.getBaseStats().getConstitution() >= 4
        && character.getBaseStats().getConstitution() <= 18);
    assertTrue(character.getBaseStats().getDexterity() >= 4
        && character.getBaseStats().getDexterity() <= 18);
    assertTrue(character.getBaseStats().getCharisma() >= 4
        && character.getBaseStats().getCharisma() <= 18);
  }

  @Test
  public void testPrintBaseStats() {
    int masterseed = 69;
    Character character = new Character(masterseed);
    String expected = String.format("\n %11s the %s\n", "Mia", "Thieving")
        + String.format("%15s : %d\n", "Strength", 13)
        + String.format("%15s : %d\n", "Constitution", 17)
        + String.format("%15s : %d\n", "Dexterity", 13)
        + String.format("%15s : %d\n", "Charisma", 13)
        + String.format("%15s : %d\n", "Health", 56);

    assertEquals(expected, character.printBaseStats());
  }

  @Test
  public void testPrintModifiedStats() {
    int masterseed = 69;
    Character character = new Character(masterseed);
    String expected = String.format("\n %11s the %s\n", "Mia", "Thieving")
        + String.format("%15s : %d\n", "Strength", 13)
        + String.format("%15s : %d\n", "Constitution", 17)
        + String.format("%15s : %d\n", "Dexterity", 13)
        + String.format("%15s : %d\n", "Charisma", 13)
        + String.format("%15s : %d\n", "Health", 56);
    assertEquals(expected, character.printModifiedStats());
  }

  @Test
  public void resetCharacter() {
    int masterseed = 69;
    Character character = new Character(masterseed);
    Arena arena = new Arena();
    ArrayList<Item> bag = arena.createEquipmentBag(masterseed, 40);
    arena.equipItems(character, bag, masterseed);
    character.resetCharacter();
    String expected = String.format("\n %11s the %s\n", "Mia", "Thieving")
        + String.format("%15s : %d\n", "Strength", 13)
        + String.format("%15s : %d\n", "Constitution", 17)
        + String.format("%15s : %d\n", "Dexterity", 13)
        + String.format("%15s : %d\n", "Charisma", 13)
        + String.format("%15s : %d\n", "Health", 56);
    assertEquals(expected, character.printBaseStats());
  }

  @Test
  public void checkHealth() {
    int masterseed = 69;
    Character character = new Character(masterseed);
    assertEquals(56, character.getCurrentHealth());
  }

  @Test
  public void testStrikingPower() {
    int masterseed = 69;
    Character character = new Character(masterseed);
    assertEquals(14, character.calculateStrikingPower(character, masterseed));
  }

  @Test
  public void testAvoidanceAbility() {
    int masterseed = 69;
    Character character = new Character(masterseed);
    assertEquals(14, character.calculateAvoidanceAbility(character, masterseed));
  }

  @Test
  public void testPotentialDamage() {
    int masterseed = 69;
    Character character = new Character(masterseed);
    assertEquals(13, character.calculatePotentialStrikingDamage(character, masterseed));
  }

  @Test
  public void testAttackDamaging() {
    int masterseed = 42;
    Character attacker = new Character(masterseed);
    Character defender = new Character(masterseed);
    attacker.attack(defender, masterseed);
    assertEquals(58, defender.getCurrentHealth());
  }


  @Test
  public void testAttackNotDamaging() {
    int masterseed = 69;
    Character attacker = new Character(masterseed);
    Character defender = new Character(masterseed);
    int expected = defender.getCurrentHealth();
    attacker.attack(defender, masterseed);
    assertEquals(expected, defender.getCurrentHealth());
  }

  @Test
  public void testMaxBeltCapacity() {
    int masterSeed = 69; //41212
    Random random = new Random(masterSeed);
    Arena arena = new Arena();
    Character player = arena.createCharacter(random.nextInt());
    ArrayList<Item> items = arena.createEquipmentBag(random.nextInt(), 60);
    arena.equipItems(player, items, masterSeed);
    int totalbeltCapacity = 0;
    for (Belt belt : player.getBelts()) {
      if (belt.getBeltSize().equals(BeltSize.SMALL)) {
        totalbeltCapacity += 1;
      } else if (belt.getBeltSize().equals(BeltSize.MEDIUM)) {
        totalbeltCapacity += 2;
      } else if (belt.getBeltSize().equals(BeltSize.LARGE)) {
        totalbeltCapacity += 4;
      }
    }
    assertEquals(10, totalbeltCapacity);
  }

  @Test
  public void testBeltUnitsAtMost10() {
    int masterSeed = 45; //41212
    Random random = new Random(masterSeed);
    Arena arena = new Arena();
    Character player = arena.createCharacter(random.nextInt());
    ArrayList<Item> items = arena.createEquipmentBag(random.nextInt(), 60);
    arena.equipItems(player, items, masterSeed);
    int totalbeltCapacity = 0;
    for (Belt belt : player.getBelts()) {
      if (belt.getBeltSize().equals(BeltSize.SMALL)) {
        totalbeltCapacity += 1;
      } else if (belt.getBeltSize().equals(BeltSize.MEDIUM)) {
        totalbeltCapacity += 2;
      } else if (belt.getBeltSize().equals(BeltSize.LARGE)) {
        totalbeltCapacity += 4;
      }
    }
    assertFalse(totalbeltCapacity > 10);
  }


}

