package characters

/**
 * A Knight Character
 */
class Knight(private val charName: String) extends Character {
  def attack(victim: Character) {
    val damage = dnd.dungeon.random.nextInt(health)
    println(name + " is stabbing " + victim.name + ", damage: " + damage)
    victim.health -= damage
  }
  def name = charName
}

/**
 * A Knight Companion object
 */
object Knight {
  def apply(charName: String) = new Knight(charName)
}