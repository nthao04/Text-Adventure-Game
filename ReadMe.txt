Welcome to the Space Station!

----------

Commands:
Print list of commands: Help
Move to different rooms: North, East, South, West
Pick up items: Pickup item-name
Inspect items: Inspect item-name
Drop items: Drop item-name
View inventory: Inventory
View items in the room: Explore
Equip items from inventory: Equip item-name
Unequip items: Unequip
Replenish health: Heal item-name
Solve puzzles: Solve puzzle-name
Interact w/monsters: Examine monster-name

Combat Commands:
Attack1
- Deals basic damage
- Gives the player a skill point
Attack2: Doubled attack
- Deals double damage
- Costs a skill point

----------

Map:
 --- --- ---
| 3 | 1 | 2 |
 --- --- ---
    | 4 | 5 |
     ---|---
        | 6 |
         ---

Rooms:
1 Master Control Zone (Item: Bat)
2 Supply Zone (Monster: Drone)
3 Cafeteria (Item: Cake, Puzzle: Riddle1)
4 Base Zone (Item: Soda)
5 Storage Zone (Monster: Robot)
6 Seclusion Zone (Item: Spear, Puzzle: Riddle2)

----------

Files & Usages:

Game:
Main class that runs the game

GameController.java:
Controls the game data (player commands)

GameModel.java:
Initializes and provides the data for the controller (Map & Player)

GameView.java:
Provides the information to be printed onto the console (room info, items list, etc)

Item.java:
Represents the item information (itemName, itemDescription)

Map.java:
Calls the RoomReader class to be able to read and access the rooms

Monster.java:
Represents the monster information (monsterName, description, health, attack, dead)

Player.java:
Moves the player through the map using a moveTo method and updates and stores the current room ID

Puzzle.java:
Represents the puzzle information (name, question, answer, attempts)

Room.java:
Represents the room information (room ID, visited boolean, room name, adjacent rooms, room description)

Reader Classes:
Reads their respective text files and formats them into hashmaps & arrays (Items.txt, Puzzles.txt, Rooms.txt)

Txt Documents:
Text documents that provides the information of all the items, rooms, and puzzles