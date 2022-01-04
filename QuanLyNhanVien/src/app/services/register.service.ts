import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable } from 'rxjs';
import { throwError } from 'rxjs/internal/observable/throwError';
import { User } from '../user';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  private httpOptionPost = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem('token'),
      // Authorization: 'Basic ' + btoa('username:password'),
    }),
    responseType: 'text' as 'json'
  };

  private httpOptionGet = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem('token'),
      // Authorization: 'Basic ' + btoa('username:password'),
    }),
  };

  constructor(private http: HttpClient) { }

  public doRegistration(user: User) {
    return this.http.post("http://localhost:8080/register", user, this.httpOptionPost)
      ;
  }

  // public getAllRoles(){
  //   return this.http.get<any>("http://localhost:8080/getAllRoles",this.httpOptions)
  //     .pipe(catchError(this.handleError));
  // }

  public getAllUnits() {
    return this.http.get("http://localhost:8080/getAllUnits", this.httpOptionGet);
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` + `body was: ${error.error}`
      );
    }
    // return an observable with a user-facing error message
    return throwError('Something bad happened; please try again later.');
  }
}
