trait ShoppingRepo {
  def save(newShoppingCart: Map[String, Int]): String
  def findShoppingCart: Map[String, Int]
}

case class ShoppingRepoImpl() extends ShoppingRepo {
  var shoppingCart = Map.empty[String, Int]

  def save(newShoppingCart: Map[String, Int]): String = {
    shoppingCart = newShoppingCart
    "success"
  }

  def findShoppingCart: Map[String, Int] = shoppingCart
}
