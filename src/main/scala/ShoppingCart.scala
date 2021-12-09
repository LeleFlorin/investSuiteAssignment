import scala.io.StdIn

object ShoppingCart extends App {


  def executeCLI(command: List[String], shoppingService: ShoppingService): Unit = command match {
    case List("add_product", name) => println(s"status: ${shoppingService.addProduct(name)}")
    case List("remove_product", name) => println(s"status: ${shoppingService.removeProduct(name)}")
    case List("get_cart") =>
      val (status, products) = shoppingService.getShoppingCart
      println(s"status: $status, products: $products")
    case x => println(s"Invalid command: '${x.mkString(" ")}'. Available commands are: 'add_product <prod_name>', 'remove_product <prod_name>', 'get_cart'")
  }


  val shoppingService = ShoppingServiceImpl(ShoppingRepoImpl())
  LazyList.from(1).foreach(_ => executeCLI(StdIn.readLine.split(" ").toList, shoppingService))
}
