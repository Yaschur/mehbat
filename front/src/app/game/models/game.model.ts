export class Game {

	static noGame: Game = new Game([], false);

	constructor(public lines: string[], public canPlay: boolean) { }
}
