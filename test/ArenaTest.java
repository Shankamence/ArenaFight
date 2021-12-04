import com.arenafight.Arena;
import com.arenafight.Character;
import com.arenafight.Item;
import com.arenafight.Roll;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * A test class used to test the Arena class and its methods.
 * It tests various scnearios such as fighting
 * with no equipment, fighting with equipment.
 * It tests the roll for damage and whether the gear has been
 * equipped properly to a character.
 */
public class ArenaTest {

  @Test
  public void bareHandedFight() {
    Roll roll = new Roll();
    int masterSeed = 69; //41212
    Random random = new Random(masterSeed);
    Arena arena = new Arena();
    Character player = arena.createCharacter(random.nextInt());
    Character opponent = arena.createCharacter(random.nextInt());
    assertEquals("BareHanded", player.getWeapon().getItemType());
    assertEquals(player.printBaseStats(), player.printModifiedStats());
    arena.battle(player, opponent, random.nextInt());
  }

  @Test
  public void equippedFight() {
    Roll roll = new Roll();
    int masterSeed = 69; //41212
    Random random = new Random(masterSeed);
    Arena arena = new Arena();
    Character player = arena.createCharacter(random.nextInt());
    Character opponent = arena.createCharacter(random.nextInt());
    ArrayList<Item> items1 = arena.createEquipmentBag(random.nextInt(), 40);
    ArrayList<Item> items2 = arena.createEquipmentBag(random.nextInt(), 40);
    arena.equipItems(player, items1, random.nextInt());
    arena.equipItems(opponent, items2, random.nextInt());
    player.setWeapon(arena.equipWeapon(random.nextInt()));
    opponent.setWeapon(arena.equipWeapon(random.nextInt()));
    assertEquals(items1.size(),20);

  }

  @Test
  public void testRollForDamage() {
    Roll roll = new Roll();
    int x = roll.rollInRange(4, 6, 2);
    assertTrue("Range incorrect", x >= 4 && x <= 6);
  }

  @Test
  public void testOnlyOneHelmet() {
    int masterSeed = 69; //41212
    Random random = new Random(masterSeed);
    Arena arena = new Arena();
    Character player = arena.createCharacter(random.nextInt());
    ArrayList<Item> items = arena.createEquipmentBag(random.nextInt(), 60);
    arena.equipItems(player, items, masterSeed);
    assertEquals(1, StringUtils.countMatches(arena.printCharacterEquipment(player), "Helmet"));
  }

  @Test
  public void testOnlyOneFootwear() {
    int masterSeed = 69; //41212
    Random random = new Random(masterSeed);
    Arena arena = new Arena();
    Character player = arena.createCharacter(random.nextInt());
    ArrayList<Item> items = arena.createEquipmentBag(random.nextInt(), 60);
    arena.equipItems(player, items, masterSeed);
    assertEquals(1, StringUtils.countMatches(arena.printCharacterEquipment(player), "Boots"));
  }


  @Test
  public void createEquipmentBagTest() {
    Arena arena = new Arena();
    int masterSeed = 6712; //41212
    Random random = new Random(masterSeed);
    ArrayList<Item> items = arena.createEquipmentBag(random.nextInt(), 60);
    assertEquals(items.size(), 60);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEquipmentBagException() {
    Arena arena = new Arena();
    ArrayList<Item> items = new ArrayList<>();
    int masterSeed = 6712; //41212
    Random random = new Random(masterSeed);
    items = arena.createEquipmentBag(random.nextInt(), 20);
    assertEquals(items.size(), 60);
  }

  @Test
  public void testEquippedCorrectly() {
    int masterSeed = 634; //41212
    Random random = new Random(masterSeed);
    Arena arena = new Arena();
    Character player = arena.createCharacter(random.nextInt());
    ArrayList<Item> items = arena.createEquipmentBag(random.nextInt(), 60);
    arena.equipItems(player, items, masterSeed);
    System.out.println(arena.printCharacterEquipment(player));
    assertEquals(1, StringUtils.countMatches(arena.printCharacterEquipment(player), "Helmet"));
    assertEquals(1, StringUtils.countMatches(arena.printCharacterEquipment(player), "Boots"));
    assertEquals(5, StringUtils.countMatches(arena.printCharacterEquipment(player), "Belt") / 2);
    assertEquals(5, StringUtils.countMatches(arena.printCharacterEquipment(player), "Potion") / 2);
  }


  @Test
  public void testAtLeastFiveHeadgearPerBag() {
    Arena arena = new Arena();
    ArrayList<Item> items = new ArrayList<>();
    int masterSeed = 6712; //41212
    Random random = new Random(masterSeed);
    items = arena.createEquipmentBag(random.nextInt(), 40);
    int count = 0;
    for (Item item : items) {
      if (item.getItemType().equals("Headgear")) {
        count++;
      }
    }
    assertTrue(count >= 5);
  }

  @Test
  public void testAtLeastFiveFootwearPerBag() {
    Arena arena = new Arena();
    ArrayList<Item> items = new ArrayList<>();
    int masterSeed = 6712; //41212
    Random random = new Random(masterSeed);
    items = arena.createEquipmentBag(random.nextInt(), 40);
    int count = 0;
    for (Item item : items) {
      if (item.getItemType().equals("Footwear")) {
        count++;
      }
    }
    assertTrue(count >= 5);
  }

  @Test
  public void testAtLeastFifteenBeltsPerBag() {
    Arena arena = new Arena();
    ArrayList<Item> items = new ArrayList<>();
    int masterSeed = 6712; //41212
    Random random = new Random(masterSeed);
    items = arena.createEquipmentBag(random.nextInt(), 40);
    int count = 0;
    for (Item item : items) {
      if (item.getItemType().equals("Belt")) {
        count++;
      }
    }
    assertTrue(count >= 15);
  }

  @Test
  public void testAtLeastFifteenPotionsPerBag() {
    Arena arena = new Arena();
    int masterSeed = 6712; //41212
    Random random = new Random(masterSeed);
    ArrayList<Item> items = items = arena.createEquipmentBag(random.nextInt(), 40);
    int count = 0;
    for (Item item : items) {
      if (item.getItemType().equals("Potion")) {
        count++;
      }
    }
    assertTrue(count >= 15);
  }

}