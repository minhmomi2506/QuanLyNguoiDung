import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { EditUserHistory } from '../edituserhistory';
import { User } from '../user';
import { LoginjwtService } from './loginjwt.service';


@Injectable({
  providedIn: 'root'
})
export class SearchDeleteService {
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
  constructor(private http: HttpClient, private login: LoginjwtService) { }

  public getAllUsers() {
    return this.http.get("http://localhost:8080/getAllUsers", this.httpOptionGet)
      .pipe(catchError(this.handleError));;
  }

  public getAllUsersByStr(str: string) {
    return this.http.get("http://localhost:8080/getAllUsersByStr/" + str, this.httpOptionGet)
      .pipe(catchError(this.handleError));
  }

  public getAllExcept1(id: number) {
    return this.http.get("http://localhost:8080/getAllExcept1/" + id, this.httpOptionGet)
      .pipe(catchError(this.handleError));
  }

  public deleteUser(id: number) {
    return this.http.delete("http://localhost:8080/deleteUser/" + id, this.httpOptionGet)
      .pipe(catchError(this.handleError));
  }

  public findUserById(id: number) {
    return this.http.get("http://localhost:8080/findUserById/" + id, this.httpOptionGet)
      .pipe(catchError(this.handleError));
  }

  public editUser(user: User, id: any) {
    return this.http.put("http://localhost:8080/editUser/" + id, user, this.httpOptionPost)
      .pipe(catchError(this.handleError));
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
