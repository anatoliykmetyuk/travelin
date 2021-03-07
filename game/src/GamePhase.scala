package game

enum GamePhase:
  case
    Setup,
    FirstCardSelection,
    AmbiguousFirstTurn,
    Player1Turn, Player2Turn
