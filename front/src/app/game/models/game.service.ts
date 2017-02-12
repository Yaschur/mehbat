import { Injectable } from '@angular/core';
import { AuthHttp } from 'angular2-jwt';
import { Response } from '@angular/http';
import { Headers } from '@angular/http';
import { environment } from '../../../environments/environment';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

import { Game } from './game.model';

@Injectable()
export class GameService {

	private headers = new Headers({ 'Content-Type': 'application/json' });

	constructor(private aHttp: AuthHttp) { }

	getGame(): Observable<Game> {
		return this.aHttp
			.get(environment.api)
			.map(resp => resp.json() as Game);
	}
	addLine(line: string): Observable<Response> {
		return this.aHttp
			.post(environment.api, JSON.stringify({ line: line }), { headers: this.headers });
	}
}
