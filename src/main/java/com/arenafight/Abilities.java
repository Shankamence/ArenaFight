package com.arenafight;

/**
 * This class represent the ability values of the characters or the gear that add on to the
 * characters base stats. It contains strength, constitution, dexterity and charisma.
 * The character holds 2 references to objects of this class,
 * one for base values and one for modified values.
 */
public class Abilities {
  private int strength;
  private int constitution;
  private int dexterity;
  private int charisma;

  /**
   * Constructs an ability object that set the value for
   * strength, constitution, dexterity and charisma.
   *
   * @param strength int sets the value for strength in the ability object
   * @param constitution int sets the value for constitution in the ability object
   * @param dexterity int sets the value for dexterity in the ability object
   * @param charisma int sets the value for charisma in the ability object
   */
  public Abilities(int strength, int constitution, int dexterity, int charisma) {
    this.strength = strength;
    this.constitution = constitution;
    this.dexterity = dexterity;
    this.charisma = charisma;
  }

  /**
   * Returns the strength attribute from the object.
   *
   * @return int strength value
   */
  public int getStrength() {
    return strength;
  }

  /**
   * Returns the constitution attribute from the object.
   *
   * @return int constitution value
   */
  public int getConstitution() {
    return constitution;
  }

  /**
   * Returns the dexterity attribute from the object.
   *
   * @return int dexterity value
   */
  public int getDexterity() {
    return dexterity;
  }

  /**
   * Returns the charisma attribute from the object.
   *
   * @return int charisma value
   */
  public int getCharisma() {
    return charisma;
  }

  /**
   * Sets the charisma value for the ability object.
   *
   * @param charisma int the required charisma value to set
   */
  public void setCharisma(int charisma) {
    this.charisma = charisma;
  }

  /**
   * Sets the dexterity value for the ability object.
   *
   * @param dexterity int the required dexterity value to set
   */
  public void setDexterity(int dexterity) {
    this.dexterity = dexterity;
  }

  /**
   * Sets the constitution value for the ability object.
   *
   * @param constitution int the required constitution value to set
   */
  public void setConstitution(int constitution) {
    this.constitution = constitution;
  }

  /**
   * Sets the strength value for the ability object.
   *
   * @param strength int the required strength value to set
   */
  public void setStrength(int strength) {
    this.strength = strength;
  }

  /** Method that changes the values present in the abilities object to negative.
   * This allows us to convert a gear that is beneficial to the character to a detrimental one.
   * This is used for converting 25% of the equipment bag to negative.
   */
  public void makeNegative() {
    this.strength = -1 * this.strength;
    this.constitution = -1 * this.constitution;
    this.charisma = -1 * this.charisma;
    this.dexterity = -1 * this.dexterity;
  }
}
