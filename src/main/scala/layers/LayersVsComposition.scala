package layers


object Layers {
  //#data
  case class Person(name: String)
  case class Customer(customerNumber: Int, p: Person)
  //#data

  type HttpRequest = String
  type HttpResponse = String

  //#web-request
  type WebRequest = HttpRequest => HttpResponse
  //#web-request

  //#serialisation
  trait Serialiser {
    def serialise(c: Customer): HttpResponse
    def deserialie(input: HttpRequest): Person
  }
  //#serialisation

  //#controller
  class WebController(
                       service: ApplicationService,
                       serialiser: Serialiser) {
    def register(p: HttpRequest): HttpResponse = {
      val person = serialiser.deserialie(p)
      val c = service.registerCustomer(person)
      serialiser.serialise(c)
    }
  }
  //#controller

  //#service
  class ApplicationService(dataStore: CustomerRepo) {

    def registerCustomer(p: Person): Customer = {
      // validate them?
      // Generate a unique customer number?
      val c = Customer(1, p)
      dataStore.saveCustomer(c)
      c
    }
  }
  //#service

  //#repo
  trait CustomerRepo {
    def saveCustomer(p: Customer): Unit
  }
  //#repo

}

object OutWithTheLayers {
  object FunctionalLayers {
    //#data-without
    case class Person(name: String)
    case class Customer(customerNumber: Int, p: Person)
    //#data-without

    type HttpRequest = String
    type HttpResponse = String

    //#request-type-func
    type WebRequest = HttpRequest => HttpResponse
    //#request-type-func

    //#serialize-func
    val serialiseCustomer: Customer => HttpResponse = ???
    //#serialize-func

    //#de-serialize-func
    val deSerialisePerson: HttpRequest => Person = ???
    //#de-serialize-func

    //#customer-func
    val createCustomer: Person => Customer = ???
    //#customer-func


    //#db-func
    val saveCustomer: Customer => Customer = ???
    //#db-func

    //#full-request-function
    val registerCustomer: WebRequest =
      deSerialisePerson andThen
        createCustomer andThen
        saveCustomer andThen
        serialiseCustomer
    //#full-request-function
  }

}

object FunctionComposition {

  {
    //#add
    def add(x: Int, y: Int) = x + y
    //#add
  }

  {
    //#add10
    def add10(x: Int) = add(x, 10)
    //#add10
  }

  {
    //#add10-curried
    val add10Curried = (add _).curried(10)
    //#add10-curried
  }


  //#add-curried
  def addCurried(x: Int)(y: Int): Int = x + y
  //#add-curried


  {
    //#what-type
    val whatTypeAmI = addCurried(10) _
    //#what-type
  }

  {
    //#what-type2
    val whatTypeAmI: Int => Int = addCurried(10) _
    //#what-type2
  }

}