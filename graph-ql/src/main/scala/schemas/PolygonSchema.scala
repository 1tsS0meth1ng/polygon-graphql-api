package schemas

import Types.StockTypes
import com.softwaremill.macwire._
import sangria.schema.{Argument, Context, Field, IntType, ListType, ObjectType, OptionInputType, OptionType, Projector, StringType, ValidOutType, fields}


class PolygonSchema extends Schema with StockTypes {

  val PerPage = Argument("perPage", OptionInputType(IntType), defaultValue = 50)
  val PageArg = Argument("page", OptionInputType(IntType), defaultValue = 1)

  override def queries: List[Field[Unit, Unit]] = {
    List(
      Field(
        name = "Exchanges",
        fieldType = ListType(exchangeType),
        resolve = _ => stocksResolver.exchanges
      ),
      Field(
        name = "Tickers" ,
        fieldType = ListType(tickerType),
        arguments= PerPage::PageArg::Nil,
        resolve = (ctx: Context[Unit, Unit]) => tickerResolver.tickers(ctx.arg(PerPage),ctx.arg(PageArg))
      ),
      Field(
        name = "Company" ,
        fieldType = OptionType(companyType),
        arguments = companyID::Nil,
        resolve = (ctx: Context[Unit, Unit]) => companyResolver.company(ctx.arg(companyID))
      )
    )
  }

  override def mutations: List[Field[Unit, Unit]] = List()
}
