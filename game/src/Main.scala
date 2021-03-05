package game

enum Action {
  case
    Bus, Train, Airplane,
    ArrogantBackpacker
}

enum Country(value: Int, action: Action | Special) {
  import Action.ArrogantBackpacker
  case
    CzechRepublic extends Country(3, ArrogantBackpacker)
}

enum Special {
  case DiplomaticImmunity
}

@main def Main = println(Country.CzechRepublic)