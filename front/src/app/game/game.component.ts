import { Component, OnInit } from '@angular/core';
import { Game } from './models/game.model';
import { GameService } from './models/game.service';

@Component({
	selector: 'app-game-place',
	templateUrl: 'game.component.html'
})
export class GameComponent implements OnInit {

	game: Game = Game.noGame;

	constructor(private gameSrv: GameService) { }

	ngOnInit() {
		this.getGame();
	}

	public getGame() {
		this.gameSrv.getGame()
			.subscribe(
				resp => this.game = resp,
				error => {
					console.error(error);
					this.game = Game.noGame;
				}
			);
	}
}
