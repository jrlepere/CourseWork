package dnd

import characters._

/**
 * The dungeon for battles
 */
object dungeon {
  val random = new scala.util.Random(System.nanoTime())

  /**
   * The main method for the duel.
   */
  def main(args: Array[String]) {
    val dragon = Dragon("Drago")
    val knight = Knight("Lance")
    var round = 0
    println(dragon.name + ": " + dragon.health)
    println(knight.name + ": " + knight.health)
    println
    while (dragon.health >= 0 && knight.health >= 0) {
      round += 1
      print(round + ": ")
      if (random.nextInt(2) == 0)
        dragon.attack(knight)
      else
        knight.attack(dragon)
      println(dragon.name + ": " + dragon.health)
      println(knight.name + ": " + knight.health)
      println
    }
    if (dragon.health < 0) print(dragon.name + " is dead")
    else print(knight.name + " is dead")
  }
}