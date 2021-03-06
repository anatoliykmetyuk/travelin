package game

// TODO braces => colons
enum Action {
  case
    Bus, Train, Airplane,
    ArrogantBackpacker,
    Imperialism,
}

import Action._
enum Country(val value: Int, val action: Action | Special) {
  case CzechRepublic extends Country(3, ArrogantBackpacker)
  case Belgium extends Country(6, Imperialism)
}

enum Special {
  case DiplomaticImmunity
}
