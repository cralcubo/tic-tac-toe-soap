TIC TAC TOE SOAP
================

The SOAP service of the tic tac toe game.

Services
========

There are two services available:

	- GameService
	- PlayerService
	
GameService
===========
This is the WSDL of the game service: $BASE_URL/tic-tac-toe-soap/GameService?wsdl

This service has the following requests available:

	- createGame: A game is created.
	- deleteGame: To delete a game a gameId must be specified.
	- getGame: A gameId must be specified.
	- getGames: Return a list of all the games created.
	- startGame: Start a game.

PlayerService
=============
This is the WSDL of the player service: $BASE_URL/tic-tac-toe-soap/PlayerService?wsdl

This service has the following requests available:

	- getPlayer: A playerId must be specified.
	- getPlayers: All the players created are returned.
	- play: Two arguments must be specified: playerId and a coordinate.
	
##Note: 
A coordinate must be one of the following:

				 A1 | A2 | A3
				--------------
				 B1 | B2 | B3
				--------------
				 C1 | C2 | C3

Testing tool
============
To test the web service SoapUI was used, you can download it from: [SoapUI](http://sourceforge.net/projects/soapui/files/ "SoapUI Download")  