import com.arenafight.Character;
import com.arenafight.ItemFactory;
import com.arenafight.weapons.Weapon;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * A test class that is used to test the methods present inside the Weapon class.
 * It tests the damage range of weapon and the item type returned by a weapon.
 */
public class WeaponTest {

  @Test
  public void testWeaponDamageRange() {
    int masterSeed = 7;
    Weapon weapon = new ItemFactory().makeWeapon(masterSeed); // Axe
    Character character = new Character(masterSeed);
    assertTrue(weapon.getWeaponDamage(masterSeed, character) >= 6
        && weapon.getWeaponDamage(masterSeed, character) <= 10);
  }

  @Test
  public void testGetItemType() {
    int masterSeed = 7;
    Weapon weapon = new ItemFactory().makeWeapon(masterSeed); // Axe
    Character character = new Character(masterSeed);
    assertEquals("Axe", weapon.getItemType());
  }

}
