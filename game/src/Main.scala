package game

enum Action {
  case
    Bus, Train, Airplane,
    ArrogantBackpacker,
    Imperialism,
}

enum Country(value: Int, action: Action | Special) {
  case
    CzechRepublic extends Country(3, Action.ArrogantBackpacker)
    Belgium(6, Imperialism)

}

enum Special {
  case DiplomaticImmunity
}

@main def Main = println(Country.CzechRepublic)