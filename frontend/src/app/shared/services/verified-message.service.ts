import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VerifiedMessageService {

  private _verifiedMessage = new BehaviorSubject<string>('');
  readonly verifiedMessage$ = this._verifiedMessage.asObservable();

  setVerifiedMessage(message: string) {
    this._verifiedMessage.next(message);
  }
}
