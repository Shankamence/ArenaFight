import com.arenafight.ItemFactory;
import com.arenafight.gear.Gear;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A test class used to test the Gear class and its methods.
 * This class tests the getMethods of the gear classes get ability values and
 * the get abilities method.
 */
public class GearTest {

  @Test
  public void testGetConstitution() {
    int masterSeed = 69;
    Gear gear = (Gear) new ItemFactory().makeHeadGear(masterSeed);
    assertEquals(3, gear.getConstitution());

  }

  @Test
  public void testGetStrength() {
    int masterSeed = 4;
    Gear gear = (Gear) new ItemFactory().makeBelt(masterSeed);
    assertEquals(3, gear.getConstitution());
  }

  @Test
  public void testGetDexterity() {
    int masterSeed = 4;
    Gear gear = (Gear) new ItemFactory().makeFootwear(masterSeed);
    assertEquals(1, gear.getDexterity());
  }

  @Test
  public void testGetCharisma() {
    int masterSeed = 6;
    Gear gear = (Gear) new ItemFactory().makeBelt(masterSeed);
    assertEquals(3, gear.getCharisma());
  }

}
