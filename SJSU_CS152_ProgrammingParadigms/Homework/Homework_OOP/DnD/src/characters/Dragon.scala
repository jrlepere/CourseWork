package characters

/**
 * A Dragon Character
 */
class Dragon(private val charName: String) extends Character {
  def attack(victim: Character) {
    val damage = dnd.dungeon.random.nextInt(health)
    println(name + " is flaming " + victim.name + ", damage: " + damage)
    victim.health -= damage
  }
  def name = charName
}

/**
 * A Dragon Companion object
 */
object Dragon {
  def apply(charName: String) = new Dragon(charName)
}