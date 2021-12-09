
trait ShoppingService {
  def addProduct(productName: String): String
  def removeProduct(productName: String): String
  def getShoppingCart: (String, Map[String, Int])
}

case class ShoppingServiceImpl(shoppingRepo: ShoppingRepo) extends ShoppingService {
  def addProduct(productName: String): String = {
    val shoppingCart = shoppingRepo.findShoppingCart
    val newProductQuantity = shoppingCart.get(productName).map(x => x + 1).getOrElse(1)

    shoppingRepo.save(shoppingCart.updated(productName, newProductQuantity))
  }

  def removeProduct(productName: String): String = {
    val shoppingCart = shoppingRepo.findShoppingCart
    val productQuantity = shoppingCart.getOrElse(productName, 0)

    if(productQuantity < 0) "the item is not in shopping cart"
    else if(productQuantity == 1) shoppingRepo.save(shoppingCart.removed(productName))
    else shoppingRepo.save(shoppingCart.updated(productName, productQuantity - 1))
  }

  def getShoppingCart: (String, Map[String, Int]) = {
    ("success", shoppingRepo.findShoppingCart)
  }
}
