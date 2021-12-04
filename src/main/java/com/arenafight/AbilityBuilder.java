package com.arenafight;

/**
 * The ability builder class is a builder class for the abilities class that allows
 * users to set specific attributes of the ability class. This is useful for gear that only
 * boost values for specific attributes,
 * thus allowing us to set the value of that attribute alone
 * without the need to set everything else to 0 every time.
 */
public class AbilityBuilder {
  private int strength;
  private int charisma;
  private int dexterity;
  private int constitution;

  /**
   * Constructs an ability builder object that defaults all the abilities to zero and
   * that way the values that we do not choose to set through the builder methods remain with
   * the default value set here.
   */
  public AbilityBuilder() {
    this.strength = 0;
    this.charisma = 0;
    this.dexterity = 0;
    this.constitution = 0;
  }

  /**
   * This method is called to set the strength for ability object.
   * @param strength int strength value to set
   * @return an object of type AbilityBuilder
   */
  public AbilityBuilder strength(int strength) {
    this.strength = strength;
    return this;
  }

  /**
   * This method is called to set the charisma for ability object.
   * @param charisma int charisma value to set
   * @return an object of type AbilityBuilder
   */
  public AbilityBuilder charisma(int charisma) {
    this.charisma = charisma;
    return this;
  }

  /**
   * This method is called to set the dexterity for ability object.
   * @param dexterity int dexterity value to set
   * @return an object of type AbilityBuilder
   */
  public AbilityBuilder dexterity(int dexterity) {
    this.dexterity = dexterity;
    return this;
  }

  /**
   * This method is called to set the constitution for ability object.
   * @param constitution int constitution value to set
   * @return an object of type AbilityBuilder
   */
  public AbilityBuilder constitution(int constitution) {
    this.constitution = constitution;
    return this;
  }

  /**
   * Used to build the ability object from the values given through the ability builder.
   * It calls the constructor of the Ability class.
   * @return an object of type Ability
   */
  public Abilities build() {
    return new Abilities(strength,constitution,dexterity,charisma);
  }
}
