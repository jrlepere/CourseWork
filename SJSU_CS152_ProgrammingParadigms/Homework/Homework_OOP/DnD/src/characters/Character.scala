package characters

/**
 * A DnD Character
 */
trait Character {
  /**
   * The character's health
   */
  var health: Int = 100
  /**
   * An attack on another character.
   * @param victom The victim of the attack
   */
  def attack(victim: Character)
  /**
   * Gets the name of the character.
   * @return The name of the character.
   */
  def name: String
}