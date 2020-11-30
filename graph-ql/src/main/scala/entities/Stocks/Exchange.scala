package entities.Stocks

import entities.Entity

case class Exchange(id: String,
                    created: String,
                    updated: String,
                    name: String,
                    tape: String,
                    mic: String,
                    exchangeType: String,
                    market: String) extends Entity
